package com.filmbooking.enumsAndConstants.enums;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public enum AccountRoleEnum {
    SUPERADMIN("superadmin"),
    ADMIN("admin"),
    CUSTOMER("customer");

    private final String accountRole;

    AccountRoleEnum(String accountRole) {
        this.accountRole = accountRole;
    }

    public String getAccountRole() {
        return accountRole;
    }
}
