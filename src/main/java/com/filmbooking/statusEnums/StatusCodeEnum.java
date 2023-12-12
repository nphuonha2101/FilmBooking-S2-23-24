package com.filmbooking.statusEnums;

public enum StatusCodeEnum {
    SUCCESSFUL(200, "Thành công"),
    FOUND_USER(201, "Tìm thấy người dùng"),
    SENT_RESET_PASSWD_EMAIL(202, "Đã gửi email đặt lại mật khẩu"),
    PASSWORD_CHANGE_SUCCESSFUL(203, "Đổi mật khẩu thành công"),
    CREATE_NEW_USER_SUCCESSFUL(204, "Tạo người dùng mới thành công"),
    BOOK_FILM_SUCCESSFUL(205, "Đặt vé thành công"),
    UPDATE_FILM_SUCCESSFUL(206, "Cập nhật phim thành công"),
    DELETE_FILM_SUCCESSFUL(207, "Xóa phim thành công"),
    ADD_FILM_SUCCESSFUL(208, "Thêm phim thành công"),
    UPDATE_SHOWTIME_SUCCESSFUL(209, "Cập nhật suất chiếu thành công"),
    DELETE_SHOWTIME_SUCCESSFUL(210, "Xóa suất chiếu thành công"),
    ADD_SHOWTIME_SUCCESSFUL(211, "Thêm suất chiếu thành công"),
    UPDATE_ROOM_SUCCESSFUL(212, "Cập nhật phòng chiếu thành công"),
    DELETE_ROOM_SUCCESSFUL(213, "Xóa phòng chiếu thành công"),
    ADD_ROOM_SUCCESSFUL(214, "Thêm phòng chiếu thành công"),
    REMOVE_OLD_IMG_FAILED(300, "Xóa ảnh cũ thất bại"),
    USERNAME_NOT_FOUND(400, "Tên người dùng không tồn tại"),
    EMAIL_NOT_FOUND(401, "Email người dùng không tồn tại"),
    USER_NOT_FOUND(402, "Người dùng không tồn tại"),
    PASSWORD_NOT_MATCH(403, "Mật khẩu không đúng"),
    EMAIL_NOT_MATCH(404, "Email không khớp với tên người dùng"),
    PASSWORD_CONFIRM_NOT_MATCH(405, "Mật khẩu xác nhận không khớp"),
    USERNAME_EXISTED(406, "Tên người dùng đã tồn tại"),
    EMAIL_EXISTED(407, "Email đã tồn tại"),
    FILM_NOT_FOUND(408, "Không tìm thấy phim"),
    SHOWTIME_NOT_FOUND(409, "Không tìm thấy suất chiếu"),
    IMG_UPLOAD_FAILED(410, "Tải ảnh lên thất bại"),
    IMG_UPLOAD_NOT_FOUND(411, "Không tìm thấy ảnh tải lên"),
    NOT_VALID_INPUT(501, "Dữ liệu không hợp lệ"),
    PLS_CHOOSE_SEAT(502, "Vui lòng chọn ghế"),
    PLS_FILL_ALL_REQUIRED_FIELDS(503, "Vui lòng điền đầy đủ thông tin bắt buộc");

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
