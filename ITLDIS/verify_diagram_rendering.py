#!/usr/bin/env python3
"""Verify diagram renders correctly"""

import re
from playwright.sync_api import sync_playwright

with open('SERVICE-MODULE-UML-DIAGRAM.md', 'r', encoding='utf-8') as f:
    content = f.read()

pattern = r'```mermaid\n(.*?)\n```'
match = re.search(pattern, content, re.DOTALL)
diagram = match.group(1) if match else ""

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
{diagram}
    </div>
    <div id="status"></div>
    <script>
        mermaid.initialize({{
            startOnLoad: true,
            theme: 'default',
            securityLevel: 'loose',
            classDiagram: {{ useMaxWidth: false }},
            logLevel: 'error'
        }});
        
        setTimeout(() => {{
            const svg = document.querySelector('svg');
            const errors = document.querySelectorAll('.error');
            let status = 'SVG: ' + (svg ? 'YES' : 'NO') + '\\n';
            status += 'SVG Length: ' + (svg ? svg.outerHTML.length : 0) + '\\n';
            status += 'Errors: ' + errors.length + '\\n';
            errors.forEach(e => status += 'Error: ' + e.textContent + '\\n');
            if (svg) {{
                const text = svg.textContent || '';
                if (text.includes('Syntax error')) {{
                    status += 'WARNING: SVG contains error text\\n';
                }}
            }}
            document.getElementById('status').textContent = status;
        }}, 15000);
    </script>
</body>
</html>"""

with sync_playwright() as p:
    browser = p.chromium.launch(headless=True)
    page = browser.new_page()
    page.set_content(html)
    page.wait_for_timeout(16000)
    status = page.evaluate('document.getElementById("status").textContent')
    browser.close()
    print(status)

