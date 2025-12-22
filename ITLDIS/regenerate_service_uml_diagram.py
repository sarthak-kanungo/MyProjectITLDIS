#!/usr/bin/env python3
"""
Script to regenerate ITLDIS Service Module UML Class Diagram with improved error handling
"""

import re
import os
import sys
from pathlib import Path
from playwright.sync_api import sync_playwright
from PIL import Image
import io

def clean_mermaid_code(mermaid_code):
    """Clean and fix Mermaid code syntax"""
    cleaned = mermaid_code
    
    # Remove HTML entities
    cleaned = cleaned.replace('&lt;', '<').replace('&gt;', '>').replace('&amp;', '&')
    cleaned = cleaned.replace('<br/>', ' ').replace('<br>', ' ')
    
    # Fix relationship labels with forward slashes
    cleaned = re.sub(r':\s*([^:\n]+)/([^:\n]+)', r': \1 \2', cleaned)
    
    # Fix spaces in relationship labels
    cleaned = re.sub(r':\s*forwards to', r': forwardsTo', cleaned)
    
    # Remove empty parentheses from method signatures (keep them but ensure they're valid)
    # Actually, keep parentheses as they are valid in Mermaid
    
    # Clean up whitespace
    lines = cleaned.split('\n')
    cleaned_lines = []
    for line in lines:
        stripped = line.strip()
        if stripped and not stripped.startswith('%%') or stripped.startswith('%%'):
            cleaned_lines.append(stripped)
    cleaned = '\n'.join(cleaned_lines)
    
    return cleaned

