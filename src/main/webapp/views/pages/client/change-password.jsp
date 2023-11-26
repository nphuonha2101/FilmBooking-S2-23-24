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
<fmt:setBundle basename="properties.pageTitle" var="pageTitle"/>
<fmt:setBundle basename="properties.statusCode" var="statusCodeMsg"/>

<section class="content section centered-vertical-content">
    <div class="centered-vertical-content container form__container">
        <h2 class="title"><fmt:message key="changePasswordSectionTitle" bundle="${pageTitle}"/> </h2>

        <c:if test="${not empty statusCodeSuccess}">
            <span class="successful-span message-span">
                <span class="material-symbols-rounded">task_alt</span>
                <fmt:message key="${statusCodeSuccess}" bundle="${statusCodeMsg}"/>
            </span>
        </c:if>

        <c:if test="${not empty statusCodeErr}">
            <span class="error-span message-span" id="error-message">
                <span class="material-symbols-rounded">warning</span>
                <fmt:message key="${statusCodeErr}" bundle="${statusCodeMsg}"/>
            </span>
        </c:if>


        <form action="change-password" method="post">
            <label for="current-password">
                <span class="material-symbols-rounded">password</span>
                <fmt:message bundle="${msg}" key="password"/>
            </label>
            <input type="password" name="current-password" id="current-password" placeholder=" <fmt:message bundle="${msg}" key="password"/>" autocomplete="true" required>

            <label for="new-password">
                <span class="material-symbols-rounded">password</span>
                <fmt:message bundle="${msg}" key="newPassword"/>
            </label>
            <input type="password" name="new-password" id="new-password" placeholder=" <fmt:message bundle="${msg}" key="newPassword"/>" autocomplete="true" required>

            <label for="confirm-new-password">
                <span class="material-symbols-rounded">password</span>
                <fmt:message bundle="${msg}" key="confirmPassword"/>
            </label>
            <input type="password" name="confirm-new-password" id="confirm-new-password"
                   placeholder=" <fmt:message bundle="${msg}" key="confirmPassword"/>" autocomplete="true" required>

            <input type="submit" class="primary-filled-button button" value=" <fmt:message bundle="${msg}" key="changePasswd"/>">
        </form>
        <span>${additionElement}</span>
    </div>
</section>