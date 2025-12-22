#!/usr/bin/env python3
"""
Script to convert ITLDIS Service Module UML Class Diagram from markdown file to JPEG image
"""

import re
import os
import sys
from pathlib import Path
from playwright.sync_api import sync_playwright
from PIL import Image
import io

def extract_mermaid_diagram(markdown_file):
    """Extract Mermaid diagram from markdown file"""
    with open(markdown_file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Find mermaid code block
    pattern = r'```mermaid\n(.*?)\n```'
    match = re.search(pattern, content, re.DOTALL)
    
    if match:
        return match.group(1)
    else:
        raise Exception("No Mermaid diagram found in markdown file")

def mermaid_to_jpeg(mermaid_code, output_path, title="Service Module UML Diagram", width=7680, height=4320):
    """Convert Mermaid diagram code to JPEG image"""
    
    # Clean up the mermaid code
    cleaned_code = mermaid_code
    
    # Remove any HTML tags that might cause issues
    cleaned_code = cleaned_code.replace('<br/>', ' ').replace('<br>', ' ')
    cleaned_code = cleaned_code.replace('&lt;', '<').replace('&gt;', '>')
    cleaned_code = cleaned_code.replace('&amp;', '&')
    
    # Fix relationship labels with special characters
    cleaned_code = re.sub(r':\s*([^:]+)/([^:]+)', r': \1 \2', cleaned_code)
    
    # Remove any leading/trailing whitespace from each line but preserve structure
    lines = cleaned_code.split('\n')
    cleaned_lines = []
    for line in lines:
        stripped = line.strip()
        if stripped:
            cleaned_lines.append(stripped)
    cleaned_code = '\n'.join(cleaned_lines)
    
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
            zoom: 1.5;
        }}
        .mermaid {{
            display: block;
            width: 100%;
        }}
    </style>
</head>
<body>
    <div class="mermaid">
{cleaned_code}
    </div>
    <script>
        mermaid.initialize({{
            startOnLoad: true,
            theme: 'default',
            securityLevel: 'loose',
            classDiagram: {{
                classDefault: {{
                    fill: '#fff',
                    stroke: '#333',
                    strokeWidth: 2
                }},
                useMaxWidth: true,
                padding: 20
            }}
        }});
    </script>
</body>
</html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page(viewport={'width': width, 'height': height})
        
        # Set content and wait for mermaid to render
        page.set_content(html_content, wait_until='domcontentloaded')
        
        # Wait for mermaid to render
        try:
            # Wait for SVG to appear (with longer timeout for complex diagrams)
            page.wait_for_selector('svg', timeout=90000)
            
            # Additional wait to ensure rendering is complete
            page.wait_for_timeout(10000)
            
            # Check for Mermaid errors
            error_elements = page.query_selector_all('.error')
            if error_elements:
                error_text = ""
                for elem in error_elements:
                    try:
                        error_text += elem.inner_text() + " "
                    except:
                        pass
                if error_text:
                    browser.close()
                    raise Exception(f"Mermaid rendering error: {error_text.strip()}")
            
            # Verify SVG is actually rendered
            svg_element = page.query_selector('svg')
            if not svg_element:
                browser.close()
                raise Exception("SVG element not found after rendering")
            
            # Check if SVG has content
            svg_content = page.evaluate('document.querySelector("svg").innerHTML')
            if not svg_content or len(svg_content) < 100:
                browser.close()
                raise Exception("SVG appears to be empty or corrupted")
            
        except Exception as e:
            browser.close()
            raise Exception(f"Failed to render diagram: {str(e)}")
        
        # Take screenshot with full page
        screenshot_bytes = page.screenshot(full_page=True, type='png')
        
        browser.close()
    
    # Convert PNG to JPEG with maximum quality
    img = Image.open(io.BytesIO(screenshot_bytes))
    
    # Convert RGBA to RGB if needed
    if img.mode == 'RGBA':
        rgb_img = Image.new('RGB', img.size, (255, 255, 255))
        rgb_img.paste(img, mask=img.split()[3])
        img = rgb_img
    
    # Save as JPEG with maximum quality
    img.save(output_path, 'JPEG', quality=100, optimize=False)
    
    # Verify file was created and has reasonable size
    file_size = os.path.getsize(output_path)
    if file_size < 10000:  # Less than 10KB is suspicious
        raise Exception(f"Generated image is too small ({file_size} bytes) - likely corrupted")
    
    print(f"Created: {output_path} ({file_size // 1024} KB)")

def main():
    """Main function to convert Service Module UML diagram"""
    # Set UTF-8 encoding for stdout
    if sys.platform == 'win32':
        import io
        sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')
    
    script_dir = Path(__file__).parent
    markdown_file = script_dir / "SERVICE-MODULE-UML-DIAGRAM.md"
    output_dir = script_dir
    
    if not markdown_file.exists():
        print(f"ERROR: {markdown_file} not found!")
        return
    
    print(f"Reading markdown file: {markdown_file}")
    
    # Extract diagram
    try:
        diagram = extract_mermaid_diagram(markdown_file)
        print(f"Found diagram (length: {len(diagram)} characters)")
    except Exception as e:
        print(f"ERROR: {e}")
        return
    
    # Output filename
    output_filename = "Service-Module-UML-Class-Diagram.jpg"
    output_path = output_dir / output_filename
    
    print(f"\nConverting UML Class Diagram to JPEG...")
    print(f"   Output: {output_filename}")
    
    try:
        if output_path.exists():
            output_path.unlink()
            print(f"   Deleted old file: {output_path}")
        
        mermaid_to_jpeg(diagram, output_path, "Service Module UML Class Diagram", width=7680, height=4320)
        print(f"   ✓ Successfully created: {output_path}")
    except Exception as e:
        print(f"   ✗ ERROR converting diagram: {e}")
        import traceback
        traceback.print_exc()
        return
    
    print(f"\n{'='*60}")
    print(f"Conversion complete! Generated image: {output_path}")
    print(f"{'='*60}")

if __name__ == "__main__":
    main()

