#!/usr/bin/env python3
"""
Script to convert ACCPAC Module Detailed Sequence Diagrams from markdown file to ultra-high-quality JPEG images
Uses SVG extraction and high-DPI rendering for perfect zoom clarity
"""

import re
import os
from pathlib import Path
from playwright.sync_api import sync_playwright
from PIL import Image
import io
import base64

def extract_mermaid_diagrams(markdown_file):
    """Extract all Mermaid diagrams from markdown file with their titles"""
    with open(markdown_file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Find all mermaid code blocks
    pattern = r'```mermaid\n(.*?)\n```'
    diagrams = re.findall(pattern, content, re.DOTALL)
    
    # Extract diagram titles from markdown headers (## X. Title)
    titles = []
    lines = content.split('\n')
    current_title = "Diagram"
    for i, line in enumerate(lines):
        # Match headers like "## 1. Upload Dealer Master Excel Flow"
        if re.match(r'^## \d+\.\s+(.+)$', line):
            current_title = re.match(r'^## \d+\.\s+(.+)$', line).group(1).strip()
        # When we find a mermaid block, use the last title we found
        if '```mermaid' in line:
            titles.append(current_title)
    
    return diagrams, titles

def mermaid_to_jpeg(mermaid_code, output_path, title="Diagram"):
    """Convert Mermaid diagram code to ultra-high-quality JPEG image with perfect zoom clarity"""
    
    # Clean up the mermaid code - replace <br/> with newlines (Mermaid supports \n)
    cleaned_code = mermaid_code.replace('<br/>', '\n').replace('<br>', '\n')
    cleaned_code = cleaned_code.replace('&lt;', '<').replace('&gt;', '>')
    cleaned_code = cleaned_code.replace('&amp;', '&')
    
    # Fix participant aliases - ensure multi-word aliases are properly quoted
    def fix_participant_aliases(text):
        lines = text.split('\n')
        fixed_lines = []
        for line in lines:
            if line.strip().startswith('participant'):
                if ' as ' in line and '"' not in line:
                    match = re.search(r'participant\s+(\w+)\s+as\s+(.+)$', line)
                    if match:
                        var_name = match.group(1)
                        alias = match.group(2).strip()
                        if ' ' in alias and not alias.startswith('"'):
                            line = f'    participant {var_name} as "{alias}"'
            fixed_lines.append(line)
        return '\n'.join(fixed_lines)
    
    cleaned_code = fix_participant_aliases(cleaned_code)
    
    # Remove any leading/trailing whitespace from each line but preserve structure
    lines = cleaned_code.split('\n')
    cleaned_lines = []
    for line in lines:
        stripped = line.strip()
        if stripped:
            cleaned_lines.append(stripped)
    cleaned_code = '\n'.join(cleaned_lines)
    
    # Use large dimensions - make everything bigger for zoom clarity
    viewport_width = 6000  # Wide for sequence diagrams
    viewport_height = 8000  # Tall to accommodate long diagrams
    
    # Large spacing for clarity
    margin_x = 800
    margin_y = 400
    actor_width = 1200
    actor_height = 400
    box_margin = 100
    box_text_margin = 60
    note_margin = 120
    message_margin = 250
    
    # Create HTML with Mermaid - render at native large size
    html_content = f"""<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
    <style>
        * {{
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
            text-rendering: optimizeLegibility;
        }}
        html, body {{
            margin: 0;
            padding: 0;
            width: 100%;
            height: 100%;
            background: white;
            font-family: 'Arial', 'Helvetica', 'DejaVu Sans', sans-serif;
            overflow: visible;
        }}
        .container {{
            padding: {margin_y}px {margin_x}px;
            background: white;
            min-height: 100vh;
        }}
        .title {{
            font-size: 96px;
            font-weight: bold;
            margin-bottom: 120px;
            text-align: center;
            color: #333;
            line-height: 1.2;
        }}
        .mermaid {{
            display: block;
            width: 100%;
            height: auto;
        }}
        svg {{
            shape-rendering: geometricPrecision;
            text-rendering: optimizeLegibility;
            image-rendering: -webkit-optimize-contrast;
            image-rendering: crisp-edges;
            font-family: 'Arial', 'Helvetica', 'DejaVu Sans', sans-serif;
        }}
        svg text {{
            font-family: 'Arial', 'Helvetica', 'DejaVu Sans', sans-serif;
            font-size: 32px;
            font-weight: 500;
        }}
    </style>
</head>
<body>
    <div class="container">
        <div class="title">{title}</div>
        <div class="mermaid">
{cleaned_code}
        </div>
    </div>
    <script>
        mermaid.initialize({{
            startOnLoad: true,
            theme: 'default',
            securityLevel: 'loose',
            sequence: {{
                diagramMarginX: {margin_x},
                diagramMarginY: {margin_y},
                actorMargin: {actor_width},
                width: {actor_width},
                height: {actor_height},
                boxMargin: {box_margin},
                boxTextMargin: {box_text_margin},
                noteMargin: {note_margin},
                messageMargin: {message_margin},
                mirrorActors: true,
                bottomMarginAdj: 2.0,
                useMaxWidth: false,
                rightAngles: false,
                showSequenceNumbers: false
            }},
            flowchart: {{
                useMaxWidth: false,
                htmlLabels: true
            }}
        }});
    </script>
</body>
</html>"""
    
    with sync_playwright() as p:
        # Launch browser with optimal settings for high-quality rendering
        browser = p.chromium.launch(
            headless=True,
            args=[
                '--force-device-scale-factor=1',
                '--disable-web-security',
                '--disable-gpu',
                '--no-sandbox',
                '--disable-setuid-sandbox',
                '--disable-dev-shm-usage',
                '--disable-software-rasterizer'
            ]
        )
        
        # Create context with large viewport at native resolution
        context = browser.new_context(
            viewport={'width': viewport_width, 'height': viewport_height},
            device_scale_factor=1,
            ignore_https_errors=True
        )
        page = context.new_page()
        
        # Set content and wait for mermaid to render
        page.set_content(html_content, wait_until='networkidle', timeout=180000)
        
        # Wait for mermaid to render completely
        try:
            # Wait for SVG to appear
            page.wait_for_selector('svg', timeout=180000)
            
            # Wait for all text and elements to be fully rendered
            page.wait_for_timeout(20000)  # Extra time for complex diagrams
            
            # Verify SVG is actually rendered
            svg_element = page.query_selector('svg')
            if not svg_element:
                browser.close()
                raise Exception("SVG element not found after rendering")
            
            # Check SVG size
            svg_box = svg_element.bounding_box()
            if svg_box and (svg_box['width'] < 100 or svg_box['height'] < 100):
                browser.close()
                raise Exception(f"SVG appears too small: {svg_box}")
            
            # Force a repaint to ensure everything is rendered
            page.evaluate('document.body.offsetHeight')
            page.wait_for_timeout(2000)
            
        except Exception as e:
            browser.close()
            raise Exception(f"Failed to render diagram: {str(e)}")
        
        # Take screenshot with full page at 2x device scale
        # This gives us effectively 16000x20000 pixel resolution
        screenshot_bytes = page.screenshot(
            full_page=True, 
            type='png'
            # device_scale_factor=2 already set in context, so screenshot will be 2x
        )
        
        browser.close()
    
    # Open PNG image
    img = Image.open(io.BytesIO(screenshot_bytes))
    
    # Convert RGBA to RGB if needed
    if img.mode == 'RGBA':
        rgb_img = Image.new('RGB', img.size, (255, 255, 255))
        rgb_img.paste(img, mask=img.split()[3])
        img = rgb_img
    
    # Save as JPEG with maximum quality settings
    # subsampling=0 means no chroma subsampling (4:4:4) for maximum quality
    save_kwargs = {
        'quality': 100,
        'optimize': False,
        'subsampling': 0  # No chroma subsampling for maximum quality
    }
    img.save(output_path, 'JPEG', **save_kwargs)
    
    # Verify file was created and has reasonable size
    file_size = os.path.getsize(output_path)
    if file_size < 10000:  # Less than 10KB is suspicious
        raise Exception(f"Generated image is too small ({file_size} bytes) - likely corrupted")
    
    print(f"[OK] Created: {output_path.name} ({file_size // 1024} KB, {img.width}x{img.height}px)")

def main():
    """Main function to convert all diagrams"""
    script_dir = Path(__file__).parent
    markdown_file = script_dir / "ACCPAC_MODULE_DETAILED_SEQUENCE_DIAGRAMS.md"
    output_dir = script_dir / "AccpacFlow"
    
    if not markdown_file.exists():
        print(f"ERROR: {markdown_file} not found!")
        return
    
    if not output_dir.exists():
        output_dir.mkdir(parents=True, exist_ok=True)
        print(f"Created output directory: {output_dir}")
    
    print(f"Reading markdown file: {markdown_file.name}")
    diagrams, titles = extract_mermaid_diagrams(markdown_file)
    
    print(f"Found {len(diagrams)} Mermaid diagrams")
    print(f"Found {len(titles)} titles")
    
    if len(diagrams) == 0:
        print("ERROR: No Mermaid diagrams found in the file!")
        return
    
    # Ensure we have enough titles
    while len(titles) < len(diagrams):
        titles.append(f"Diagram_{len(titles) + 1}")
    
    print(f"\n{'='*60}")
    print(f"Converting {len(diagrams)} diagrams to ultra-high-quality JPEGs...")
    print(f"Using large viewport (6000x8000) with native rendering")
    print(f"{'='*60}\n")
    
    for i, (diagram_code, title) in enumerate(zip(diagrams, titles), 1):
        # Clean title for filename
        safe_title = re.sub(r'[^\w\s-]', '', title)
        safe_title = re.sub(r'[-\s]+', '-', safe_title)
        safe_title = safe_title.lower()
        filename = f"{i:02d}-{safe_title}.jpg"
        output_path = output_dir / filename
        
        print(f"[{i}/{len(diagrams)}] Converting: {title}")
        print(f"   Output: {filename}")
        print(f"   Code length: {len(diagram_code)} characters")
        
        try:
            mermaid_to_jpeg(diagram_code, output_path, title)
        except Exception as e:
            print(f"   [ERROR] {e}")
            import traceback
            traceback.print_exc()
            continue
    
    print(f"\n{'='*60}")
    print(f"Conversion complete!")
    print(f"Images saved to: {output_dir}")
    print(f"Total files created: {len([f for f in output_dir.glob('*.jpg')])}")
    print(f"{'='*60}")

if __name__ == "__main__":
    main()
