package com.sun.image.codec.jpeg;

/**
 * Compatibility class for deprecated com.sun.image.codec.jpeg.JPEGEncodeParam
 */
public class JPEGEncodeParam {
    
    public static final int BINARY_FILE_TYPE = 2;
    
    public void setQuality(float quality, boolean forceBaseline) {
        // Quality setting is handled by ImageIO defaults
    }
}
