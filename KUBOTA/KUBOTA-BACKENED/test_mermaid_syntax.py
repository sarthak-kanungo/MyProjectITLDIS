#!/usr/bin/env python3
"""
Test script to validate Mermaid syntax
"""

import re
from pathlib import Path
from playwright.sync_api import sync_playwright

def test_mermaid_syntax(mermaid_code):
    """Test if Mermaid code renders without errors"""
    html_content = f"""<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
    <style>
        body {{
            margin: 0;
            padding: 80px;
            background: white;
            font-family: Arial, sans-serif;
        }}
        .mermaid {{
            display: block;
            width: 100%;
        }}
    </style>
</head>
<body>
    <div class="mermaid">
{mermaid_code}
    </div>
    <script>
        mermaid.initialize({{
            startOnLoad: true,
            theme: 'default',
            securityLevel: 'loose',
            sequence: {{
                diagramMarginX: 100,
                diagramMarginY: 20,
                actorMargin: 100,
                width: 300,
                height: 130,
                boxMargin: 20,
                boxTextMargin: 10,
                noteMargin: 20,
                messageMargin: 70,
                mirrorActors: true,
                bottomMarginAdj: 1,
                useMaxWidth: true,
                rightAngles: false,
                showSequenceNumbers: false
            }}
        }});
    </script>
</body>
</html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page(viewport={'width': 1920, 'height': 1080})
        
        page.set_content(html_content, wait_until='domcontentloaded')
        
        try:
            page.wait_for_selector('svg', timeout=10000)
            page.wait_for_timeout(2000)
            
            # Check for error messages - try multiple selectors and get more details
            error_info = page.evaluate('''
                () => {
                    // Try to find error elements
                    const errorDiv = document.querySelector('.error-text');
                    if (errorDiv) return {text: errorDiv.textContent, html: errorDiv.innerHTML};
                    
                    const errorSpan = document.querySelector('span.error-text');
                    if (errorSpan) return {text: errorSpan.textContent, html: errorSpan.innerHTML};
                    
                    // Check body text
                    const bodyText = document.body.textContent;
                    if (bodyText.includes('Syntax error')) {
                        return {text: bodyText, html: document.body.innerHTML.substring(0, 500)};
                    }
                    
                    // Check for any error indicators
                    const allText = document.body.innerText || document.body.textContent;
                    if (allText.includes('error') || allText.includes('Error')) {
                        return {text: allText.substring(0, 200), html: 'Found error text'};
                    }
                    
                    return null;
                }
            ''')
            
            error_text = error_info['text'] if error_info else None
            
            svg_content = page.evaluate('document.querySelector("svg") ? document.querySelector("svg").innerHTML : null')
            
            browser.close()
            
            if error_text:
                return False, error_text
            elif svg_content and len(svg_content) > 500:
                return True, "Success"
            else:
                return False, "SVG not rendered properly"
                
        except Exception as e:
            browser.close()
            return False, str(e)

if __name__ == "__main__":
    import sys
    import io
    if sys.platform == 'win32':
        sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')
    
    cleaned_file = Path("ExceptionFlow/03_3-final-clean.txt")
    
    if not cleaned_file.exists():
        print(f"File not found: {cleaned_file}")
        exit(1)
    
    with open(cleaned_file, 'r', encoding='utf-8') as f:
        code = f.read()
    
    print("Testing Mermaid syntax...")
    success, message = test_mermaid_syntax(code)
    
    if success:
        print("SUCCESS: Syntax is valid!")
    else:
        print(f"ERROR: Syntax error: {message}")
        if len(message) > 100:
            print(f"Full error message (first 500 chars): {message[:500]}")

