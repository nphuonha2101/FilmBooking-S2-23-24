<%--
  Created by IntelliJ IDEA.
  User: QDang
  Date: 26-09-2023
  Time: 14:37
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
<fmt:setBundle basename="properties.messageAdmin" var="adminMsg"/>
    <div class="admin-panel">
        <h4><fmt:message key="managementTools" bundle="${adminMsg}"/></h4>
        <a class="links button rounded-button" href="${pageContext.request.contextPath}/admin/management/film"><fmt:message key="filmManagement" bundle="${adminMsg}"/> </a>
        <a class="links button rounded-button" href="${pageContext.request.contextPath}/admin/management/showtime"><fmt:message key="showtimeManagement" bundle="${adminMsg}"/></a>
        <a class="links button rounded-button" href="${pageContext.request.contextPath}/admin/management/room"><fmt:message key="roomManagement" bundle="${adminMsg}"/></a>
    </div>

