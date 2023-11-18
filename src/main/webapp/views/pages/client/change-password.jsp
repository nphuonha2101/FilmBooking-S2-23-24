<%--
  Created by IntelliJ IDEA.
  User: ConMuaXaDan
  Date: 11/8/2023
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:choose>
    <c:when test="${empty sessionScope.lang || sessionScope.lang eq 'default'}">
        <fmt:setLocale value="default"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${sessionScope.lang}"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="properties.statusCode" var="statusCodeMsg"/>

<section class="content section centered-vertical-content">
    <div class="centered-vertical-content container form__container">
        <h2 class="title">Đổi mật khẩu</h2>

        <c:if test="${not empty statusCodeErr}">
            <span class="error-span message-span" id="error-message">
                <span class="material-symbols-outlined">warning </span>
                <fmt:message key="${statusCodeErr}" bundle="${statusCodeMsg}"/>
            </span>
        </c:if>

        <c:if test="${not empty statusCodeSuccess}">
            <span class="successful-span message-span">
                <span class="material-symbols-outlined">task_alt</span>
                <fmt:message key="${statusCodeSuccess}" bundle="${statusCodeMsg}"/>
            </span>
        </c:if>

        <form action="change-password" method="post">

            <label for="current-password">
                <span class="material-symbols-outlined">password</span>
                Mật khẩu hiện tại
            </label>

            <input type="password" name="current-password" id="current-password" placeholder="Mật khẩu hiện tại"
                   autocomplete="true" required>
            <label for="new-password">
                <span class="material-symbols-outlined">password</span>
                Mật khẩu mới
            </label>
            <input type="password" name="new-password" id="new-password" placeholder="Mật khẩu mới" autocomplete="true"
                   required>

            <label for="confirm-new-password">
                <span class="material-symbols-outlined">password</span>
                Xác nhận mật khẩu
            </label>
            <input type="password" name="confirm-new-password" id="confirm-new-password"
                   placeholder="Xác nhận mật khẩu" autocomplete="true" required>

            <input type="submit" class="primary-filled-button button" value="Đặt lại mật khẩu">
        </form>
        <span>${additionElement}</span>
    </div>

</section>