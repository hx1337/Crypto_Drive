package com.crypto.drive;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {

    public static String encrypt(String Data, String password) throws  Exception {
        String AES="AES";
        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[]  encVal = c.doFinal(Data.getBytes());
        String encryptionValue = android.util.Base64.encodeToString(encVal, android.util.Base64.DEFAULT);
        return encryptionValue;

    }

    public static SecretKeySpec generateKey(String password) throws  Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return  secretKeySpec;
    }






}
