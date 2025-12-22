#!/usr/bin/env python3
"""Build working diagram incrementally"""

from playwright.sync_api import sync_playwright

def test_code(code, name):
    html = f"""<html><head><script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script></head>
<body><div class="mermaid">{code}</div>
<script>mermaid.initialize({{startOnLoad: true, securityLevel: 'loose'}});</script>
<div id="result"></div>
<script>
setTimeout(() => {{
    const svg = document.querySelector('svg');
    const errs = document.querySelectorAll('.error');
    const svgText = svg ? svg.textContent : '';
    document.getElementById('result').textContent = 
        'SVG: ' + (svg ? 'YES' : 'NO') + ' | ' +
        'Errors: ' + errs.length + ' | ' +
        'HasErrorText: ' + (svgText.includes('Syntax error') ? 'YES' : 'NO');
}}, 10000);
</script></body></html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page()
        page.set_content(html)
        page.wait_for_timeout(11000)
        result = page.evaluate('document.getElementById("result").textContent')
        browser.close()
        return result

# Start with minimal
base = """classDiagram
    class A {
        +method()
    }
    class B {
        +method()
    }
    A --> B"""

print("Testing base...")
result = test_code(base, "base")
print(f"Base: {result}")

# Add one class at a time from the actual diagram
classes_to_test = [
    "serviceAction",
    "serviceDAO", 
    "serviceForm",
    "Connection",
    "HttpSession"
]

for cls in classes_to_test:
    test = base + f"\n    class {cls} {{\n        +method()\n    }}"
    result = test_code(test, cls)
    print(f"With {cls}: {result}")
    if "HasErrorText: YES" in result:
        print(f"  *** {cls} causes error! ***")
        break

