<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 15-09-2023
  Time: 9:25 PM
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
<fmt:setBundle basename="properties.statusCode" var="statusCode"/>

<section class="content section centered-vertical-content">
    <div class="centered-vertical-content container form__container">
        <h2 class="title"><fmt:message key="forgotPassSectionTitle" bundle="${pageTitle}"/></h2>

        <%--        Status Code Messages--%>
        <jsp:include page="/views/components/statusCodeMessage.jsp"/>

        <form action="<c:url value="${pageContext.request.contextPath}/forgot-password"/>" method="post">
            <label for="username">
                <span class="material-symbols-rounded">person</span>
                <fmt:message key="username" bundle="${msg}"/>:
                <span class="warning-color"> *</span>
            </label>
            <input type="text" name="username" id="username"
                   placeholder=" <fmt:message key="username" bundle="${msg}"/>"
                   autocomplete="true" required>

            <label for="email">
                <span class="material-symbols-rounded">mail</span>
                <fmt:message key="email" bundle="${msg}"/>:
                <span class="warning-color"> *</span>
            </label>
            <input type="email" name="email" id="email" placeholder=" <fmt:message key="email" bundle="${msg}"/>"
                   autocomplete="true" required>

            <input type="submit" class="primary-filled-button button"
                   value=" <fmt:message key="send" bundle="${msg}"/>">

            <div class="centered-vertical-content">
                <p><fmt:message key="return" bundle="${msg}"/>
                    <span> <a class="links" href="<c:url value="${pageContext.request.contextPath}/login"/>">
                        <fmt:message key="login" bundle="${msg}"/>!</a></span>
                </p>
            </div>
        </form>
    </div>
</section>