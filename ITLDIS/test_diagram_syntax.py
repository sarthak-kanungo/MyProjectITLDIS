#!/usr/bin/env python3
"""Test diagram syntax and find exact error location"""

import re
from playwright.sync_api import sync_playwright

def test_diagram(code):
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
            const errors = document.querySelectorAll('.error');
            let msg = 'SVG: ' + (svg ? 'YES' : 'NO') + '\\n';
            msg += 'Errors: ' + errors.length + '\\n';
            errors.forEach(e => msg += 'Error: ' + e.textContent + '\\n');
            document.getElementById('result').textContent = msg;
        }}, 10000);
    </script>
</body>
</html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page()
        page.set_content(html)
        page.wait_for_timeout(12000)
        result = page.evaluate('document.getElementById("result").textContent')
        browser.close()
        return result

with open('SERVICE-MODULE-UML-DIAGRAM.md', 'r', encoding='utf-8') as f:
    content = f.read()

pattern = r'```mermaid\n(.*?)\n```'
match = re.search(pattern, content, re.DOTALL)
if match:
    diagram = match.group(1)
    print("Testing full diagram...")
    result = test_diagram(diagram)
    print(result)

