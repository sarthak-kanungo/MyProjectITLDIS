#!/usr/bin/env python3
"""
Convert Markdown Preview to PDF
Usage: python convert_md_preview_to_pdf.py <input.md> <output.pdf>
"""

import re
import os
import sys
from pathlib import Path
from playwright.sync_api import sync_playwright

def markdown_to_pdf(markdown_file, output_pdf):
    """Convert markdown file to PDF with Mermaid support"""
    
    # Read markdown file
    with open(markdown_file, 'r', encoding='utf-8') as f:
        md_content = f.read()
    
    # Extract Mermaid diagrams and replace with placeholders
    mermaid_blocks = []
    pattern = r'```mermaid\n(.*?)\n```'
    
    def replace_mermaid(match):
        idx = len(mermaid_blocks)
        mermaid_blocks.append(match.group(1))
        return f'<div class="mermaid" id="mermaid-{idx}"></div>'
    
    # Replace mermaid code blocks with divs
    html_content = re.sub(pattern, replace_mermaid, md_content, flags=re.DOTALL)
    
    # Convert markdown to HTML (simple conversion)
    # For full markdown support, use markdown library
    html_content = html_content.replace('\n\n', '</p><p>')
    html_content = html_content.replace('\n', '<br>')
    
    # Wrap in paragraphs
    html_content = f'<p>{html_content}</p>'
    
    # Restore Mermaid blocks
    for idx, mermaid_code in enumerate(mermaid_blocks):
        html_content = html_content.replace(
            f'<div class="mermaid" id="mermaid-{idx}"></div>',
            f'<div class="mermaid">{mermaid_code}</div>'
        )
    
    # Create full HTML document
    full_html = f"""<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
    <style>
        @page {{
            margin: 1.5cm;
        }}
        body {{
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
            line-height: 1.6;
            color: #333;
            max-width: 100%;
            margin: 0;
            padding: 20px;
        }}
        h1 {{
            font-size: 2em;
            color: #2c3e50;
            border-bottom: 3px solid #3498db;
            padding-bottom: 10px;
            margin-top: 0;
        }}
        h2 {{
            font-size: 1.5em;
            color: #34495e;
            margin-top: 1.5em;
            border-bottom: 2px solid #ecf0f1;
            padding-bottom: 5px;
        }}
        h3 {{
            font-size: 1.2em;
            color: #34495e;
            margin-top: 1.2em;
        }}
        p {{
            margin: 1em 0;
        }}
        code {{
            background-color: #f4f4f4;
            padding: 2px 6px;
            border-radius: 3px;
            font-family: 'Courier New', Consolas, monospace;
            font-size: 0.9em;
        }}
        pre {{
            background-color: #f4f4f4;
            padding: 15px;
            border-radius: 5px;
            overflow-x: auto;
            border-left: 4px solid #3498db;
        }}
        .mermaid {{
            text-align: center;
            margin: 30px 0;
            padding: 20px;
            background-color: #fafafa;
            border-radius: 5px;
        }}
        table {{
            border-collapse: collapse;
            width: 100%;
            margin: 20px 0;
        }}
        table th, table td {{
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }}
        table th {{
            background-color: #3498db;
            color: white;
            font-weight: bold;
        }}
        table tr:nth-child(even) {{
            background-color: #f9f9f9;
        }}
        ul, ol {{
            margin: 1em 0;
            padding-left: 2em;
        }}
        li {{
            margin: 0.5em 0;
        }}
        blockquote {{
            border-left: 4px solid #3498db;
            margin: 1em 0;
            padding-left: 1em;
            color: #666;
            font-style: italic;
        }}
    </style>
</head>
<body>
{html_content}
    <script>
        mermaid.initialize({{
            startOnLoad: true,
            theme: 'default',
            securityLevel: 'loose',
            flowchart: {{
                useMaxWidth: true
            }},
            classDiagram: {{
                useMaxWidth: true
            }}
        }});
    </script>
</body>
</html>"""
    
    # Use Playwright to convert HTML to PDF
    print(f"Converting {markdown_file} to PDF...")
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page()
        
        # Set content
        page.set_content(full_html, wait_until='networkidle', timeout=120000)
        
        # Wait for Mermaid diagrams to render
        print("Waiting for diagrams to render...")
        page.wait_for_timeout(10000)
        
        # Check if Mermaid rendered
        mermaid_count = page.evaluate('document.querySelectorAll(".mermaid").length')
        print(f"Found {mermaid_count} Mermaid diagram(s)")
        
        # Generate PDF
        print("Generating PDF...")
        page.pdf(
            path=output_pdf,
            format='A4',
            margin={
                'top': '1.5cm',
                'bottom': '1.5cm',
                'left': '1.5cm',
                'right': '1.5cm'
            },
            print_background=True,
            display_header_footer=True,
            header_template='<div style="font-size:10px;text-align:center;width:100%;color:#666;"><span class="title"></span></div>',
            footer_template='<div style="font-size:10px;text-align:center;width:100%;color:#666;">Page <span class="pageNumber"></span> of <span class="totalPages"></span></div>'
        )
        
        browser.close()
    
    file_size = os.path.getsize(output_pdf)
    print(f"✓ PDF created: {output_pdf}")
    print(f"  File size: {file_size // 1024} KB")

def main():
    """Main function"""
    if len(sys.argv) < 3:
        print("Usage: python convert_md_preview_to_pdf.py <input.md> <output.pdf>")
        print("\nExample:")
        print("  python convert_md_preview_to_pdf.py ACCPAC-MODULE-UML-DIAGRAM.md ACCPAC-Diagram.pdf")
        sys.exit(1)
    
    input_file = sys.argv[1]
    output_file = sys.argv[2]
    
    if not os.path.exists(input_file):
        print(f"ERROR: Input file not found: {input_file}")
        sys.exit(1)
    
    try:
        markdown_to_pdf(input_file, output_file)
        print("\n✓ Conversion complete!")
    except Exception as e:
        print(f"✗ ERROR: {e}")
        import traceback
        traceback.print_exc()
        sys.exit(1)

if __name__ == "__main__":
    main()

