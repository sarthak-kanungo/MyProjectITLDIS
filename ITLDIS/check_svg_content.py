#!/usr/bin/env python3
"""Check SVG actual content"""

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
<div id="info"></div>
<script>
setTimeout(() => {{
    const svg = document.querySelector('svg');
    if (svg) {{
        const rects = svg.querySelectorAll('rect');
        const texts = svg.querySelectorAll('text');
        const paths = svg.querySelectorAll('path');
        const gs = svg.querySelectorAll('g');
        const errorIcon = svg.querySelector('.error-icon');
        const errorText = svg.querySelector('.error-text');
        
        let info = 'SVG dimensions: ' + svg.getAttribute('width') + 'x' + svg.getAttribute('height') + '\\n';
        info += 'Rects: ' + rects.length + '\\n';
        info += 'Texts: ' + texts.length + '\\n';
        info += 'Paths: ' + paths.length + '\\n';
        info += 'Groups: ' + gs.length + '\\n';
        info += 'Error icon: ' + (errorIcon ? 'YES' : 'NO') + '\\n';
        info += 'Error text element: ' + (errorText ? 'YES' : 'NO') + '\\n';
        if (errorText) {{
            info += 'Error text content: ' + errorText.textContent + '\\n';
        }}
        if (texts.length > 0) {{
            info += 'First text: ' + texts[0].textContent + '\\n';
        }}
        document.getElementById('info').textContent = info;
    }} else {{
        document.getElementById('info').textContent = 'No SVG found';
    }}
}}, 15000);
</script></body></html>"""

with sync_playwright() as p:
    browser = p.chromium.launch(headless=True)
    page = browser.new_page()
    page.set_content(html)
    page.wait_for_timeout(16000)
    info = page.evaluate('document.getElementById("info").textContent')
    browser.close()
    print(info)

