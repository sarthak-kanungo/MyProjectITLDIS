#!/usr/bin/env python3
"""Find exact error location by testing sections"""

import re
from playwright.sync_api import sync_playwright

def test_section(code, name):
    html = f"""<html><head><script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script></head>
<body><div class="mermaid">{code}</div>
<script>mermaid.initialize({{startOnLoad: true, securityLevel: 'loose'}});</script></body></html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page()
        page.set_content(html)
        page.wait_for_timeout(8000)
        svg = page.query_selector('svg')
        errors = page.evaluate('''() => {
            const errs = [];
            document.querySelectorAll('.error').forEach(e => errs.push(e.textContent));
            return errs;
        }''')
        browser.close()
        return {'svg': svg is not None, 'errors': errors}

with open('SERVICE-MODULE-UML-DIAGRAM.md', 'r', encoding='utf-8') as f:
    content = f.read()

pattern = r'```mermaid\n(.*?)\n```'
match = re.search(pattern, content, re.DOTALL)
if not match:
    print("No diagram")
    exit(1)

full = match.group(1)
lines = full.split('\n')

# Test in chunks
print("Testing sections...")
for chunk_size in [100, 200, 300, 400, len(lines)]:
    test_code = '\n'.join(lines[:chunk_size])
    result = test_section(test_code, f"First {chunk_size} lines")
    if result['errors']:
        print(f"\nERROR at line {chunk_size}:")
        for err in result['errors']:
            print(f"  {err}")
        print(f"\nLast 5 lines of problematic section:")
        for line in lines[max(0, chunk_size-5):chunk_size]:
            print(f"  {line}")
        break
    elif not result['svg']:
        print(f"No SVG at line {chunk_size}")
        break
    else:
        print(f"Lines 1-{chunk_size}: OK")

