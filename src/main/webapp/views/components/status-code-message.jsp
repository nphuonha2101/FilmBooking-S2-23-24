<%--
  Created by IntelliJ IDEA.
  User: nphuonha
  Date: 12/12/2023
  Time: 10:45 PM
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

<fmt:setBundle basename="properties.pageTitle" var="pageTitleMsg"/>
<fmt:setBundle basename="properties.message" var="msg"/>
<fmt:setBundle basename="properties.statusCode" var="statusCodeMsg"/>

<c:if test="${not empty statusCodeSuccess}">
    <div class="alert d-flex align-items-center alert-success alert-dismissible fade show successful-alert-color " role="alert">
            <span class="material-symbols-rounded">task_alt</span>
            <fmt:message key="${statusCodeSuccess}" bundle="${statusCodeMsg}"/>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>

<c:if test="${not empty statusCodeErr}">
    <div class="alert d-flex align-items-center  alert-danger alert-dismissible fade show error-alert-color" role="alert">
        <span class="material-symbols-rounded">warning</span>
        <fmt:message key="${statusCodeErr}" bundle="${statusCodeMsg}"/>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>
