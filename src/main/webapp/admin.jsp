<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 16-09-2023
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Film Booking - Admin page</title>
    <%@include file="head-links.jsp" %>
    <%@include file="already-login-nav.jsp" %>
</head>
<style>
    <%@include file="resources/css/style.css" %>
</style>
<body>

<section class="section">
    <div class="tab wrapper centered-content">
        <div class="tab-headers centered-content container">
            <label for="admin-film-content" class="tab-label active">Film Management</label>
            <label for="admin-registration-content" class="tab-label">Registration Management</label>
        </div>

        <div class="tab-contents wrapper">
            <div id="admin-film-content" class="tab-content active">
                <h2>Film Management</h2>
            </div>

            <div id="admin-registration-content" class="tab-content">
                <h2>Registration Management</h2>
            </div>
        </div>
    </div>
</section>

<%@include file="footer.jsp" %>
</body>
</html>
