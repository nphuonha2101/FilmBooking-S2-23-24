<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 16-09-2023
  Time: 7:02 AM
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
<fmt:setBundle basename="properties.statusCode" var="statusCode"/>

<section class="section">
    <div class="wrapper">

        <h2 class="title"><fmt:message key="${sectionTitle}" bundle="${pageTitle}"/></h2>
        <c:choose>
            <c:when test="${not empty filmsData}">
                <div class="grid-items wrapper">

                    <!-- create film cards -->
                    <c:forEach var="film" items="${filmsData}" varStatus="loop">
                        <div class="item-cards centered-vertical-content" id="card-${loop.index}">
                            <div class="film-img-in-card div-img"
                                 style="background-image: url('<c:url value="${film.imgPath}"/>')"
                                 id="film-img-card-${loop.index}"></div>
                            <div class="overlay">
                                <h3>${film.filmName}</h3>
                                <p>
                                    <span class="font-bold"><fmt:message bundle="${msg}" key="ticketPrices"/>:</span>
                                        ${film.filmPrice} VNĐ/<fmt:message bundle="${msg}" key="person"/>
                                </p>
                                <p>
                                    <span class="font-bold"><fmt:message bundle="${msg}" key="director"/>:</span>
                                        ${film.director}
                                </p>
                                <p>
                                    <span class="font-bold"><fmt:message bundle="${msg}" key="cast"/>:</span>
                                        ${film.cast}
                                </p>
                                <p>
                                    <span class="font-bold"><fmt:message bundle="${msg}" key="filmLength"/>:</span>
                                        ${film.filmLength} <fmt:message bundle="${msg}" key="minutes"/>
                                </p>
                            </div>
                            <form action="${pageContext.request.contextPath}/film-info" class="hidden-form"
                                  id="hidden-form"
                                  method="get">
                                <input type="hidden" name="film" value="${film.slug}">
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </c:when>

            <c:otherwise>
                <div class="wrapper centered-vertical-content">
                    <h3><fmt:message key="${statusCodeErr}" bundle="${statusCode}"/></h3>
                    <p><fmt:message key="${messageDescription}" bundle="${msg}"/>  ${searchQuery}</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <%--    Pagination--%>
    <jsp:include page="/views/components/pagination.jsp"/>
</section>