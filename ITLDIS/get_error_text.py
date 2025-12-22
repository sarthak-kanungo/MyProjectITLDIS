#!/usr/bin/env python3
"""Get the actual error text from SVG"""

import re
from playwright.sync_api import sync_playwright

with open('SERVICE-MODULE-UML-DIAGRAM.md', 'r', encoding='utf-8') as f:
    content = f.read()

pattern = r'```mermaid\n(.*?)\n```'
match = re.search(pattern, content, re.DOTALL)
diagram = match.group(1).strip()

html = f"""<html><head><script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script></head>
<body><div class="mermaid">{diagram}</div>
<script>mermaid.initialize({{startOnLoad: true, securityLevel: 'loose'}});</script>
<div id="output"></div>
<script>
setTimeout(() => {{
    const svg = document.querySelector('svg');
    if (svg) {{
        const textElements = svg.querySelectorAll('text');
        let allText = '';
        textElements.forEach(t => {{
            const txt = t.textContent || '';
            if (txt.includes('error') || txt.includes('Error') || txt.includes('Syntax')) {{
                allText += txt + ' | ';
            }}
        }});
        document.getElementById('output').textContent = 'Error text found: ' + (allText || 'None') + '\\nFull SVG text: ' + (svg.textContent || '').substring(0, 1000);
    }} else {{
        document.getElementById('output').textContent = 'No SVG';
    }}
}}, 15000);
</script></body></html>"""

with sync_playwright() as p:
    browser = p.chromium.launch(headless=True)
    page = browser.new_page()
    page.set_content(html)
    page.wait_for_timeout(16000)
    output = page.evaluate('document.getElementById("output").textContent')
    browser.close()
    print(output)

