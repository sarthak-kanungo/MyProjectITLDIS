#!/usr/bin/env python3
"""
Script to convert a single Mermaid diagram to ultra-high-quality JPEG image
"""

import re
import os
from pathlib import Path
from playwright.sync_api import sync_playwright
from PIL import Image
import io

# Increase PIL image size limit for high-resolution images
Image.MAX_IMAGE_PIXELS = None  # Remove limit

def extract_single_mermaid_diagram(markdown_file, diagram_number=1):
    """Extract a specific Mermaid diagram from markdown file"""
    with open(markdown_file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Find all mermaid code blocks
    pattern = r'```mermaid\n(.*?)\n```'
    diagrams = re.findall(pattern, content, re.DOTALL)
    
    # Extract diagram titles from markdown headers
    titles = []
    lines = content.split('\n')
    current_title = "Diagram"
    for i, line in enumerate(lines):
        if re.match(r'^## \d+\.\s+(.+)$', line):
            current_title = re.match(r'^## \d+\.\s+(.+)$', line).group(1).strip()
        if '```mermaid' in line:
            titles.append(current_title)
    
    if diagram_number < 1 or diagram_number > len(diagrams):
        raise ValueError(f"Diagram number {diagram_number} not found. Available: 1-{len(diagrams)}")
    
    return diagrams[diagram_number - 1], titles[diagram_number - 1] if diagram_number <= len(titles) else f"Diagram {diagram_number}"

def mermaid_to_jpeg(mermaid_code, output_path, title="Diagram"):
    """Convert Mermaid diagram code to ultra-high-quality JPEG image"""
    
    # Clean up the mermaid code
    cleaned_code = mermaid_code.replace('<br/>', '\n').replace('<br>', '\n')
    cleaned_code = cleaned_code.replace('&lt;', '<').replace('&gt;', '>')
    cleaned_code = cleaned_code.replace('&amp;', '&')
    
    # Fix participant aliases
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
    
    # Remove any leading/trailing whitespace
    lines = cleaned_code.split('\n')
    cleaned_lines = []
    for line in lines:
        stripped = line.strip()
        if stripped:
            cleaned_lines.append(stripped)
    cleaned_code = '\n'.join(cleaned_lines)
    
    # Use high dimensions for HD quality and zoom clarity
    # Optimized for maximum quality without exceeding browser limits
    viewport_width = 6000  # Wide enough for sequence diagrams
    viewport_height = 8000  # Tall enough for long diagrams
    
    # Large spacing for clarity - optimized for HD rendering
    margin_x = 1200
    margin_y = 600
    actor_width = 1800
    actor_height = 600
    box_margin = 150
    box_text_margin = 100
    note_margin = 180
    message_margin = 350
    title_font_size = 140
    title_margin_bottom = 180
    svg_text_size = 48
    
    # Create HTML with Mermaid - using scale_factor variable
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
            font-size: {title_font_size}px;
            font-weight: bold;
            margin-bottom: {title_margin_bottom}px;
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
            font-size: {svg_text_size}px;
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
        
        # Use device_scale_factor=1 with large viewport for HD quality
        # The large viewport and font sizes ensure crisp rendering
        context = browser.new_context(
            viewport={'width': viewport_width, 'height': viewport_height},
            device_scale_factor=1,  # Use native scale
            ignore_https_errors=True
        )
        page = context.new_page()
        
        page.set_content(html_content, wait_until='networkidle', timeout=180000)
        
        try:
            page.wait_for_selector('svg', timeout=180000)
            page.wait_for_timeout(20000)
            
            svg_element = page.query_selector('svg')
            if not svg_element:
                browser.close()
                raise Exception("SVG element not found after rendering")
            
            svg_box = svg_element.bounding_box()
            if svg_box and (svg_box['width'] < 100 or svg_box['height'] < 100):
                browser.close()
                raise Exception(f"SVG appears too small: {svg_box}")
            
            page.evaluate('document.body.offsetHeight')
            page.wait_for_timeout(2000)
            
        except Exception as e:
            browser.close()
            raise Exception(f"Failed to render diagram: {str(e)}")
        
        screenshot_bytes = page.screenshot(
            full_page=True, 
            type='png'
        )
        
        browser.close()
    
    img = Image.open(io.BytesIO(screenshot_bytes))
    
    if img.mode == 'RGBA':
        rgb_img = Image.new('RGB', img.size, (255, 255, 255))
        rgb_img.paste(img, mask=img.split()[3])
        img = rgb_img
    
    save_kwargs = {
        'quality': 100,
        'optimize': False,
        'subsampling': 0
    }
    img.save(output_path, 'JPEG', **save_kwargs)
    
    file_size = os.path.getsize(output_path)
    if file_size < 10000:
        raise Exception(f"Generated image is too small ({file_size} bytes) - likely corrupted")
    
    print(f"[OK] Created: {output_path.name} ({file_size // 1024} KB, {img.width}x{img.height}px)")

def main():
    script_dir = Path(__file__).parent
    markdown_file = script_dir.parent / "KUBOTA-SEQUENCE-DIAGRAMS.md"
    output_dir = script_dir / "AccpacFlow"
    
    if not markdown_file.exists():
        print(f"ERROR: {markdown_file} not found!")
        return
    
    if not output_dir.exists():
        output_dir.mkdir(parents=True, exist_ok=True)
        print(f"Created output directory: {output_dir}")
    
    print(f"Reading markdown file: {markdown_file.name}")
    diagram_code, title = extract_single_mermaid_diagram(markdown_file, diagram_number=1)
    
    # Clean title for filename
    safe_title = re.sub(r'[^\w\s-]', '', title)
    safe_title = re.sub(r'[-\s]+', '-', safe_title)
    safe_title = safe_title.lower()
    filename = f"01-user-authentication-authorization-flow.jpg"
    output_path = output_dir / filename
    
    print(f"\nConverting: {title}")
    print(f"Output: {filename}")
    print(f"Code length: {len(diagram_code)} characters")
    
    try:
        mermaid_to_jpeg(diagram_code, output_path, title)
        print(f"\nConversion complete!")
        print(f"Image saved to: {output_path}")
    except Exception as e:
        print(f"[ERROR] {e}")
        import traceback
        traceback.print_exc()

if __name__ == "__main__":
    main()

