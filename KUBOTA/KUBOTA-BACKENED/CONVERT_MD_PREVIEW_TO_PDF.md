# Convert Markdown Preview to PDF in VSCode

## Best Method: Markdown PDF Extension

### Step 1: Install Extension

```bash
code --install-extension yzane.markdown-pdf
code --install-extension bierner.markdown-mermaid
```

### Step 2: Convert to PDF

**Method 1: Right-Click Menu**
1. Open your markdown file (e.g., `ACCPAC-MODULE-UML-DIAGRAM.md`)
2. Right-click in the editor
3. Select **"Markdown PDF: Export (pdf)"**
4. Choose save location
5. PDF is created!

**Method 2: Command Palette**
1. Open your markdown file
2. Press `Ctrl+Shift+P` (or `Cmd+Shift+P` on Mac)
3. Type: `Markdown PDF: Export (pdf)`
4. Press Enter
5. Select save location

---

## Configuration for Best Results

### Add to VSCode Settings (`settings.json`)

Press `Ctrl+Shift+P` → Type "Preferences: Open User Settings (JSON)"

```json
{
  // Markdown PDF Settings
  "markdown-pdf.type": "pdf",
  "markdown-pdf.format": "A4",
  "markdown-pdf.orientation": "portrait",
  "markdown-pdf.margin.top": "1.5cm",
  "markdown-pdf.margin.bottom": "1.5cm",
  "markdown-pdf.margin.left": "1.5cm",
  "markdown-pdf.margin.right": "1.5cm",
  
  // For Mermaid diagrams
  "markdown-pdf.mermaidServer": "https://mermaid.ink",
  
  // Quality settings
  "markdown-pdf.scale": 1.5,
  "markdown-pdf.displayHeaderFooter": true,
  "markdown-pdf.headerTemplate": "<div style='font-size:10px;text-align:center;width:100%;'><span class='title'></span></div>",
  "markdown-pdf.footerTemplate": "<div style='font-size:10px;text-align:center;width:100%;'><span class='pageNumber'></span> / <span class='totalPages'></span></div>",
  
  // Print background graphics
  "markdown-pdf.printBackground": true,
  
  // Styles
  "markdown-pdf.styles": [],
  "markdown-pdf.includeDefaultStyles": true
}
```

---

## Alternative Extensions

### 1. Markdown Preview Enhanced

**Install:**
```bash
code --install-extension shd101wyy.markdown-preview-enhanced
```

**Usage:**
1. Open markdown file
2. Right-click → "Markdown Preview Enhanced: Open Preview to the Side"
3. In preview, right-click → "Chrome (Puppeteer)" → "PDF"
4. Save PDF

**Advantages:**
- High-quality rendering
- Supports advanced markdown features
- Custom CSS support

### 2. Markdown All in One

**Install:**
```bash
code --install-extension yzhang.markdown-all-in-one
```

**Usage:**
1. Open markdown file
2. `Ctrl+Shift+P` → "Markdown All in One: Print current document to HTML"
3. Open HTML in browser
4. Print to PDF (Ctrl+P → Save as PDF)

---

## Python Script Method (For Automation)

### Create PDF from Markdown Preview

