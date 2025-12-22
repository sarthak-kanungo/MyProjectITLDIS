#!/usr/bin/env python3
"""Verify the warranty diagram image was generated correctly"""

import re
from playwright.sync_api import sync_playwright

def verify_diagram(mermaid_code):
    html = f"""<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
</head>
<body>
    <div class="mermaid">
{mermaid_code}
    </div>
    <script>
        mermaid.initialize({{
            startOnLoad: true,
            theme: 'default',
            securityLevel: 'loose'
        }});
    </script>
</body>
</html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page()
        page.set_content(html, wait_until='domcontentloaded')
        page.wait_for_selector('svg', timeout=30000)
        page.wait_for_timeout(5000)
        
        svg = page.query_selector('svg')
        if svg:
            svg_text = page.evaluate('document.querySelector("svg").textContent')
            has_error = 'Syntax error' in svg_text or 'error' in svg_text.lower()
            has_classes = 'WarrantyWcrController' in svg_text or 'WarrantyPcr' in svg_text
            
            browser.close()
            return {
                'has_svg': True,
                'has_error': has_error,
                'has_classes': has_classes,
                'preview': svg_text[:200]
            }
        else:
            browser.close()
            return {'has_svg': False, 'has_error': True, 'has_classes': False}

# Read and verify
with open('WARRANTY-MODULE-UML-DIAGRAM.md', 'r', encoding='utf-8') as f:
    content = f.read()

pattern = r'```mermaid\n(.*?)\n```'
match = re.search(pattern, content, re.DOTALL)

if match:
    diagram = match.group(1)
    result = verify_diagram(diagram)
    
    print(f"SVG Found: {result['has_svg']}")
    print(f"Has Error: {result['has_error']}")
    print(f"Has Classes: {result['has_classes']}")
    if result.get('preview'):
        print(f"\nPreview: {result['preview']}")
    
    if result['has_svg'] and result['has_classes'] and not result['has_error']:
        print("\n✓ Diagram rendered successfully!")
    else:
        print("\n✗ Diagram has issues!")
else:
    print("No diagram found!")

