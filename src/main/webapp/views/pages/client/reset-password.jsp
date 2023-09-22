<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 15-09-2023
  Time: 9:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<body>
<section class="content section centered-content">
    <div class="centered-content container form__container">
        <h2 class="title">Đặt lại mật khẩu</h2>
        <span class="successful-span">${successfulMessage}</span>
        <form action="reset-password" method="post">
            <label for="new-password">Mật khẩu mới</label> <span class="error-span" id="password-error">${passwordError}</span>
            <input type="password" name="new-password" id="new-password" placeholder="Mật khẩu mới" required>
            <label for="confirm-new-password">Xác nhận mật khẩu</label> <span class="error-span" id="confirm-password-error">${confirmPasswordError}</span>
            <input type="password" name="confirm-new-password" id="confirm-new-password" placeholder="Xác nhận mật khẩu" required>
            <input type="submit" class="primary-filled-button button" value="Đặt lại mật khẩu">
        </form>
    <span>${additionElement}</span>
    </div>

</section>