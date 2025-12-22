#!/usr/bin/env python3
"""Final regeneration with proper waiting"""

import re
from playwright.sync_api import sync_playwright
from PIL import Image
import io
import os

with open('SERVICE-MODULE-UML-DIAGRAM.md', 'r', encoding='utf-8') as f:
    content = f.read()

pattern = r'```mermaid\n(.*?)\n```'
match = re.search(pattern, content, re.DOTALL)
diagram = match.group(1).strip()

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
        }}
        .mermaid {{
            display: block;
        }}
    </style>
</head>
<body>
    <div class="mermaid">
{diagram}
    </div>
    <script>
        mermaid.initialize({{
            startOnLoad: true,
            theme: 'default',
            securityLevel: 'loose',
            classDiagram: {{
                useMaxWidth: false,
                padding: 10
            }}
        }});
    </script>
</body>
</html>"""

with sync_playwright() as p:
    browser = p.chromium.launch(headless=True)
    page = browser.new_page(viewport={'width': 7680, 'height': 4320})
    page.set_content(html, wait_until='domcontentloaded')
    
    # Wait for SVG
    page.wait_for_selector('svg', timeout=60000)
    
    # Wait for diagram to fully render - check multiple times
    for i in range(25):
        page.wait_for_timeout(2000)
        svg_info = page.evaluate('''() => {
            const svg = document.querySelector('svg');
            if (!svg) return {ready: false};
            const rects = svg.querySelectorAll('rect');
            const texts = svg.querySelectorAll('text');
            const paths = svg.querySelectorAll('path');
            return {
                ready: true,
                rects: rects.length,
                texts: texts.length,
                paths: paths.length,
                hasContent: rects.length > 10 && paths.length > 10
            };
        }''')
        
        if svg_info['ready'] and svg_info['hasContent']:
            # Check if it's actually rendered (has substantial content)
            if svg_info['rects'] > 10:
                break
    
    # Take screenshot
    screenshot = page.screenshot(full_page=True, type='png')
    browser.close()

# Convert to JPEG
img = Image.open(io.BytesIO(screenshot))
if img.mode == 'RGBA':
    rgb = Image.new('RGB', img.size, (255, 255, 255))
    rgb.paste(img, mask=img.split()[3])
    img = rgb

output_path = 'Service-Module-UML-Class-Diagram.jpg'
img.save(output_path, 'JPEG', quality=100)
print(f"Created: {output_path} ({os.path.getsize(output_path) // 1024} KB)")

