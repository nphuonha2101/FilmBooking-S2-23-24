<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 03-Oct-23
  Time: 2:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

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

<section class="section centered-vertical-content">
    <div class="container centered-vertical-content wrapper">
        <c:set var="film" value="${filmData}"/>

        <h2 class="title"><fmt:message key="filmInfoSectionTitle" bundle="${pageTitle}"/></h2>

        <div id="film-details" class="centered-vertical-content">

            <%--        Status Code Messages--%>
            <jsp:include page="/views/components/statusCodeMessage.jsp"/>

            <div class="two-col__wrapper">
                <div class="film-img-box" style="background-image: url('<c:url value="${film.imgPath}"/>')"
                     id="film-img"></div>
                <div class="wrapper">
                    <h3> ${film.filmName}</h3>
                    <br>
                    <p class="font-bold"><fmt:message bundle="${msg}" key="ticketPrices"/>: <span>${film.filmPrice} VNĐ/<fmt:message
                            bundle="${msg}" key="person"/></span></p>
                    <p class="font-bold"><fmt:message bundle="${msg}" key="director"/>: <span>${film.director} </span>
                    </p>
                    <p class="font-bold"><fmt:message bundle="${msg}" key="cast"/>: <span>${film.cast}</span></p>
                    <p class="font-bold"><fmt:message bundle="${msg}" key="filmLength"/>:
                        <span>${film.filmLength} <fmt:message bundle="${msg}" key="minutes"/></span></p>
                    <p class="font-bold"><fmt:message bundle="${msg}" key="genre"/>: <span>${filmGenreNames}</span></p>
                </div>
            </div>

            <br>

            <div class="wrapper centered-vertical-content" id="film-description">
                <div class="wrapper outlined-container">
                    <div class="wrapper centered-vertical-content">
                        <h3><fmt:message bundle="${msg}" key="description"/></h3>
                    </div>
                    <div class="wrapper">
                        <p>${film.filmDescription}</p>
                    </div>
                </div>
            </div>

            <br>

            <div class="wrapper centered-vertical-content">
                <c:if test="${not empty film.filmTrailerLink}">
                    <div class="wrapper outlined-container">
                        <div class="wrapper centered-vertical-content">
                            <h3>Trailer</h3>
                        </div>
                        <div class="centered-vertical-content wrapper">
                            <iframe class="trailer-frame"
                                    src="${film.filmTrailerLink}"
                                    title="${film.filmName} Trailer"
                                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                                    allowfullscreen></iframe>
                        </div>
                    </div>
                </c:if>
            </div>
            <br>
            <div class="wrapper centered-vertical-content">
                <div class="wrapper outlined-container">
                    <div class="wrapper centered-vertical-content">
                        <h3><fmt:message bundle="${msg}" key="chooseShowtime"/></h3>
                    </div>

                    <div class="wrapper two-col__wrapper">
                        <div class="wrapper">
                            <c:set var="showtimeList" value="${film.showtimeList}"/>
                            <select name="select-showtime" id="select-showtime">
                                <c:forEach var="showtime" items="${showtimeList}" varStatus="loop">
                                    <option value="${showtime.showtimeID}">${showtime.room.roomName}
                                        - ${showtime.room.theater.theaterName} - ${showtime.showtimeDate}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="wrapper centered-vertical-content">
                            <p><fmt:message bundle="${msg}" key="showtime"/>: <span id="selected-showtime"></span></p>
                        </div>
                    </div>
                </div>
            </div>

            <%--            </div>--%>

            <div class="wrapper centered-vertical-content">
                <form action="${pageContext.request.contextPath}/film-info" method="post">
                    <input type="hidden" name="showtime-id" id="showtime-id">
                    <input class="primary-filled-button button" type="submit"
                           value="<fmt:message bundle="${msg}" key="continue"/>">
                </form>
            </div>
        </div>
    </div>
</section>