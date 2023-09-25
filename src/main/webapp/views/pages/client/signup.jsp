<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 15-09-2023
  Time: 6:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<section class="content section centered-vertical-content">
    <div class="centered-vertical-content container form__container">
        <h2 class="title">Đăng ký tài khoản</h2>
        <span class="successful-span">${successfulMessage}</span>
        <form action="signup" method="post">
            <label for="username">Tên người dùng</label> <span class="error-span" id="username-error">${usernameError}</span>
            <input type="text" name="username" id="username" placeholder="Tên người dùng" required>
            <label for="user-full-name">Họ và tên</label> <span class="error-span" id="fullname-error">${fullnameError}</span>
            <input type="text" name="user-full-name" id="user-full-name" placeholder="Họ và tên" required>
            <label for="email">Email</label> <span class="error-span" id="email-error">${emailError}</span>
            <input type="email" name="email" id="email" placeholder="Email" required>
            <label for="password">Mật khẩu</label> <span class="error-span" id="password-error">${passwordError}</span>
            <input type="password" name="password" id="password" placeholder="Mật khẩu" required>
            <label for="confirm-password">Xác nhận mật khẩu</label> <span
                class="error-span" id="confirm-password-error">${confirmPasswordError}</span>
            <input type="password" name="confirm-password" id="confirm-password" placeholder="Xác nhận mật khẩu"
                   required>

            <input type="submit" class="primary-filled-button button" value="Đăng ký">
        </form>
        <p>Nếu bạn đã có tài khoản. <span><a class="links" href="login">Đăng nhập ngay!</a> </span></p>

    </div>
</section>

