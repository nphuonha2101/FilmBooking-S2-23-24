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
    <jsp:include page="${navigationComponent}" />
</head>
<body>

<jsp:include page="${dynamicContents}"/>

<%--<jsp:include page="/views/pages/client/home.jsp"/>--%>

<%@include file="/views/components/footer.jsp" %>
</body>
</html>
