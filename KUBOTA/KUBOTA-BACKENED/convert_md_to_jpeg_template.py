#!/usr/bin/env python3
"""
Simple Template: Convert Markdown File (with Mermaid Diagram) to JPEG
Usage: python convert_md_to_jpeg_template.py <input.md> <output.jpg>
"""

import re
import os
import sys
from pathlib import Path
from playwright.sync_api import sync_playwright
from PIL import Image

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

def mermaid_to_jpeg(mermaid_code, output_path):
    """Convert Mermaid diagram to JPEG"""
    
    # Clean up the mermaid code
    cleaned_code = mermaid_code.replace('<br/>', ' ').replace('<br>', ' ')
    
    # Create HTML with Mermaid
    html_content = f"""<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
    <style>
        body {{
            margin: 0;
            padding: 100px;
            background: white;
            font-family: Arial, sans-serif;
        }}
        .mermaid {{
            display: block;
        }}
        svg {{
            max-width: none !important;
        }}
        svg text {{
            font-size: 20px !important;
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
            securityLevel: 'loose'
        }});
    </script>
</body>
</html>"""
    
    # Use Playwright to render and screenshot
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        context = browser.new_context(
            viewport={'width': 12000, 'height': 8000},
            device_scale_factor=2.0
        )
        page = context.new_page()
        
        # Set content and wait for rendering
        page.set_content(html_content, wait_until='domcontentloaded', timeout=120000)
        
        # Wait for SVG to render
        page.wait_for_selector('svg', timeout=90000)
        page.wait_for_timeout(5000)
        
        # Take screenshot
        screenshot_bytes = page.screenshot(
            full_page=True,
            type='png',
            timeout=120000
        )
        
        browser.close()
    
    # Save as PNG first
    png_path = str(output_path).replace('.jpg', '.png').replace('.jpeg', '.png')
    with open(png_path, 'wb') as f:
        f.write(screenshot_bytes)
    
    # Convert PNG to JPEG
    Image.MAX_IMAGE_PIXELS = None
    png = Image.open(png_path)
    jpg = png.convert('RGB')
    jpg.save(output_path, 'JPEG', quality=95, optimize=True)
    
    print(f"✓ Created: {output_path}")
    print(f"  Dimensions: {jpg.size[0]}x{jpg.size[1]} pixels")
    print(f"  File size: {os.path.getsize(output_path) // 1024} KB")

def main():
    """Main function"""
    if len(sys.argv) < 3:
        print("Usage: python convert_md_to_jpeg_template.py <input.md> <output.jpg>")
        print("\nExample:")
        print("  python convert_md_to_jpeg_template.py diagram.md diagram.jpg")
        sys.exit(1)
    
    input_file = sys.argv[1]
    output_file = sys.argv[2]
    
    if not os.path.exists(input_file):
        print(f"ERROR: Input file not found: {input_file}")
        sys.exit(1)
    
    print(f"Reading: {input_file}")
    
    try:
        diagram = extract_mermaid_diagram(input_file)
        print(f"Found Mermaid diagram ({len(diagram)} characters)")
        print(f"Converting to JPEG...")
        mermaid_to_jpeg(diagram, output_file)
        print(f"\n✓ Conversion complete!")
    except Exception as e:
        print(f"✗ ERROR: {e}")
        import traceback
        traceback.print_exc()
        sys.exit(1)

if __name__ == "__main__":
    main()

