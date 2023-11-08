<%--
  Created by IntelliJ IDEA.
  User: ConMuaXaDan
  Date: 11/8/2023
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<section class="content section centered-vertical-content">
    <div class="centered-vertical-content container form__container">
        <h2 class="title">Đổi mật khẩu</h2>
        <span class="successful-span">${successfulMessage}</span>
        <form action="change-password" method="post">
            <label for="new-password">
                <span class="material-symbols-outlined">password</span>
                Mật khẩu hiện tại
            </label>
            <input type="password" name="current-password" id="current-password" placeholder="Mật khẩu hiện tại" autocomplete="true" required>
            <span class="error-span message-span" id="current-password-error">${currentPasswordError}</span>
            <label for="new-password">
                <span class="material-symbols-outlined">password</span>
                Mật khẩu mới
            </label>
            <input type="password" name="new-password" id="new-password" placeholder="Mật khẩu mới" autocomplete="true" required>
            <span class="error-span message-span" id="password-error">${passwordError}</span>

            <label for="confirm-new-password">
                <span class="material-symbols-outlined">password</span>
                Xác nhận mật khẩu
            </label>
            <input type="password" name="confirm-new-password" id="confirm-new-password"
                   placeholder="Xác nhận mật khẩu" autocomplete="true" required>
            <span class="error-span message-span"
                  id="confirm-password-error">${confirmPasswordError}</span>

            <input type="submit" class="primary-filled-button button" value="Đặt lại mật khẩu">
        </form>
        <span>${additionElement}</span>
    </div>

</section>