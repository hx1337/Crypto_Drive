package com.crypto.drive;

import java.util.Random;

public class SecureKeyGeneratior {



    public static char[] Genaratekey(int len)
    {
        String keystring = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rndm_method = new Random();

        char[] key = new char[len];

        for (int i = 0; i < len; i++) {
            key[i] = keystring.charAt(rndm_method.nextInt(keystring.length()));
        }
        return key;
    }
}
