<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 02-10-2023
  Time: 3:19 PM
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
    <title><fmt:message bundle="${pageTitleMsg}" key="404Title"/></title>
    <jsp:include page="../components/head-links.jsp"/>
</head>
<body>

<header>
    <jsp:include page="/views/components/navigation-bar.jsp"/>
</header>

<main>
    <div class="wrapper centered-vertical-content">
        <div class="centered-vertical-content wrapper">
            <h1 class="font-Merriweather">404</h1>
            <h2 class="font-Merriweather"><fmt:message key="404Message" bundle="${msg}"/></h2>

            <%-- img source: https://github.com/blairlee227/IlluStatus?tab=readme-ov-file --%>
            <img alt="404" src="<c:url value="/resources/images/404img.svg"/>" style="width: calc(60vw) !important;"/>
            <a href="home" class="button primary-filled-button rounded-button"><fmt:message key="home" bundle="${msg}"/></a>
        </div>
    </div>
</main>

<footer>
    <jsp:include page="/views/components/footer.jsp"/>
</footer>
</body>
</html>
