<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 15-09-2023
  Time: 9:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<section class="content section centered-vertical-content">
    <div class="centered-vertical-content container form__container">
        <h2 class="title">Quên mật khẩu</h2>
<%--        <p style="text-align: center">Forgot your password? No problem, keeping your smile, and we'll get your account back ^^</p>--%>
        <form action="forgot-password" method="post">
            <label for="username">Tên người dùng</label> <span class="error-span" id="username-error">${usernameError}</span>
            <input type="text" name="username" id="username" placeholder="Tên người dùng" required>
            <label for="email">Email</label> <span class="error-span" id="email-error">${emailError}</span>
            <input type="email" name="email" id="email" placeholder="Email" required>
            <input type="submit" class="primary-filled-button button" value="Gửi">
        </form>
    </div>
</section>

