#!/usr/bin/env python3
"""Test minimal Mermaid diagram"""
import sys
import io
if sys.platform == 'win32':
    sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')

from playwright.sync_api import sync_playwright

minimal_code = """sequenceDiagram
participant A as A
participant B as B
A->>B: Hello
"""

html = f"""<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
</head>
<body>
<div class="mermaid">
{minimal_code}
</div>
<script>mermaid.initialize({{startOnLoad: true}});</script>
</body>
</html>"""

with sync_playwright() as p:
    browser = p.chromium.launch(headless=True)
    page = browser.new_page()
    page.set_content(html, wait_until='domcontentloaded')
    page.wait_for_selector('svg', timeout=10000)
    page.wait_for_timeout(2000)
    svg = page.evaluate('document.querySelector("svg") ? document.querySelector("svg").innerHTML : null')
    browser.close()
    if svg and len(svg) > 100:
        print("SUCCESS: Minimal diagram works!")
    else:
        print("ERROR: Minimal diagram failed")

