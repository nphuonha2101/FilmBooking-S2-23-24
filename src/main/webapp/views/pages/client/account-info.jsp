<%--
  Created by IntelliJ IDEA.
  User: ConMuaXaDan
  Date: 11/7/2023
  Time: 2:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<section class="content section centered-vertical-content">
    <div class="centered-vertical-content container form__container">
        <h2 class="title">Thông tin tài khoản</h2>

        <c:set var="loginUser" value="${sessionScope.loginUser}"/>
        <table class="hidden-table">
            <tbody>
            <tr>
                <td>Họ và tên:</td>
                <td>${loginUser.userFullName}</td>
            </tr>
            <tr>
                <td>E-mail:</td>
                <td>${loginUser.userEmail}</td>
            </tr>
            <tr>
                <td>Vai trò:</td>

                <c:choose>
                    <c:when test="${loginUser.accountRole eq 'customer'}">
                        <td>Khách hàng</td>
                    </c:when>
                    <c:otherwise>
                        <td>Quản trị viên</td>
                    </c:otherwise>
                </c:choose>

            </tr>
            </tbody>
        </table>
        <div class="wrapper justify-right-row">
            <a class="links" style="margin: 0 1rem;" href="change-password">Đổi mật khẩu</a>
            <a class="primary-filled-button button rounded-button" href="change-info?username=${loginUser.username}">Đổi thông tin</a>
        </div>

    </div>
</section>