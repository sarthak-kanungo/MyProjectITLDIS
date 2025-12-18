#!/usr/bin/env python3
"""Test script to see actual Mermaid error messages"""

import re
from pathlib import Path
from playwright.sync_api import sync_playwright

def test_mermaid_diagram(diagram_code, diagram_num):
    """Test a Mermaid diagram and show the error if any"""
    
    html_content = f"""<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
    <style>
        body {{
            margin: 20px;
            background: white;
            font-family: Arial, sans-serif;
        }}
        .mermaid {{
            display: block;
            width: 100%;
        }}
        .error {{
            color: red;
            font-weight: bold;
        }}
    </style>
</head>
<body>
    <h2>Diagram {diagram_num}</h2>
    <div class="mermaid">
{diagram_code}
    </div>
    <script>
        mermaid.initialize({{
            startOnLoad: true,
            theme: 'default',
            securityLevel: 'loose'
        }});
    </script>
</body>
</html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=False)  # Show browser to see error
        page = browser.new_page(viewport={'width': 1920, 'height': 1080})
        
        page.set_content(html_content, wait_until='domcontentloaded')
        page.wait_for_timeout(5000)
        
        # Get page content to see if there are errors
        content = page.content()
        svg = page.query_selector('svg')
        
        if svg:
            svg_text = svg.inner_text()
            print(f"\nDiagram {diagram_num} SVG Text (first 500 chars):")
            print(svg_text[:500])
        else:
            print(f"\nDiagram {diagram_num}: No SVG found!")
            # Check for error messages
            body_text = page.inner_text('body')
            print("Body text:")
            print(body_text[:1000])
        
        input("Press Enter to close browser...")
        browser.close()

def main():
    script_dir = Path(__file__).parent
    markdown_file = script_dir / "STORAGE_MODULE_SEQUENCE_DIAGRAMS.md"
    
    with open(markdown_file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    pattern = r'```mermaid\n(.*?)\n```'
    diagrams = re.findall(pattern, content, re.DOTALL)
    
    # Test diagrams 6 and 7
    for i in [5, 6]:  # 0-indexed, so 5=diagram 6, 6=diagram 7
        print(f"\n{'='*60}")
        print(f"Testing Diagram {i+1}")
        print(f"{'='*60}")
        test_mermaid_diagram(diagrams[i], i+1)

if __name__ == "__main__":
    main()
