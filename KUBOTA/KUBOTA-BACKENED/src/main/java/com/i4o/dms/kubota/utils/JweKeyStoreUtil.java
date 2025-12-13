package com.i4o.dms.kubota.utils;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class JweKeyStoreUtil {
	
    public static RSAPrivateKey loadPrivateKeyFromKeyStore(String keyStorePath, String keyStorePassword, String alias, String keyPassword) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS"); // or PKCS12 for .p12/.pfx
        try (FileInputStream keyStoreInputStream = new FileInputStream(keyStorePath)) {
            keyStore.load(keyStoreInputStream, keyStorePassword.toCharArray());
        }

        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias,
                new KeyStore.PasswordProtection(keyPassword.toCharArray()));
        PrivateKey privateKey = privateKeyEntry.getPrivateKey();
        
        return (RSAPrivateKey) privateKey;
    }

    public static RSAPublicKey loadPublicKeyFromKeyStore(String keyStorePath, String keyStorePassword, String alias) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS"); // or PKCS12 for .p12/.pfx
        try (FileInputStream keyStoreInputStream = new FileInputStream(keyStorePath)) {
            keyStore.load(keyStoreInputStream, keyStorePassword.toCharArray());
        }

        Certificate cert = keyStore.getCertificate(alias);
        PublicKey publicKey = cert.getPublicKey();
        
        return (RSAPublicKey) publicKey;
    }
}
