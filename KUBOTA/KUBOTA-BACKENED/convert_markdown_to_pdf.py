#!/usr/bin/env python3
"""
Script to convert Markdown file with Mermaid diagrams to PDF
"""

import re
import os
from pathlib import Path
from playwright.sync_api import sync_playwright

def markdown_to_html(markdown_file):
    """Convert markdown file to HTML with Mermaid support"""
    with open(markdown_file, 'r', encoding='utf-8') as f:
        lines = f.readlines()
    
    html_lines = []
    in_code_block = False
    code_block_type = None
    code_content = []
    
    for line in lines:
        # Handle code blocks (including Mermaid)
        if line.strip().startswith('```'):
            if in_code_block:
                # End of code block
                if code_block_type == 'mermaid':
                    html_lines.append('<div class="mermaid">')
                    html_lines.append(''.join(code_content))
                    html_lines.append('</div>')
                else:
                    html_lines.append('<pre><code>')
                    html_lines.append(''.join(code_content))
                    html_lines.append('</code></pre>')
                code_content = []
                in_code_block = False
                code_block_type = None
            else:
                # Start of code block
                in_code_block = True
                code_block_type = line.strip()[3:].strip() or 'code'
                if code_block_type == 'mermaid':
                    code_block_type = 'mermaid'
                else:
                    code_block_type = 'code'
            continue
        
        if in_code_block:
            code_content.append(line)
            continue
        
        # Convert markdown to HTML
        line = line.rstrip()
        
        # Headers
        if line.startswith('# '):
            html_lines.append(f'<h1>{line[2:]}</h1>')
        elif line.startswith('## '):
            html_lines.append(f'<h2>{line[3:]}</h2>')
        elif line.startswith('### '):
            html_lines.append(f'<h3>{line[4:]}</h3>')
        elif line.startswith('#### '):
            html_lines.append(f'<h4>{line[5:]}</h4>')
        # Horizontal rule
        elif line.strip() == '---':
            html_lines.append('<hr>')
        # Lists
        elif line.strip().startswith('- ') or line.strip().startswith('* '):
            content = line.strip()[2:]
            html_lines.append(f'<li>{content}</li>')
        # Bold
        elif '**' in line:
            line = re.sub(r'\*\*(.+?)\*\*', r'<strong>\1</strong>', line)
            html_lines.append(f'<p>{line}</p>')
        # Links
        elif '[' in line and '](' in line:
            line = re.sub(r'\[([^\]]+)\]\(([^\)]+)\)', r'<a href="\2">\1</a>', line)
            html_lines.append(f'<p>{line}</p>')
        # Code inline
        elif '`' in line:
            line = re.sub(r'`([^`]+)`', r'<code>\1</code>', line)
            html_lines.append(f'<p>{line}</p>')
        # Empty line
        elif not line.strip():
            html_lines.append('<br>')
        # Regular paragraph
        else:
            html_lines.append(f'<p>{line}</p>')
    
    # Handle any remaining code block
    if in_code_block:
        if code_block_type == 'mermaid':
            html_lines.append('<div class="mermaid">')
            html_lines.append(''.join(code_content))
            html_lines.append('</div>')
        else:
            html_lines.append('<pre><code>')
            html_lines.append(''.join(code_content))
            html_lines.append('</code></pre>')
    
    html_body = '\n'.join(html_lines)
    
    # Create full HTML document with Mermaid support
    html_content = f"""<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KUBOTA DMS - Sequence Diagrams</title>
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
    <style>
        * {{
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }}
        body {{
            font-family: 'Arial', 'Helvetica', 'DejaVu Sans', sans-serif;
            line-height: 1.6;
            color: #333;
            max-width: 1200px;
            margin: 0 auto;
            padding: 40px 20px;
            background: white;
        }}
        h1 {{
            color: #2c3e50;
            border-bottom: 3px solid #3498db;
            padding-bottom: 10px;
            margin-top: 0;
            font-size: 2.5em;
        }}
        h2 {{
            color: #34495e;
            border-bottom: 2px solid #ecf0f1;
            padding-bottom: 8px;
            margin-top: 40px;
            font-size: 2em;
        }}
        h3 {{
            color: #555;
            margin-top: 30px;
            font-size: 1.5em;
        }}
        h4 {{
            color: #666;
            margin-top: 20px;
            font-size: 1.2em;
        }}
        p {{
            margin: 15px 0;
            text-align: justify;
        }}
        code {{
            background-color: #f4f4f4;
            padding: 2px 6px;
            border-radius: 3px;
            font-family: 'Courier New', monospace;
            font-size: 0.9em;
        }}
        pre {{
            background-color: #f8f8f8;
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 15px;
            overflow-x: auto;
        }}
        pre code {{
            background-color: transparent;
            padding: 0;
        }}
        .mermaid {{
            background: white;
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 20px;
            margin: 30px 0;
            text-align: center;
            page-break-inside: avoid;
        }}
        ul, ol {{
            margin: 15px 0;
            padding-left: 30px;
        }}
        li {{
            margin: 8px 0;
        }}
        hr {{
            border: none;
            border-top: 2px solid #ecf0f1;
            margin: 40px 0;
        }}
        a {{
            color: #3498db;
            text-decoration: none;
        }}
        a:hover {{
            text-decoration: underline;
        }}
        @media print {{
            body {{
                padding: 20px;
            }}
            .mermaid {{
                page-break-inside: avoid;
            }}
            h2 {{
                page-break-before: always;
            }}
        }}
    </style>
</head>
<body>
{html_body}
    <script>
        mermaid.initialize({{
            startOnLoad: true,
            theme: 'default',
            securityLevel: 'loose',
            sequence: {{
                diagramMarginX: 50,
                diagramMarginY: 10,
                actorMargin: 50,
                width: 150,
                height: 65,
                boxMargin: 10,
                boxTextMargin: 5,
                noteMargin: 10,
                messageMargin: 35,
                mirrorActors: true,
                bottomMarginAdj: 1,
                useMaxWidth: true,
                rightAngles: false,
                showSequenceNumbers: false
            }},
            flowchart: {{
                useMaxWidth: true,
                htmlLabels: true
            }}
        }});
        
        // Wait for all Mermaid diagrams to render
        document.addEventListener('DOMContentLoaded', function() {{
            setTimeout(function() {{
                // All diagrams should be rendered now
            }}, 3000);
        }});
    </script>
</body>
</html>"""
    
    return html_content

