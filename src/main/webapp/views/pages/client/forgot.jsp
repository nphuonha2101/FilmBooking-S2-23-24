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

        <c:if test="${not empty successfulMessage}">
            <span class="successful-span message-span" id="successful-message">
                    <span class="material-symbols-outlined">
                        task_alt </span> ${successfulMessage}</span>
        </c:if>

        <c:if test="${not empty errorMessage}">
            <span class="error-span message-span" id="error-message"><span class="material-symbols-outlined">
    warning
    </span> ${errorMessage}</span>
        </c:if>

        <form action="forgot-password" method="post">
            <label for="username">
                <span class="material-symbols-outlined">person</span>
                Tên người dùng
            </label>
            <input type="text" name="username" id="username" placeholder="Tên người dùng" autocomplete="true" required>

            <label for="email">
                <span class="material-symbols-outlined">mail</span>
                Email
            </label>
            <input type="email" name="email" id="email" placeholder="Email" autocomplete="true" required>

            <input type="submit" class="primary-filled-button button" value="Gửi">
            <div class="centered-vertical-content">
                <p>Nhấn vào đây để trở về<span> <a class="links" href="login">Đăng nhập!</a> </span></p>
            </div>
        </form>
    </div>
</section>