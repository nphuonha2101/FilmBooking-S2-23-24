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
        <h2 class="title"><fmt:message key="changePasswordSectionTitle" bundle="${pageTitle}"/></h2>

        <%--        Status Code Messages--%>
        <jsp:include page="/views/components/statusCodeMessage.jsp"/>

        <form action="<c:url value="${pageContext.request.contextPath}/auth/change-password"/>" method="post">
            <label for="current-password">
                <span class="material-symbols-rounded">password</span>
                <fmt:message bundle="${msg}" key="password"/>
            </label>
            <input type="password" name="current-password" id="current-password"
                   placeholder=" <fmt:message bundle="${msg}" key="password"/>" autocomplete="true" required>

            <label for="new-password">
                <span class="material-symbols-rounded">password</span>
                <fmt:message bundle="${msg}" key="newPassword"/>
            </label>
            <input type="password" name="new-password" id="new-password"
                   placeholder=" <fmt:message bundle="${msg}" key="newPassword"/>" autocomplete="true" required>

            <label for="confirm-new-password">
                <span class="material-symbols-rounded">password</span>
                <fmt:message bundle="${msg}" key="confirmPassword"/>
            </label>
            <input type="password" name="confirm-new-password" id="confirm-new-password"
                   placeholder=" <fmt:message bundle="${msg}" key="confirmPassword"/>" autocomplete="true" required>

            <input type="submit" class="primary-filled-button button"
                   value=" <fmt:message bundle="${msg}" key="changePasswd"/>">
        </form>
        <p><fmt:message bundle="${msg}" key="return"/>
            <span><a class="links" href="<c:url value="${pageContext.request.contextPath}/auth/account-info"/>">
            <fmt:message bundle="${msg}" key="info"/> </a> </span>
        </p>
    </div>
</section>