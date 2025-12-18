#!/usr/bin/env python3
"""
Script to regenerate diagrams 4, 6, and 7 from STORAGE_MODULE_SEQUENCE_DIAGRAMS.md
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
    
    # Fix participant aliases - handle <br/> in participant definitions
    # Pattern: "as Service Layer<br/>(Various Controllers)" -> as "Service Layer (Various Controllers)"
    cleaned_code = re.sub(r'as\s+([^<]+)<br/>\(([^)]+)\)', r'as "\1 (\2)"', cleaned_code)
    
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
    
    # Fix complex quotes in message labels - simplify them
    # Remove nested quotes and escape sequences
    cleaned_code = re.sub(r'\([^)]*filename=\\"[^"]*\\"[^)]*\)', lambda m: m.group(0).replace('\\"', "'"), cleaned_code)
    cleaned_code = re.sub(r':\s*"([^"]+)"', r': \1', cleaned_code)  # Remove quotes from labels
    
    # Fix parentheses in message labels that might cause issues
    # Simplify complex expressions
    cleaned_code = re.sub(r'\([^)]*\+[^)]*\+[^)]*\)', lambda m: m.group(0).replace('"', "'"), cleaned_code)
    
    # Ensure proper spacing around arrows
    cleaned_code = re.sub(r'(\w)->>\s*(\w)', r'\1->>\2', cleaned_code)
    cleaned_code = re.sub(r'(\w)->\s*(\w)', r'\1->\2', cleaned_code)
    
    # Fix Note syntax - ensure proper formatting
    cleaned_code = re.sub(r'Note over ([^:]+):\s*([^\n]+)', r'Note over \1: \2', cleaned_code)
    
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
    """Main function to regenerate diagrams 4, 6, and 7"""
    # Set UTF-8 encoding for stdout
    if sys.platform == 'win32':
        import io
        sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')
    
    script_dir = Path(__file__).parent
    markdown_file = script_dir / "STORAGE_MODULE_SEQUENCE_DIAGRAMS.md"
    output_dir = script_dir / "StorageFlow"
    
    if not markdown_file.exists():
        print(f"ERROR: {markdown_file} not found!")
        return
    
    if not output_dir.exists():
        output_dir.mkdir(parents=True, exist_ok=True)
    
    print(f"Reading markdown file: {markdown_file}")
    
    # Diagrams to regenerate: 4, 6, 7
    diagrams_to_regenerate = [
        (4, "04_4-File-Retrieval-Flow.jpg", "4. File Retrieval Flow"),
        (6, "06_6-File-Deletion-Flow.jpg", "6. File Deletion Flow"),
        (7, "07_7-Error-Handling-Flow.jpg", "7. Error Handling Flow")
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
            
            # Additional cleaning for specific issues
            # Remove any remaining <br/> tags
            diagram_code = diagram_code.replace('<br/>', ' ').replace('<br>', ' ')
            
            # Simplify parentheses in message labels - ensure they don't break parsing
            # Replace complex parentheses patterns that might cause issues
            diagram_code = re.sub(r'\([^)]*\([^)]*\)[^)]*\)', lambda m: m.group(0).replace('(', '[').replace(')', ']'), diagram_code)
            
            # Simplify complex message labels
            # Remove problematic quote sequences
            diagram_code = re.sub(r'filename=\\"([^"]+)\\"', r"filename='\1'", diagram_code)
            diagram_code = re.sub(r'\([^)]*\\"[^)]*\)', lambda m: m.group(0).replace('\\"', "'"), diagram_code)
            
            # Fix any remaining special characters that might break Mermaid
            diagram_code = re.sub(r'[^\w\s\-\(\)\[\]\.\/\:\=\>\<\!]', ' ', diagram_code)
            
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

