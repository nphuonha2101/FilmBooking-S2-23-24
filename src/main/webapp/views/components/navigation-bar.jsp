<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 15-09-2023
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="top-nav" id="navigation-bar">
    <div class="centered-horizontal-content wrapper" id="left-nav-elements">
        <a class="site-logo" href="home">FilmBooking</a>
    </div>


    <ul id="right-nav-link">

        <li><a class="nav-links" href="home">Trang chủ</a></li>
        <li><a class="nav-links" href="https://github.com/nphuonha2101/FilmBooking-WebProgramming"
               target="_blank">GitHub</a></li>

        <c:if test="${not empty sessionScope.username}">
            <li>
                <div class="drop-down-menu">
                    <button class="nav-links light-filled-button">
                            ${sessionScope.userFullName}</button>
                    <div class="drop-down-contents">
                        <a class="drop-down-links" href="">Lịch sử đăng ký</a>
                        <c:choose>
                            <c:when test="${sessionScope.accountRole eq 'admin'}">
                                <a class="drop-down-links" href="admin">Trang Admin</a>
                            </c:when>
                        </c:choose>
                        <a class="drop-down-links" href="">Tài khoản của bạn</a>

                    </div>
                </div>
            </li>
        </c:if>

        <c:choose>
            <c:when test="${not empty sessionScope.username}">
                <li><a class="nav-links" href="logout">Đăng xuất</a></li>
            </c:when>
            <c:when test="${empty sessionScope.username}">
                <li><a class="nav-links" href="signup">Đăng ký</a></li>
                <li><a class="nav-links light-filled-button button" href="login">Đăng nhập</a></li>
            </c:when>
        </c:choose>
    </ul>
</nav>