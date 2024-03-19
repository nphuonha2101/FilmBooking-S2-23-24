<%--
  Created by IntelliJ IDEA.
  User: ConMuaXaDan
  Date: 11/10/2023
  Time: 1:39 PM
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

<c:set var="loginUser" value="${sessionScope.loginUser}"/>
<section class="content section centered-vertical-content">
    <div class="centered-vertical-content container form__container">
        <h2 class="title"><fmt:message key="changeInfoSectionTitle" bundle="${pageTitle}"/></h2>

        <%--        Status Code Messages--%>
        <jsp:include page="/views/components/statusCodeMessage.jsp"/>

        <form action="<c:url value="${pageContext.request.contextPath}/auth/change-info"/>" method="post">
            <label for="username">
                <span class="material-symbols-rounded">person</span>
                <fmt:message bundle="${msg}" key="username"/>
            </label>
            <input type="text" name="username" id="username" class="readonly-input" value="${loginUser.username}"
                   placeholder=" <fmt:message bundle="${msg}" key="username"/>" autocomplete="true" readonly>

            <label for="user-full-name">
                <span class="material-symbols-rounded">badge</span>
                <fmt:message bundle="${msg}" key="fullname"/>
            </label>
            <input type="text" name="user-full-name" id="user-full-name" value="${loginUser.userFullName}"
                   placeholder="<fmt:message bundle="${msg}" key="fullname"/>" autocomplete="true" required>

            <label for="email">
                <span class="material-symbols-rounded">mail</span>
                <fmt:message bundle="${msg}" key="email"/>
            </label>
            <input type="email" name="email" id="email" value="${loginUser.userEmail}"
                   placeholder="    <fmt:message bundle="${msg}" key="email"/>"
                   autocomplete="true" required>

            <label for="password">
                <span class="material-symbols-rounded">password</span>
                <fmt:message bundle="${msg}" key="typeYourPassword"/>
            </label>
            <input type="password" name="password" id="password"
                   placeholder="    <fmt:message bundle="${msg}" key="typeYourPassword"/>" autocomplete="true" required>

            <input type="submit" class="primary-filled-button button"
                   value="<fmt:message bundle="${msg}" key="change"/> ">
        </form>
        <p><fmt:message bundle="${msg}" key="return"/>
            <span><a class="links" href="<c:url value="${pageContext.request.contextPath}/auth/account-info"/>">
                <fmt:message bundle="${msg}" key="info"/>
            </a></span>
        </p>
    </div>
</section>
