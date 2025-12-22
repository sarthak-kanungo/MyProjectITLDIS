#!/usr/bin/env python3
"""Capture exact Mermaid error message"""

import re
from playwright.sync_api import sync_playwright

with open('SERVICE-MODULE-UML-DIAGRAM.md', 'r', encoding='utf-8') as f:
    content = f.read()

pattern = r'```mermaid\n(.*?)\n```'
match = re.search(pattern, content, re.DOTALL)
diagram = match.group(1).strip()

html = f"""<!DOCTYPE html>
<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
</head>
<body>
    <div class="mermaid">
{diagram}
    </div>
    <div id="error-details"></div>
    <script>
        mermaid.initialize({{
            startOnLoad: true,
            securityLevel: 'loose',
            logLevel: 'error'
        }});
        
        setTimeout(() => {{
            const svg = document.querySelector('svg');
            const errors = document.querySelectorAll('.error');
            let details = 'SVG exists: ' + (svg ? 'YES' : 'NO') + '\\n';
            if (svg) {{
                details += 'SVG text length: ' + (svg.textContent || '').length + '\\n';
                details += 'SVG text (first 500 chars): ' + (svg.textContent || '').substring(0, 500) + '\\n';
            }}
            details += 'Error elements: ' + errors.length + '\\n';
            errors.forEach((e, i) => {{
                details += 'Error ' + i + ': ' + e.textContent + '\\n';
            }});
            document.getElementById('error-details').textContent = details;
        }}, 15000);
    </script>
</body>
</html>"""

with sync_playwright() as p:
    browser = p.chromium.launch(headless=True)
    page = browser.new_page()
    page.set_content(html)
    page.wait_for_timeout(16000)
    details = page.evaluate('document.getElementById("error-details").textContent')
    browser.close()
    print(details)

