#!/usr/bin/env python3
"""
Script to convert ITLDIS Catalogue Module UML Class Diagram from markdown file to JPEG image
"""

import re
import os
import sys
from pathlib import Path
from playwright.sync_api import sync_playwright
from PIL import Image
import io

def extract_mermaid_diagram(markdown_file):
    """Extract Mermaid diagram from markdown file"""
    with open(markdown_file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Find mermaid code block
    pattern = r'```mermaid\n(.*?)\n```'
    match = re.search(pattern, content, re.DOTALL)
    
    if match:
        return match.group(1)
    else:
        raise Exception("No Mermaid diagram found in markdown file")

def mermaid_to_jpeg(mermaid_code, output_path, title="Catalogue Module UML Diagram", width=16000, height=12000):
    """Convert Mermaid diagram code to high-resolution PNG image for zoom (lossless quality)"""
    
    # Clean up the mermaid code
    cleaned_code = mermaid_code
    
    # Remove any HTML tags that might cause issues
    cleaned_code = cleaned_code.replace('<br/>', ' ').replace('<br>', ' ')
    cleaned_code = cleaned_code.replace('&lt;', '<').replace('&gt;', '>')
    cleaned_code = cleaned_code.replace('&amp;', '&')
    
    # Fix relationship labels with special characters
    cleaned_code = re.sub(r':\s*([^:]+)/([^:]+)', r': \1 \2', cleaned_code)
    
    # Remove any leading/trailing whitespace from each line but preserve structure
    lines = cleaned_code.split('\n')
    cleaned_lines = []
    for line in lines:
        stripped = line.strip()
        if stripped:
            cleaned_lines.append(stripped)
    cleaned_code = '\n'.join(cleaned_lines)
    
    html_content = f"""<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
    <style>
        * {{
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
            text-rendering: optimizeLegibility;
        }}
        body {{
            margin: 0;
            padding: 100px;
            background: white;
            font-family: 'Arial', 'Helvetica', sans-serif;
        }}
        .mermaid {{
            display: block;
            width: 100%;
            min-width: 14000px;
            min-height: 10000px;
        }}
        svg {{
            shape-rendering: geometricPrecision;
            text-rendering: geometricPrecision;
            max-width: none !important;
            width: 100% !important;
            height: auto !important;
        }}
        svg text {{
            font-size: 28px !important;
            font-weight: 600;
        }}
        .mermaid .classBox {{
            min-width: 200px;
        }}
        .mermaid .classText {{
            font-size: 28px !important;
        }}
        body {{
            overflow: visible !important;
        }}
    </style>
</head>
<body>
    <div class="mermaid">
{cleaned_code}
    </div>
    <script>
        mermaid.initialize({{
            startOnLoad: true,
            theme: 'default',
            securityLevel: 'loose',
            classDiagram: {{
                classDefault: {{
                    fill: '#fff',
                    stroke: '#333',
                    strokeWidth: 7
                }},
                useMaxWidth: false,
                padding: 100,
                diagramPadding: 50
            }},
            fontSize: 28,
            fontFamily: 'Arial, Helvetica, sans-serif',
            flowchart: {{
                useMaxWidth: false,
                htmlLabels: true
            }}
        }});
        
    </script>
</body>
</html>"""
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        # Use moderate device scale factor for sharp rendering
        context = browser.new_context(
            viewport={'width': width, 'height': height},
            device_scale_factor=2.0,
            has_touch=False,
            is_mobile=False
        )
        page = context.new_page()
        
        # Set content and wait for mermaid to render
        page.set_content(html_content, wait_until='domcontentloaded')
        
        # Wait for mermaid to render
        try:
            # Wait for SVG to appear (with longer timeout for complex diagrams)
            page.wait_for_selector('svg', timeout=90000)
            
            # Additional wait to ensure rendering is complete - longer for detailed diagrams
            page.wait_for_timeout(20000)
            
            # Check for Mermaid errors
            error_elements = page.query_selector_all('.error')
            if error_elements:
                error_text = ""
                for elem in error_elements:
                    try:
                        error_text += elem.inner_text() + " "
                    except:
                        pass
                if error_text:
                    browser.close()
                    raise Exception(f"Mermaid rendering error: {error_text.strip()}")
            
            # Verify SVG is actually rendered
            svg_element = page.query_selector('svg')
            if not svg_element:
                browser.close()
                raise Exception("SVG element not found after rendering")
            
            # Check if SVG has content
            svg_content = page.evaluate('document.querySelector("svg").innerHTML')
            if not svg_content or len(svg_content) < 100:
                browser.close()
                raise Exception("SVG appears to be empty or corrupted")
            
            # Wait for all elements to be fully rendered
            page.wait_for_timeout(5000)
            
            # Verify SVG has content
            svg_html = page.evaluate('document.querySelector("svg") ? document.querySelector("svg").innerHTML : ""')
            if svg_html and len(svg_html) > 500:
                text_count = len([m for m in svg_html.split('<text')]) - 1
                print(f"   Found {text_count} text elements in diagram")
            else:
                print(f"   Warning: SVG might not be fully rendered, waiting longer...")
                page.wait_for_timeout(10000)
            
        except Exception as e:
            browser.close()
            raise Exception(f"Failed to render diagram: {str(e)}")
        
        # Wait a bit more for everything to settle and ensure all details are visible
        page.wait_for_timeout(5000)
        
        # Scroll to top to ensure we capture from the beginning
        page.evaluate('window.scrollTo(0, 0)')
        page.wait_for_timeout(1000)
        
        # Get the actual SVG dimensions to ensure full capture
        js_code = """
        () => {
            const svg = document.querySelector('svg');
            if (svg) {
                // Get viewBox for accurate dimensions
                const viewBox = svg.getAttribute('viewBox');
                let svgWidth = 0;
                let svgHeight = 0;
                
                if (viewBox) {
                    const parts = viewBox.split(' ');
                    if (parts.length === 4) {
                        svgWidth = parseFloat(parts[2]);
                        svgHeight = parseFloat(parts[3]);
                    }
                }
                
                // Fallback to width/height attributes or client dimensions
                if (!svgWidth || !svgHeight) {
                    svgWidth = parseFloat(svg.getAttribute('width')) || svg.clientWidth || 0;
                    svgHeight = parseFloat(svg.getAttribute('height')) || svg.clientHeight || 0;
                }
                
                const rect = svg.getBoundingClientRect();
                const finalWidth = Math.max(svgWidth, rect.width) + 1200;
                const finalHeight = Math.max(svgHeight, rect.height) + 1200;
                
                return {
                    width: finalWidth,
                    height: finalHeight,
                    viewBox: viewBox,
                    rectWidth: rect.width,
                    rectHeight: rect.height
                };
            }
            return { width: 16000, height: 12000 };
        }
        """
        content_size = page.evaluate(js_code)
        svg_width = int(content_size.get('width', 16000))
        svg_height = int(content_size.get('height', 12000))
        print(f"   SVG dimensions: {svg_width}x{svg_height}")
        
        # Ensure viewport is large enough
        final_width = max(width, svg_width)
        final_height = max(height, svg_height)
        if final_width > width or final_height > height:
            print(f"   Setting viewport to: {final_width}x{final_height}")
            page.set_viewport_size({'width': final_width, 'height': final_height})
            page.wait_for_timeout(3000)
        
        # Ensure SVG renders at full size
        page.evaluate("""
        () => {
            const svg = document.querySelector('svg');
            const mermaidDiv = document.querySelector('.mermaid');
            if (svg) {
                // Remove size constraints
                svg.removeAttribute('width');
                svg.removeAttribute('height');
                svg.style.width = 'auto';
                svg.style.height = 'auto';
                svg.style.maxWidth = 'none';
                svg.style.maxHeight = 'none';
            }
            if (mermaidDiv) {
                mermaidDiv.style.width = 'auto';
                mermaidDiv.style.height = 'auto';
            }
        }
        """)
        page.wait_for_timeout(3000)
        
        # Get updated dimensions after scaling
        content_size = page.evaluate(js_code)
        svg_width = int(content_size.get('width', 16000))
        svg_height = int(content_size.get('height', 12000))
        print(f"   Updated SVG dimensions after scaling: {svg_width}x{svg_height}")
        
        # Cap viewport at reasonable size to avoid screenshot failures
        # Playwright has limits, so use max 14000x10000
        max_viewport_width = 14000
        max_viewport_height = 10000
        final_width = min(max(width, int(svg_width * 0.8)), max_viewport_width)
        final_height = min(max(height, int(svg_height * 0.8)), max_viewport_height)
        
        print(f"   Setting viewport to: {final_width}x{final_height}")
        page.set_viewport_size({'width': final_width, 'height': final_height})
        page.wait_for_timeout(3000)
        
        # Scroll to ensure we start from top
        page.evaluate('window.scrollTo(0, 0)')
        page.wait_for_timeout(1000)
        
        # Use full page screenshot - should work with capped viewport
        try:
            screenshot_bytes = page.screenshot(
                full_page=True,
                type='png',
                timeout=120000
            )
            print(f"   Full page screenshot successful")
        except Exception as e:
            error_msg = str(e)[:150]
            print(f"   Full page screenshot failed: {error_msg}")
            # Fallback: try viewport screenshot
            try:
                screenshot_bytes = page.screenshot(
                    type='png',
                    timeout=120000
                )
                print(f"   Viewport screenshot successful")
            except Exception as e2:
                # Last resort: try SVG element
                svg_element = page.query_selector('svg')
                if svg_element:
                    screenshot_bytes = svg_element.screenshot(type='png', timeout=120000)
                    print(f"   SVG element screenshot used as fallback")
                else:
                    raise Exception(f"All screenshot methods failed: {str(e2)}")
        
        context.close()
        browser.close()
    
    # Save as PNG for lossless quality (better for zoom)
    # Change extension to .png if output path ends with .jpg
    if str(output_path).endswith('.jpg') or str(output_path).endswith('.jpeg'):
        output_path = Path(str(output_path).replace('.jpg', '.png').replace('.jpeg', '.png'))
    
    with open(output_path, 'wb') as f:
        f.write(screenshot_bytes)
    
    # Verify file was created and has reasonable size
    file_size = os.path.getsize(output_path)
    if file_size < 10000:  # Less than 10KB is suspicious
        raise Exception(f"Generated image is too small ({file_size} bytes) - likely corrupted")
    
    print(f"Created: {output_path} ({file_size // 1024} KB)")

def main():
    """Main function to convert Catalogue Module UML diagram"""
    # Set UTF-8 encoding for stdout
    if sys.platform == 'win32':
        import io
        sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')
    
    script_dir = Path(__file__).parent
    markdown_file = script_dir / "CATALOGUE-MODULE-UML-DIAGRAM.md"
    output_dir = script_dir
    
    if not markdown_file.exists():
        print(f"ERROR: {markdown_file} not found!")
        return
    
    print(f"Reading markdown file: {markdown_file}")
    
    # Extract diagram
    try:
        diagram = extract_mermaid_diagram(markdown_file)
        print(f"Found diagram (length: {len(diagram)} characters)")
    except Exception as e:
        print(f"ERROR: {e}")
        return
    
    # Output filename (PNG for lossless quality)
    output_filename = "Catalogue-Module-UML-Class-Diagram.png"
    output_path = output_dir / output_filename
    
    print(f"\nConverting UML Class Diagram to ultra-high-resolution PNG...")
    print(f"   Output: {output_filename}")
    print(f"   Resolution: 16000x12000 with native 28px fonts + 2x device scale (lossless PNG)")
    
    try:
        if output_path.exists():
            output_path.unlink()
            print(f"   Deleted old file: {output_path}")
        
        # Also delete old JPG if it exists
        old_jpg = output_dir / "Catalogue-Module-UML-Class-Diagram.jpg"
        if old_jpg.exists():
            old_jpg.unlink()
            print(f"   Deleted old JPG file: {old_jpg}")
        
        mermaid_to_jpeg(diagram, output_path, "Catalogue Module UML Class Diagram", width=16000, height=12000)
        print(f"   ✓ Successfully created: {output_path}")
    except Exception as e:
        print(f"   ✗ ERROR converting diagram: {e}")
        import traceback
        traceback.print_exc()
        return
    
    print(f"\n{'='*60}")
    print(f"Conversion complete! Generated image: {output_path}")
    print(f"{'='*60}")

if __name__ == "__main__":
    main()

