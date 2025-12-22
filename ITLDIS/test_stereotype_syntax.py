#!/usr/bin/env python3
"""Test stereotype syntax"""

from playwright.sync_api import sync_playwright

def test_code(code, name):
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
            const hasError = svgText.includes('Syntax error');
            let msg = 'SVG: ' + (svg ? 'YES' : 'NO') + '\\n';
            msg += 'Error: ' + (hasError ? 'YES' : 'NO');
            document.getElementById('result').textContent = msg;
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

# Test different stereotype syntaxes
test_code("classDiagram\n    class A {\n        +method()\n    }", "No stereotype")

test_code("classDiagram\n    class A <<interface>> {\n        +method()\n    }", "Stereotype before brace")

test_code("classDiagram\n    class A {\n        <<interface>>\n        +method()\n    }", "Stereotype inside brace")

test_code("classDiagram\n    class A {\n        <<RestController>>\n        +method()\n    }", "RestController stereotype")

test_code("classDiagram\n    class A {\n        +method()\n    }\n    note for A : RestController", "Note instead")

