from PIL import Image
import os

Image.MAX_IMAGE_PIXELS = None

png_file = "ACCPAC-Module-UML-Class-Diagram.png"
jpeg_file = "ACCPAC-Module-UML-Class-Diagram.jpg"

# Verify PNG
png = Image.open(png_file)
png_size = os.path.getsize(png_file)

print("=== Full Image Extracted ===")
print(f"PNG Dimensions: {png.size[0]}x{png.size[1]} pixels")
print(f"Resolution: {png.size[0] * png.size[1] / 1000000:.1f} MP")
print(f"PNG File Size: {png_size // 1024} KB")
print("")

# Convert to JPEG
jpg = png.convert('RGB')
jpg.save(jpeg_file, 'JPEG', quality=95, optimize=True)
jpeg_size = os.path.getsize(jpeg_file)

print(f"JPEG File Size: {jpeg_size // 1024} KB")
print("")
print("Full image extracted successfully!")
print("Location: C:\\MyProjectITLDIS\\KUBOTA\\KUBOTA-BACKENED\\")

