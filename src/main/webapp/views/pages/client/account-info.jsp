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
        <a class="links" href="change-password">  <input type="button" value="Đổi mật khẩu"></a>
        <a href="">  <input type="button" value="Thay đổi thông tin"></a>

    </div>
</section>