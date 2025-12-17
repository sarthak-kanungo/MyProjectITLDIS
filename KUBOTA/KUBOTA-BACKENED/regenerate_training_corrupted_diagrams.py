#!/usr/bin/env python3
"""
Script to regenerate corrupted Training Module diagrams (8, 11, 12, 14, 15)
"""

import re
import os
from pathlib import Path
from playwright.sync_api import sync_playwright
from PIL import Image
import io

def extract_specific_diagrams(markdown_file, diagram_indices):
    """Extract specific Mermaid diagrams from markdown file by index (1-based)"""
    with open(markdown_file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Find all mermaid code blocks
    pattern = r'```mermaid\n(.*?)\n```'
    diagrams = re.findall(pattern, content, re.DOTALL)
    
    # Extract titles
    titles = []
    lines = content.split('\n')
    current_title = "Diagram"
    for i, line in enumerate(lines):
        if line.startswith('## '):
            current_title = line.replace('## ', '').strip()
        if '```mermaid' in line:
            titles.append(current_title)
    
    # Return only requested diagrams
    result = []
    for idx in diagram_indices:
        if idx <= len(diagrams):
            result.append((diagrams[idx - 1], titles[idx - 1], idx))
    
    return result

def mermaid_to_jpeg(mermaid_code, output_path, title="Diagram", width=7680, height=4320):
    """Convert Mermaid diagram code to JPEG image with enhanced cleaning"""
    import re
    
    # Clean up the mermaid code - replace <br/> with spaces
    cleaned_code = mermaid_code.replace('<br/>', ' ').replace('<br>', ' ')
    cleaned_code = cleaned_code.replace('&lt;', '<').replace('&gt;', '>')
    cleaned_code = cleaned_code.replace('&amp;', '&')
    
    # Fix participant aliases - wrap multi-word aliases in quotes
    cleaned_code = re.sub(r'as\s+([A-Z][a-zA-Z]+(?:\s+[A-Z][a-zA-Z]+)+)', r'as "\1"', cleaned_code)
    cleaned_code = re.sub(r'as\s+([A-Z]+(?:\s+[A-Z]+)+)', r'as "\1"', cleaned_code)
    
    # Fix generic types - replace angle brackets with spaces
    cleaned_code = re.sub(r'<([^>]+)>', r' \1', cleaned_code)
    
    # Remove any leading/trailing whitespace from each line but preserve structure
    lines = cleaned_code.split('\n')
    cleaned_lines = []
    for line in lines:
        stripped = line.strip()
        if stripped:
            cleaned_lines.append(stripped)
    cleaned_code = '\n'.join(cleaned_lines)
    
    # Fix quotes in message labels - remove quotes that might cause issues
    cleaned_code = re.sub(r':\s*"([^"]+)"', r': \1', cleaned_code)
    
    # Fix special characters in labels - escape curly braces in strings
    # Replace {variable} patterns with (variable) to avoid Mermaid parsing issues
    cleaned_code = re.sub(r'\{([^}]+)\}', r'(\1)', cleaned_code)
    
    # Fix !isEmpty and similar expressions
    cleaned_code = cleaned_code.replace('!isEmpty', 'not empty')
    cleaned_code = cleaned_code.replace('!=', 'not equal')
    cleaned_code = cleaned_code.replace('<=', 'less than or equal')
    cleaned_code = cleaned_code.replace('>=', 'greater than or equal')
    
    # Fix Integer.MAX_VALUE
    cleaned_code = cleaned_code.replace('Integer.MAX_VALUE', 'MAX_VALUE')
    
    # Ensure proper spacing around arrows
    cleaned_code = re.sub(r'(\w)->>\s*(\w)', r'\1->>\2', cleaned_code)
    cleaned_code = re.sub(r'(\w)->\s*(\w)', r'\1->\2', cleaned_code)
    
    # Fix rect syntax - ensure proper formatting
    cleaned_code = re.sub(r'rect\s+rgb\(([^)]+)\)', r'rect rgb(\1)', cleaned_code)
    
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
            
            # Check for errors
            page_text = page.inner_text('body').lower()
            if 'syntax error' in page_text:
                # Try to get error details
                error_elements = page.query_selector_all('text[fill*="red"], .error')
                if error_elements:
                    svg_element = page.query_selector('svg')
                    if svg_element:
                        svg_box = svg_element.bounding_box()
                        if not svg_box or svg_box['width'] < 100 or svg_box['height'] < 100:
                            browser.close()
                            raise Exception("SVG appears to be an error message")
            
            # Verify SVG is actually rendered
            svg_element = page.query_selector('svg')
            if not svg_element:
                browser.close()
                raise Exception("SVG element not found after rendering")
            
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
    """Main function to regenerate corrupted diagrams"""
    import sys
    # Set UTF-8 encoding for stdout
    if sys.platform == 'win32':
        import io
        sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')
    
    script_dir = Path(__file__).parent
    markdown_file = script_dir / "TRAINING_MODULE_SEQUENCE_DIAGRAMS.md"
    output_dir = script_dir / "TrainingFlow"
    
    # Diagrams to regenerate: 8, 11, 12, 14, 15
    diagram_indices = [8, 11, 12, 14, 15]
    
    if not markdown_file.exists():
        print(f"ERROR: {markdown_file} not found!")
        return
    
    if not output_dir.exists():
        output_dir.mkdir(parents=True, exist_ok=True)
        print(f"Created output directory: {output_dir}")
    
    print(f"Reading markdown file: {markdown_file}")
    diagrams_to_regenerate = extract_specific_diagrams(markdown_file, diagram_indices)
    
    print(f"Found {len(diagrams_to_regenerate)} diagrams to regenerate")
    
    for diagram_code, title, idx in diagrams_to_regenerate:
        # Clean title for filename
        safe_title = re.sub(r'[^\w\s-]', '', title)
        safe_title = re.sub(r'[-\s]+', '-', safe_title)
        if len(safe_title) > 100:
            safe_title = safe_title[:100]
        
        filename = f"{idx:02d}_{safe_title}.jpg"
        output_path = output_dir / filename
        
        print(f"\nRegenerating diagram {idx}: {title}")
        print(f"   Code length: {len(diagram_code)} characters")
        
        try:
            # Delete old file if exists
            if output_path.exists():
                output_path.unlink()
                print(f"Deleted old file: {output_path}")
            
            mermaid_to_jpeg(diagram_code, output_path, title, width=7680, height=4320)
            print(f"Successfully regenerated: {output_path}")
        except Exception as e:
            print(f"ERROR regenerating diagram {idx}: {e}")
            import traceback
            traceback.print_exc()
    
    print(f"\nRegeneration complete! Images saved to: {output_dir}")

if __name__ == "__main__":
    main()

