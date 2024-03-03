<%--
  Created by IntelliJ IDEA.
  User: ConMuaXaDan
  Date: 1/19/2024
  Time: 9:32 AM
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

<div class="wrapper centered-vertical-content">
    <div class="centered-vertical-content w-50">

            <%--        Img copyright: https://www.flaticon.com/authors/marz-gallery--%>
            <img alt="${statusCode}" src="<c:url value="/resources/images/${paymentStatusImg}"/>"/>
            <h3 class="font-Merriweather"><fmt:message key="${statusCode}"
                                                       bundle="${statusCodeMsg}"/></h3>

            <p style="text-align: center"><fmt:message key="${paymentMessage}" bundle="${msg}"/></p>

            <a href="<c:url value="${pageContext.request.contextPath}/home"/>"
               class="button primary-filled-button rounded-button"><fmt:message key="home"
                                                                                bundle="${msg}"/></a>
    </div>
</div>
