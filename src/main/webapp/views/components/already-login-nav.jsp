<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 15-09-2023
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="top-nav" id="already-login-nav">
    <div class="centered-content wrapper" id="left-welcome">
        <p>Xin chào, ${sessionScope.userFullName} (${sessionScope.accountRole})</p>
    </div>
    <ul id="centered-nav-links">
        <li><a class="nav-links" href="home">Trang chủ</a></li>
        <li><a class="nav-links" href="https://github.com/nphuonha2101/FilmBooking-WebProgramming"
               target="_blank">GitHub</a></li>
    </ul>
    <ul id="right-nav-link">
        <li>
            <div class="drop-down-menu">
                <a class="nav-links">Tính năng</a>
                <div class="drop-down-contents">
                    <a class="drop-down-links">Lịch sử đăng ký</a>
                    <c:choose>
                        <c:when test="${sessionScope.accountRole eq 'admin'}">
                            <a class="drop-down-links" href="admin">Trang Admin</a>
                        </c:when>
                    </c:choose>

                </div>
            </div>
        </li>
        <li><a class="nav-links" href="logout">Đăng xuất</a></li>
    </ul>
</nav>