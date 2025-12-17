#!/usr/bin/env python3
"""
Script to generate all sequence diagram images from SPARES_MODULE_SEQUENCE_DIAGRAMS.md
"""

import re
import os
import sys
from pathlib import Path
from playwright.sync_api import sync_playwright
from PIL import Image
import io

def mermaid_to_jpeg(mermaid_code, output_path, title="Diagram", width=7680, height=4320):
    """Convert Mermaid diagram code to JPEG image"""
    import re
    
    # Clean up the mermaid code - replace <br/> with spaces
    cleaned_code = mermaid_code.replace('<br/>', ' ')
    cleaned_code = cleaned_code.replace('<br>', ' ')
    cleaned_code = cleaned_code.replace('&lt;', '<').replace('&gt;', '>')
    cleaned_code = cleaned_code.replace('&amp;', '&')
    
    # Fix participant aliases
    cleaned_code = re.sub(r'as\s+([A-Z][a-zA-Z]+(?:\s+[A-Z][a-zA-Z]+)+)', r'as "\1"', cleaned_code)
    cleaned_code = re.sub(r'as\s+([A-Z]+(?:\s+[A-Z]+)+)', r'as "\1"', cleaned_code)
    
    # Fix generic types in message labels
    cleaned_code = re.sub(r'<([^>]+)>', r' \1', cleaned_code)
    
    # Remove any leading/trailing whitespace from each line but preserve structure
    lines = cleaned_code.split('\n')
    cleaned_lines = []
    for line in lines:
        stripped = line.strip()
        if stripped:
            cleaned_lines.append(stripped)
    cleaned_code = '\n'.join(cleaned_lines)
    
    # Fix quotes in message labels
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

def sanitize_filename(title):
    """Convert diagram title to a safe filename"""
    # Remove numbering prefix (e.g., "1. " or "12. ")
    title = re.sub(r'^\d+\.\s+', '', title)
    # Replace spaces and special characters with hyphens
    title = re.sub(r'[^\w\s-]', '', title)
    title = re.sub(r'[-\s]+', '-', title)
    return title.strip('-')

def main():
    """Main function to generate all spares diagrams"""
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
        print(f"Created output directory: {output_dir}")
    
    print(f"Reading markdown file: {markdown_file}")
    
    # Read the markdown file
    with open(markdown_file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Extract mermaid diagrams
    pattern = r'```mermaid\n(.*?)\n```'
    diagrams = re.findall(pattern, content, re.DOTALL)
    
    if len(diagrams) == 0:
        print("ERROR: No Mermaid diagrams found in the file!")
        return
    
    print(f"Found {len(diagrams)} diagrams to generate")
    
    # Extract section titles
    titles = []
    lines = content.split('\n')
    for i, line in enumerate(lines):
        if line.startswith('## ') and re.match(r'## \d+\.', line):
            # Extract the title after the number
            title_match = re.match(r'## \d+\.\s+(.+)', line)
            if title_match:
                titles.append(title_match.group(1))
    
    # Define diagram configurations
    diagram_configs = []
    for i in range(len(diagrams)):
        if i < len(titles):
            title = titles[i]
            # Create filename: 01_1-Title-Name.jpg, 02_2-Title-Name.jpg, etc.
            number = str(i + 1).zfill(2)
            filename = f"{number}_{i+1}-{sanitize_filename(title)}.jpg"
            diagram_configs.append({
                'index': i,
                'title': f"{i+1}. {title}",
                'filename': filename
            })
        else:
            # Fallback if title not found
            diagram_configs.append({
                'index': i,
                'title': f"Diagram {i+1}",
                'filename': f"{str(i+1).zfill(2)}_{i+1}-Spares-Diagram-{i+1}.jpg"
            })
    
    # Generate each diagram
    success_count = 0
    for config in diagram_configs:
        if config['index'] >= len(diagrams):
            print(f"\nWARNING: Diagram {config['index'] + 1} not found, skipping...")
            continue
        
        diagram_code = diagrams[config['index']]
        output_path = output_dir / config['filename']
        
        print(f"\nGenerating diagram {config['index'] + 1}: {config['title']}")
        print(f"   Code length: {len(diagram_code)} characters")
        print(f"   Output: {output_path}")
        
        try:
            # Delete old file if it exists
            if output_path.exists():
                output_path.unlink()
                print(f"   Deleted old file")
            
            mermaid_to_jpeg(diagram_code, output_path, config['title'], width=7680, height=4320)
            print(f"   ✓ Successfully generated")
            success_count += 1
        except Exception as e:
            print(f"   ✗ ERROR generating diagram {config['index'] + 1}: {e}")
            import traceback
            traceback.print_exc()
    
    print(f"\n{'='*60}")
    print(f"Generation complete! Generated {success_count} out of {len(diagram_configs)} diagrams")
    print(f"Output directory: {output_dir}")

if __name__ == "__main__":
    main()

