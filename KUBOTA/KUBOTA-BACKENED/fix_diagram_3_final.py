#!/usr/bin/env python3
"""
Final fix for diagram 3 - ensure no syntax errors
"""

import os
import sys
from pathlib import Path
from playwright.sync_api import sync_playwright
from PIL import Image
import io

def mermaid_to_jpeg(mermaid_code, output_path, width=7680, height=4320):
    """Convert Mermaid diagram code to JPEG image"""
    
    html_content = f"""<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
    <style>
        body {{
            margin: 0;
            padding: 80px;
            background: white;
            font-family: Arial, sans-serif;
            zoom: 2;
        }}
        .mermaid {{
            display: block;
            width: 100%;
        }}
    </style>
</head>
<body>
    <div class="mermaid">
{mermaid_code}
    </div>
    <script>
        mermaid.initialize({{
            startOnLoad: true,
            theme: 'default',
            securityLevel: 'loose',
            sequence: {{
                diagramMarginX: 100,
                diagramMarginY: 20,
                actorMargin: 100,
                width: 300,
                height: 130,
                boxMargin: 20,
                boxTextMargin: 10,
                noteMargin: 20,
                messageMargin: 70,
                mirrorActors: true,
                bottomMarginAdj: 1,
                useMaxWidth: true,
                rightAngles: false,
                showSequenceNumbers: false
            }}
        }});
    </script>
</body>
</html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page(viewport={'width': width, 'height': height})
        
        page.set_content(html_content, wait_until='domcontentloaded')
        page.wait_for_timeout(8000)
        
        # Check for errors
        error_text = page.evaluate('''
            () => {
                const errorDiv = document.querySelector('.error-text');
                return errorDiv ? errorDiv.textContent : null;
            }
        ''')
        
        if error_text and "Syntax error" in error_text:
            # Try to get more details about the error
            error_details = page.evaluate('''
                () => {
                    const body = document.body.innerText || document.body.textContent;
                    return body.substring(0, 500);
                }
            ''')
            print(f"ERROR: Mermaid syntax error detected")
            print(f"Error details: {error_details[:200]}")
            browser.close()
            raise Exception(f"Mermaid syntax error: {error_text}")
        
        try:
            page.wait_for_selector('svg', timeout=60000)
            page.wait_for_timeout(2000)
            
            svg_element = page.query_selector('svg')
            if not svg_element:
                browser.close()
                raise Exception("SVG element not found")
            
            svg_inner_html = page.evaluate('document.querySelector("svg").innerHTML')
            if not svg_inner_html or len(svg_inner_html.strip()) < 500:
                browser.close()
                raise Exception(f"SVG content too short")
            
            screenshot_bytes = page.screenshot(full_page=True, type='png')
            browser.close()
            
        except Exception as e:
            browser.close()
            raise Exception(f"Failed to render: {str(e)}")
    
    img = Image.open(io.BytesIO(screenshot_bytes))
    
    if img.mode == 'RGBA':
        rgb_img = Image.new('RGB', img.size, (255, 255, 255))
        rgb_img.paste(img, mask=img.split()[3])
        img = rgb_img
    
    img.save(output_path, 'JPEG', quality=100, optimize=False)
    
    file_size = os.path.getsize(output_path)
    if file_size < 10000:
        raise Exception(f"Image too small: {file_size} bytes")
    
    print(f"✓ Created: {output_path} ({file_size // 1024} KB)")

def main():
    if sys.platform == 'win32':
        import io
        sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')
    
    script_dir = Path(__file__).parent
    mermaid_file = script_dir / "ExceptionFlow" / "03_3-no-alt-final.txt"
    output_path = script_dir / "ExceptionFlow" / "03_3-Validation-Exception-Handling-Flow.jpg"
    
    if not mermaid_file.exists():
        print(f"ERROR: {mermaid_file} not found!")
        return
    
    with open(mermaid_file, 'r', encoding='utf-8') as f:
        diagram_code = f.read().strip()
    
    print(f"Generating diagram 3 with proper syntax...")
    print(f"Output: {output_path}")
    
    try:
        if output_path.exists():
            output_path.unlink()
        
        mermaid_to_jpeg(diagram_code, output_path, width=7680, height=4320)
        print(f"✓ Successfully generated without errors!")
    except Exception as e:
        print(f"✗ ERROR: {e}")
        import traceback
        traceback.print_exc()

if __name__ == "__main__":
    main()

