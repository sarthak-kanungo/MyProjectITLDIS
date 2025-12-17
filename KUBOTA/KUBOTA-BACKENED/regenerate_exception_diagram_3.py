#!/usr/bin/env python3
"""
Script to regenerate diagram 3 from EXCEPTION_MODULE_SEQUENCE_DIAGRAMS.md
"""

import re
import os
import sys
from pathlib import Path
from playwright.sync_api import sync_playwright
from PIL import Image
import io

def mermaid_to_jpeg(mermaid_code, output_path, title="Diagram", width=7680, height=4320):
    """Convert Mermaid diagram code to JPEG image"""
    
    # Step 1: Fix HashMap participant - use a simpler alias without angle brackets
    cleaned_code = re.sub(
        r'participant HashMap as HashMap<String,\s*String>',
        'participant HashMap as HashMapStringString',
        mermaid_code
    )
    
    # Ensure participant aliases with spaces are properly quoted
    cleaned_code = re.sub(
        r'participant Client as Client Application',
        'participant Client as "Client Application"',
        cleaned_code
    )
    cleaned_code = re.sub(
        r'participant Validator as Spring Validator',
        'participant Validator as "Spring Validator"',
        cleaned_code
    )
    
    # Step 2: Fix the complex lambda expression - replace with simpler text
    # Match the lambda expression with <br/> tags
    cleaned_code = re.sub(
        r'forEach\(\(error\) -> \{<br/>\s*fieldName = \(\(FieldError\) error\)\.getField\(\)<br/>\s*errorMessage = error\.getDefaultMessage\(\)<br/>\s*errors\.put\(fieldName, errorMessage\)<br/>\}\)',
        'forEach: extract fieldName and errorMessage from each error',
        cleaned_code,
        flags=re.DOTALL
    )
    
    # Step 3: Handle <br/> tags - replace with spaces
    cleaned_code = cleaned_code.replace('<br/>', ' ')
    cleaned_code = cleaned_code.replace('<br>', ' ')
    
    # Step 4: Fix generic types in messages - simplify them
    cleaned_code = re.sub(r'new ApiResponse<>\(\)', 'new ApiResponse()', cleaned_code)
    cleaned_code = re.sub(r'new HashMap<String,\s*String>\(\)', 'new HashMap()', cleaned_code)
    
    # Step 5: Fix quotes in messages that might break Mermaid
    # Remove quotes from setMessage parameter
    cleaned_code = re.sub(
        r'setMessage\("Validation Failed" \+ ex\.getMessage\(\)\)',
        'setMessage(Validation Failed + ex.getMessage())',
        cleaned_code
    )
    
    # Step 6: Fix Note message - remove quotes and simplify
    cleaned_code = re.sub(
        r'Note right of HashMap: e\.g\., "email": "([^"]+)", "age": "([^"]+)"',
        r'Note right of HashMap: e.g., email: \1, age: \2',
        cleaned_code
    )
    
    # Step 7: Fix curly braces in messages - replace with parentheses
    # Match curly braces more flexibly
    cleaned_code = re.sub(
        r'HTTP 400 Response \{status: (\d+), message: ([^}]+)\}',
        r'HTTP 400 Response (status: \1, message: \2)',
        cleaned_code
    )
    
    # Step 8: Fix class annotations and method calls that might have issues
    # Simplify @ExceptionHandler annotation - handle both with and without <br/> tag
    cleaned_code = re.sub(
        r'@ExceptionHandler\(MethodArgumentNotValidException\.class\)<br/>\s*handleValidationExceptions\(ex\)',
        '@ExceptionHandler handleValidationExceptions',
        cleaned_code
    )
    cleaned_code = re.sub(
        r'@ExceptionHandler\(MethodArgumentNotValidException\.class\)\s+handleValidationExceptions\(ex\)',
        '@ExceptionHandler handleValidationExceptions',
        cleaned_code
    )
    cleaned_code = re.sub(
        r'@ExceptionHandler\(MethodArgumentNotValidException\)\s+handleValidationExceptions\(ex\)',
        '@ExceptionHandler handleValidationExceptions',
        cleaned_code
    )
    
    # Fix @Valid annotation
    cleaned_code = re.sub(r'@Valid', 'Valid', cleaned_code)
    
    # Remove @ symbol from @ExceptionHandler
    cleaned_code = re.sub(r'@ExceptionHandler', 'ExceptionHandler', cleaned_code)
    
    # Simplify method chaining with dots
    cleaned_code = re.sub(
        r'ex\.getBindingResult\(\)\.getAllErrors\(\)',
        'getAllErrors',
        cleaned_code
    )
    
    # Simplify setMessage with plus sign
    cleaned_code = re.sub(
        r'setMessage\(Validation Failed \+ ex\.getMessage\(\)\)',
        'setMessage(Validation Failed)',
        cleaned_code
    )
    
    # Simplify setStatus with nested calls
    cleaned_code = re.sub(
        r'setStatus\(HttpStatus\.BAD_REQUEST\.value\(\)\)',
        'setStatus(BAD_REQUEST)',
        cleaned_code
    )
    
    # Simplify ResponseEntity method chaining - make it shorter
    cleaned_code = re.sub(
        r'ResponseEntity\.status\(HttpStatus\.BAD_REQUEST\)\.body\(apiResponse\)',
        'ResponseEntity status body',
        cleaned_code
    )
    
    # Simplify long messages that might cause issues - do this AFTER <br/> replacement
    # First handle the original with <br/> tags
    cleaned_code = re.sub(
        r'forEach.*?extract.*?error',
        'forEach extract errors',
        cleaned_code,
        flags=re.DOTALL
    )
    
    cleaned_code = re.sub(
        r'setMessage\(Validation Failed\)',
        'setMessage ValidationFailed',
        cleaned_code
    )
    
    cleaned_code = re.sub(
        r'setStatus\(BAD_REQUEST\)',
        'setStatus BADREQUEST',
        cleaned_code
    )
    
    # Simplify HTTP response message - match more flexibly
    cleaned_code = re.sub(
        r'HTTP 400 Response.*',
        'HTTP 400 Response',
        cleaned_code
    )
    
    # Remove "with status 400" - "with" might be a reserved word
    cleaned_code = re.sub(
        r'ResponseEntity with status 400',
        'ResponseEntity Response',
        cleaned_code
    )
    
    cleaned_code = re.sub(
        r'ExceptionHandler handleValidationExceptions',
        'handleValidationExceptions',
        cleaned_code
    )
    
    # Remove "or" from messages - might cause parsing issues
    cleaned_code = re.sub(r'\s+or\s+', ' ', cleaned_code)
    
    # Simplify "POST or PUT" to just "POST PUT"
    cleaned_code = re.sub(r'POST or PUT', 'POST PUT', cleaned_code)
    
    # Fix Note with colons - replace colons with dashes
    cleaned_code = re.sub(
        r'Note right of HashMap: e\.g\., email: ([^,]+), age: ([^)]+)',
        r'Note right of HashMap: e.g., email - \1, age - \2',
        cleaned_code
    )
    
    # Step 9: Clean up any remaining HTML entities
    cleaned_code = cleaned_code.replace('&lt;', '<').replace('&gt;', '>')
    cleaned_code = cleaned_code.replace('&amp;', '&')
    
    # Step 10: Fix loop labels - replace spaces with underscores
    cleaned_code = re.sub(r'loop For Each Field Error', 'loop ForEachFieldError', cleaned_code)
    
    # Simplify alt/else labels - remove spaces
    cleaned_code = re.sub(r'alt Validation Fails', 'alt ValidationFails', cleaned_code)
    cleaned_code = re.sub(r'else Validation Passes', 'else ValidationPasses', cleaned_code)
    
    # Remove Note that spans multiple participants - might cause issues
    cleaned_code = re.sub(
        r'Note over Client,ResponseEntity: Method Argument Validation Exception Handling',
        '',
        cleaned_code
    )
    
    # Remove the Note completely - Notes can cause syntax issues
    cleaned_code = re.sub(
        r'Note right of HashMap:.*\n',
        '',
        cleaned_code
    )
    
    # Step 11: Remove comment lines and clean up
    # Remove Mermaid comment lines (they might cause issues)
    cleaned_code = re.sub(r'%%.*\n', '', cleaned_code)
    
    # Remove any leading/trailing whitespace from each line but preserve structure
    lines = cleaned_code.split('\n')
    cleaned_lines = []
    for line in lines:
        stripped = line.strip()
        if stripped:
            cleaned_lines.append(stripped)
    cleaned_code = '\n'.join(cleaned_lines)
    
    # Step 12: Simplify participant aliases - remove spaces instead of quoting
    # Quoted aliases might be causing syntax issues
    cleaned_code = re.sub(
        r'participant Client as "Client Application"',
        'participant Client as ClientApplication',
        cleaned_code
    )
    cleaned_code = re.sub(
        r'participant Validator as "Spring Validator"',
        'participant Validator as SpringValidator',
        cleaned_code
    )
    cleaned_code = re.sub(
        r'participant Client as Client Application',
        'participant Client as ClientApplication',
        cleaned_code
    )
    cleaned_code = re.sub(
        r'participant Validator as Spring Validator',
        'participant Validator as SpringValidator',
        cleaned_code
    )
    
    # Now remove quotes from message labels (but not from participant aliases)
    # Only remove quotes from lines that have arrows
    lines = cleaned_code.split('\n')
    fixed_lines = []
    for line in lines:
        if line.strip().startswith('participant'):
            # Keep participant lines as-is (they may have quotes)
            fixed_lines.append(line)
        elif '->>' in line or '->' in line:
            # Remove quotes from arrow message lines
            line = re.sub(r':\s*"([^"]+)"', r': \1', line)
            fixed_lines.append(line)
        else:
            fixed_lines.append(line)
    cleaned_code = '\n'.join(fixed_lines)
    
    # Step 13: Remove parentheses from message labels (they can cause issues)
    # But keep them in method calls where necessary
    lines = cleaned_code.split('\n')
    fixed_lines = []
    for line in lines:
        # Only process arrow lines, not Note lines
        if '->>' in line or '->' in line:
            # Replace parentheses with spaces in message part only
            if ':' in line:
                parts = line.split(':', 1)
                if len(parts) == 2:
                    message = parts[1]
                    # Replace parentheses with spaces
                    message = message.replace('(', ' ').replace(')', ' ')
                    # Replace forward slashes with space
                    message = message.replace('/', ' ')
                    # Replace colons in messages (but keep the one after arrow which we split on)
                    message = message.replace(':', ' ')
                    # Replace dashes with spaces
                    message = message.replace('-', ' ')
                    # Replace commas with spaces
                    message = message.replace(',', ' ')
                    # Replace ellipsis
                    message = message.replace('...', ' etc')
                    # Clean up multiple spaces
                    message = ' '.join(message.split())
                    line = parts[0] + ': ' + message
        fixed_lines.append(line)
    cleaned_code = '\n'.join(fixed_lines)
    
    # Step 13.5: Simplify remaining long messages AFTER all cleaning
    cleaned_code = re.sub(
        r':\s*forEach extract.*?error',
        ': forEach extract errors',
        cleaned_code
    )
    # Fix any typos from previous replacements
    cleaned_code = re.sub(r'errorssMessage', 'errors', cleaned_code)
    cleaned_code = re.sub(r'forEach extract.*?from each error', 'forEach extract errors', cleaned_code)
    cleaned_code = re.sub(
        r':\s*HTTP 400 Response.*',
        ': HTTP 400 Response',
        cleaned_code
    )
    
    # Step 14: Ensure proper spacing around arrows
    cleaned_code = re.sub(r'(\w)->>\s*(\w)', r'\1->>\2', cleaned_code)
    cleaned_code = re.sub(r'(\w)->\s*(\w)', r'\1->\2', cleaned_code)
    
    # Debug: Print cleaned code for verification
    print(f"\n   Cleaned code preview (first 500 chars):")
    print(f"   {cleaned_code[:500]}...")
    
    # Save cleaned code to file for debugging
    debug_file = Path(output_path).parent / "03_3-cleaned-mermaid.txt"
    try:
        with open(debug_file, 'w', encoding='utf-8') as f:
            f.write(cleaned_code)
        print(f"   Debug: Saved cleaned code to {debug_file.name}")
    except Exception as e:
        print(f"   Warning: Could not save debug file: {e}")
    
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
        
        # Set content and wait for mermaid to render
        page.set_content(html_content, wait_until='domcontentloaded')
        
        # Wait for mermaid to render
        try:
            # Wait for SVG to appear (with longer timeout)
            page.wait_for_selector('svg', timeout=60000)
            
            # Additional wait to ensure rendering is complete
            page.wait_for_timeout(5000)
            
            # Verify SVG is actually rendered
            svg_element = page.query_selector('svg')
            if not svg_element:
                browser.close()
                raise Exception("SVG element not found after rendering")
            
            # Check if SVG has actual content
            svg_inner_html = page.evaluate('document.querySelector("svg").innerHTML')
            if not svg_inner_html or len(svg_inner_html.strip()) < 500:
                # Try waiting a bit more
                page.wait_for_timeout(3000)
                svg_inner_html = page.evaluate('document.querySelector("svg").innerHTML')
                if not svg_inner_html or len(svg_inner_html.strip()) < 500:
                    browser.close()
                    # Try to get error message from Mermaid
                    error_msg = page.evaluate('''
                        () => {{
                            const errorDiv = document.querySelector('.error-text');
                            return errorDiv ? errorDiv.textContent : 'No error message found';
                        }}
                    ''')
                    raise Exception(f"SVG content too short ({len(svg_inner_html) if svg_inner_html else 0} chars). Error: {error_msg}")
            
        except Exception as e:
            browser.close()
            raise Exception(f"Failed to render diagram: {str(e)}")
        
        # Take screenshot with full page
        screenshot_bytes = page.screenshot(full_page=True, type='png')
        
        browser.close()
    
    # Convert PNG to JPEG with maximum quality
    img = Image.open(io.BytesIO(screenshot_bytes))
    
    # Convert RGBA to RGB if needed
    if img.mode == 'RGBA':
        rgb_img = Image.new('RGB', img.size, (255, 255, 255))
        rgb_img.paste(img, mask=img.split()[3])
        img = rgb_img
    
    # Save as JPEG with maximum quality
    img.save(output_path, 'JPEG', quality=100, optimize=False)
    
    # Verify file was created and has reasonable size
    file_size = os.path.getsize(output_path)
    if file_size < 10000:  # Less than 10KB is suspicious
        raise Exception(f"Generated image is too small ({file_size} bytes) - likely corrupted")
    
    # Verify image dimensions are reasonable
    width, height = img.size
    if width < 100 or height < 100:
        raise Exception(f"Generated image dimensions are too small ({width}x{height}) - likely corrupted")
    
    print(f"Created: {output_path} ({file_size // 1024} KB, {width}x{height})")

