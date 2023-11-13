<%--
  Created by IntelliJ IDEA.
  User: ConMuaXaDan
  Date: 11/10/2023
  Time: 1:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="loginUser" value="${sessionScope.loginUser}"/>

<section class="content section centered-vertical-content">
    <div class="centered-vertical-content container form__container">
        <h2 class="title">Đổi thông tin tài khoản</h2>
        <form action="change-info" method="post">
            <label for="username">
                <span class="material-symbols-outlined">person</span>
                Tên người dùng
            </label>
            <input type="text" name="username" id="username" class="readonly-input" value="${loginUser.username}" placeholder="Tên người dùng" autocomplete="true" readonly>

            <label for="user-full-name">
                <span class="material-symbols-outlined">badge</span>
                Họ và tên
            </label>
            <input type="text" name="user-full-name" id="user-full-name" value="${loginUser.userFullName}" placeholder="Họ và tên" autocomplete="true" required>
            <span class="error-span message-span" id="fullname-error">${fullnameError}</span>

            <label for="email">
                <span class="material-symbols-outlined">mail</span>
                Email
            </label>
            <input type="email" name="email" id="email" value="${loginUser.userEmail}" placeholder="Email" autocomplete="true" required>
            <span class="error-span message-span" id="email-error">${emailError}</span>

            <label for="password">
                <span class="material-symbols-outlined">password</span>
                Mật khẩu
            </label>
            <input type="password" name="password" id="password" placeholder="Mật khẩu" autocomplete="true" required>
            <span class="error-span message-span" id="password-error">${passwordError}</span>

            <input type="submit" class="primary-filled-button button" value="Thay đổi">
        </form>
        <p>Trở về trang <span><a class="links" href="account-info">Thông tin!</a> </span></p>
    </div>
</section>
