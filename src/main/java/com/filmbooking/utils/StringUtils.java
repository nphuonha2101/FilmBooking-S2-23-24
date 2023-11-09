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

    public static String arr2DToString(String[][] arr) {
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

    public static String createRandomStringUtil(int length) {
        StringBuilder result = new StringBuilder();
        double choice;
        for (int i = 0; i < length; i++) {
            choice = (int) (3 * Math.random());

            if (choice >= 0 && choice < 1)
                result.append(createRandomLowerCaseChar());
            if (choice >= 1 && choice < 2)
                result.append(createRandomUpperCaseChar());
            if (choice >= 2 && choice <= 3)
                result.append((int) (9 * Math.random()));
        }
        return result.toString();
    }

    private static char createRandomLowerCaseChar() {
        return (char) (97  + (26 * Math.random()));
    }

    private static char createRandomUpperCaseChar() {
        return (char) (65 + (26 * Math.random()));
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
        System.out.println(arr2DToString(convertTo2DArr(data)));
        System.out.println(createRandomStringUtil(9));

    }
}
