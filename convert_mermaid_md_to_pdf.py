import asyncio
import os
import re
from pathlib import Path

import markdown
from pyppeteer import launch


MD_TEMPLATE = """<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>{title}</title>
  <style>
    body {{
      font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
      margin: 24px;
      line-height: 1.5;
      max-width: 1200px;
    }}
    pre code {{
      background: #f5f5f5;
      padding: 8px 12px;
      border-radius: 4px;
      display: block;
      overflow-x: auto;
    }}
    .mermaid {{
      text-align: center;
      margin: 20px 0;
      background: white;
      padding: 20px;
    }}
    h1, h2, h3 {{
      margin-top: 30px;
      margin-bottom: 15px;
    }}
  </style>
  <!-- Mermaid -->
  <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
  <script>
    mermaid.initialize({{
      startOnLoad: true,
      theme: 'default',
      flowchart: {{ useMaxWidth: true }},
      sequence: {{ diagramMarginX: 50, diagramMarginY: 10 }}
    }});
  </script>
</head>
<body>
{body}
</body>
</html>
"""


def md_to_html(md_path: Path) -> str:
    text = md_path.read_text(encoding="utf-8")

    # Extract mermaid code blocks and convert them to <div class="mermaid"> blocks
    # This is necessary because Mermaid needs div.mermaid, not pre>code.mermaid
    mermaid_pattern = r'```mermaid\n(.*?)```'
    
    def replace_mermaid(match):
        mermaid_code = match.group(1).strip()
        return f'<div class="mermaid">\n{mermaid_code}\n</div>'
    
    # Replace mermaid code blocks with div.mermaid before markdown processing
    text_with_divs = re.sub(mermaid_pattern, replace_mermaid, text, flags=re.DOTALL)
    
    # Now process the markdown
    html_body = markdown.markdown(
        text_with_divs,
        extensions=["extra", "smarty", "toc"],
        output_format="html5",
    )

    return MD_TEMPLATE.format(title=md_path.name, body=html_body)


def _find_chromium_executable() -> Path | None:
    """
    Try to locate an existing Chrome/Chromium/Edge executable on Windows so that
    pyppeteer does not attempt to download its own Chromium build (which can 404).
    """
    candidate_paths = [
        # Microsoft Edge (most likely to exist on a corporate Windows machine)
        Path(r"C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe"),
        Path(r"C:\Program Files\Microsoft\Edge\Application\msedge.exe"),
        # Google Chrome
        Path(r"C:\Program Files\Google\Chrome\Application\chrome.exe"),
        Path(r"C:\Program Files (x86)\Google\Chrome\Application\chrome.exe"),
    ]

    for path in candidate_paths:
        if path.is_file():
            return path
    return None


async def html_to_pdf(html: str, output_pdf: Path) -> None:
    executable = _find_chromium_executable()
    launch_kwargs = {"args": ["--no-sandbox"]}
    if executable is not None:
        launch_kwargs["executablePath"] = str(executable)

    browser = await launch(**launch_kwargs)
    try:
        page = await browser.newPage()
        await page.setContent(html)

        # Wait for Mermaid to finish rendering all diagrams
        await page.evaluate("""
            async () => {
                // Wait for Mermaid to initialize
                await new Promise((resolve) => {
                    if (window.mermaid) {
                        resolve();
                    } else {
                        window.addEventListener('load', resolve);
                    }
                });
                
                // Wait for all mermaid diagrams to render
                const mermaidDivs = document.querySelectorAll('.mermaid');
                if (mermaidDivs.length > 0) {
                    // Check if diagrams are rendered (they should have SVG children)
                    const checkRendered = () => {
                        const allRendered = Array.from(mermaidDivs).every(div => {
                            return div.querySelector('svg') !== null;
                        });
                        return allRendered;
                    };
                    
                    // Poll until all diagrams are rendered (max 10 seconds)
                    const startTime = Date.now();
                    while (!checkRendered() && (Date.now() - startTime) < 10000) {
                        await new Promise(resolve => setTimeout(resolve, 100));
                    }
                    
                    // Extra wait to ensure rendering is complete
                    await new Promise(resolve => setTimeout(resolve, 1000));
                }
            }
        """)

        await page.pdf(
            path=str(output_pdf),
            format="A4",
            printBackground=True,
            margin={"top": "20mm", "right": "15mm", "bottom": "20mm", "left": "15mm"},
        )
    finally:
        await browser.close()


async def main():
    import argparse

    parser = argparse.ArgumentParser(
        description="Convert a Markdown file with Mermaid diagrams to PDF using headless Chromium."
    )
    parser.add_argument("input_md", help="Path to the input .md file")
    parser.add_argument(
        "output_pdf",
        nargs="?",
        help="Path to the output .pdf file (default: same name as input, .pdf)",
    )

    args = parser.parse_args()

    md_path = Path(args.input_md).resolve()
    if not md_path.is_file():
        raise SystemExit(f"Input markdown file not found: {md_path}")

    output_pdf = (
        Path(args.output_pdf).resolve()
        if args.output_pdf
        else md_path.with_suffix(".pdf")
    )

    html = md_to_html(md_path)
    await html_to_pdf(html, output_pdf)

    print(f"PDF written to: {output_pdf}")


if __name__ == "__main__":
    asyncio.run(main())


