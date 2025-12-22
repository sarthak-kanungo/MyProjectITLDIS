#!/usr/bin/env python3
"""Test warranty diagram in sections to find the error"""

import re
from playwright.sync_api import sync_playwright

def test_mermaid(code, name):
    html = f"""<!DOCTYPE html>
<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
</head>
<body>
    <div class="mermaid">
{code}
    </div>
    <div id="result"></div>
    <script>
        mermaid.initialize({{startOnLoad: true, securityLevel: 'loose'}});
        setTimeout(() => {{
            const svg = document.querySelector('svg');
            const svgText = svg ? svg.textContent : '';
            const hasError = svgText.includes('Syntax error') || svgText.includes('error');
            const result = 'SVG: ' + (svg ? 'YES' : 'NO') + ' | Error: ' + (hasError ? 'YES' : 'NO');
            document.getElementById('result').textContent = result;
        }}, 10000);
    </script>
</body>
</html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page()
        page.set_content(html)
        page.wait_for_timeout(10000)
        result = page.evaluate('document.getElementById("result").textContent')
        browser.close()
        print(f"{name}: {result}")

# Read the file
with open('WARRANTY-MODULE-UML-DIAGRAM.md', 'r', encoding='utf-8') as f:
    content = f.read()

pattern = r'```mermaid\n(.*?)\n```'
match = re.search(pattern, content, re.DOTALL)

if match:
    full_diagram = match.group(1)
    
    # Test just the class definitions (first part)
    lines = full_diagram.split('\n')
    
    # Find where relationships start
    rel_start = None
    for i, line in enumerate(lines):
        if 'RELATIONSHIPS' in line and 'Controllers to Services' in line:
            rel_start = i
            break
    
    if rel_start:
        classes_only = '\n'.join(lines[:rel_start])
        test_mermaid(classes_only, "Classes only")
        
        # Test first few relationships
        first_rels = '\n'.join(lines[:rel_start + 20])
        test_mermaid(first_rels, "Classes + First 20 relationships")
        
        # Test full diagram
        test_mermaid(full_diagram, "Full diagram")

