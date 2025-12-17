#!/usr/bin/env python3
"""Test Mermaid rendering directly and capture actual error"""
import sys
import io
if sys.platform == 'win32':
    sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')

from pathlib import Path
from playwright.sync_api import sync_playwright

cleaned_file = Path("ExceptionFlow/03_3-fixed-final.txt")
with open(cleaned_file, 'r', encoding='utf-8') as f:
    code = f.read()

html = f"""<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
</head>
<body>
<div class="mermaid">
{code}
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
    page.wait_for_timeout(5000)
    
    # Get all text content to find error
    all_text = page.evaluate('document.body.innerText')
    print("Page content:")
    print(all_text[:1000])
    
    # Check for SVG
    svg = page.evaluate('document.querySelector("svg") ? "SVG found" : "No SVG"')
    print(f"\nSVG status: {svg}")
    
    browser.close()

