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
            <label for="username">
                <span class="material-symbols-outlined">person</span>
                Tên người dùng
            </label>
            <input type="text" name="username" id="username" placeholder="Tên người dùng" autocomplete="true" required>
            <span class="error-span message-span" id="username-error">${usernameError}</span>

            <label for="user-full-name">
                <span class="material-symbols-outlined">badge</span>
                Họ và tên
            </label>
            <input type="text" name="user-full-name" id="user-full-name" placeholder="Họ và tên" autocomplete="true" required>
            <span class="error-span message-span" id="fullname-error">${fullnameError}</span>

            <label for="email">
                <span class="material-symbols-outlined">mail</span>
                Email
            </label>
            <input type="email" name="email" id="email" placeholder="Email" autocomplete="true" required>
            <span class="error-span message-span" id="email-error">${emailError}</span>

            <label for="password">
                <span class="material-symbols-outlined">password</span>
                Mật khẩu
            </label>
            <input type="password" name="password" id="password" placeholder="Mật khẩu" autocomplete="true" required>
            <span class="error-span message-span" id="password-error">${passwordError}</span>

            <label for="confirm-password">
                <span class="material-symbols-outlined">password</span>
                Xác nhận mật khẩu
            </label>
            <input type="password" name="confirm-password" id="confirm-password" placeholder="Xác nhận mật khẩu"
                   autocomplete="true" required>
            <span class="error-span message-span" id="confirm-password-error">${confirmPasswordError}</span>


            <input type="submit" class="primary-filled-button button" value="Đăng ký">
        </form>
        <p>Nếu bạn đã có tài khoản. <span><a class="links" href="login">Đăng nhập ngay!</a> </span></p>

    </div>
</section>


