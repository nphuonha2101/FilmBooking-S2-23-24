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
            <div class="centered-horizontal-content wrapper">
                <input type="text" name="q" id="search-input-form"
                       placeholder="<fmt:message key="seachPlaceholder" bundle="${msg}"/>"/>
            </div>

            <div style="border: 2px solid #ccedff !important;" class="outlined-container">
                <h4><fmt:message key="priceRange" bundle="${msg}"/> </h4>
                <span style="text-transform: capitalize"><fmt:message key="from" bundle="${msg}"/> </span>
                <input type="number" class="small-input" min="0" value="0" name="begin-price">
                <span style="text-transform: capitalize"><fmt:message key="to" bundle="${msg}"/> </span>
                <input type="number" class="small-input" min="0" value="0" name="end-price">
            </div>

            <div class="wrapper centered-vertical-content">
                <input type="submit" value="<fmt:message key="search" bundle="${msg}"/>"
                       class="button primary-filled-button rounded-button"/>
            </div>
        </form>

    </div>
</section>
