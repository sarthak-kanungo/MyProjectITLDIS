#!/usr/bin/env python3
"""Test minimal warranty diagram to find syntax error"""

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
            msg += 'Error: ' + (hasError ? 'YES' : 'NO') + '\\n';
            if (hasError && svgText) {{
                const errorMatch = svgText.match(/Syntax error[^\\n]*/);
                if (errorMatch) msg += 'Error text: ' + errorMatch[0].substring(0, 100);
            }}
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
        print(f"\n{name}:")
        print(result)

# Test cases
test_code("classDiagram\n    class A {\n        +method()\n    }", "Simple class")

test_code("""classDiagram
    class WarrantyWcrController {
        <<RestController>>
        +pcrWarrantyClaim()
    }""", "One controller class")

test_code("""classDiagram
    class WarrantyWcrController {
        <<RestController>>
        +pcrWarrantyClaim()
        +goodwillWarrantyClaim()
        +saveWcr()
    }
    WarrantyWcrController --> WarrantyWcrRepo : uses""", "Class with relationship")

# Check for problematic characters
test_code("""classDiagram
    class WarrantyWcr {
        -Long id
        -String wcrNo
    }""", "Entity with attributes")

test_code("""classDiagram
    class WarrantyWcr {
        -Long id
    }
    WarrantyWcr --> WarrantyPcr : ManyToOne""", "Entity relationship")

