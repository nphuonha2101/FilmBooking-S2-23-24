package com.filmbooking.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashTextGeneratorUtils {
    public static String generateSHA256String(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(str.getBytes(StandardCharsets.UTF_8));

            BigInteger no = new BigInteger(1, messageDigest);
            String hexText = no.toString(16);

            while (hexText.length() < 32)
                hexText += "0" + hexText;


            return hexText;
        } catch (NoSuchAlgorithmException e) {
            e.getMessage();
        }
        return null;
    }

    public static void main(String[] args)  {
        System.out.println(HashTextGeneratorUtils.generateSHA256String("Hello"));
    }
}
