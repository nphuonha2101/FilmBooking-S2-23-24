package com.filmbooking.utils.validateUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean validate(String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(Regex.validate(UserRegex.USERNAME.getRegex(), "nphuonha2101"));
    }
}
