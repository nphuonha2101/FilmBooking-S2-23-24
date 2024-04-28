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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    

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
        <h2 class="title"><fmt:message key="signupSectionTitle" bundle="${pageTitle}"/></h2>

        <%--        Status Code Messages--%>
        <jsp:include page="/views/components/statusCodeMessage.jsp"/>
        
        <form action="<c:url value="${pageContext.request.contextPath}/signup"/>" method="post">
            <label for="username">
                <span class="material-symbols-rounded">person</span>
                <fmt:message bundle="${msg}" key="username"/>:
                <span class="warning-color"> *</span>
            </label>
            <input type="text" name="username" id="username" placeholder="<fmt:message bundle="${msg}" key="username"/>"
                   autocomplete="true" required>

            <label for="user-full-name">
                <span class="material-symbols-rounded">badge</span>
                <fmt:message bundle="${msg}" key="fullname"/>:
                <span class="warning-color"> *</span>
            </label>
            <input type="text" name="user-full-name" id="user-full-name"
                   placeholder="<fmt:message bundle="${msg}" key="fullname"/>" autocomplete="true"
                   required>

            <label for="email">
                <span class="material-symbols-rounded">mail</span>
                <fmt:message bundle="${msg}" key="email"/>:
                <span class="warning-color"> *</span>
            </label>
            <input type="email" name="email" id="email" placeholder="Email" autocomplete="true" required>

            <label for="password">
                <span class="material-symbols-rounded">password</span>
                <fmt:message bundle="${msg}" key="password"/>:
                <span class="warning-color"> *</span>
            </label>
            <input type="password" name="password" id="password"
                   placeholder="<fmt:message bundle="${msg}" key="password"/>" autocomplete="true" required>

            <label for="confirm-password">
                <span class="material-symbols-rounded">password</span>
                <fmt:message bundle="${msg}" key="confirmPassword"/>:
                <span class="warning-color"> *</span>
            </label>
            <input type="password" name="confirm-password" id="confirm-password"
                   placeholder="<fmt:message bundle="${msg}" key="confirmPassword"/>"
                   autocomplete="true" required>

            <input type="submit" class="primary-filled-button button"
                   value="<fmt:message bundle="${msg}" key="register"/>">
        </form>
        <p><fmt:message bundle="${msg}" key="ifHaveAccount"/> <span><a class="links" href="<c:url value="${pageContext.request.contextPath}/login"/>"><fmt:message
                bundle="${msg}" key="login"/></a> </span></p>

    </div>
</section>


</body>
</html>