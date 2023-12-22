package com.filmbooking.utils.validateUtils;

public enum UserRegexEnum {
    USERNAME("^[a-zA-Z]\\w{2,20}$"),
    USER_EMAIL("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z.]{2,}$"),
    USER_FULL_NAME("^[\\p{Lu}\\p{Lt}][\\p{Ll}\\p{Mn}]+(\\s[\\p{Lu}\\p{Lt}][\\p{Ll}\\p{Mn}]+)*$");

    private final String regex;
    UserRegexEnum(String regex) {
        this.regex = regex;
    }

    String getRegex() {
        return this.regex;
    }
}