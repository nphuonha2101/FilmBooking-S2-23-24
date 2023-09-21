<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section class="content section centered-content">
    <div class="centered-content container form__container">
        <h2 class="title">Login to your account</h2>
        <form action="handles-login" method="post">
            <label for="username">Username</label> <span class="error-span" id="username-error">${usernameError}</span>
            <input type="text" name="username" id="username" placeholder="Username" required>
            <label for="password">Password</label> <span class="error-span" id="password-error">${passwordError}</span>
            <input type="password" name="password" id="password" placeholder="Password" required>
            <input type="submit" class="primary-filled-button button" value="Login">
        </form>
        <p>If you don't have account? <span><a href="signup.jsp">Register now!</a> </span></p>
        <p>Or <span><a href="forgot.jsp">you forgot your password?</a> </span></p>

    </div>
</section>
