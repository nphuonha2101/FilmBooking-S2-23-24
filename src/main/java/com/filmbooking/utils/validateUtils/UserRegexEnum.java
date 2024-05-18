package com.filmbooking.utils.validateUtils;

public enum UserRegexEnum {
    USERNAME("^[a-zA-Z]\\w{2,20}$"),
    USER_EMAIL("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z.]{2,}$"),
    USER_FULL_NAME("^[a-zA-Z\\s]{1,}[a-zA-Z]$"),
    USER_PASSWORD("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&.])[A-Za-z\\d@$!%*?&.]{8,}$");

    private final String regex;
    UserRegexEnum(String regex) {
        this.regex = regex;
    }

    String getRegex() {
        return this.regex;
    }
}