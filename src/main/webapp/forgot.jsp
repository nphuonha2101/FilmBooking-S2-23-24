<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 15-09-2023
  Time: 9:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgot your password</title>
    <%@include file="head-links.jsp"%>
    <%@include file="not-login-nav.jsp" %>
</head>
<style><%@include file="resources/css/style.css"%></style>
<body>
<section class="content section centered-content">
    <div class="centered-content container form__container">
        <h2 class="title">Forgot password</h2>
        <p style="text-align: center">Forgot your password? No problem, keeping your smile, and we'll get your account back ^^</p>
        <form action="handles-forgot" method="post">
            <label for="username">Username</label> <span class="error-span" id="username-error">${usernameError}</span>
            <input type="text" name="username" id="username" placeholder="Username" required>
            <label for="email">Email</label> <span class="error-span" id="email-error">${emailError}</span>
            <input type="email" name="email" id="email" placeholder="Email" required>
            <input type="submit" class="light-filled-button button" value="Submit">
        </form>
    </div>
</section>

<%@include file="footer.jsp"%>
</body>
</html>
