package com.filmbooking.utils.validateUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    /**
     * Validate value with regex
     * @param regex     Regex to validate
     * @param value     Value to validate
     * @return          true if value matches regex, false otherwise
     */
    public static boolean validate(UserRegexEnum regex, String value) {
        Pattern pattern = Pattern.compile(regex.getRegex(), Pattern.CASE_INSENSITIVE);
        // check if value matches regex
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(validate(UserRegexEnum.USER_EMAIL, "nphuonha" ));
        System.out.println(validate(UserRegexEnum.USER_EMAIL, "21130122@st.hcmuaf.edu.vn" ));
        System.out.println(validate(UserRegexEnum.USER_FULL_NAME, "Nguyễn Phương Nhã"));
    }
}
