package com.filmbooking.enumsAndConstants.enums;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public enum TokenStateEnum {
    ACTIVE("active"),
    USED("inactive"),
    EXPIRED("expired");

    private final String tokenState;

    TokenStateEnum(String tokenState) {
        this.tokenState = tokenState;
    }

    public String getTokenState() {
        return tokenState;
    }
}
