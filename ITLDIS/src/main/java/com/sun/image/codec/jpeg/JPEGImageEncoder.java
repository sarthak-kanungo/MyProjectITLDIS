package com.sun.image.codec.jpeg;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Compatibility interface for deprecated com.sun.image.codec.jpeg.JPEGImageEncoder
 */
public interface JPEGImageEncoder {
    JPEGEncodeParam getDefaultJPEGEncodeParam(BufferedImage image);
    void setJPEGEncodeParam(JPEGEncodeParam param);
    void encode(BufferedImage image) throws IOException;
}
