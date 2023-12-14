<jsp:useBean id="StringUtils" scope="application" class="com.filmbooking.utils.StringUtils"/>
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
<fmt:setBundle basename="properties.statusCode" var="statusCodeMsg"/>

<div class="wrapper centered-vertical-content">
    <div class="centered-vertical-content wrapper">

        <h1 class="font-Merriweather">${httpErrorCode}</h1>
        <h3 class="font-Merriweather"><fmt:message key="${httpErrorMessage}"
                                                   bundle="${statusCodeMsg}"/></h3>

        <%-- img source: https://github.com/blairlee227/IlluStatus?tab=readme-ov-file --%>
        <img alt="${httpErrorCode}" src="<c:url value="/resources/images/${errorImgName}"/>"
             style=" width: calc(60vw) !important;"
        />
        <a href="${pageContext.request.contextPath}/home" class="button primary-filled-button rounded-button"><fmt:message key="home"
                                                                                        bundle="${msg}"/></a>
    </div>
</div>

