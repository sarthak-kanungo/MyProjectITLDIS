package com.i4o.dms.itldis.common.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author suraj.gaur
 * @implNote implemented for custom image file filtration with respect to image size limit and 
 * compressing the images. 
 */
public class ImageCompressNLimiterInterceptor implements HandlerInterceptor {
	
	@Value("${image.maxFileSize}")
    private long maxFileSize; 
	
	@Value("${image.compressLimitSize}")
    private long compressLimitSize; 
	
	@Value("${image.compressionQualityVal}")
    private float compressionQualityVal;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//Calling validation here because we want to intercept request before implementing business logic
		if(request instanceof MultipartHttpServletRequest) {
			return validateImage(request, response);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) 
			throws Exception {
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
	private boolean validateImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MultiValueMap<String, MultipartFile> multiFileMap = ((MultipartHttpServletRequest) request).getMultiFileMap();

		for (String str : multiFileMap.keySet()) {
			List<MultipartFile> multipartList = multiFileMap.get(str);

			for (int i = 0; i < multipartList.size(); i++) {
				MultipartFile file = multipartList.get(i);

				if (isImgageUnderSize(file)) {
					// Image file size exceeds the allowed limit
					response.sendError(403, "Image file size exceeds the allowed limit.");
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

					return false;
				} else if (isEligibleForCompression(file)) {
//						System.out.println("Media size before compression: " + file.getSize());

					try {
						// Compressing the file
						MultipartFile compressedImage = createCompressedMultipartFile(file);

						// Injecting the compressed file into request container to the same file stored
						// on particular index
						((MultipartHttpServletRequest) request).getMultiFileMap().get(str).set(i, compressedImage);
					} catch (IOException e) {
						response.sendError(500, "There is some error while compressing the file");
						response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						e.printStackTrace();
						return false;
					}
				}
			}
		}

		return true;
	}

	private MultipartFile createCompressedMultipartFile(MultipartFile file) throws IOException {
		byte[] compressedByteArr = compressImage(file.getBytes());
		
		//Creating MutipartFile using our CustomMultipartFile class. 
		MultipartFile compressedImage = new CustomMultipartFile(file.getName(), 
				file.getOriginalFilename(), file.getContentType(), compressedByteArr);
		
		return compressedImage;
	}
	
	private byte[] compressImage(byte[] imageData) throws IOException {
		float compressionQuality = compressionQualityVal;
		
		ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
		BufferedImage originalImage = ImageIO.read(bais);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		//filling ByteArrayOutputStream with compressed image bytes
		compressBufferedImage(originalImage, compressionQuality, baos);
		
		//closing ByteArrayInputStream.
		bais.close();

		return baos.toByteArray();
	}
	
	public void compressBufferedImage(BufferedImage image, float compressionQuality, ByteArrayOutputStream output) {
        try {
            // Get all available writers
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");

            if (writers.hasNext()) {
                ImageWriter writer = writers.next();

                // Set compression parameters
                ImageWriteParam writeParam = writer.getDefaultWriteParam();
                writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                writeParam.setCompressionQuality(compressionQuality);
                
                ImageOutputStream imageOutput = ImageIO.createImageOutputStream(output);
                writer.setOutput(imageOutput);

                // Write the image with compression
                writer.write(null, new IIOImage(image, null, null), writeParam);

                // Close streams
                imageOutput.close();
                writer.dispose();

//                System.out.println("Image compressed successfully.");

            } else {
                System.err.println("No suitable image writer found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private boolean isImgageUnderSize(MultipartFile file) {
		return file != null && isImageFile(file) && file.getSize() > maxFileSize;
	}
	
	private boolean isEligibleForCompression(MultipartFile file) {
		return file != null && isImageFile(file) && file.getSize() < maxFileSize && file.getSize() > compressLimitSize;
	}
	
	private boolean isImageFile(MultipartFile file) {
		return Objects.requireNonNull(file.getContentType()).startsWith("image");
	}

}
