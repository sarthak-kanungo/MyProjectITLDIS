#!/usr/bin/env python3
"""Test minimal Mermaid class diagram"""

from playwright.sync_api import sync_playwright

def test_minimal():
    html = """<!DOCTYPE html>
<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
</head>
<body>
    <div class="mermaid">
classDiagram
    class A {
        +method1()
    }
    class B {
        +method2()
    }
    A --> B
    </div>
    <div id="errors"></div>
    <script>
        mermaid.initialize({startOnLoad: true, securityLevel: 'loose'});
        setTimeout(() => {
            const svg = document.querySelector('svg');
            const errors = document.querySelectorAll('.error');
            document.getElementById('errors').innerHTML = 'SVG: ' + (svg ? 'Found' : 'Not found') + '<br>Errors: ' + errors.length;
            errors.forEach(e => document.getElementById('errors').innerHTML += '<br>' + e.textContent);
        }, 5000);
    </script>
</body>
</html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page()
        page.set_content(html)
        page.wait_for_timeout(6000)
        result = page.evaluate('document.getElementById("errors").innerHTML')
        browser.close()
        print("Minimal test result:", result)

test_minimal()

