package com.filmbooking.statusEnums;

public enum StatusEnum {
    USERNAME_NOT_FOUND(100, "Tên người dùng không tồn tại"),
    EMAIL_NOT_FOUND(101, "Email người dùng không tồn tại"),
    USER_NOT_FOUND(102, "Người dùng không tồn tại"),
    PASSWORD_NOT_MATCH(103, "Mật khẩu không đúng"),
    EMAIL_NOT_MATCH(104, "Email không khớp với tên người dùng"),
    SUCCESSFUL(200, "Thành công"),
    FOUND_USER(201, "Tìm thấy người dùng"),
    SENT_RESET_PASSWD_EMAIL(202, "Đã gửi email đặt lại mật khẩu"),

    NOT_VALID_INPUT(501, "Dữ liệu không hợp lệ");

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
