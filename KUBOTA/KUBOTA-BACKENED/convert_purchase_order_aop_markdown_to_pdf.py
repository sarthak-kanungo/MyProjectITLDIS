#!/usr/bin/env python3
"""
Script to convert PURCHASE_ORDER_AOP_SEQUENCE_DIAGRAMS.md (with Mermaid diagrams) to PDF
"""

from pathlib import Path
from convert_markdown_to_pdf import markdown_to_pdf


def main():
    script_dir = Path(__file__).parent
    markdown_file = script_dir / "PURCHASE_ORDER_AOP_SEQUENCE_DIAGRAMS.md"
    output_pdf = script_dir / "PURCHASE_ORDER_AOP_SEQUENCE_DIAGRAMS.pdf"

    if not markdown_file.exists():
        print(f"ERROR: {markdown_file} not found!")
        return

    print(f"{'='*60}")
    print("Converting PURCHASE_ORDER_AOP_SEQUENCE_DIAGRAMS.md to PDF")
    print(f"{'='*60}\n")

    try:
        markdown_to_pdf(markdown_file, output_pdf)
        print(f"\n{'='*60}")
        print("Conversion complete!")
        print(f"PDF saved to: {output_pdf}")
        print(f"{'='*60}")
    except Exception as e:
        print(f"\n[ERROR] {e}")
        import traceback
        traceback.print_exc()


if __name__ == "__main__":
    main()


