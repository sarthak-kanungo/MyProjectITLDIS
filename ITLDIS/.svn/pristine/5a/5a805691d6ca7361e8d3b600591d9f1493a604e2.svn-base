package EAMG.utility;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mina.yadav
 */
public class PasswordEncryption {

    /**
     *
     * @param a
     */
    public static void main(String a[]) {

        String estr = encryptStr("test2009");

       // System.out.println("estr:" + estr);

        String dstr = decryptStr("aGVzZEdWemRESXdNVEE9");

      //  System.out.println(dstr);

    }

    /**************************************************************************************
     * Method used to Encrypt a Input String.
     * <br><br><b>Steps:</b>
     * <br>1. Create Byte Array of the input String using 'UTF8'.
     * <br>2. return the Encrypted String using sun.misc.BASE64Encoder.
     * <br><br><b>Author</b> - Mina
     * @param input Any String
     * @return String
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     * @throws IllegalBlockSizeException
     ****************************************************************************************/
    public static String encrypt(String input) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
       // input = input.trim();
        //$1
        byte[] inputBytes = input.trim().getBytes("UTF8");
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
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws IOException
     * @throws UnsupportedEncodingException
     ****************************************************************************************/
    public static String decrypt(byte[] encryptionBytes) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, IOException {
        //$1
        byte[] recoveredBytes = new sun.misc.BASE64Decoder().decodeBuffer(new String(encryptionBytes));
        //$2
        String recovered = new String(recoveredBytes, "UTF8");
        //   System.out.println("recovered" + recovered);
        //$3
        return recovered;
    }

    /**************************************************************************************
     * Method to encrypt a string.
     * <br><br><b>Steps:</b>
     * <br>1. Encrypt the String by calling 'encrypt' method of 'V_SPAS_Encryption'.
     * <br>2. return the encrypted String.
     * <br><br><b>Author</b> - Mina
     * @param str any text
     * @return String
     ****************************************************************************************/
    public static String encryptStr(String str) {
        String encr_str = "";

        if (str != null) {
            try {
                //$1
                encr_str = encrypt(str);
                encr_str = encrypt("hes" + encr_str);
                String newstr = "";
                for (int i = 0; i < encr_str.length(); i++) {
                    char a1 = encr_str.charAt(i);
                    a1 = myencry(a1, "encr");
                    newstr += a1;
                }
                //  System.out.println("newstr:"+newstr);
            } catch (Exception ae) {
                ae.printStackTrace();
            }
        }
        // //System.out.println("encr_str=" + encr_str);
        //$2
        return encr_str;
    }

    /**************************************************************************************
     * Method to decrypt a string.
     * <br><br><b>Steps:</b>
     * <br>1. Get the bytes of a String in a byte Array.
     * <br>2. Decrypt the String by calling 'decrypt' method of 'V_SPAS_Encryption'.
     * <br>3. return the decrypted String.
     * <br><br><b>Author</b> - Mina
     * @param str any text
     * @return String
     ****************************************************************************************/
    public static String decryptStr(String str) {
        String decr_str = "";

        if (str != null) {
            try {
                String desstr = "";
                for (int i = 0; i < str.length(); i++) {
                    char a1 = str.charAt(i);
                    a1 = myencry(a1, "decr");
                    desstr += a1;
                }
                //$1
                byte[] passBytes = str.getBytes();
                //$2
                decr_str = decrypt(passBytes);
                passBytes = decr_str.substring(3).getBytes();
                decr_str = decrypt(passBytes);
            } catch (Exception ae) {
                ae.printStackTrace();
            }
        }
        //$3
        return decr_str;
    }

    /**
     *
     * @param a
     * @param type
     * @return
     */
    public static char myencry(char a, String type) {
        if (type.equals("encr")) {
            a++;
        } else if (type.equals("decr")) {
            a--;
        }
        return a;
    }
}
