package com.filmbooking.enumsAndConstants.enums;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public enum TokenTypeEnum {
    PASSWORD_RESET("PASSWORD_RESET"), AUTHENTICATION("AUTHENTICATION");

    private final String tokenType;

    TokenTypeEnum(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getTokenType() {
        return tokenType;
    }

}
