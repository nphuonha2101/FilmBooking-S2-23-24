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
    <title>Film Booking</title>
<c:choose>

    <c:when test="${not empty sessionScope.username}">
        <jsp:include page="/views/components/already-login-nav.jsp"/>
    </c:when>
    <c:when test="${empty sessionScope.username}">
        <jsp:include page="/views/components/not-login-nav.jsp"/>
    </c:when>
</c:choose>


    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fira+Sans:ital,wght@0,400;0,500;0,600;0,700;0,800;1,400;1,500;1,600;1,700;1,800&family=Inclusive+Sans:ital@0;1&family=Inter:wght@300;400;500;600&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>" />

</head>
<body>

<jsp:include page="${dynamicContents}"/>

<%--<jsp:include page="/views/pages/client/home.jsp"/>--%>

<jsp:include page="/views/components/footer.jsp"/>
</body>
</html>