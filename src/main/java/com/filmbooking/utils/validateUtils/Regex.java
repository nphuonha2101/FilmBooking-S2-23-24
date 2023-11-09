package com.filmbooking.utils.validateUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean validate(String value, String regex) {
        Pattern pattern = Pattern.compile(value, Pattern.CASE_INSENSITIVE);
        // check if pattern matches regex
        Matcher matcher = pattern.matcher(regex);

        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(Regex.validate(UserRegex.USERNAME.getRegex(), "nphuonha2101"));
        System.out.println(Regex.validate(UserRegex.USER_EMAIL.getRegex(), "admin@gmail.com"));
    }
}