def markdown_to_pdf(markdown_file, output_pdf):
    """Convert markdown file with Mermaid diagrams to PDF"""
    
    print(f"Reading markdown file: {markdown_file}")
    html_content = markdown_to_html(markdown_file)
    
    with sync_playwright() as p:
        browser = p.chromium.launch(
            headless=True,
            args=[
                '--disable-web-security',
                '--disable-gpu',
                '--no-sandbox',
                '--disable-setuid-sandbox'
            ]
        )
        
        # Create a new page
        page = browser.new_page()
        
        # Set content and wait for Mermaid to render
        print("Rendering HTML with Mermaid diagrams...")
        page.set_content(html_content, wait_until='networkidle', timeout=180000)
        
        # Wait for all Mermaid diagrams to render
        print("Waiting for Mermaid diagrams to render...")
        try:
            # Wait for all SVG elements (Mermaid diagrams)
            page.wait_for_selector('svg', timeout=120000)
            page.wait_for_timeout(8000)  # Extra wait for complex diagrams
            
            # Verify all diagrams are rendered
            svg_count = len(page.query_selector_all('svg'))
            print(f"Found {svg_count} Mermaid diagrams")
            
        except Exception as e:
            print(f"Warning: {e}")
        
        # Generate PDF
        print("Generating PDF...")
        page.pdf(
            path=output_pdf,
            format='A4',
            margin={
                'top': '20mm',
                'right': '15mm',
                'bottom': '20mm',
                'left': '15mm'
            },
            print_background=True,
            prefer_css_page_size=True
        )
        
        browser.close()
    
    file_size = os.path.getsize(output_pdf)
    print(f"\n[OK] PDF created: {output_pdf.name} ({file_size // 1024} KB)")

def main():
    script_dir = Path(__file__).parent
    markdown_file = script_dir.parent / "KUBOTA-SEQUENCE-DIAGRAMS.md"
    output_pdf = script_dir / "KUBOTA-SEQUENCE-DIAGRAMS.pdf"
    
    if not markdown_file.exists():
        print(f"ERROR: {markdown_file} not found!")
        return
    
    print(f"{'='*60}")
    print(f"Converting Markdown to PDF")
    print(f"{'='*60}\n")
    
    try:
        markdown_to_pdf(markdown_file, output_pdf)
        print(f"\n{'='*60}")
        print(f"Conversion complete!")
        print(f"PDF saved to: {output_pdf}")
        print(f"{'='*60}")
    except Exception as e:
        print(f"\n[ERROR] {e}")
        import traceback
        traceback.print_exc()

if __name__ == "__main__":
    main()
