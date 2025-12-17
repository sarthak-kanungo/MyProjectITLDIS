#!/usr/bin/env python3
"""
Generate correct diagram 3 without syntax errors
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
    <script src="https://cdn.jsdelivr.net/npm/mermaid@9/dist/mermaid.min.js"></script>
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
        
        # Wait longer for rendering
        page.wait_for_timeout(8000)
        
        try:
            # Check for errors first
            error_text = page.evaluate('''
                () => {
                    const errorDiv = document.querySelector('.error-text');
                    return errorDiv ? errorDiv.textContent : null;
                }
            ''')
            
            if error_text:
                print(f"Warning: Mermaid error detected: {error_text}")
                # Wait a bit more - sometimes it renders despite the error
                page.wait_for_timeout(3000)
            
            page.wait_for_selector('svg', timeout=60000)
            
            svg_element = page.query_selector('svg')
            if not svg_element:
                browser.close()
                raise Exception("SVG element not found after rendering")
            
            svg_inner_html = page.evaluate('document.querySelector("svg").innerHTML')
            if not svg_inner_html or len(svg_inner_html.strip()) < 500:
                page.wait_for_timeout(3000)
                svg_inner_html = page.evaluate('document.querySelector("svg").innerHTML')
                if not svg_inner_html or len(svg_inner_html.strip()) < 500:
                    browser.close()
                    raise Exception(f"SVG content too short ({len(svg_inner_html) if svg_inner_html else 0} chars)")
            
            # Take screenshot
            screenshot_bytes = page.screenshot(full_page=True, type='png')
            browser.close()
            
        except Exception as e:
            browser.close()
            raise Exception(f"Failed to render diagram: {str(e)}")
    
    img = Image.open(io.BytesIO(screenshot_bytes))
    
    if img.mode == 'RGBA':
        rgb_img = Image.new('RGB', img.size, (255, 255, 255))
        rgb_img.paste(img, mask=img.split()[3])
        img = rgb_img
    
    img.save(output_path, 'JPEG', quality=100, optimize=False)
    
    file_size = os.path.getsize(output_path)
    if file_size < 10000:
        raise Exception(f"Generated image is too small ({file_size} bytes)")
    
    width, height = img.size
    if width < 100 or height < 100:
        raise Exception(f"Generated image dimensions are too small ({width}x{height})")
    
    print(f"Created: {output_path} ({file_size // 1024} KB, {width}x{height})")
    return True

def main():
    if sys.platform == 'win32':
        import io
        sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')
    
    script_dir = Path(__file__).parent
    mermaid_file = script_dir / "ExceptionFlow" / "03_3-correct-final.txt"
    output_path = script_dir / "ExceptionFlow" / "03_3-Validation-Exception-Handling-Flow.jpg"
    
    if not mermaid_file.exists():
        print(f"ERROR: {mermaid_file} not found!")
        return
    
    print(f"Reading Mermaid code from: {mermaid_file}")
    
    with open(mermaid_file, 'r', encoding='utf-8') as f:
        diagram_code = f.read().strip()
    
    print(f"\nGenerating diagram 3: Validation Exception Handling Flow")
    print(f"   Code length: {len(diagram_code)} characters")
    print(f"   Output: {output_path}")
    
    try:
        if output_path.exists():
            output_path.unlink()
            print(f"   Deleted old file")
        
        success = mermaid_to_jpeg(diagram_code, output_path, width=7680, height=4320)
        if success:
            print(f"   ✓ Successfully generated correct image")
    except Exception as e:
        print(f"   ✗ ERROR: {e}")
        import traceback
        traceback.print_exc()

if __name__ == "__main__":
    main()

