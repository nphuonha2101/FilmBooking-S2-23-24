<%--
  Created by IntelliJ IDEA.
  User: nphuo
  Date: 4/28/2024
  Time: 9:53 AM
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

<!DOCTYPE html>
<c:choose>
    <c:when test="${empty sessionScope.lang || sessionScope.lang eq 'default'}">
        <html lang="vi">
    </c:when>
    <c:otherwise>
        <html lang="en">
    </c:otherwise>
</c:choose>
<head>
    <title><fmt:message key="${pageTitle}" bundle="${pageTitleMsg}"/></title>
    <jsp:include page="/views/components/head-links.jsp"/>

    <c:forEach var="customsStyleSheet" items="${customStyleSheets}">
        <link rel="stylesheet" href="${customsStyleSheet}">
    </c:forEach>

</head>
<body>
<jsp:include page="${dynamicContents}"/>
</body>

</html>