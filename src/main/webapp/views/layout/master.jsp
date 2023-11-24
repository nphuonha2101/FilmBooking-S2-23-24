<%--
  Created by IntelliJ IDEA.
  User: QDang
  Date: 21-09-2023
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
</head>
<body>
<header>
    <jsp:include page="/views/components/navigation-bar.jsp"/>
</header>
<main>
    <section class="hero section">
        <div class="wrapper" id="banner">
            <jsp:include page="${bannerPage}"/>
        </div>
    </section>
    <jsp:include page="${dynamicContents}"/>
    <jsp:include page="/views/pages/client/search-modal.jsp"/>

</main>

<footer class="footer">
    <jsp:include page="/views/components/footer.jsp"/>
</footer>
</body>
<script type="text/javascript">${additionScript}</script>
</html>