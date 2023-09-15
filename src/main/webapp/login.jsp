<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <%@include file="head-links.jsp"%>
    <%@include file="not-login-nav.jsp" %>
</head>
<style><%@include file="resources/css/style.css"%></style>
<body>
<section class="content section centered-content">
    <div class="centered-content container form__container">
        <h2 class="title">Login to your account</h2>
        <form action="handles-login" enctype="multipart/form-data" method="post">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" placeholder="Username" required>
            <label for="password">Password</label>
            <input type="password" name="password" id="password" placeholder="Password" required>
            <input type="submit" class="light-filled-button button" value="Login">
        </form>
        <p>Don't have account? <span><a href="signup.jsp">Signup now</a> </span></p>
        <p>Or you <span><a href="forgot.jsp">forgot your password?</a> </span></p>

    </div>
</section>

<%@include file="footer.jsp"%>
</body>
</html>