def mermaid_to_jpeg(mermaid_code, output_path, width=7680, height=4320):
    """Convert Mermaid diagram code to JPEG image with better error handling"""
    
    cleaned_code = clean_mermaid_code(mermaid_code)
    
    html_content = f"""<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
    <style>
        body {{
            margin: 0;
            padding: 40px;
            background: white;
            font-family: Arial, sans-serif;
        }}
        .mermaid {{
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;
            min-height: 100vh;
        }}
        .error {{
            color: red;
            font-weight: bold;
            padding: 20px;
        }}
    </style>
</head>
<body>
    <div class="mermaid">
{cleaned_code}
    </div>
    <script>
        try {{
            mermaid.initialize({{
                startOnLoad: true,
                theme: 'default',
                securityLevel: 'loose',
                classDiagram: {{
                    classDefault: {{
                        fill: '#fff',
                        stroke: '#333',
                        strokeWidth: 2
                    }},
                    useMaxWidth: false,
                    padding: 10,
                    diagramPadding: 10
                }},
                logLevel: 'error'
            }});
        }} catch (e) {{
            console.error('Mermaid initialization error:', e);
            document.body.innerHTML = '<div class="error">Error: ' + e.message + '</div>';
        }}
    </script>
</body>
</html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page(viewport={'width': width, 'height': height})
        
        try:
            # Set content and wait for mermaid to render
            page.set_content(html_content, wait_until='domcontentloaded')
            
            # Wait for SVG to appear
            try:
                # First wait for the mermaid div to be processed
                page.wait_for_timeout(3000)
                
                # Check for error messages first
                error_elements = page.query_selector_all('.error, [class*="error"], [id*="error"]')
                if error_elements:
                    error_texts = []
                    for elem in error_elements:
                        try:
                            text = elem.inner_text()
                            if text and len(text.strip()) > 0:
                                error_texts.append(text.strip())
                        except:
                            pass
                    if error_texts:
                        browser.close()
                        raise Exception(f"Mermaid error detected: {' | '.join(error_texts)}")
                
                # Now wait for SVG
                page.wait_for_selector('svg', timeout=120000)
                
                # Wait for SVG to be fully rendered (check if it has content)
                for i in range(30):  # Wait up to 30 seconds
                    page.wait_for_timeout(1000)
                    svg = page.query_selector('svg')
                    if svg:
                        svg_html = page.evaluate('document.querySelector("svg")?.outerHTML || ""')
                        if svg_html and len(svg_html) > 500:  # SVG has substantial content
                            break
                
                # Final wait to ensure everything is stable
                page.wait_for_timeout(5000)
            except Exception as e:
                # Check for error messages
                error_divs = page.query_selector_all('.error, [class*="error"]')
                error_text = ""
                for elem in error_divs:
                    try:
                        text = elem.inner_text()
                        if text and 'error' in text.lower():
                            error_text += text + " "
                    except:
                        pass
                
                # Also check console errors
                console_logs = []
                page.on('console', lambda msg: console_logs.append(msg.text))
                
                if error_text:
                    browser.close()
                    raise Exception(f"Mermaid syntax error detected: {error_text.strip()}")
                else:
                    browser.close()
                    raise Exception(f"SVG not found after timeout: {str(e)}")
            
            # Verify SVG is rendered
            svg_element = page.query_selector('svg')
            if not svg_element:
                browser.close()
                raise Exception("SVG element not found")
            
            # Check for any error messages that might have appeared
            # Only check for actual syntax errors, not warnings
            error_texts = page.evaluate('''() => {
                const errors = [];
                const errorElements = document.querySelectorAll('.error, [class*="error"], [id*="error"]');
                errorElements.forEach(el => {
                    const text = (el.textContent || el.innerText || "").trim();
                    // Only flag actual syntax errors, not generic error divs
                    if (text && (text.includes('Syntax error') || text.includes('Parse error'))) {
                        errors.push(text);
                    }
                });
                return errors;
            }''')
            
            if error_texts:
                browser.close()
                raise Exception(f"Mermaid syntax error detected: {' | '.join(error_texts)}")
            
            # Check SVG content
            svg_html = page.evaluate('document.querySelector("svg")?.outerHTML || ""')
            if not svg_html or len(svg_html) < 200:
                browser.close()
                raise Exception(f"SVG appears empty or corrupted (length: {len(svg_html)})")
            
            # Verify SVG has actual content (not just error message)
            svg_text = page.evaluate('document.querySelector("svg")?.textContent || ""')
            if svg_text and ('Syntax error' in svg_text or ('error' in svg_text.lower() and len(svg_text) < 100)):
                browser.close()
                raise Exception(f"SVG contains error message: {svg_text[:200]}")
            
            # Take screenshot
            screenshot_bytes = page.screenshot(full_page=True, type='png')
            browser.close()
            
        except Exception as e:
            browser.close()
            raise Exception(f"Rendering failed: {str(e)}")
    
    # Convert PNG to JPEG
    img = Image.open(io.BytesIO(screenshot_bytes))
    
    if img.mode == 'RGBA':
        rgb_img = Image.new('RGB', img.size, (255, 255, 255))
        rgb_img.paste(img, mask=img.split()[3])
        img = rgb_img
    
    img.save(output_path, 'JPEG', quality=100, optimize=False)
    
    file_size = os.path.getsize(output_path)
    if file_size < 10000:
        raise Exception(f"Generated image too small ({file_size} bytes)")
    
    print(f"Created: {output_path} ({file_size // 1024} KB)")

def main():
    if sys.platform == 'win32':
        import io
        sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')
    
    script_dir = Path(__file__).parent
    markdown_file = script_dir / "SERVICE-MODULE-UML-DIAGRAM.md"
    output_path = script_dir / "Service-Module-UML-Class-Diagram.jpg"
    
    if not markdown_file.exists():
        print(f"ERROR: {markdown_file} not found!")
        return
    
    print(f"Reading: {markdown_file}")
    
    with open(markdown_file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    pattern = r'```mermaid\n(.*?)\n```'
    match = re.search(pattern, content, re.DOTALL)
    
    if not match:
        print("ERROR: No Mermaid diagram found!")
        return
    
    diagram = match.group(1)
    print(f"Found diagram ({len(diagram)} characters)")
    
    if output_path.exists():
        output_path.unlink()
        print("Deleted old file")
    
    try:
        mermaid_to_jpeg(diagram, output_path)
        print(f"✓ Successfully created: {output_path}")
    except Exception as e:
        print(f"✗ ERROR: {e}")
        import traceback
        traceback.print_exc()

if __name__ == "__main__":
    main()

