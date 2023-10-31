package com.filmbooking.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtils {
    public static String[][] convertTo2DArr(String data) {
        String[][] result = null;
        String[] rows = data.split(" ");
        result = new String[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            String[] cols = rows[i].split("");
            result[i] = new String[cols.length];
            System.arraycopy(cols, 0, result[i], 0, cols.length);
//            for (int j = 0; j < cols.length; j++) {
//                result[i][j] = cols[j];
//            }
        }
        return result;
    }

    public static String arrToString(String[][] arr) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                result.append(arr[i][j]);

            }
            result.append(" ");
        }
        return result.toString().trim();
    }

    public static void printArr(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }

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

    public static String handlesInputString(String str) {
        str = str.trim();
        str = str.replaceAll("<script>", "&lt;script&gt;");
        str = str.replaceAll("</script>", "&lt;/script&gt;");

        return str;
    }


    public static void main(String[] args) {
        String data = "01010101 01010101 01010101";
        printArr(convertTo2DArr(data));
        System.out.println(arrToString(convertTo2DArr(data)));
    }
}
