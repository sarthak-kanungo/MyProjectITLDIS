#!/usr/bin/env python3
"""
Script to convert Service Module Mermaid diagrams from markdown file to JPEG images
"""

import re
import os
import sys
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
        if line.startswith('## '):
            current_title = line.replace('## ', '').strip()
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
    # Extract number and main title
    parts = title.split('.', 1)
    if len(parts) == 2:
        num = parts[0].strip()
        name = parts[1].strip()
    else:
        num = "0"
        name = title.strip()
    
    # Clean up the name
    name = name.replace('Flow', '').strip()
    name = re.sub(r'[^\w\s-]', '', name)
    name = re.sub(r'\s+', '-', name)
    name = name.strip('-')
    
    # Format as: 01_1-Title-Name.jpg
    return f"{num.zfill(2)}_{num}-{name}.jpg"

def main():
    """Main function to convert all Service Module diagrams"""
    # Set UTF-8 encoding for stdout
    if sys.platform == 'win32':
        import io
        sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')
    
    script_dir = Path(__file__).parent
    markdown_file = script_dir / "SERVICE_MODULE_SEQUENCE_DIAGRAMS.md"
    output_dir = script_dir / "ServiceFlow"
    
    if not markdown_file.exists():
        print(f"ERROR: {markdown_file} not found!")
        return
    
    if not output_dir.exists():
        output_dir.mkdir(parents=True, exist_ok=True)
        print(f"Created output directory: {output_dir}")
    
    print(f"Reading markdown file: {markdown_file}")
    
    # Extract diagrams and titles
    diagrams, titles = extract_mermaid_diagrams(markdown_file)
    
    print(f"Found {len(diagrams)} diagrams")
    
    if len(diagrams) == 0:
        print("ERROR: No diagrams found!")
        return
    
    # Map of diagram numbers to simplified names for filenames
    diagram_names = {
        1: "Service-Booking-Creation-Flow",
        2: "Service-Booking-Search-Flow",
        3: "Job-Card-Creation-Flow",
        4: "Job-Card-Update-Flow",
        5: "Service-Claim-Creation-and-Approval-Flow",
        6: "Activity-Proposal-Creation-and-Approval-Flow",
        7: "Activity-Claim-Creation-Flow",
        8: "Activity-Report-Creation-Flow",
        9: "PSC-Post-Sales-Check-Creation-Flow",
        10: "MRC-Machine-Reconditioning-Creation-Flow"
    }
    
    # Convert each diagram
    for i, (diagram, title) in enumerate(zip(diagrams, titles), 1):
        diagram_num = i
        if diagram_num in diagram_names:
            filename = f"{diagram_num:02d}_{diagram_num}-{diagram_names[diagram_num]}.jpg"
        else:
            filename = sanitize_filename(title)
        
        output_path = output_dir / filename
        
        print(f"\n[{i}/{len(diagrams)}] Converting: {title}")
        print(f"   Output: {filename}")
        print(f"   Code length: {len(diagram)} characters")
        
        try:
            if output_path.exists():
                output_path.unlink()
                print(f"   Deleted old file: {output_path}")
            
            mermaid_to_jpeg(diagram, output_path, title, width=7680, height=4320)
            print(f"   ✓ Successfully created: {output_path}")
        except Exception as e:
            print(f"   ✗ ERROR converting diagram {i}: {e}")
            import traceback
            traceback.print_exc()
    
    print(f"\n{'='*60}")
    print(f"Conversion complete! Generated {len(diagrams)} images in {output_dir}")
    print(f"{'='*60}")

if __name__ == "__main__":
    main()

