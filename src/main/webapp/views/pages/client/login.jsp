<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<section class="content section centered-vertical-content">
    <div class="centered-vertical-content container form__container">
        <h2 class="title">Đăng nhập</h2>

        <c:if test="${not empty errorMessage}">
            <span class="error-span message-span" id="error-message"><span class="material-symbols-outlined">
    warning
    </span> ${errorMessage}</span>
        </c:if>

        <form action="login" method="post">
            <label for="username">
                <span class="material-symbols-outlined">person</span>
                Tên người dùng
            </label>
            <input type="text" name="username" id="username" placeholder="Tên người dùng" autocomplete="true" required>

            <label for="password">
                <span class="material-symbols-outlined">password</span>
                Mật khẩu
            </label>
            <input type="password" name="password" id="password" placeholder="Mật khẩu" autocomplete="true" required>
            <input type="submit" class="primary-filled-button button" value="Đăng nhập">
        </form>
        <p>Nếu bạn chưa có tài khoản <span><a class="links" href="signup">Đăng ký ngay!</a> </span></p>
        <p>Hoặc <span><a class="links" href="forgot-password">bạn quên mật khẩu?</a> </span></p>
    </div>
</section>
