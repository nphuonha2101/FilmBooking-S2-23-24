<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

<fmt:bundle basename="properties.message">
<section class="content section centered-vertical-content">
    <div class="centered-vertical-content container form__container">
        <h2 class="title"><fmt:message key="login"/> </h2>

        <c:if test="${not empty errorMessage}">
            <span class="error-span message-span" id="error-message"><span class="material-symbols-outlined">
    warning
    </span> ${errorMessage}</span>
        </c:if>

        <form action="login" method="post">
            <label for="username">
                <span class="material-symbols-outlined">person</span>
                <fmt:message key="usernameOrEmail"/>
            </label>
            <input type="text" name="username" id="username" placeholder=" <fmt:message key="usernameOrEmail"/>" autocomplete="true" required>

            <label for="password">
                <span class="material-symbols-outlined">password</span>
                <fmt:message key="password"/>
            </label>
            <input type="password" name="password" id="password" placeholder=" <fmt:message key="password"/>" autocomplete="true" required>
            <input type="submit" class="primary-filled-button button" value=" <fmt:message key="login"/>">
        </form>
        <p><fmt:message key="dontHaveAccount"/> <span><a class="links" href="signup"><fmt:message key="signupNow"/> </a> </span></p>
        <p><fmt:message key="Or"/> <span><a class="links" href="forgot-password"><fmt:message key="youForgotPassword"/></a> </span></p>
    </div>
</section>
</fmt:bundle>