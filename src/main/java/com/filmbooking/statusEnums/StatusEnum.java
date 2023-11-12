package com.filmbooking.statusEnums;

public enum StatusEnum {
    USERNAME_NOT_FOUND(100, "Tên người dùng không tồn tại"),
    EMAIL_NOT_FOUND(101, "Email người dùng không tồn tại"),
    PASSWORD_NOT_MATCH(102, "Mật khẩu không đúng"),
    USER_NOT_FOUND(103, "Người dùng không tồn tại"),
    FOUND_USER(201, "Tìm thấy người dùng"),
    NOT_VALID_INPUT(199, "Dữ liệu không hợp lệ");

    private final int statusCode;
    private final String message;

    private StatusEnum(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
    public String getMessage() {
        return this.message;
    }
}
