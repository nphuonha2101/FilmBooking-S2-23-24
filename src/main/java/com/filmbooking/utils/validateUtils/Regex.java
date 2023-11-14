package com.filmbooking.utils.validateUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean validate(UserRegex regex, String value) {
        Pattern pattern = Pattern.compile(regex.getRegex(), Pattern.CASE_INSENSITIVE);
        // check if value matches regex
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(validate(UserRegex.USER_EMAIL, "nphuonha" ));
        System.out.println(validate(UserRegex.USER_EMAIL, "21130122@st.hcmuaf.edu.vn" ));
    }
}
