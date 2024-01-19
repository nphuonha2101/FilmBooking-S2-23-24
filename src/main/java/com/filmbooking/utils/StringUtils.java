package com.filmbooking.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.Base64;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class StringUtils {
    /**
     * Convert string to 2 dimension array.
     *
     * @param data data that you want to create 2D array. For each element in a row separated with "" character and
     *             each row separated with " " character.
     * @return an 2D array from <b>data</b>
     */
    public static String[][] convertTo2DArr(String data) {
        String[][] result = null;
        String[] rows = data.split(" ");
        result = new String[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            String[] cols = rows[i].split("");
            result[i] = new String[cols.length];
            System.arraycopy(cols, 0, result[i], 0, cols.length);
        }
        return result;
    }

    /**
     * Convert 2D array to String. For each element in a row separated with "" character and
     * each row separated with " " character.
     *
     * @param arr an 2D array want to convert to String.
     * @return a String from 2D array.
     */
    public static String arr2DToString(String[][] arr) {
        StringBuilder result = new StringBuilder();
        for (String[] strings : arr) {
            for (String string : strings) {
                result.append(string);
            }
            result.append(" ");
        }
        return result.toString().trim();
    }

    public static void printArr(String[][] arr) {
        for (String[] strings : arr) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }

    }

    /**
     * Generate a hex String using {@link MessageDigest} and algorithm <b>SHA-256</b>.
     * A String from SHA-256 have length equals 64
     *
     * @param str a String that want to convert to SHA-256 hex String
     * @return a hex String from SHA-256 algorithm
     */
    public static String generateSHA256String(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(str.getBytes(StandardCharsets.UTF_8));

            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hexText = new StringBuilder(no.toString(16));

            while (hexText.length() < 64)
                hexText.insert(0, "0");

            return hexText.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }


    public static String hmacSHA512(String secretKey, String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            mac.init(secretKeySpec);
            byte[] digest = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

            // Convert the digest bytes to a hex string
            BigInteger bigInt = new BigInteger(1, digest);
            StringBuilder hexString = new StringBuilder(bigInt.toString(16));

            // Pad with 0s to ensure the hex string has a length of 128
            while (hexString.length() < 128) {
                hexString.insert(0, "0");
            }

            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmacSHA512", e);
        }
    }


    /**
     * Create random String with uppercase character, lowercase character, and number from 0-9.
     *
     * @param length is a result length that you want.
     * @return a random String
     */
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

    /**
     * Remove diacritical marks such as `, ~, ., ... with base ASCII character using {@link Normalizer.Form} NFD. Ex: "Nhã" => "Nha"
     *
     * @param string is a String that want to remove diacritical marks
     * @return {@link String} without critical mark
     */
    public static String removeDiacriticalMarks(String string) {
        // normalize the Unicode string to a normal form (NFD) string
        String normalized = Normalizer.normalize(string, Normalizer.Form.NFD);
        System.out.println(normalized);
        // check diacritical marks
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        // replace all diacritical marks with empty string
        return pattern.matcher(normalized).replaceAll("");
    }

    /**
     * Create slug from a name using method {@link StringUtils#removeDiacriticalMarks(String)} with a slug's length. Ex: "Nhắm Mắt Thấy Mùa Hè" => "nham-mat-thay-mua-he".
     *
     * @param name       is a name that want to create slug
     * @param slugLength is a slug's length
     * @return a slug with <b>slugLength</b>
     */
    public static String createSlug(String name, int slugLength) {
        StringBuilder slugBuilder = new StringBuilder();
        StringTokenizer nameTokens = new StringTokenizer(name, " ");
        int countCharacters = 0;

        while ((countCharacters <= slugLength) && nameTokens.hasMoreTokens()) {
            String token = nameTokens.nextToken();

            if (countCharacters + token.length() > slugLength)
                break;

            slugBuilder.append(token).append(" ");
            countCharacters = slugBuilder.length();
        }

        String slug = slugBuilder.toString().trim().toLowerCase();

        slug = removeDiacriticalMarks(slug);
        slug = slug.replaceAll(" ", "-");
        return slug;
    }


    private static char createRandomLowerCaseChar() {
        return (char) (97 + (26 * Math.random()));
    }

    private static char createRandomUpperCaseChar() {
        return (char) (65 + (26 * Math.random()));
    }

    /**
     * Handle input String from {@link jakarta.servlet.http.HttpServletRequest#getParameter(String)} to avoid JavaScript injection.
     *
     * @param str that a string want to handle.
     * @return a string after handle (trim string, remove <b>script</b> tag with equivalent HTML code.
     */
    public static String handlesInputString(String str) {
        assert str != null;
        str = str.trim();
        str = str.replaceAll("<script>", "&lt;script&gt;");
        str = str.replaceAll("</script>", "&lt;/script&gt;");

        return str;
    }

    public static void main(String[] args) {

        System.out.println(StringUtils.generateSHA256String("1234"));
        System.out.println(StringUtils.createSlug("Nhắm mawst thấy mùa hè Nhắm mt thấy mùa hè Nhắm mt thấy mùa hè Nhắm mt thấy mùa hè", 20));
    }
}
