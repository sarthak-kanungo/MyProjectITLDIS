#!/usr/bin/env python3
"""Diagnose Mermaid syntax errors in Warranty Module diagram"""

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
        pre {{ background: #f5f5f5; padding: 10px; overflow: auto; }}
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
            var errorTexts = document.querySelectorAll('.errorText');
            
            var report = 'SVG Found: ' + (svg ? 'YES' : 'NO') + '\\n';
            report += 'Error Divs: ' + errorDivs.length + '\\n';
            report += 'Error Texts: ' + errorTexts.length + '\\n';
            
            if (!svg) {{
                errorContainer.innerHTML += '<div class="error">No SVG found after rendering</div>';
            }} else {{
                var svgText = svg.textContent || '';
                if (svgText.includes('Syntax error')) {{
                    errorContainer.innerHTML += '<div class="error">SVG contains syntax error message</div>';
                    errorContainer.innerHTML += '<pre>' + svgText.substring(0, 500) + '</pre>';
                }}
            }}
            
            errorDivs.forEach(function(err) {{
                report += 'Error: ' + err.textContent + '\\n';
                errorContainer.innerHTML += '<div class="error">Found error: ' + err.textContent + '</div>';
            }});
            
            errorTexts.forEach(function(err) {{
                report += 'Error Text: ' + err.textContent + '\\n';
                errorContainer.innerHTML += '<div class="error">Error Text: ' + err.textContent + '</div>';
            }});
            
            console.log(report);
            errorContainer.innerHTML += '<pre>' + report + '</pre>';
        }}, 15000);
    </script>
</body>
</html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page()
        page.set_content(html, wait_until='domcontentloaded')
        page.wait_for_timeout(15000)
        
        # Get error container content
        error_content = page.evaluate('document.getElementById("error-container").innerHTML')
        
        # Check for SVG
        svg = page.query_selector('svg')
        svg_text = ""
        if svg:
            svg_text = page.evaluate('document.querySelector("svg").textContent')
        
        browser.close()
        
        return {
            'has_svg': svg is not None,
            'svg_text': svg_text[:500] if svg_text else '',
            'error_html': error_content
        }

# Read the markdown file
with open('WARRANTY-MODULE-UML-DIAGRAM.md', 'r', encoding='utf-8') as f:
    content = f.read()

# Extract mermaid diagram
pattern = r'```mermaid\n(.*?)\n```'
match = re.search(pattern, content, re.DOTALL)

if match:
    diagram = match.group(1)
    print(f"Found diagram ({len(diagram)} characters)")
    print("\nDiagnosing...")
    
    result = diagnose_mermaid(diagram)
    
    print(f"\nSVG Found: {result['has_svg']}")
    if result['svg_text']:
        print(f"\nSVG Text (first 500 chars):\n{result['svg_text']}")
    if result['error_html']:
        print(f"\nError HTML:\n{result['error_html']}")
else:
    print("No mermaid diagram found!")

