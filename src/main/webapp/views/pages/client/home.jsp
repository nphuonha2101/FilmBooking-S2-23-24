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

<section class="section">
    <div class="wrapper">

        <h2 class="title"><fmt:message key="newFilmArriveSectionTitle" bundle="${pageTitle}"/> </h2>

        <div class="grid-items wrapper">

            <!-- create film cards -->
            <c:forEach var="film" items="${filmsData}" varStatus="loop">
                <div class="item-cards container centered-vertical-content" id="card-${loop.index}">
                    <div class="film-img-in-card"
                         style="background-image: url('<c:url value="${film.imgPath}"/>')"
                         id="film-img-card-${loop.index}"></div>
                    <div class="wrapper">
                        <h4>${film.filmName}</h4>
                            <%--                    <p>Phòng: ${film.roomID}</p>--%>
                        <p><fmt:message bundle="${msg}" key="ticketPrices"/>: ${film.filmPrice} VNĐ/người</p>
                        <p><fmt:message bundle="${msg}" key="director"/>: ${film.director}</p>
                            <%--                    <p>Thể loại: ${film.genre}</p>--%>
                    </div>
                    <form action="film-info" class="hidden-form" id="hidden-form" method="get">
                        <input type="hidden" name="film-id" value="${film.filmID}">
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</section>