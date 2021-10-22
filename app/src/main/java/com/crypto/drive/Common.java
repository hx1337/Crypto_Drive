package com.crypto.drive;

import java.util.Random;

public class Common {

  // public static String URL_PATTERN="http://adverse.gq/fuckyou.php";
    public static String URL_PATTERN="http://192.168.1.6:8080/cryptodrive/AppServer/controller.jsp";

    public static String uid="1";
    public static String utype="";

    public static int logcount=0;

    public static String Fragment="";
    public static String otp;

    public static char[] SendOTP(int len)
        {
            String numbers = "0123456789";
            Random rndm_method = new Random();

            char[] otp = new char[len];

            for (int i = 0; i < len; i++) {
                otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
            }
            return otp;
        }
}
