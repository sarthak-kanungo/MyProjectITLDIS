#!/usr/bin/env python3
"""Find the exact location of Mermaid syntax error"""

import re
from playwright.sync_api import sync_playwright

def test_mermaid_section(code, section_name):
    """Test a section of Mermaid code"""
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
        mermaid.initialize({{startOnLoad: true, securityLevel: 'loose'}});
    </script>
</body>
</html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page()
        page.set_content(html)
        page.wait_for_timeout(8000)
        
        svg = page.query_selector('svg')
        error_divs = page.query_selector_all('.error')
        errors = []
        for err in error_divs:
            try:
                errors.append(page.evaluate('(e) => e.textContent', err))
            except:
                pass
        
        browser.close()
        return {'has_svg': svg is not None, 'errors': errors}

# Read full diagram
with open('SERVICE-MODULE-UML-DIAGRAM.md', 'r', encoding='utf-8') as f:
    content = f.read()

pattern = r'```mermaid\n(.*?)\n```'
match = re.search(pattern, content, re.DOTALL)
if not match:
    print("No diagram found")
    exit(1)

full_diagram = match.group(1)
lines = full_diagram.split('\n')

# Test incrementally
print("Testing diagram sections...")
for i in range(10, len(lines), 50):
    test_code = '\n'.join(lines[:i])
    result = test_mermaid_section(test_code, f"Lines 1-{i}")
    if result['errors']:
        print(f"\nERROR FOUND at line {i}:")
        print(f"Errors: {result['errors']}")
        print(f"\nProblematic section (last 10 lines):")
        print('\n'.join(lines[max(0, i-10):i]))
        break
    elif not result['has_svg']:
        print(f"SVG not found at line {i}")
        break
    else:
        print(f"Lines 1-{i}: OK")

