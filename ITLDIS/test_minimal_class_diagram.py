#!/usr/bin/env python3
"""Test minimal class diagram to understand syntax"""

from playwright.sync_api import sync_playwright

test_cases = [
    ("Simple class", "classDiagram\n    class A {\n        +method()\n    }"),
    ("Class with param", "classDiagram\n    class A {\n        +method(String)\n    }"),
    ("Class with space in param", "classDiagram\n    class A {\n        +method(String param)\n    }"),
    ("Class with Connection param", "classDiagram\n    class A {\n        +method(Connection)\n    }"),
    ("Two constructors", "classDiagram\n    class A {\n        +A()\n        +A(Connection)\n    }"),
]

for name, code in test_cases:
    html = f"""<html><head><script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script></head>
<body><div class="mermaid">{code}</div>
<script>mermaid.initialize({{startOnLoad: true}});</script></body></html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page()
        page.set_content(html)
        page.wait_for_timeout(5000)
        svg = page.query_selector('svg')
        errors = page.query_selector_all('.error')
        error_text = ""
        if errors:
            error_text = page.evaluate('(e) => e.textContent', errors[0])
        browser.close()
        print(f"{name}: SVG={svg is not None}, Errors={len(errors)}, ErrorText={error_text[:50] if error_text else 'None'}")

