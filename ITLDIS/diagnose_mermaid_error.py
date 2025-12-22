#!/usr/bin/env python3
"""Diagnose Mermaid syntax errors"""

import re
from playwright.sync_api import sync_playwright

def diagnose_mermaid(mermaid_code):
    html = f"""<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
    <style>
        body {{ padding: 20px; }}
        .error {{ color: red; font-weight: bold; background: #ffe6e6; padding: 10px; margin: 10px 0; }}
    </style>
</head>
<body>
    <div id="mermaid-container" class="mermaid">
{mermaid_code}
    </div>
    <div id="error-container"></div>
    <script>
        var errorContainer = document.getElementById('error-container');
        
        mermaid.initialize({{
            startOnLoad: true,
            theme: 'default',
            securityLevel: 'loose',
            logLevel: 'error'
        }});
        
        // Listen for errors
        window.addEventListener('error', function(e) {{
            errorContainer.innerHTML += '<div class="error">Window Error: ' + e.message + '</div>';
        }});
        
        // Check after a delay
        setTimeout(function() {{
            var svg = document.querySelector('svg');
            var errorDivs = document.querySelectorAll('.error');
            
            if (!svg) {{
                errorContainer.innerHTML += '<div class="error">No SVG found after rendering</div>';
            }}
            
            errorDivs.forEach(function(err) {{
                errorContainer.innerHTML += '<div class="error">Found error: ' + err.textContent + '</div>';
            }});
        }}, 10000);
    </script>
</body>
</html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page()
        page.set_content(html, wait_until='domcontentloaded')
        page.wait_for_timeout(12000)
        
        svg = page.query_selector('svg')
        error_container = page.query_selector('#error-container')
        error_divs = page.query_selector_all('.error')
        
        errors = []
        if error_container:
            errors.append(error_container.inner_text())
        for err in error_divs:
            errors.append(err.inner_text())
        
        browser.close()
        
        return {
            'has_svg': svg is not None,
            'errors': errors
        }

# Read diagram
with open('SERVICE-MODULE-UML-DIAGRAM.md', 'r', encoding='utf-8') as f:
    content = f.read()

pattern = r'```mermaid\n(.*?)\n```'
match = re.search(pattern, content, re.DOTALL)
if match:
    diagram = match.group(1)
    print("Diagnosing Mermaid syntax...")
    result = diagnose_mermaid(diagram)
    print(f"\nSVG rendered: {result['has_svg']}")
    print(f"Errors found: {len(result['errors'])}")
    for err in result['errors']:
        if err.strip():
            print(f"  - {err.strip()}")

