from PIL import Image
import os

Image.MAX_IMAGE_PIXELS = None

png_file = "ACCPAC-Module-UML-Class-Diagram.png"
jpeg_file = "ACCPAC-Module-UML-Class-Diagram.jpg"

png = Image.open(png_file)
jpg = png.convert('RGB')
jpg.save(jpeg_file, 'JPEG', quality=95, optimize=True)

png_size = os.path.getsize(png_file)
jpeg_size = os.path.getsize(jpeg_file)

print(f"PNG: {png_size // 1024} KB")
print(f"JPEG: {jpeg_size // 1024} KB")

