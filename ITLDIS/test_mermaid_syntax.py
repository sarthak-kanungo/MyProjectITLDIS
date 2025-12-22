#!/usr/bin/env python3
"""Test Mermaid syntax to identify errors"""

import re
from playwright.sync_api import sync_playwright

def test_mermaid_syntax(mermaid_code):
    """Test Mermaid code and capture any errors"""
    html_content = f"""<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
</head>
<body>
    <div class="mermaid">
{mermaid_code}
    </div>
    <div id="error-output"></div>
    <script>
        mermaid.initialize({{
            startOnLoad: true,
            theme: 'default',
            securityLevel: 'loose',
            logLevel: 'error'
        }});
        
        window.addEventListener('error', function(e) {{
            document.getElementById('error-output').innerHTML = 'Error: ' + e.message;
            console.error('Error:', e);
        }});
    </script>
</body>
</html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page()
        page.set_content(html_content, wait_until='domcontentloaded')
        
        # Wait a bit
        page.wait_for_timeout(5000)
        
        # Check for errors
        error_div = page.query_selector('#error-output')
        error_text = ""
        if error_div:
            error_text = error_div.inner_text()
        
        # Check console
        console_messages = []
        page.on('console', lambda msg: console_messages.append(msg.text))
        
        # Check for SVG
        svg = page.query_selector('svg')
        
        browser.close()
        
        return {
            'has_svg': svg is not None,
            'error_text': error_text,
            'console_messages': console_messages
        }

# Read the diagram
with open('SERVICE-MODULE-UML-DIAGRAM.md', 'r', encoding='utf-8') as f:
    content = f.read()

pattern = r'```mermaid\n(.*?)\n```'
match = re.search(pattern, content, re.DOTALL)
if match:
    diagram = match.group(1)
    print("Testing Mermaid syntax...")
    result = test_mermaid_syntax(diagram)
    print(f"Has SVG: {result['has_svg']}")
    print(f"Error text: {result['error_text']}")
    print(f"Console messages: {result['console_messages']}")

