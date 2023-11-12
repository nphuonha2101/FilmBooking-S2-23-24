package com.filmbooking.utils.validateUtils;

public enum UserRegex {
    USERNAME("^[a-zA-Z]\\w{2,20}$"),
    USER_EMAIL("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z.]{2,}$"),
    USER_FULL_NAME("^[A-Z][a-z]+(\\\\s[A-Z][a-z]+)*$");

    private final String regex;
    UserRegex(String regex) {
        this.regex = regex;
    }

    String getRegex() {
        return this.regex;
    }
}