```python
#!/usr/bin/env python3
"""
Convert Markdown Preview to PDF using Playwright
"""

import re
import os
from pathlib import Path
from playwright.sync_api import sync_playwright
import markdown
from markdown.extensions import codehilite, fenced_code, tables

def markdown_to_pdf(markdown_file, output_pdf):
    """Convert markdown file to PDF"""
    
    # Read markdown file
    with open(markdown_file, 'r', encoding='utf-8') as f:
        md_content = f.read()
    
    # Convert markdown to HTML
    md = markdown.Markdown(
        extensions=['codehilite', 'fenced_code', 'tables', 'toc']
    )
    html_content = md.convert(md_content)
    
    # Create full HTML document with Mermaid support
    full_html = f"""<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
    <style>
        body {{
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
            line-height: 1.6;
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            color: #333;
        }}
        h1, h2, h3, h4, h5, h6 {{
            color: #2c3e50;
            margin-top: 1.5em;
        }}
        code {{
            background-color: #f4f4f4;
            padding: 2px 6px;
            border-radius: 3px;
            font-family: 'Courier New', monospace;
        }}
        pre {{
            background-color: #f4f4f4;
            padding: 15px;
            border-radius: 5px;
            overflow-x: auto;
        }}
        .mermaid {{
            text-align: center;
            margin: 20px 0;
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
            background-color: #f2f2f2;
            font-weight: bold;
        }}
    </style>
</head>
<body>
{html_content}
    <script>
        mermaid.initialize({{
            startOnLoad: true,
            theme: 'default',
            securityLevel: 'loose'
        }});
    </script>
</body>
</html>"""
    
    # Use Playwright to convert HTML to PDF
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page()
        
        # Set content
        page.set_content(full_html, wait_until='networkidle', timeout=120000)
        
        # Wait for Mermaid diagrams to render
        page.wait_for_timeout(5000)
        
        # Generate PDF
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
            header_template='<div style="font-size:10px;text-align:center;width:100%;"><span class="title"></span></div>',
            footer_template='<div style="font-size:10px;text-align:center;width:100%;"><span class="pageNumber"></span> / <span class="totalPages"></span></div>'
        )
        
        browser.close()
    
    print(f"✓ PDF created: {output_pdf}")

if __name__ == "__main__":
    import sys
    
    if len(sys.argv) < 3:
        print("Usage: python markdown_to_pdf.py <input.md> <output.pdf>")
        sys.exit(1)
    
    markdown_file = sys.argv[1]
    output_pdf = sys.argv[2]
    
    markdown_to_pdf(markdown_file, output_pdf)
```

**Install dependencies:**
```bash
pip install playwright markdown
playwright install chromium
```

---

## Quick Reference

### Method 1: VSCode Extension (Easiest)
1. Install: `code --install-extension yzane.markdown-pdf`
2. Right-click → "Markdown PDF: Export (pdf)"
3. Done!

### Method 2: Command Palette
1. `Ctrl+Shift+P`
2. Type: `Markdown PDF: Export (pdf)`
3. Enter

### Method 3: Python Script (Automation)
```bash
python markdown_to_pdf.py input.md output.pdf
```

---

## Troubleshooting

### Issue: Mermaid diagrams not rendering in PDF
**Solution**: 
- Install `bierner.markdown-mermaid` extension
- Or use `markdown-pdf.mermaidServer` setting

### Issue: PDF quality is low
**Solution**: Increase scale in settings:
```json
{
  "markdown-pdf.scale": 2
}
```

### Issue: Export fails
**Solution**:
- Check internet connection (for Mermaid CDN)
- Try different format first (HTML, then convert)
- Check file permissions

### Issue: Diagrams cut off
**Solution**: Adjust margins:
```json
{
  "markdown-pdf.margin.top": "2cm",
  "markdown-pdf.margin.bottom": "2cm"
}
```

---

## Recommended Settings for ACCPAC Module Diagram

```json
{
  "markdown-pdf.type": "pdf",
  "markdown-pdf.format": "A4",
  "markdown-pdf.orientation": "landscape",
  "markdown-pdf.scale": 1.5,
  "markdown-pdf.margin.top": "1cm",
  "markdown-pdf.margin.bottom": "1cm",
  "markdown-pdf.margin.left": "1cm",
  "markdown-pdf.margin.right": "1cm",
  "markdown-pdf.printBackground": true,
  "markdown-pdf.mermaidServer": "https://mermaid.ink"
}
```

---

## Example: Convert ACCPAC Module Diagram

1. **Open**: `ACCPAC-MODULE-UML-DIAGRAM.md`
2. **Right-click** → **"Markdown PDF: Export (pdf)"**
3. **Save as**: `ACCPAC-Module-UML-Diagram.pdf`
4. **Done!** ✅

The PDF will include:
- All markdown content
- Rendered Mermaid diagrams
- Proper formatting
- Page numbers (if configured)

