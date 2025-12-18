#!/usr/bin/env python3
"""
Script to regenerate corrupted Training Module diagrams (8, 11, 12, 14, 15)
with improved syntax handling
"""

import re
import os
import sys
from pathlib import Path
from playwright.sync_api import sync_playwright
from PIL import Image
import io

def extract_specific_diagrams(markdown_file, diagram_indices):
    """Extract specific Mermaid diagrams from markdown file by index (1-based)"""
    with open(markdown_file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Find all mermaid code blocks
    pattern = r'```mermaid\n(.*?)\n```'
    diagrams = re.findall(pattern, content, re.DOTALL)
    
    # Extract titles
    titles = []
    lines = content.split('\n')
    current_title = "Diagram"
    for i, line in enumerate(lines):
        if line.startswith('## '):
            # Extract title after number
            title_match = re.match(r'## \d+\.\s+(.+)', line)
            if title_match:
                current_title = title_match.group(1)
        if '```mermaid' in line:
            titles.append(current_title)
    
    # Return only requested diagrams
    result = []
    for idx in diagram_indices:
        if idx <= len(diagrams):
            result.append((diagrams[idx - 1], titles[idx - 1] if idx <= len(titles) else f"Diagram {idx}", idx))
    
    return result

def clean_mermaid_code(mermaid_code):
    """Clean Mermaid code to fix syntax issues"""
    # Step 1: Handle <br/> tags first
    cleaned_code = mermaid_code.replace('<br/>', ' ').replace('<br>', ' ')
    cleaned_code = cleaned_code.replace('&lt;', '<').replace('&gt;', '>')
    cleaned_code = cleaned_code.replace('&amp;', '&')
    
    # Step 2: Fix participant aliases - remove spaces instead of quoting (more reliable)
    cleaned_code = re.sub(
        r'participant (\w+) as Client Application',
        r'participant \1 as ClientApplication',
        cleaned_code
    )
    cleaned_code = re.sub(
        r'participant (\w+) as ([A-Z][a-zA-Z]+) ([A-Z][a-zA-Z]+)',
        r'participant \1 as \2\3',
        cleaned_code
    )
    # Fix specific participant names
    cleaned_code = re.sub(
        r'participant (\w+) as Training Program Calendar',
        r'participant \1 as TrainingProgramCalendar',
        cleaned_code
    )
    cleaned_code = re.sub(
        r'participant (\w+) as Training Program Report',
        r'participant \1 as TrainingProgramReport',
        cleaned_code
    )
    cleaned_code = re.sub(
        r'participant (\w+) as Attendance Sheet',
        r'participant \1 as AttendanceSheet',
        cleaned_code
    )
    
    # Step 3: Fix generic types - remove angle brackets
    cleaned_code = re.sub(r'<([^>]+)>', r' \1', cleaned_code)
    
    # Step 4: Fix special operators and expressions
    cleaned_code = cleaned_code.replace('!=', 'not equal')
    cleaned_code = cleaned_code.replace('<=', 'less than or equal')
    cleaned_code = cleaned_code.replace('>=', 'greater than or equal')
    cleaned_code = cleaned_code.replace('!isEmpty', 'not empty')
    cleaned_code = cleaned_code.replace('Integer.MAX_VALUE', 'MAX_VALUE')
    cleaned_code = cleaned_code.replace('OR', 'or')
    cleaned_code = cleaned_code.replace('AND', 'and')
    
    # Step 5: Fix curly braces in messages - replace with parentheses
    cleaned_code = re.sub(r'\{([^}]+)\}', r'(\1)', cleaned_code)
    
    # Step 6: Fix rect blocks - remove rgb colors completely
    cleaned_code = re.sub(r'rect rgb\([^)]+\)', r'rect', cleaned_code)
    # Also handle rect without rgb
    cleaned_code = re.sub(r'rect\s*$', r'rect', cleaned_code, flags=re.MULTILINE)
    
    # Step 7: Remove comment lines that might cause issues
    cleaned_code = re.sub(r'%%.*\n', '', cleaned_code)
    
    # Step 8: Clean up lines and simplify messages
    lines = cleaned_code.split('\n')
    cleaned_lines = []
    for line in lines:
        stripped = line.strip()
        if stripped:
            # Remove quotes from message labels
            if '->>' in line or '->' in line:
                if ':' in line:
                    parts = line.split(':', 1)
                    if len(parts) == 2:
                        message = parts[1]
                        # Remove quotes, parentheses, and simplify
                        message = message.replace('"', '').replace("'", '')
                        # Remove map.put() calls - simplify them
                        message = re.sub(r'map\.put\([^)]+\)', 'map put', message)
                        # Remove complex method calls with parameters
                        message = re.sub(r'\([^)]{20,}\)', '', message)  # Remove long parameter lists
                        # Remove angle brackets and generic types
                        message = re.sub(r'<[^>]+>', '', message)
                        # Remove colons, dashes, commas, equals, plus that might cause issues
                        message = message.replace(':', ' ').replace('-', ' ').replace(',', ' ')
                        message = message.replace('=', ' ').replace('+', ' ').replace('?', ' ')
                        # Remove curly braces content
                        message = re.sub(r'\{[^}]+\}', '', message)
                        # Clean up multiple spaces
                        message = ' '.join(message.split())
                        line = parts[0] + ': ' + message
            # Simplify Note messages
            elif stripped.startswith('Note'):
                if ':' in line:
                    parts = line.split(':', 1)
                    if len(parts) == 2:
                        message = parts[1]
                        message = message.replace('"', '').replace("'", '')
                        message = ' '.join(message.split())
                        line = parts[0] + ': ' + message
            cleaned_lines.append(line)
    cleaned_code = '\n'.join(cleaned_lines)
    
    # Step 9: Fix alt/end blocks - simplify complex conditions
    # Simplify alt labels - remove complex conditions
    cleaned_code = re.sub(r'alt\s+Nomination Detail ID Exists', r'alt NominationDetailExists', cleaned_code)
    cleaned_code = re.sub(r'alt\s+Trainers Exist.*', r'alt TrainersExist', cleaned_code)
    cleaned_code = re.sub(r'alt\s+Documents Exist.*', r'alt DocumentsExist', cleaned_code)
    cleaned_code = re.sub(r'alt\s+Exception Occurs', r'alt ExceptionOccurs', cleaned_code)
    
    # Remove complex conditions from alt labels
    cleaned_code = re.sub(r'alt\s+([^\(]+)\([^)]+\)', r'alt \1', cleaned_code)
    
    # Step 10: Ensure proper spacing around arrows
    cleaned_code = re.sub(r'(\w)->>\s*(\w)', r'\1->>\2', cleaned_code)
    cleaned_code = re.sub(r'(\w)->\s*(\w)', r'\1->\2', cleaned_code)
    
    return cleaned_code

def mermaid_to_jpeg(mermaid_code, output_path, title="Diagram", width=7680, height=4320):
    """Convert Mermaid diagram code to JPEG image with enhanced cleaning"""
    
    cleaned_code = clean_mermaid_code(mermaid_code)
    
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
            zoom: 2;
        }}
        .mermaid {{
            display: block;
            width: 100%;
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
        page = browser.new_page(viewport={'width': width, 'height': height})
        
        page.set_content(html_content, wait_until='domcontentloaded')
        page.wait_for_timeout(8000)
        
        # Check for errors
        error_text = page.evaluate('''
            () => {
                const errorDiv = document.querySelector('.error-text');
                return errorDiv ? errorDiv.textContent : null;
            }
        ''')
        
        if error_text and "Syntax error" in error_text:
            # Try to get more error details
            error_details = page.evaluate('''
                () => {
                    const body = document.body.innerText || document.body.textContent;
                    return body.substring(0, 300);
                }
            ''')
            print(f"   Warning: Syntax error detected")
            print(f"   Error: {error_details[:150]}")
            # Wait longer and check if SVG actually rendered
            page.wait_for_timeout(5000)
            
            # Check if SVG has meaningful content despite error
            svg_check = page.evaluate('''
                () => {
                    const svg = document.querySelector('svg');
                    if (!svg) return {exists: false, size: 0};
                    const box = svg.getBBox();
                    return {
                        exists: true,
                        width: box.width,
                        height: box.height,
                        contentLength: svg.innerHTML.length
                    };
                }
            ''')
            
            if svg_check and svg_check.get('exists') and svg_check.get('width', 0) > 100:
                print(f"   SVG rendered despite error (size: {svg_check.get('width')}x{svg_check.get('height')})")
            else:
                raise Exception(f"Mermaid syntax error prevents rendering: {error_text}")
        
        try:
            page.wait_for_selector('svg', timeout=60000)
            page.wait_for_timeout(2000)
            
            svg_element = page.query_selector('svg')
            if not svg_element:
                browser.close()
                raise Exception("SVG element not found")
            
            svg_inner_html = page.evaluate('document.querySelector("svg").innerHTML')
            if not svg_inner_html or len(svg_inner_html.strip()) < 500:
                page.wait_for_timeout(3000)
                svg_inner_html = page.evaluate('document.querySelector("svg").innerHTML')
                if not svg_inner_html or len(svg_inner_html.strip()) < 500:
                    browser.close()
                    raise Exception(f"SVG content too short ({len(svg_inner_html) if svg_inner_html else 0} chars)")
            
            screenshot_bytes = page.screenshot(full_page=True, type='png')
            browser.close()
            
        except Exception as e:
            browser.close()
            raise Exception(f"Failed to render: {str(e)}")
    
    img = Image.open(io.BytesIO(screenshot_bytes))
    
    if img.mode == 'RGBA':
        rgb_img = Image.new('RGB', img.size, (255, 255, 255))
        rgb_img.paste(img, mask=img.split()[3])
        img = rgb_img
    
    img.save(output_path, 'JPEG', quality=100, optimize=False)
    
    file_size = os.path.getsize(output_path)
    if file_size < 10000:
        raise Exception(f"Image too small: {file_size} bytes")
    
    width, height = img.size
    if width < 100 or height < 100:
        raise Exception(f"Image dimensions too small: {width}x{height}")
    
    print(f"   ✓ Created: {output_path} ({file_size // 1024} KB, {width}x{height})")

def main():
    """Main function to regenerate corrupted diagrams"""
    if sys.platform == 'win32':
        import io
        sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')
    
    script_dir = Path(__file__).parent
    markdown_file = script_dir / "TRAINING_MODULE_SEQUENCE_DIAGRAMS.md"
    output_dir = script_dir / "TrainingFlow"
    
    # Diagrams to regenerate: 8, 11, 12, 14, 15
    diagram_indices = [8, 11, 12, 14, 15]
    
    if not markdown_file.exists():
        print(f"ERROR: {markdown_file} not found!")
        return
    
    if not output_dir.exists():
        output_dir.mkdir(parents=True, exist_ok=True)
        print(f"Created output directory: {output_dir}")
    
    print(f"Reading markdown file: {markdown_file}")
    diagrams_to_regenerate = extract_specific_diagrams(markdown_file, diagram_indices)
    
    print(f"Found {len(diagrams_to_regenerate)} diagrams to regenerate\n")
    
    success_count = 0
    for diagram_code, title, idx in diagrams_to_regenerate:
        # Create filename matching the expected pattern
        if idx == 8:
            filename = "08_8-Attendance-Sheet-Save-Flow.jpg"
        elif idx == 11:
            filename = "11_11-Attendance-Sheet-ViewEdit-Data-Flow.jpg"
        elif idx == 12:
            filename = "12_12-Attendance-Sheet-Generate-Training-Certificate-Flow.jpg"
        elif idx == 14:
            filename = "14_14-Training-Program-Report-Download-Excel-Flow.jpg"
        elif idx == 15:
            filename = "15_15-Training-Program-Calendar-Get-Master-Data-Flows.jpg"
        else:
            safe_title = re.sub(r'[^\w\s-]', '', title)
            safe_title = re.sub(r'[-\s]+', '-', safe_title)
            filename = f"{idx:02d}_{idx}-{safe_title}.jpg"
        
        output_path = output_dir / filename
        
        print(f"Regenerating diagram {idx}: {title}")
        print(f"   Code length: {len(diagram_code)} characters")
        print(f"   Output: {filename}")
        
        try:
            if output_path.exists():
                output_path.unlink()
                print(f"   Deleted old file")
            
            mermaid_to_jpeg(diagram_code, output_path, title, width=7680, height=4320)
            print(f"   ✓ Successfully regenerated\n")
            success_count += 1
        except Exception as e:
            print(f"   ✗ ERROR: {e}\n")
            import traceback
            traceback.print_exc()
    
    print(f"{'='*60}")
    print(f"Regeneration complete! Generated {success_count} out of {len(diagrams_to_regenerate)} diagrams")
    print(f"Output directory: {output_dir}")

if __name__ == "__main__":
    main()

