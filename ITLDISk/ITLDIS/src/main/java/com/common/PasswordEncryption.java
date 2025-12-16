/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

/**
 *
 * @author manish.kishore
 */
public class PasswordEncryption {
    
    
     /**************************************************************************************
     * Method used to Encrypt a Input String.
     * <br><br><b>Steps:</b>
     * <br>1. Create Byte Array of the input String using 'UTF8'.
     * <br>2. return the Encrypted String using sun.misc.BASE64Encoder.
     * <br><br><b>Author</b> - Mina
     * @param input Any String
     * @return String 
     * @throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException   
     ****************************************************************************************/

    public static String encrypt(String input) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException
    {
        input = input.trim();
        //$1
        byte[] inputBytes = input.getBytes("UTF8");
       // System.out.println("inputBytes" + inputBytes);
        //$2
        return new sun.misc.BASE64Encoder().encode(inputBytes);
    }


     /**************************************************************************************
     * Method used to Decrypt an Encrypted String.
     * <br><br><b>Steps:</b>
     * <br>1. Decode Input Byte Array using sun.misc.BASE64Encoder.
     * <br>2. Create a string from Byte Array using 'UTF8'.
     * <br>3. return the Decrypted String.
     * <br><br><b>Author</b> - Mina
     * @param encryptionBytes Byte Array of Encrypted String
     * @return String 
     * @throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, IOException
     ****************************************************************************************/

    public static String decrypt(byte[] encryptionBytes) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, IOException
    {
        //$1
        byte[] recoveredBytes = new sun.misc.BASE64Decoder().decodeBuffer(new String(encryptionBytes));
        //$2
        String recovered = new String(recoveredBytes, "UTF8");
        //System.out.println("recovered" + recovered);
        //$3
        return recovered;
    }
public static String decryptStr(String str) {
        String decr_str = "";

        if (str != null) {
            try {
                byte[] passBytes = str.getBytes();
             
                decr_str = decrypt(passBytes);
            } catch (Exception ae) {
                ae.printStackTrace();
            }
        }
       return decr_str;
    }

}
