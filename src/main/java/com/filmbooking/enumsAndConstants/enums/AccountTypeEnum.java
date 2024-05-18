package com.filmbooking.enumsAndConstants.enums;

public enum AccountTypeEnum {
    GOOGLE("google"),
    FACEBOOK("facebook"),
    NORMAL("normal");

    private final String accountType;

    AccountTypeEnum(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }
}
