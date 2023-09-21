<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: QDang
  Date: 21-09-2023
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Film Booking</title>
    <jsp:include page="/views/components/head-links.jsp" />
    <c:when test="${not empty sessionScope.username}">
        <jsp:include page="/views/components/already-login-nav.jsp"/>
    </c:when>
    <c:when test="${empty sessionScope.username}">
        <jsp:include page="/views/components/not-login-nav.jsp"/>
    </c:when>

</head>
<body>

<jsp:include page="${dynamicContents}"/>

<%--<jsp:include page="/views/pages/client/home.jsp"/>--%>

<%@include file="/views/components/footer.jsp" %>
</body>
</html>
