/*
File Name: 	mnal_Encryption
PURPOSE: 	To encrypt and decrypt string.
HISTORY:
DATE            BUILD     AUTHOR    		MODIFICATIONS
NA              1.0       Avinash Pandey		$$1 	Created
*/

package EAMG.utility;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

/**
 * <b>Purpose</b> - To encrypt and decrypt string.
 * @author mina.yadav
 * @version 1.0
 */

public class EAMG_Encryption
{
    public EAMG_Encryption()
    {
    }

     /**************************************************************************************
     * Method used to Encrypt a Input String.
     * <br><br><b>Steps:</b>
     * <br>1. Create Byte Array of the input String using 'UTF8'.
     * <br>2. return the Encrypted String using sun.misc.BASE64Encoder.
     * <br><br><b>Author</b> - Avinash
     * @param input Any String
     * @return String 
     * @throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException   
     ****************************************************************************************/

    public static String encrypt(String input) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException
    {
        //input = input.trim();
        //$1
        byte[] inputBytes = input.trim().getBytes("UTF8");
        //$2
        return new sun.misc.BASE64Encoder().encode(inputBytes);
    }


     /**************************************************************************************
     * Method used to Decrypt an Encrypted String.
     * <br><br><b>Steps:</b>
     * <br>1. Decode Input Byte Array using sun.misc.BASE64Encoder.
     * <br>2. Create a string from Byte Array using 'UTF8'.
     * <br>3. return the Decrypted String.
     * <br><br><b>Author</b> - Avinash
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
         //$3
        return recovered;
    }
}
