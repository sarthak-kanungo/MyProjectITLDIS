package com.sun.image.codec.jpeg;

import java.io.OutputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Compatibility class for deprecated com.sun.image.codec.jpeg.JPEGCodec
 * Uses ImageIO API instead
 */
public class JPEGCodec {
    
    public static JPEGImageEncoder createJPEGEncoder(OutputStream out) {
        return new JPEGImageEncoderImpl(out);
    }
    
    private static class JPEGImageEncoderImpl implements JPEGImageEncoder {
        private final OutputStream out;
        private JPEGEncodeParam param;
        
        public JPEGImageEncoderImpl(OutputStream out) {
            this.out = out;
        }
        
        @Override
        public JPEGEncodeParam getDefaultJPEGEncodeParam(BufferedImage image) {
            return new JPEGEncodeParam();
        }
        
        @Override
        public void setJPEGEncodeParam(JPEGEncodeParam param) {
            this.param = param;
        }
        
        @Override
        public void encode(BufferedImage image) throws IOException {
            ImageIO.write(image, "jpg", out);
        }
    }
}
