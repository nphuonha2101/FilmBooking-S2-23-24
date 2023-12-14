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

<section class="modal centered-vertical-content" id="modal">

    <div class="wrapper centered-vertical-content container modal-content wrapper" id="modal-content">
        <div class="justify-right-row wrapper">
            <button id="close-modal-button">
            <span class="material-symbols-rounded warning-color">
                cancel
            </span>
            </button>
        </div>

        <form action="${pageContext.request.contextPath}/search" method="get" id="search-form">
            <div class="centered-horizontal-content">
                <input type="text" name="search" id="search-input-form"
                       placeholder="<fmt:message key="seachPlaceholder" bundle="${msg}"/>"/>
                <span>&ensp;</span>
            </div>
        </form>

    </div>
</section>
