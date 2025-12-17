"""
Convert a Markdown file (with Mermaid diagrams) to PDF using Python.

Usage (from repository root):

    python convert_markdown_mermaid_to_pdf.py \
        KUBOTA/KUBOTA-BACKENED/COMMON_MODULE_SEQUENCE_DIAGRAMS.md

Requirements (install once):

    pip install markdown playwright
    python -m playwright install chromium
"""

import asyncio
import sys
from pathlib import Path

from markdown import markdown  # type: ignore
from playwright.async_api import async_playwright  # type: ignore


HTML_TEMPLATE = """<!doctype html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>{title}</title>
    <style>
      body {{
        font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
        margin: 24px;
        line-height: 1.5;
      }}
      pre {{
        background: #f5f5f5;
        padding: 8px 12px;
        overflow-x: auto;
      }}
      code {{
        font-family: "Fira Code", Consolas, "Courier New", monospace;
      }}
    </style>
    <!-- Mermaid script from CDN -->
    <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
    <script>
      mermaid.initialize({{ startOnLoad: true }});
    </script>
  </head>
  <body>
    {body}
  </body>
</html>
"""


async def render_pdf(markdown_path: Path) -> None:
    if not markdown_path.is_file():
        raise SystemExit(f"Markdown file not found: {markdown_path}")

    md_text = markdown_path.read_text(encoding="utf-8")

    # Convert markdown (including fenced code blocks) to HTML
    html_body = markdown(md_text, extensions=["fenced_code", "tables"])

    title = markdown_path.stem
    full_html = HTML_TEMPLATE.format(title=title, body=html_body)

    html_path = markdown_path.with_suffix(".html")
    pdf_path = markdown_path.with_suffix(".pdf")

    html_path.write_text(full_html, encoding="utf-8")

    async with async_playwright() as p:
        browser = await p.chromium.launch()
        page = await browser.new_page()

        # Load the local HTML file so Mermaid JS can run
        await page.goto(html_path.resolve().as_uri(), wait_until="networkidle")
        # Give Mermaid some time to render diagrams
        await page.wait_for_timeout(2000)

        await page.pdf(path=str(pdf_path), format="A4", print_background=True)
        await browser.close()

    print(f"PDF written to: {pdf_path}")


def main() -> None:
    if len(sys.argv) != 2:
        print(
            "Usage: python convert_markdown_mermaid_to_pdf.py "
            "KUBOTA/KUBOTA-BACKENED/COMMON_MODULE_SEQUENCE_DIAGRAMS.md"
        )
        raise SystemExit(1)

    md_path = Path(sys.argv[1])
    asyncio.run(render_pdf(md_path))


if __name__ == "__main__":
    main()


