<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 15-09-2023
  Time: 6:27 PM
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
<fmt:setBundle basename="properties.statusCode" var="statusCodeMsg"/>

<section class="content section centered-vertical-content">
    <div class="centered-vertical-content container form__container">
        <h2 class="title">Đăng ký tài khoản</h2>

        <c:if test="${not empty statusCodeSuccess}">
            <span class="successful-span message-span">
                <span class="material-symbols-outlined">task_alt</span>
                <fmt:message key="${statusCodeSuccess}" bundle="${statusCodeMsg}"/>
            </span>
        </c:if>

        <c:if test="${not empty statusCodeErr}">
            <span class="error-span message-span" id="error-message">
                <span class="material-symbols-outlined">warning</span>
                <fmt:message key="${statusCodeErr}" bundle="${statusCodeMsg}"/>
            </span>
        </c:if>

        <form action="signup" method="post">
            <label for="username">
                <span class="material-symbols-outlined">person</span>
                <fmt:message bundle="${msg}" key="username"/>
            </label>
            <input type="text" name="username" id="username" placeholder="<fmt:message bundle="${msg}" key="username"/>" autocomplete="true" required>

            <label for="user-full-name">
                <span class="material-symbols-outlined">badge</span>
                <fmt:message bundle="${msg}" key="fullname"/>
            </label>
            <input type="text" name="user-full-name" id="user-full-name" placeholder="<fmt:message bundle="${msg}" key="fullname"/>" autocomplete="true"
                   required>

            <label for="email">
                <span class="material-symbols-outlined">mail</span>
                <fmt:message bundle="${msg}" key="email"/>
            </label>
            <input type="email" name="email" id="email" placeholder="Email" autocomplete="true" required>

            <label for="password">
                <span class="material-symbols-outlined">password</span>
                <fmt:message bundle="${msg}" key="password"/>
            </label>
            <input type="password" name="password" id="password" placeholder="<fmt:message bundle="${msg}" key="password"/>" autocomplete="true" required>

            <label for="confirm-password">
                <span class="material-symbols-outlined">password</span>
                <fmt:message bundle="${msg}" key="confirmPassword"/>
            </label>
            <input type="password" name="confirm-password" id="confirm-password" placeholder="<fmt:message bundle="${msg}" key="confirmPassword"/>"
                   autocomplete="true" required>

            <input type="submit" class="primary-filled-button button" value="<fmt:message bundle="${msg}" key="register"/>">
        </form>
        <p><fmt:message bundle="${msg}" key="ifHaveAccount"/> <span><a class="links" href="login"><fmt:message bundle="${msg}" key="login"/></a> </span></p>

    </div>
</section>


