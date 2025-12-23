#!/usr/bin/env python3
"""
Script to convert PNG to high-quality JPEG
"""

from PIL import Image
import os
from pathlib import Path

# Increase PIL's decompression bomb limit
Image.MAX_IMAGE_PIXELS = None

def convert_png_to_jpeg(png_path, jpeg_path=None, quality=95):
    """Convert PNG to high-quality JPEG"""
    if jpeg_path is None:
        jpeg_path = str(Path(png_path).with_suffix('.jpg'))
    
    print(f"Opening PNG: {png_path}")
    img = Image.open(png_path)
    
    # Get original size
    width, height = img.size
    print(f"Original size: {width}x{height} pixels")
    
    # Convert to RGB if needed (JPEG doesn't support transparency)
    if img.mode in ('RGBA', 'LA', 'P'):
        print("Converting to RGB (removing alpha channel)...")
        # Create white background
        rgb_img = Image.new('RGB', img.size, (255, 255, 255))
        if img.mode == 'P':
            img = img.convert('RGBA')
        rgb_img.paste(img, mask=img.split()[-1] if img.mode in ('RGBA', 'LA') else None)
        img = rgb_img
    elif img.mode != 'RGB':
        img = img.convert('RGB')
    
    print(f"Saving as JPEG with {quality}% quality...")
    img.save(jpeg_path, 'JPEG', quality=quality, optimize=True)
    
    # Get file sizes
    png_size = os.path.getsize(png_path)
    jpeg_size = os.path.getsize(jpeg_path)
    
    print(f"\nConversion complete!")
    print(f"  PNG size:  {png_size // 1024} KB")
    print(f"  JPEG size: {jpeg_size // 1024} KB")
    print(f"  Compression ratio: {png_size / jpeg_size:.2f}x")
    print(f"  Output: {jpeg_path}")

if __name__ == "__main__":
    script_dir = Path(__file__).parent
    png_file = script_dir / "Spares-Module-UML-Class-Diagram.png"
    jpeg_file = script_dir / "Spares-Module-UML-Class-Diagram.jpg"
    
    if not png_file.exists():
        print(f"ERROR: {png_file} not found!")
        exit(1)
    
    convert_png_to_jpeg(str(png_file), str(jpeg_file), quality=95)

