<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<section class="content section centered-content">
    <div class="centered-content container form__container">
        <h2 class="title">Đăng nhập</h2>
        <form action="login" method="post">
            <label for="username">Username</label> <span class="error-span" id="username-error">${usernameError}</span>
            <input type="text" name="username" id="username" placeholder="Username" required>
            <label for="password">Mật khẩu</label> <span class="error-span" id="password-error">${passwordError}</span>
            <input type="password" name="password" id="password" placeholder="Mật khẩu" required>
            <input type="submit"  class="primary-filled-button button" value="Đăng nhập">
        </form>
        <p>Nếu bạn chưa có tài khoản <span><a class="links" href="signup">Đăng ký ngay!</a> </span></p>
        <p>Hoặc <span><a class="links" href="forgot-password">bạn quên mật khẩu?</a> </span></p>

    </div>
</section>
