<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: QDang
  Date: 21-09-2023
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${pageTitle}</title>

    <jsp:include page="/views/components/head-links.jsp"/>

</head>
<body>
<header>
    <jsp:include page="/views/components/navigation-bar.jsp"/>
</header>
<main>
    <section class="hero section">
<%--        <div class="wrapper" id="welcome">--%>
<%--            <c:choose>--%>
<%--                <c:when test="${not empty sessionScope.username}">--%>
<%--                    <h1>Xin chào, ${sessionScope.userFullName} (${sessionScope.accountRole})</h1>--%>
<%--                </c:when>--%>
<%--                <c:when test="${empty sessionScope.username}">--%>
<%--                    <h1>Xin chào, khách</h1>--%>
<%--                </c:when>--%>
<%--            </c:choose>--%>
<%--        </div>--%>

        <div class="wrapper" id="banner">
            <jsp:include page="${bannerPage}"/>
        </div>
    </section>
    <jsp:include page="${dynamicContents}"/>
    <jsp:include page="${modalName}"/>

</main>

<!-- Modal -->
<%--<jsp:include page="/views/pages/client/home.jsp"/>--%>

<footer class="footer">
    <jsp:include page="/views/components/footer.jsp"/>
</footer>
</body>
<script type="text/javascript">${additionScript}</script>
</html>