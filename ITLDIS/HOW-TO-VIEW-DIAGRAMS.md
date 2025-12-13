# How to View ITLDIS Sequence Diagrams

The sequence diagrams in `SEQUENCE-DIAGRAMS.md` are written in **Mermaid** format. Here are several ways to view them:

## Method 1: GitHub/GitLab (Recommended)

If your repository is on GitHub or GitLab, the diagrams will automatically render when you view the `.md` file:

1. **Push the file to GitHub/GitLab**
2. **Open the file** in the web interface
3. The diagrams will render automatically

**GitHub**: https://github.com/your-repo/ITLDIS/blob/main/SEQUENCE-DIAGRAMS.md
**GitLab**: https://gitlab.com/your-repo/ITLDIS/-/blob/main/SEQUENCE-DIAGRAMS.md

## Method 2: VS Code with Mermaid Extension

1. **Install Mermaid Extension**:
   - Open VS Code
   - Go to Extensions (Ctrl+Shift+X)
   - Search for "Mermaid Preview" or "Markdown Preview Mermaid Support"
   - Install one of these extensions:
     - `bierner.markdown-mermaid`
     - `bierner.markdown-preview-mermaid-support`
     - `bpruitt-goddard.mermaid-markdown-syntax-highlighting`

2. **View the Diagrams**:
   - Open `SEQUENCE-DIAGRAMS.md` in VS Code
   - Press `Ctrl+Shift+V` (or `Cmd+Shift+V` on Mac) to open Markdown Preview
   - The diagrams will render in the preview pane

## Method 3: Online Mermaid Editor

1. **Visit Mermaid Live Editor**:
   - Go to: https://mermaid.live/
   - Or: https://mermaid-js.github.io/mermaid-live-editor/

2. **Copy and Paste**:
   - Open `SEQUENCE-DIAGRAMS.md`
   - Copy a diagram code block (the content between ```mermaid and ```)
   - Paste it into the Mermaid Live Editor
   - The diagram will render instantly
   - You can export as PNG, SVG, or PDF

## Method 4: Typora (Markdown Editor)

1. **Install Typora**:
   - Download from: https://typora.io/
   - Typora has built-in Mermaid support

2. **Open the File**:
   - Open `SEQUENCE-DIAGRAMS.md` in Typora
   - Diagrams will render automatically

## Method 5: Mark Text (Markdown Editor)

1. **Install Mark Text**:
   - Download from: https://marktext.app/
   - Has built-in Mermaid support

2. **Open the File**:
   - Open `SEQUENCE-DIAGRAMS.md` in Mark Text
   - Diagrams will render automatically

## Method 6: Obsidian (Note-taking App)

1. **Install Obsidian**:
   - Download from: https://obsidian.md/
   - Enable Mermaid plugin (usually enabled by default)

2. **Open the File**:
   - Create/open a vault
   - Open `SEQUENCE-DIAGRAMS.md`
   - Diagrams will render in preview mode

## Method 7: Convert to Images

### Using Mermaid CLI

1. **Install Mermaid CLI**:
   ```bash
   npm install -g @mermaid-js/mermaid-cli
   ```

2. **Convert to PNG**:
   ```bash
   mmdc -i SEQUENCE-DIAGRAMS.md -o sequence-diagrams.png
   ```

3. **Convert to SVG**:
   ```bash
   mmdc -i SEQUENCE-DIAGRAMS.md -o sequence-diagrams.svg
   ```

### Using Online Tools

1. **Mermaid.ink**:
   - Visit: https://mermaid.ink/
   - Paste your Mermaid code
   - Get a direct image URL

2. **Kroki**:
   - Visit: https://kroki.io/
   - Select Mermaid
   - Paste code and generate image

## Method 8: IntelliJ IDEA / WebStorm

1. **Install Mermaid Plugin**:
   - Go to File → Settings → Plugins
   - Search for "Mermaid"
   - Install "Mermaid" plugin

2. **View Diagrams**:
   - Open `SEQUENCE-DIAGRAMS.md`
   - Right-click on a diagram → "Mermaid Preview"
   - Or use the preview panel

## Method 9: Browser Extensions

### Chrome/Edge Extensions:
- **Mermaid Diagrams**: https://chrome.google.com/webstore
- Search for "Mermaid Diagrams" extension
- Allows viewing `.md` files with Mermaid diagrams

## Method 10: Quick View Script

Create a simple HTML file to view all diagrams:

```html
<!DOCTYPE html>
<html>
<head>
    <title>ITLDIS Sequence Diagrams</title>
    <script src="https://cdn.jsdelivr.net/npm/mermaid/dist/mermaid.min.js"></script>
    <script>
        mermaid.initialize({ startOnLoad: true });
    </script>
</head>
<body>
    <h1>ITLDIS Sequence Diagrams</h1>
    <!-- Copy diagram code blocks here -->
    <div class="mermaid">
    sequenceDiagram
        participant User
        participant Browser
        ...
    </div>
</body>
</html>
```

## Troubleshooting

### Diagrams Not Rendering?

1. **Check Mermaid Syntax**:
   - Ensure code blocks are marked as `mermaid`
   - No extra spaces or characters

2. **Update Extension**:
   - Make sure your Mermaid extension is up to date

3. **Try Online Editor**:
   - Use https://mermaid.live/ to verify syntax

### Export Issues?

1. **Use Mermaid CLI** for batch conversion
2. **Use Online Tools** for single diagrams
3. **Screenshot** the rendered diagram if needed

## Recommended Approach

**For Quick Viewing**: Use **Mermaid Live Editor** (https://mermaid.live/)
**For Development**: Use **VS Code with Mermaid Extension**
**For Documentation**: Push to **GitHub/GitLab** for automatic rendering
**For Presentations**: Export as **PNG/SVG** using Mermaid CLI or online tools

## Quick Start (Easiest Method)

1. **Open**: https://mermaid.live/
2. **Copy** any diagram code block from `SEQUENCE-DIAGRAMS.md`
3. **Paste** into the editor
4. **View** the rendered diagram instantly!

---

**Note**: All diagrams in `SEQUENCE-DIAGRAMS.md` are in standard Mermaid format and should work with any Mermaid-compatible viewer.

