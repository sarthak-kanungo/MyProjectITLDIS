#!/usr/bin/env python3
"""Remove stereotype lines from warranty diagram"""

import re

# Read the file
with open('WARRANTY-MODULE-UML-DIAGRAM.md', 'r', encoding='utf-8') as f:
    content = f.read()

# Find the mermaid diagram section
pattern = r'(```mermaid\n)(.*?)(\n```)'
match = re.search(pattern, content, re.DOTALL)

if match:
    diagram_start = match.group(1)
    diagram_content = match.group(2)
    diagram_end = match.group(3)
    
    # Remove all lines that contain only <<...>>
    lines = diagram_content.split('\n')
    fixed_lines = []
    
    for line in lines:
        stripped = line.strip()
        # Skip lines that are just stereotypes like <<RestController>>, <<interface>>, etc.
        if stripped.startswith('<<') and stripped.endswith('>>'):
            continue
        fixed_lines.append(line)
    
    fixed_diagram = '\n'.join(fixed_lines)
    
    # Replace in content
    new_content = content[:match.start()] + diagram_start + fixed_diagram + diagram_end + content[match.end():]
    
    # Write back
    with open('WARRANTY-MODULE-UML-DIAGRAM.md', 'w', encoding='utf-8') as f:
        f.write(new_content)
    
    print(f"Removed stereotypes. Original: {len(diagram_content)} chars, Fixed: {len(fixed_diagram)} chars")
    print("Fixed diagram saved!")
else:
    print("No mermaid diagram found!")

