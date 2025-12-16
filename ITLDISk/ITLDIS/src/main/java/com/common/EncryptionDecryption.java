package com.common;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.util.Base64;
/**
 *<b>Purpose</b> - encrypted and decrypted and actual String .
 * @author avinash.pandey
 * @version 1.0
 */
public class EncryptionDecryption {

    private static String keyStr = "&%#@?,:*";
    private static String algorithm = "DES";
    private static byte[] keyValue = new byte[]{0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};// your key
    private static String ciphertransformations = "DES/CBC/PKCS5Padding";// your Cipher transformations

    // Performs Encryption
    public static String encrypt(String plainText) throws Exception {
        Key key = generateKey();
        Cipher chiper = Cipher.getInstance(ciphertransformations);
        chiper.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(keyValue));
        byte[] encVal = chiper.doFinal(plainText.getBytes());
    //    String encryptedValue = new BASE64Encoder().encode(encVal);
        String encryptedValue = Base64.getEncoder().encodeToString(encVal);
        return encryptedValue;
    }

    // Performs decryption
    public static String decrypt(String encryptedText) throws Exception {
        // generate key
        Key key = generateKey();
        Cipher chiper = Cipher.getInstance(ciphertransformations);
        chiper.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(keyValue));
//        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedText);
        byte[] decordedValue = Base64.getDecoder().decode(encryptedText);
        byte[] decValue = chiper.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

//generateKey() is used to generate a secret key for AES algorithm
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyStr.getBytes(), algorithm);
        return key;
    }

    // performs encryption & decryption
    public static void main(String[] args) throws Exception {


        String plainText = "Maharashtra@123";
        String encryptedText = EncryptionDecryption.encrypt(plainText);
        String decryptedText = EncryptionDecryption.decrypt("H8ivJiUrbFXrFFHDwuczYA==");
        System.out.println("Plain Text : " + plainText);
        System.out.println("Encrypted Text : " + encryptedText);
        System.out.println("Result : " + encryptedText.equalsIgnoreCase("REq5w2f991efvNjP8tldug=="));
        System.out.println("Decrypted Text 11: " + decryptedText);
    }
}
