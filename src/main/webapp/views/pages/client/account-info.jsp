<%--
  Created by IntelliJ IDEA.
  User: ConMuaXaDan
  Date: 11/7/2023
  Time: 2:52 PM
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
<fmt:setBundle basename="properties.message" var="msg"/>
<fmt:setBundle basename="properties.pageTitle" var="pageTitle"/>

<section class="content section centered-vertical-content">
    <div class="centered-vertical-content container form__container">
        <h2 class="title"><fmt:message key="accountInfoSectionTitle" bundle="${pageTitle}"/></h2>

        <c:set var="loginUser" value="${sessionScope.loginUser}"/>
        <table class="hidden-table">
            <tbody>
            <tr>
                <td><fmt:message bundle="${msg}" key="fullname"/>:</td>
                <td>${loginUser.userFullName}</td>
            </tr>
            <tr>
                <td><fmt:message bundle="${msg}" key="email"/>:</td>
                <td>${loginUser.userEmail}</td>
            </tr>
            <tr>
                <td><fmt:message bundle="${msg}" key="role"/>:</td>
                <c:if test="${not empty loginUser}">
                    <c:choose>
                        <c:when test="${loginUser.accountRole eq 'customer'}">
                            <td><fmt:message bundle="${msg}" key="customer"/></td>
                        </c:when>
                        <c:otherwise>
                            <td><fmt:message bundle="${msg}" key="admin"/></td>
                        </c:otherwise>
                    </c:choose>
                </c:if>

            </tr>
            </tbody>
        </table>

        <div class="wrapper justify-right-row">
            <a class="links" style="margin: 0 1rem;" href="${pageContext.request.contextPath}/auth/change-password"><fmt:message bundle="${msg}"
                                                                                         key="changePasswd"/></a>
            <a class="primary-filled-button button rounded-button"
               href="${pageContext.request.contextPath}/auth/change-info?username=${loginUser.username}"><fmt:message bundle="${msg}" key="changeInfo"/>
            </a>
        </div>
    </div>
</section>