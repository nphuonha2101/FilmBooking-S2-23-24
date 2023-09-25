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

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fira+Sans:ital,wght@0,400;0,500;0,600;0,700;0,800;1,400;1,500;1,600;1,700;1,800&family=Inclusive+Sans:ital@0;1&family=Inter:wght@300;400;500;600&display=swap"
          rel="stylesheet">

    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>

    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>"/>

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