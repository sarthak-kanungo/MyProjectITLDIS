# VSCode Extensions for Converting Markdown (Mermaid) to JPEG

## Recommended Extensions

### 1. **Markdown Preview Mermaid Support** + **Markdown PDF**

#### Extension 1: Markdown Preview Mermaid Support
- **Extension ID**: `bierner.markdown-mermaid`
- **Publisher**: Matt Bierner
- **What it does**: Adds Mermaid diagram rendering to VSCode's built-in markdown preview

#### Extension 2: Markdown PDF
- **Extension ID**: `yzane.markdown-pdf`
- **Publisher**: yzane
- **What it does**: Converts markdown files to PDF, HTML, PNG, or JPEG

#### Installation:
```bash
code --install-extension bierner.markdown-mermaid
code --install-extension yzane.markdown-pdf
```

#### Usage:
1. Open your markdown file with Mermaid diagram
2. Right-click in the editor
3. Select "Markdown PDF: Export (pdf)" or "Markdown PDF: Export (png/jpeg)"
4. Choose output format (JPEG)

---

### 2. **Mermaid Preview** + **Export**

#### Extension: Mermaid Preview
- **Extension ID**: `vstirbu.vscode-mermaid-preview`
- **Publisher**: Vlad Stirbu
- **What it does**: Preview and export Mermaid diagrams

#### Installation:
```bash
code --install-extension vstirbu.vscode-mermaid-preview
```

#### Usage:
1. Open markdown file
2. Open command palette (Ctrl+Shift+P)
3. Type "Mermaid: Export Diagram"
4. Select format (PNG/JPEG/SVG)

---

### 3. **Markdown Preview Enhanced**

#### Extension: Markdown Preview Enhanced
- **Extension ID**: `shd101wyy.markdown-preview-enhanced`
- **Publisher**: Yiyi Wang
- **What it does**: Advanced markdown preview with Mermaid support and export options

#### Installation:
```bash
code --install-extension shd101wyy.markdown-preview-enhanced
```

#### Usage:
1. Open markdown file
2. Right-click → "Markdown Preview Enhanced: Open Preview to the Side"
3. In preview, right-click → "Chrome (Puppeteer)" → "Export (PNG/JPEG)"

---

### 4. **Mermaid Editor**

#### Extension: Mermaid Editor
- **Extension ID**: `tomoyukim.vscode-mermaid-editor`
- **Publisher**: Tomoyuki Matsuda
- **What it does**: Visual Mermaid diagram editor with export

#### Installation:
```bash
code --install-extension tomoyukim.vscode-mermaid-editor
```

#### Usage:
1. Open `.mmd` file or markdown with mermaid code block
2. Right-click → "Mermaid: Export as Image"
3. Choose format (PNG/JPEG/SVG)

---

## Best Option: Markdown PDF Extension

### Complete Setup Guide

#### Step 1: Install Extension
```bash
code --install-extension yzane.markdown-pdf
code --install-extension bierner.markdown-mermaid
```

#### Step 2: Configure Settings

Add to your VSCode `settings.json`:

```json
{
  "markdown-pdf.mermaidServer": "https://mermaid.ink",
  "markdown-pdf.type": "jpeg",
  "markdown-pdf.quality": 100,
  "markdown-pdf.scale": 2,
  "markdown-pdf.margin.top": "1cm",
  "markdown-pdf.margin.bottom": "1cm",
  "markdown-pdf.margin.left": "1cm",
  "markdown-pdf.margin.right": "1cm"
}
```

#### Step 3: Export to JPEG

1. Open your markdown file (e.g., `ACCPAC-MODULE-UML-DIAGRAM.md`)
2. Right-click in the editor
3. Select **"Markdown PDF: Export (jpeg)"**
4. Choose save location
5. Done! JPEG file is created

---

## Alternative: Using Command Palette

### Method 1: Markdown PDF
1. Open markdown file
2. Press `Ctrl+Shift+P` (or `Cmd+Shift+P` on Mac)
3. Type: `Markdown PDF: Export (jpeg)`
4. Press Enter
5. Select save location

### Method 2: Mermaid Preview
1. Open markdown file
2. Press `Ctrl+Shift+P`
3. Type: `Mermaid: Export Diagram`
4. Select format: JPEG
5. Save file

---

## Comparison of Extensions

| Extension | Mermaid Support | Export Format | Ease of Use | Quality |
|-----------|----------------|----------------|-------------|---------|
| Markdown PDF | ✅ Yes | PDF, PNG, JPEG | ⭐⭐⭐⭐⭐ | High |
| Mermaid Preview | ✅ Yes | PNG, JPEG, SVG | ⭐⭐⭐⭐ | High |
| Markdown Preview Enhanced | ✅ Yes | PDF, PNG, JPEG | ⭐⭐⭐ | Very High |
| Mermaid Editor | ✅ Yes | PNG, JPEG, SVG | ⭐⭐⭐⭐ | Medium |

---

## Recommended Workflow

### For Quick Export:
**Use: Markdown PDF Extension**
- Fastest method
- One-click export
- Good quality output

### For High Quality:
**Use: Markdown Preview Enhanced**
- Best rendering quality
- More export options
- Requires Puppeteer setup

### For Diagram Editing:
**Use: Mermaid Editor**
- Visual editor
- Real-time preview
- Export capabilities

---

## Installation Commands (Copy-Paste)

```bash
# Option 1: Markdown PDF (Recommended)
code --install-extension yzane.markdown-pdf
code --install-extension bierner.markdown-mermaid

# Option 2: Mermaid Preview
code --install-extension vstirbu.vscode-mermaid-preview

# Option 3: Markdown Preview Enhanced
code --install-extension shd101wyy.markdown-preview-enhanced

# Option 4: Mermaid Editor
code --install-extension tomoyukim.vscode-mermaid-editor
```

---

## Troubleshooting

### Issue: Mermaid diagrams not rendering
**Solution**: Install `bierner.markdown-mermaid` extension

### Issue: Export quality is low
**Solution**: Increase scale in settings:
```json
{
  "markdown-pdf.scale": 2
}
```

### Issue: Export fails
**Solution**: 
- Check internet connection (Mermaid CDN)
- Try different export format (PNG first, then convert)
- Use local Mermaid server

### Issue: Diagrams cut off
**Solution**: Adjust margins in settings:
```json
{
  "markdown-pdf.margin.top": "2cm",
  "markdown-pdf.margin.bottom": "2cm"
}
```

---

## Quick Start Example

1. **Install**:
   ```bash
   code --install-extension yzane.markdown-pdf
   code --install-extension bierner.markdown-mermaid
   ```

2. **Open** your markdown file: `ACCPAC-MODULE-UML-DIAGRAM.md`

3. **Right-click** → **"Markdown PDF: Export (jpeg)"**

4. **Save** the JPEG file

5. **Done!** ✅

---

## Additional Tips

- **For high resolution**: Use PNG export, then convert to JPEG manually
- **For batch processing**: Use Python script (more control)
- **For quick preview**: Use Mermaid Preview extension
- **For editing**: Use Mermaid Editor extension

---

## References

- [Markdown PDF Extension](https://marketplace.visualstudio.com/items?itemName=yzane.markdown-pdf)
- [Mermaid Preview Extension](https://marketplace.visualstudio.com/items?itemName=vstirbu.vscode-mermaid-preview)
- [Markdown Preview Enhanced](https://marketplace.visualstudio.com/items?itemName=shd101wyy.markdown-preview-enhanced)

