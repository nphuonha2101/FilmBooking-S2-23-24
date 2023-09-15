<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 15-09-2023
  Time: 9:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reset your password</title>
    <%@include file="head-links.jsp"%>
    <%@include file="not-login-nav.jsp" %>
</head>
<style><%@include file="resources/css/style.css"%></style>
<body>
<section class="content section centered-content">
    <div class="centered-content container form__container">
        <h2 class="title">Reset your password</h2>
        <form action="handles-login" enctype="multipart/form-data" method="post">
            <label for="new-password">New password</label>
            <input type="password" name="new-password" id="new-password" placeholder="New password" required>
            <label for="confirm-new-password">Confirm new password</label>
            <input type="password" name="confirm-new-password" id="confirm-new-password" placeholder="Confirm new password" required>
            <input type="submit" class="light-filled-button button" value="Reset password">
        </form>
    </div>
</section>

<%@include file="footer.jsp"%>
</body>
</html>
