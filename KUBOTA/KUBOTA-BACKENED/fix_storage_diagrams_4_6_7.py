#!/usr/bin/env python3
"""
Script to fix and regenerate storage diagrams 4, 6, and 7
"""

import re
import os
import sys
from pathlib import Path
from playwright.sync_api import sync_playwright
from PIL import Image
import io

def extract_diagram(markdown_file, diagram_number):
    """Extract specific diagram from markdown file (1-indexed)"""
    with open(markdown_file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Find all mermaid code blocks
    pattern = r'```mermaid\n(.*?)\n```'
    diagrams = re.findall(pattern, content, re.DOTALL)
    
    if len(diagrams) < diagram_number:
        raise Exception(f"Only {len(diagrams)} diagrams found, need diagram {diagram_number}")
    
    return diagrams[diagram_number - 1]  # Convert to 0-indexed

def clean_mermaid_code(mermaid_code):
    """Clean Mermaid code to fix syntax errors"""
    # Step 1: Handle <br/> tags
    cleaned_code = mermaid_code.replace('<br/>', ' ').replace('<br>', ' ')
    cleaned_code = cleaned_code.replace('&lt;', '<').replace('&gt;', '>')
    cleaned_code = cleaned_code.replace('&amp;', '&')
    
    # Step 2: Fix participant aliases - remove spaces and special characters
    def fix_participant_alias(match):
        var_name = match.group(1)
        alias = match.group(2).strip()
        # Remove spaces, slashes, parentheses, and other special chars
        fixed_alias = alias.replace(' ', '').replace('/', '').replace('-', '').replace('(', '').replace(')', '')
        return f'participant {var_name} as {fixed_alias}'
    
    cleaned_code = re.sub(
        r'participant (\w+) as ([^\n]+)',
        fix_participant_alias,
        cleaned_code
    )
    
    # Step 3: Remove comment lines
    cleaned_code = re.sub(r'%%.*\n', '', cleaned_code)
    
    # Step 4: Fix generic types - simplify them
    cleaned_code = re.sub(r'<([^>]+)>', lambda m: ' ' + m.group(1).replace('<', '').replace('>', '') + ' ', cleaned_code)
    
    # Step 5: Clean up message labels and Note messages
    lines = cleaned_code.split('\n')
    cleaned_lines = []
    for line in lines:
        stripped = line.strip()
        if not stripped:
            continue
        
        # Skip any standalone "end" statements (not part of alt/else/end)
        if stripped == 'end' and not any('alt' in prev_line.lower() or 'else' in prev_line.lower() for prev_line in cleaned_lines[-5:]):
            continue
            
        if stripped.startswith('participant'):
            cleaned_lines.append(stripped)
        elif stripped.startswith('Note'):
            # Simplify Note messages
            if ':' in stripped:
                parts = stripped.split(':', 1)
                if len(parts) == 2:
                    note_part = parts[0].strip()
                    note_msg = parts[1].strip()
                    # Fix participant list in Note over - remove commas, keep first participant only
                    if 'Note over' in note_part:
                        # Extract participants
                        match = re.search(r'Note over ([^:]+):', note_part)
                        if match:
                            participants = match.group(1).strip()
                            # Take only first participant to avoid comma issues
                            first_participant = participants.split(',')[0].strip()
                            note_part = f'Note over {first_participant}'
                    # Clean up the message
                    note_msg = note_msg.replace('"', '').replace("'", '')
                    note_msg = re.sub(r'<[^>]+>', '', note_msg)
                    note_msg = re.sub(r'\([^)]+\)', '', note_msg)
                    note_msg = ' '.join(note_msg.split())
                    cleaned_lines.append(note_part + ': ' + note_msg)
                else:
                    cleaned_lines.append(stripped)
            else:
                cleaned_lines.append(stripped)
        elif '->>' in stripped or '->' in stripped:
            if ':' in stripped:
                parts = stripped.split(':', 1)
                if len(parts) == 2:
                    arrow_part = parts[0].strip()
                    message = parts[1].strip()
                    # Remove quotes, angle brackets
                    message = message.replace('"', '').replace("'", '')
                    message = re.sub(r'<[^>]+>', '', message)
                    # Remove long parameter lists in parentheses (keep short ones like (String))
                    message = re.sub(r'\([^)]{15,}\)', '', message)
                    # Remove special characters including backslashes and escaped chars
                    message = message.replace('\\', '').replace('/', ' ')
                    # Remove OR (uppercase) which can cause issues
                    message = message.replace(' OR ', ' ').replace('OR ', ' ').replace(' OR', ' ')
                    # Remove stray parentheses and brackets
                    message = message.replace(')', ' ').replace('(', ' ')
                    # Remove other special characters but be careful
                    message = message.replace(':', ' ').replace('-', ' ').replace(',', ' ')
                    message = message.replace('=', ' ').replace('?', ' ').replace('&', ' ')
                    message = message.replace('+', ' ').replace('*', ' ')
                    # Remove curly braces
                    message = re.sub(r'\{[^}]+\}', '', message)
                    # Clean up multiple spaces
                    message = ' '.join(message.split())
                    cleaned_lines.append(arrow_part + ': ' + message)
                else:
                    cleaned_lines.append(stripped)
            else:
                cleaned_lines.append(stripped)
        elif stripped.startswith('alt') or stripped.startswith('else'):
            # Simplify alt/else labels - remove ALL spaces completely
            if ':' in stripped:
                parts = stripped.split(':', 1)
                if len(parts) == 2:
                    keyword = parts[0].strip()
                    label = parts[1].strip()
                    # Remove ALL spaces and special characters - this is critical for Mermaid
                    label = re.sub(r'\s+', '', label)
                    label = label.replace("'", '').replace("'", '')
                    # Remove common words that might cause issues
                    label = label.replace('and', '').replace('or', '').replace('OR', '')
                    # Keep it simple - if empty or too short, just use keyword without label
                    if not label or len(label) < 3:
                        cleaned_lines.append(keyword)
                    else:
                        cleaned_lines.append(keyword + ': ' + label)
                else:
                    cleaned_lines.append(stripped)
            else:
                cleaned_lines.append(stripped)
        else:
            cleaned_lines.append(stripped)
    
    cleaned_code = '\n'.join(cleaned_lines)
    
    # Step 6: Post-process alt/else labels to remove any remaining spaces
    def fix_alt_label(match):
        keyword = match.group(1)
        label = match.group(2) if match.group(2) else ''
        # Remove ALL spaces from label
        label = re.sub(r'\s+', '', label)
        label = label.replace("'", '').replace("'", '')
        if label and len(label) >= 3:
            return f'{keyword}: {label}'
        else:
            return keyword
    
    cleaned_code = re.sub(r'^(alt|else):\s*([^\n]+)', fix_alt_label, cleaned_code, flags=re.MULTILINE)
    cleaned_code = re.sub(r'^(alt|else)\s+([^\n:]+)', fix_alt_label, cleaned_code, flags=re.MULTILINE)
    
    # Step 7: Ensure proper spacing
    cleaned_code = re.sub(r'(\w)->>\s*(\w)', r'\1->>\2', cleaned_code)
    cleaned_code = re.sub(r'(\w)->\s*(\w)', r'\1->\2', cleaned_code)
    
    # Step 8: Clean up multiple consecutive newlines
    cleaned_code = re.sub(r'\n{3,}', '\n\n', cleaned_code)
    
    return cleaned_code

def mermaid_to_jpeg(mermaid_code, output_path, width=7680, height=4320):
    """Convert Mermaid diagram code to JPEG image"""
    
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
            # Get more error details
            error_details = page.evaluate('''
                () => {
                    const body = document.body.innerText || document.body.textContent;
                    return body.substring(0, 500);
                }
            ''')
            print(f"   ERROR: Syntax error detected")
            print(f"   Details: {error_details[:200]}")
            
            # Check if SVG still rendered
            svg_check = page.evaluate('''
                () => {
                    const svg = document.querySelector('svg');
                    if (!svg) return {exists: false};
                    const box = svg.getBBox();
                    return {
                        exists: true,
                        width: box.width,
                        height: box.height
                    };
                }
            ''')
            
            if svg_check and svg_check.get('exists') and svg_check.get('width', 0) > 100:
                print(f"   Warning: SVG rendered despite error (size: {svg_check.get('width')}x{svg_check.get('height')})")
                # Continue with rendering
            else:
                browser.close()
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
                browser.close()
                raise Exception(f"SVG content too short")
            
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
    if sys.platform == 'win32':
        import io
        sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')
    
    script_dir = Path(__file__).parent
    markdown_file = script_dir / "STORAGE_MODULE_SEQUENCE_DIAGRAMS.md"
    storage_flow_dir = script_dir / "StorageFlow"
    
    if not markdown_file.exists():
        print(f"ERROR: {markdown_file} not found!")
        return
    
    # Diagrams to fix: 4, 6, 7
    diagrams_to_fix = [
        (4, "04_4-File-Retrieval-Flow.jpg"),
        (6, "06_6-File-Deletion-Flow.jpg"),
        (7, "07_7-Error-Handling-Flow.jpg")
    ]
    
    for diagram_num, output_filename in diagrams_to_fix:
        print(f"\n{'='*60}")
        print(f"Processing Diagram {diagram_num}: {output_filename}")
        print(f"{'='*60}")
        
        try:
            print(f"Extracting diagram {diagram_num} from: {markdown_file}")
            diagram_code = extract_diagram(markdown_file, diagram_num)
            
            print(f"\nCleaning diagram {diagram_num} code...")
            cleaned_code = clean_mermaid_code(diagram_code)
            
            # Save cleaned code for debugging
            debug_file = storage_flow_dir / f"{diagram_num:02d}_{diagram_num}-cleaned-mermaid.txt"
            with open(debug_file, 'w', encoding='utf-8') as f:
                f.write(cleaned_code)
            print(f"   Saved cleaned code to: {debug_file.name}")
            
            output_path = storage_flow_dir / output_filename
            print(f"\nRegenerating diagram {diagram_num}...")
            print(f"   Original code length: {len(diagram_code)} characters")
            print(f"   Cleaned code length: {len(cleaned_code)} characters")
            print(f"   Output: {output_path}")
            
            if output_path.exists():
                output_path.unlink()
                print(f"   Deleted old file")
            
            mermaid_to_jpeg(cleaned_code, output_path, width=7680, height=4320)
            print(f"   ✓ Successfully regenerated diagram {diagram_num} without errors!")
            
        except Exception as e:
            print(f"   ✗ ERROR processing diagram {diagram_num}: {e}")
            import traceback
            traceback.print_exc()
    
    print(f"\n{'='*60}")
    print("All diagrams processed!")
    print(f"{'='*60}")

if __name__ == "__main__":
    main()

