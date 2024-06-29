package com.filmbooking.enumsAndConstants.enums;

public enum StatusCodeEnum {
    PAYMENT_PENDING(100, "Đang chờ thanh toán"),
    SUCCESSFUL(200, "Thành công"),
    FOUND_USER(201, "Tìm thấy người dùng"),
    SENT_RESET_PASSWD_EMAIL(202, "Đã gửi email đặt lại mật khẩu"),
    PASSWORD_CHANGE_SUCCESSFUL(203, "Đổi mật khẩu thành công"),
    CREATE_NEW_USER_SUCCESSFUL(204, "Tạo người dùng mới thành công"),
    BOOKING_FILM_SUCCESSFUL(205, "Đặt vé thành công"),
    UPDATE_FILM_SUCCESSFUL(206, "Cập nhật phim thành công"),
    DELETE_FILM_SUCCESSFUL(207, "Xóa phim thành công"),
    ADD_FILM_SUCCESSFUL(208, "Thêm phim thành công"),
    UPDATE_SHOWTIME_SUCCESSFUL(209, "Cập nhật suất chiếu thành công"),
    DELETE_SHOWTIME_SUCCESSFUL(210, "Xóa suất chiếu thành công"),
    ADD_SHOWTIME_SUCCESSFUL(211, "Thêm suất chiếu thành công"),
    UPDATE_ROOM_SUCCESSFUL(212, "Cập nhật phòng chiếu thành công"),
    DELETE_ROOM_SUCCESSFUL(213, "Xóa phòng chiếu thành công"),
    ADD_ROOM_SUCCESSFUL(214, "Thêm phòng chiếu thành công"),
    PAYMENT_SUCCESSFUL(215, "Thanh toán thành công"),
    TOKEN_VERIFIED(216, "Token đã được xác minh"),
    REMOVE_OLD_IMG_FAILED(300, "Xóa ảnh cũ thất bại"),
    TOKEN_EXPIRED(301, "Token đã hết hạn"),
    USERNAME_NOT_FOUND(400, "Tên người dùng không tồn tại"),
    EMAIL_NOT_FOUND(401, "Email người dùng không tồn tại"),
    USER_NOT_FOUND(402, "Người dùng không tồn tại"),
    PASSWORD_NOT_MATCH(403, "Mật khẩu không khớp"),
    EMAIL_NOT_MATCH(404, "Email không khớp với tên người dùng"),
    PASSWORD_CONFIRM_NOT_MATCH(405, "Mật khẩu xác nhận không khớp"),
    USERNAME_EXISTED(406, "Tên người dùng đã tồn tại"),
    EMAIL_EXISTED(407, "Email đã tồn tại"),
    FILM_NOT_FOUND(408, "Không tìm thấy phim"),
    SHOWTIME_NOT_FOUND(409, "Không tìm thấy suất chiếu"),
    IMG_UPLOAD_FAILED(410, "Tải ảnh lên thất bại"),
    IMG_UPLOAD_NOT_FOUND(411, "Không tìm thấy ảnh tải lên"),
    NO_DATA(412, "Không có dữ liệu"),
    FAILED(500, "Thất bại"),
    INVALID_INPUT(501, "Dữ liệu không hợp lệ"),
    PLS_CHOOSE_SEAT(502, "Vui lòng chọn ghế"),
    PLS_FILL_ALL_REQUIRED_FIELDS(503, "Vui lòng điền đầy đủ thông tin bắt buộc"),
    BOOKING_FILM_FAILED(504, "Đặt phim bị lỗi"),
    SEATS_HAVE_ALREADY_BOOKED(505, "Ghế này đã có người đặt!"),
    PAYMENT_FAILED(506, "Thanh toán thất bại"),
    ADD_FILM_FAILED(507, "Thêm phim thất bại"),
    ADD_SHOWTIME_FAILED(508, "Thêm suất chiếu thất bại"),
    ADD_ROOM_FAILED(509, "Thêm phòng chiếu thất bại"),
    DELETE_FILM_FAILED(510, "Xóa phim thất bại"),
    DELETE_SHOWTIME_FAILED(511, "Xóa suất chiếu thất bại"),
    DELETE_ROOM_FAILED(512, "Xóa phòng chiếu thất bại"),
    UPDATE_FILM_FAILED(513, "Cập nhật phim thất bại"),
    UPDATE_SHOWTIME_FAILED(514, "Cập nhật suất chiếu thất bại"),
    UPDATE_ROOM_FAILED(515, "Cập nhật phòng chiếu thất bại"),
    TOKEN_NOT_FOUND(516, "Không tìm thấy token"),
    PASSWORD_RESET_FAILED(517, "Đặt lại mật khẩu thất bại"),
    USERNAME_ERROR(518, "Tên người dùng không hợp lệ"),
    USER_EMAIL_ERROR(519, "Email không hợp lệ"),
    USER_FULL_NAME_ERROR(520, "Tên đầy đủ không hợp lệ"),
    USER_PASSWORD_ERROR(521, "Mật khẩu không hợp lệ"),
    RECAPTCHA_VERIFICATION_ERROR(522, "Lỗi xác thực reCAPTCHA"),
    LOGIN_AGAIN_AFTER_5_MINUTES(523, "Vui lòng đăng nhập lại sau 5 phút"),
    TOKEN_USED(524, "Token đã được sử dụng");



    private final int statusCode;
    private final String message;

    StatusCodeEnum(int statusCode, String message) {
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
