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

print("=== FULL IMAGE EXTRACTED ===")
print(f"PNG Dimensions: {png.size[0]}x{png.size[1]} pixels")
print(f"Resolution: {png.size[0] * png.size[1] / 1000000:.1f} MP")
print(f"PNG Size: {png_size // 1024} KB")
print(f"JPEG Size: {jpeg_size // 1024} KB")
print("")
print("Full complete diagram captured!")
print("Location: C:\\MyProjectITLDIS\\KUBOTA\\KUBOTA-BACKENED\\")

