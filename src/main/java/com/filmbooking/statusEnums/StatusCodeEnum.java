package com.filmbooking.statusEnums;

public enum StatusCodeEnum {
    USERNAME_NOT_FOUND(100, "Tên người dùng không tồn tại"),
    EMAIL_NOT_FOUND(101, "Email người dùng không tồn tại"),
    USER_NOT_FOUND(102, "Người dùng không tồn tại"),
    PASSWORD_NOT_MATCH(103, "Mật khẩu không đúng"),
    EMAIL_NOT_MATCH(104, "Email không khớp với tên người dùng"),
    PASSWORD_CONFIRM_NOT_MATCH(105, "Mật khẩu xác nhận không khớp"),
    USERNAME_EXISTED(106, "Tên người dùng đã tồn tại"),
    EMAIL_EXISTED(107, "Email đã tồn tại"),
    SUCCESSFUL(200, "Thành công"),
    FOUND_USER(201, "Tìm thấy người dùng"),
    SENT_RESET_PASSWD_EMAIL(202, "Đã gửi email đặt lại mật khẩu"),
    PASSWORD_CHANGE_SUCCESSFUL(203, "Đổi mật khẩu thành công"),
    CREATE_NEW_USER_SUCCESSFUL(204, "Tạo người dùng mới thành công"),
    NOT_VALID_INPUT(501, "Dữ liệu không hợp lệ"),
    PLS_CHOOSE_SEAT(502, "Vui lòng chọn ghế");

    private final int statusCode;
    private final String message;

    private StatusCodeEnum(int statusCode, String message) {
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
