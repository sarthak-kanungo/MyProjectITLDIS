#!/usr/bin/env python3
"""
Script to convert Warranty Module Mermaid diagrams from markdown file to JPEG images
"""

import re
import os
from pathlib import Path
from playwright.sync_api import sync_playwright
from PIL import Image
import io

def extract_mermaid_diagrams(markdown_file):
    """Extract all Mermaid diagrams from markdown file"""
    with open(markdown_file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Find all mermaid code blocks
    pattern = r'```mermaid\n(.*?)\n```'
    diagrams = re.findall(pattern, content, re.DOTALL)
    
    # Also extract diagram titles from markdown headers
    titles = []
    lines = content.split('\n')
    current_title = "Diagram"
    for i, line in enumerate(lines):
        if line.startswith('## ') and not line.startswith('## Summary'):
            # Extract the title, removing the number prefix
            title_text = line.replace('## ', '').strip()
            # Remove leading numbers and dots if present
            title_text = re.sub(r'^\d+\.\s*', '', title_text)
            current_title = title_text
        if '```mermaid' in line:
            titles.append(current_title)
    
    return diagrams, titles

def mermaid_to_jpeg(mermaid_code, output_path, title="Diagram", width=7680, height=4320):
    """Convert Mermaid diagram code to JPEG image"""
    import re
    
    # Clean up the mermaid code - replace <br/> with spaces (Mermaid doesn't support HTML tags)
    cleaned_code = mermaid_code.replace('<br/>', ' ').replace('<br>', ' ')
    cleaned_code = cleaned_code.replace('&lt;', '<').replace('&gt;', '>')
    cleaned_code = cleaned_code.replace('&amp;', '&')
    
    # Fix participant aliases - wrap multi-word aliases in quotes
    cleaned_code = re.sub(r'as\s+([A-Z][a-zA-Z]+(?:\s+[A-Z][a-zA-Z]+)+)', r'as "\1"', cleaned_code)
    cleaned_code = re.sub(r'as\s+([A-Z]+(?:\s+[A-Z]+)+)', r'as "\1"', cleaned_code)
    
    # Fix generic types in message labels - remove angle brackets but keep content
    cleaned_code = re.sub(r'<([^>]+)>', r' \1', cleaned_code)
    
    # Remove any leading/trailing whitespace from each line but preserve structure
    lines = cleaned_code.split('\n')
    cleaned_lines = []
    for line in lines:
        stripped = line.strip()
        if stripped:
            cleaned_lines.append(stripped)
    cleaned_code = '\n'.join(cleaned_lines)
    
    # Fix quotes in message labels - remove quotes that might break parsing
    cleaned_code = re.sub(r':\s*"([^"]+)"', r': \1', cleaned_code)
    
    # Ensure proper spacing around arrows
    cleaned_code = re.sub(r'(\w)->>\s*(\w)', r'\1->>\2', cleaned_code)
    cleaned_code = re.sub(r'(\w)->\s*(\w)', r'\1->\2', cleaned_code)
    
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
{cleaned_code}
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
        
        # Set content and wait for mermaid to render
        page.set_content(html_content, wait_until='domcontentloaded')
        
        # Wait for mermaid to render
        try:
            # Wait for SVG to appear (with longer timeout)
            page.wait_for_selector('svg', timeout=60000)
            
            # Additional wait to ensure rendering is complete
            page.wait_for_timeout(5000)
            
            # Verify SVG is actually rendered
            svg_element = page.query_selector('svg')
            if not svg_element:
                browser.close()
                raise Exception("SVG element not found after rendering")
            
            # Check if SVG has actual content
            svg_inner_html = page.evaluate('document.querySelector("svg").innerHTML')
            if not svg_inner_html or len(svg_inner_html.strip()) < 500:
                # Try waiting a bit more
                page.wait_for_timeout(3000)
                svg_inner_html = page.evaluate('document.querySelector("svg").innerHTML')
                if not svg_inner_html or len(svg_inner_html.strip()) < 500:
                    browser.close()
                    raise Exception(f"SVG content too short ({len(svg_inner_html) if svg_inner_html else 0} chars) - diagram may not have rendered")
            
        except Exception as e:
            browser.close()
            raise Exception(f"Failed to render diagram: {str(e)}")
        
        # Take screenshot with full page
        screenshot_bytes = page.screenshot(full_page=True, type='png')
        
        browser.close()
        
        # Verify screenshot was captured
        if not screenshot_bytes or len(screenshot_bytes) < 10000:
            raise Exception(f"Screenshot appears to be empty or corrupted ({len(screenshot_bytes) if screenshot_bytes else 0} bytes)")
    
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
    
    # Verify image dimensions are reasonable
    width, height = img.size
    if width < 100 or height < 100:
        raise Exception(f"Generated image dimensions are too small ({width}x{height}) - likely corrupted")
    
    
    print(f"Created: {output_path} ({file_size // 1024} KB, {width}x{height})")

def main():
    """Main function to convert all diagrams"""
    import sys
    # Set UTF-8 encoding for stdout to handle Unicode characters
    if sys.platform == 'win32':
        import io
        sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')
    
    script_dir = Path(__file__).parent
    markdown_file = script_dir / "WARRANTY_MODULE_SEQUENCE_DIAGRAMS.md"
    output_dir = script_dir / "WarrantyFlow"
    
    if not markdown_file.exists():
        print(f"ERROR: {markdown_file} not found!")
        return
    
    if not output_dir.exists():
        output_dir.mkdir(parents=True, exist_ok=True)
        print(f"Created output directory: {output_dir}")
    
    print(f"Reading markdown file: {markdown_file}")
    diagrams, titles = extract_mermaid_diagrams(markdown_file)
    
    print(f"Found {len(diagrams)} Mermaid diagrams")
    print(f"Found {len(titles)} titles")
    
    if len(diagrams) == 0:
        print("ERROR: No Mermaid diagrams found in the file!")
        return
    
    # Ensure we have enough titles
    while len(titles) < len(diagrams):
        titles.append(f"Diagram_{len(titles) + 1}")
    
    for i, (diagram_code, title) in enumerate(zip(diagrams, titles), 1):
        # Clean title for filename
        safe_title = re.sub(r'[^\w\s-]', '', title)
        safe_title = re.sub(r'[-\s]+', '-', safe_title)
        # Create filename with zero-padded number and cleaned title
        filename = f"{i:02d}_{safe_title}.jpg"
        output_path = output_dir / filename
        
        print(f"\nConverting diagram {i}/{len(diagrams)}: {title}")
        print(f"   Code length: {len(diagram_code)} characters")
        print(f"   Output file: {filename}")
        
        try:
            mermaid_to_jpeg(diagram_code, output_path, title, width=7680, height=4320)
        except Exception as e:
            print(f"ERROR converting diagram {i}: {e}")
            import traceback
            traceback.print_exc()
    
    print(f"\nConversion complete! Images saved to: {output_dir}")
    print(f"Total files created: {len(diagrams)}")

if __name__ == "__main__":
    main()

