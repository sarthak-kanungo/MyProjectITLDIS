#!/usr/bin/env python3
"""Fix and regenerate UML diagram with proper error handling"""

import re
from playwright.sync_api import sync_playwright
from PIL import Image
import io
import os

def mermaid_to_jpeg_fixed(mermaid_code, output_path):
    """Convert with better error handling and retry logic"""
    
    # Clean the code
    cleaned = mermaid_code.strip()
    
    html = f"""<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
    <style>
        body {{
            margin: 0;
            padding: 40px;
            background: white;
            font-family: Arial, sans-serif;
        }}
        .mermaid {{
            display: flex;
            justify-content: center;
            align-items: flex-start;
            width: 100%;
            min-height: 100vh;
        }}
    </style>
</head>
<body>
    <div class="mermaid">
{cleaned}
    </div>
    <script>
        mermaid.initialize({{
            startOnLoad: true,
            theme: 'default',
            securityLevel: 'loose',
            classDiagram: {{
                useMaxWidth: false,
                padding: 10,
                diagramPadding: 10
            }},
            logLevel: 'error'
        }});
    </script>
</body>
</html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page(viewport={'width': 7680, 'height': 4320})
        page.set_content(html, wait_until='domcontentloaded')
        
        # Wait for page to load
        page.wait_for_timeout(3000)
        
        # Wait for SVG with timeout
        try:
            page.wait_for_selector('svg', timeout=60000)
        except:
            pass
        
        # Wait for diagram to fully render (check multiple times)
        for attempt in range(20):
            page.wait_for_timeout(2000)
            svg = page.query_selector('svg')
            if svg:
                # Check SVG content
                svg_info = page.evaluate('''() => {
                    const svg = document.querySelector('svg');
                    if (!svg) return {exists: false, html: '', text: ''};
                    return {
                        exists: true,
                        html: svg.outerHTML || '',
                        text: svg.textContent || ''
                    };
                }''')
                
                if svg_info['exists'] and len(svg_info['html']) > 2000:
                    # Check if it's an error message
                    if 'Syntax error' in svg_info['text'] and len(svg_info['text']) < 100:
                        continue  # Still showing error, wait more
                    # SVG is ready
                    break
        
        # Final check
        svg = page.query_selector('svg')
        if not svg:
            error_info = page.evaluate('''() => {
                const errors = [];
                document.querySelectorAll('.error, [class*="error"]').forEach(e => {
                    errors.push(e.textContent || e.innerText || "");
                });
                return errors;
            }''')
            browser.close()
            if error_info:
                raise Exception(f"Mermaid error: {' | '.join(error_info)}")
            raise Exception("SVG not found")
        
        # Verify it's not an error
        svg_text = page.evaluate('document.querySelector("svg")?.textContent || ""')
        if svg_text and 'Syntax error' in svg_text and len(svg_text) < 200:
            browser.close()
            raise Exception(f"SVG contains error message: {svg_text}")
        
        # Final wait
        page.wait_for_timeout(2000)
        
        # Take screenshot
        screenshot = page.screenshot(full_page=True, type='png')
        browser.close()
    
    # Convert to JPEG
    img = Image.open(io.BytesIO(screenshot))
    if img.mode == 'RGBA':
        rgb = Image.new('RGB', img.size, (255, 255, 255))
        rgb.paste(img, mask=img.split()[3])
        img = rgb
    
    img.save(output_path, 'JPEG', quality=100)
    print(f"Created: {output_path} ({os.path.getsize(output_path) // 1024} KB)")

# Read and process
with open('SERVICE-MODULE-UML-DIAGRAM.md', 'r', encoding='utf-8') as f:
    content = f.read()

pattern = r'```mermaid\n(.*?)\n```'
match = re.search(pattern, content, re.DOTALL)
if match:
    diagram = match.group(1)
    mermaid_to_jpeg_fixed(diagram, 'Service-Module-UML-Class-Diagram.jpg')
else:
    print("No diagram found")

