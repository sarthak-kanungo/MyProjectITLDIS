package com.i4o.dms.itldis.common.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

public class ImageResizer {
	 public static byte[] resize(File icon) {
		    byte[] imageInByte = null;
	        try {
	            BufferedImage originalImage = ImageIO.read(icon);

	            int maxWidth = 450;
                int maxHeight = 600;
               
                int img_width = originalImage.getWidth();
                int img_height = originalImage.getHeight();
               
                if(img_width > maxWidth || img_height > maxHeight) {
                	
            	   float factx = (float) img_width / maxWidth;
                   float facty = (float) img_height / maxHeight;
                   float fact = (factx>facty) ? factx : facty;
                   img_width = (int) ((int) img_width / fact);
                   img_height = (int) ((int) img_height / fact);
                   

	   	            originalImage= Scalr.resize(originalImage, Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, img_width, img_height);
	   	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	   	            ImageIO.write(originalImage, "jpg", baos);
	   	            baos.flush();
	   	            imageInByte = baos.toByteArray();
	   	            baos.close();
   	            
                }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
            return imageInByte;
	    }
}
