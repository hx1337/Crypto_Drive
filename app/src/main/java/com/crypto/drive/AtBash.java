 
package com.crypto.drive;
/*********
 -*- Made by Hussain Manalodi
*********/



public class AtBash {




    public static String cipherDecryption(String msg) {
//        String alpa = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alpa = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        String reverseAlpa = "";
        // reversing alphabets
        for (int i = alpa.length()-1; i > -1; i--) {
            reverseAlpa += alpa.charAt(i);
        }


        String message = msg;

        message = message.toUpperCase();

        String dencryText = "";
        for (int i = 0; i < message.length(); i++) {
            if(message.charAt(i) == (char)32){
                dencryText += " ";
            } else {
                int count = 0;
                for (int j = 0; j < reverseAlpa.length(); j++) {
                    if (message.charAt(i) == reverseAlpa.charAt(j)){
                        dencryText += alpa.charAt(j);
                        break;
                    }
                } // inner for
            } // if-else
        } // for

        //System.out.println("Decrypted Text: " + dencryText);
        return dencryText;
    }

    public static String cipherEncryption(String msg) {
//        String alpa = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String alpa = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        String reverseAlpa = "";
        // reversing alphabets
        for (int i = alpa.length()-1; i > -1; i--) {
            reverseAlpa += alpa.charAt(i);
        }


        String message = msg;
        message = message.toUpperCase();

        String encryText = "";
        for (int i = 0; i < message.length(); i++) {
            if(message.charAt(i) == (char)32){
                encryText += " ";
            } else {
                int count = 0;
                for (int j = 0; j < alpa.length(); j++) {
                    if (message.charAt(i) == alpa.charAt(j)){
                        encryText += reverseAlpa.charAt(j);
                        break;
                    }
                } // inner for
            } // if-else
        } // for

       // System.out.println("Encrypted Text: " + encryText);
        return encryText;
    }

}
