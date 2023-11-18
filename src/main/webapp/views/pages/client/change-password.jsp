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
<fmt:setBundle basename="properties.message" var="msg"/>

<section class="content section centered-vertical-content">
    <div class="centered-vertical-content container form__container">
        <h2 class="title">Đổi mật khẩu</h2>
        <span class="successful-span">${successfulMessage}</span>
        <form action="change-password" method="post">
            <label for="new-password">
                <span class="material-symbols-outlined">password</span>
                <fmt:message bundle="${msg}" key="password"/>
            </label>
            <input type="password" name="current-password" id="current-password" placeholder=" <fmt:message bundle="${msg}" key="password"/>" autocomplete="true" required>
            <span class="error-span message-span" id="current-password-error">${currentPasswordError}</span>
            <label for="new-password">
                <span class="material-symbols-outlined">password</span>
                <fmt:message bundle="${msg}" key="newPassword"/>
            </label>
            <input type="password" name="new-password" id="new-password" placeholder=" <fmt:message bundle="${msg}" key="newPassword"/>" autocomplete="true" required>
            <span class="error-span message-span" id="password-error">${passwordError}</span>

            <label for="confirm-new-password">
                <span class="material-symbols-outlined">password</span>
                <fmt:message bundle="${msg}" key="confirmPassword"/>
            </label>
            <input type="password" name="confirm-new-password" id="confirm-new-password"
                   placeholder=" <fmt:message bundle="${msg}" key="confirmPassword"/>" autocomplete="true" required>
            <span class="error-span message-span"
                  id="confirm-password-error">${confirmPasswordError}</span>

            <input type="submit" class="primary-filled-button button" value=" <fmt:message bundle="${msg}" key="changePasswd"/>">
        </form>
        <span>${additionElement}</span>
    </div>
</section>