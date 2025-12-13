/**
 * 
 */
package com.common;

/**
 * @aman
 */

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Hex;

public class DigitalSignatureUtil {

    public static String getCurrentTimestamp() {
        return new SimpleDateFormat("ddMMyyyyHH:mm:ss").format(new Date());
    }

    public static String generateChecksum(String apiKey, String ts) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest((apiKey + ts).getBytes("UTF-8"));
        return Hex.encodeHexString(hash);
    }
}