def main():
    """Main function to regenerate diagram 3"""
    # Set UTF-8 encoding for stdout
    if sys.platform == 'win32':
        import io
        sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')
    
    script_dir = Path(__file__).parent
    markdown_file = script_dir / "EXCEPTION_MODULE_SEQUENCE_DIAGRAMS.md"
    output_dir = script_dir / "ExceptionFlow"
    output_path = output_dir / "03_3-Validation-Exception-Handling-Flow.jpg"
    
    if not markdown_file.exists():
        print(f"ERROR: {markdown_file} not found!")
        return
    
    if not output_dir.exists():
        output_dir.mkdir(parents=True, exist_ok=True)
        print(f"Created output directory: {output_dir}")
    
    print(f"Reading markdown file: {markdown_file}")
    
    # Read the markdown file
    with open(markdown_file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Extract mermaid diagrams
    pattern = r'```mermaid\n(.*?)\n```'
    diagrams = re.findall(pattern, content, re.DOTALL)
    
    if len(diagrams) < 3:
        print("ERROR: Diagram 3 not found in the file!")
        return
    
    # Get diagram 3 (index 2)
    diagram_code = diagrams[2]
    
    print(f"\nRegenerating diagram 3: Validation Exception Handling Flow")
    print(f"   Original code length: {len(diagram_code)} characters")
    print(f"   Output: {output_path}")
    
    try:
        # Delete old file if it exists
        if output_path.exists():
            output_path.unlink()
            print(f"   Deleted old file")
        
        mermaid_to_jpeg(diagram_code, output_path, "Validation Exception Handling Flow", width=7680, height=4320)
        print(f"   ✓ Successfully regenerated")
    except Exception as e:
        print(f"   ✗ ERROR regenerating diagram: {e}")
        import traceback
        traceback.print_exc()
        
        # Try to save the original code for debugging
        try:
            debug_file = output_dir / "03_3-debug-mermaid.txt"
            with open(debug_file, 'w', encoding='utf-8') as f:
                f.write(diagram_code)
            print(f"   Debug: Saved original code to {debug_file}")
        except:
            pass

if __name__ == "__main__":
    main()
