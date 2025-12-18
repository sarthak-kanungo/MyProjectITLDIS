#!/usr/bin/env python3
"""
Script to regenerate diagrams 11 and 12 from SPARES_MODULE_SEQUENCE_DIAGRAMS.md
with fixed syntax
"""

import re
import os
import sys
from pathlib import Path
from playwright.sync_api import sync_playwright
from PIL import Image
import io

def mermaid_to_jpeg(mermaid_code, output_path, title="Diagram", width=7680, height=4320):
    """Convert Mermaid diagram code to JPEG image with enhanced cleaning"""
    import re
    
    # Clean up the mermaid code - replace <br/> with spaces
    cleaned_code = mermaid_code.replace('<br/>', ' ').replace('<br>', ' ')
    cleaned_code = cleaned_code.replace('&lt;', '<').replace('&gt;', '>')
    cleaned_code = cleaned_code.replace('&amp;', '&')
    
    # Remove apostrophes that might cause issues
    cleaned_code = cleaned_code.replace("'", '').replace("'", '').replace("'", '')
    
    # Replace periods in method calls with spaces
    cleaned_code = re.sub(r'\.([a-zA-Z])', r' \1', cleaned_code)
    
    # Fix participant aliases - wrap multi-word aliases in quotes
    cleaned_code = re.sub(r'as\s+([A-Z][a-zA-Z]+(?:\s+[A-Z][a-zA-Z]+)+)', r'as "\1"', cleaned_code)
    cleaned_code = re.sub(r'as\s+([A-Z]+(?:\s+[A-Z]+)+)', r'as "\1"', cleaned_code)
    
    # Fix generic types in message labels - remove angle brackets
    cleaned_code = re.sub(r'<([^>]+)>', r' \1', cleaned_code)
    
    # Remove any leading/trailing whitespace from each line but preserve structure
    lines = cleaned_code.split('\n')
    cleaned_lines = []
    for line in lines:
        stripped = line.strip()
        if stripped:
            cleaned_lines.append(stripped)
    cleaned_code = '\n'.join(cleaned_lines)
    
    # Remove all parentheses from message labels
    lines = cleaned_code.split('\n')
    cleaned_lines = []
    for line in lines:
        if '->>' in line or '->' in line:
            # This is a message line, remove parentheses from the label part
            if ':' in line:
                parts = line.split(':', 1)
                if len(parts) == 2:
                    label = parts[1].strip()
                    # Remove parentheses but keep the rest
                    label = label.replace('(', ' ').replace(')', ' ')
                    # Clean up multiple spaces
                    label = re.sub(r'\s+', ' ', label).strip()
                    line = parts[0] + ': ' + label
        cleaned_lines.append(line)
    cleaned_code = '\n'.join(cleaned_lines)
    
    # Fix quotes in message labels - simplify them
    cleaned_code = re.sub(r':\s*"([^"]+)"', r': \1', cleaned_code)  # Remove quotes from labels
    
    # Ensure proper spacing around arrows
    cleaned_code = re.sub(r'(\w)->>\s*(\w)', r'\1->>\2', cleaned_code)
    cleaned_code = re.sub(r'(\w)->\s*(\w)', r'\1->\2', cleaned_code)
    
    # Remove Note statements (they cause syntax errors)
    cleaned_code = re.sub(r'Note over [^\n]+\n', '', cleaned_code)
    cleaned_code = re.sub(r'Note right of [^\n]+\n', '', cleaned_code)
    cleaned_code = re.sub(r'Note left of [^\n]+\n', '', cleaned_code)
    
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
            
            # Check SVG dimensions to ensure it's actually rendered (not just an error message)
            svg_box = svg_element.bounding_box()
            if svg_box and (svg_box['width'] < 100 or svg_box['height'] < 100):
                browser.close()
                raise Exception("SVG appears to be too small - likely an error message")
            
            # Check for error text in the page
            page_content = page.content()
            if 'Syntax error' in page_content or 'syntax error' in page_content.lower():
                # Try to extract the actual error message
                error_text = page.inner_text('body')
                error_msg = "Mermaid syntax error detected"
                if 'Syntax error' in error_text:
                    error_lines = [line for line in error_text.split('\n') if 'error' in line.lower() or 'Syntax' in line]
                    if error_lines:
                        error_msg += f": {error_lines[0][:200]}"
                browser.close()
                raise Exception(error_msg)
            
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

def extract_diagram_from_markdown(markdown_file, diagram_number):
    """Extract a specific diagram from markdown file by number"""
    with open(markdown_file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Find all mermaid code blocks
    pattern = r'```mermaid\n(.*?)\n```'
    diagrams = re.findall(pattern, content, re.DOTALL)
    
    if diagram_number < 1 or diagram_number > len(diagrams):
        raise Exception(f"Diagram {diagram_number} not found. Total diagrams: {len(diagrams)}")
    
    return diagrams[diagram_number - 1]

def main():
    """Main function to regenerate diagrams 11 and 12"""
    # Set UTF-8 encoding for stdout
    if sys.platform == 'win32':
        import io
        sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')
    
    script_dir = Path(__file__).parent
    markdown_file = script_dir / "SPARES_MODULE_SEQUENCE_DIAGRAMS.md"
    output_dir = script_dir / "SparesFlow"
    
    if not markdown_file.exists():
        print(f"ERROR: {markdown_file} not found!")
        return
    
    if not output_dir.exists():
        output_dir.mkdir(parents=True, exist_ok=True)
    
    print(f"Reading markdown file: {markdown_file}")
    
    # Diagrams to regenerate: 11, 12
    diagrams_to_regenerate = [
        (11, "11_11-Sales-Order-Search-Flow.jpg", "11. Sales Order Search Flow"),
        (12, "12_12-Reports-Generation-Flow.jpg", "12. Reports Generation Flow")
    ]
    
    for diagram_num, filename, title in diagrams_to_regenerate:
        print(f"\n{'='*60}")
        print(f"Regenerating diagram {diagram_num}: {title}")
        print(f"{'='*60}")
        
        try:
            diagram_code = extract_diagram_from_markdown(markdown_file, diagram_num)
            print(f"   Code length: {len(diagram_code)} characters")
            
            output_path = output_dir / filename
            
            if output_path.exists():
                output_path.unlink()
                print(f"   Deleted old file: {output_path}")
            
            mermaid_to_jpeg(diagram_code, output_path, title, width=7680, height=4320)
            print(f"   Successfully regenerated: {output_path}")
            
        except Exception as e:
            print(f"   ERROR regenerating diagram {diagram_num}: {e}")
            import traceback
            traceback.print_exc()
    
    print(f"\n{'='*60}")
    print(f"Regeneration complete!")
    print(f"{'='*60}")

if __name__ == "__main__":
    main()

