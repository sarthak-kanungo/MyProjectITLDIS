#!/usr/bin/env python3
"""Create a working UML diagram by testing incrementally"""

import re
from playwright.sync_api import sync_playwright
from PIL import Image
import io
import os

def test_and_render(code, output_path):
    """Test code and render if valid"""
    html = f"""<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
    <style>
        body {{ margin: 0; padding: 40px; background: white; }}
        .mermaid {{ display: block; }}
    </style>
</head>
<body>
    <div class="mermaid">
{code}
    </div>
    <script>
        mermaid.initialize({{
            startOnLoad: true,
            theme: 'default',
            securityLevel: 'loose',
            classDiagram: {{ useMaxWidth: false }}
        }});
    </script>
</body>
</html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page(viewport={'width': 7680, 'height': 4320})
        page.set_content(html, wait_until='domcontentloaded')
        page.wait_for_timeout(25000)
        
        # Check for errors
        error_info = page.evaluate('''() => {
            const errors = [];
            document.querySelectorAll('.error').forEach(e => {
                const text = e.textContent || "";
                if (text.includes('Syntax error') || text.includes('Parse error')) {
                    errors.push(text);
                }
            });
            return errors;
        }''')
        
        if error_info:
            browser.close()
            return False, error_info[0]
        
        svg = page.query_selector('svg')
        if not svg:
            browser.close()
            return False, "No SVG found"
        
        svg_text = page.evaluate('document.querySelector("svg")?.textContent || ""')
        if 'Syntax error' in svg_text:
            browser.close()
            return False, "SVG contains error"
        
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
    return True, f"Created: {output_path} ({os.path.getsize(output_path) // 1024} KB)"

# Read full diagram
with open('SERVICE-MODULE-UML-DIAGRAM.md', 'r', encoding='utf-8') as f:
    content = f.read()

pattern = r'```mermaid\n(.*?)\n```'
match = re.search(pattern, content, re.DOTALL)
if not match:
    print("No diagram found")
    exit(1)

full_diagram = match.group(1).strip()
lines = full_diagram.split('\n')

# Try full diagram first
print("Testing full diagram...")
success, result = test_and_render(full_diagram, 'Service-Module-UML-Class-Diagram.jpg')
if success:
    print(f"SUCCESS: {result}")
else:
    print(f"ERROR: {result}")
    print("\nTrying simplified version...")
    
    # Create simplified version - remove most method details
    simplified = []
    in_class = False
    method_count = 0
    
    for line in lines:
        if line.strip().startswith('class ') and '{' in line:
            in_class = True
            method_count = 0
            simplified.append(line)
        elif in_class and line.strip() == '}':
            in_class = False
            simplified.append(line)
        elif in_class and line.strip().startswith('+') and method_count < 5:
            # Keep only first 5 methods per class
            simplified.append(line)
            method_count += 1
        elif not in_class or not line.strip().startswith('+'):
            simplified.append(line)
    
    simplified_code = '\n'.join(simplified)
    success, result = test_and_render(simplified_code, 'Service-Module-UML-Class-Diagram.jpg')
    if success:
        print(f"SUCCESS: {result} (simplified version)")
    else:
        print(f"ERROR: Still error: {result}")

