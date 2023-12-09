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

        <h2 class="title"><fmt:message key="newFilmArriveSectionTitle" bundle="${pageTitle}"/></h2>

        <div class="grid-items wrapper">

            <!-- create film cards -->
            <c:forEach var="film" items="${filmsData}" varStatus="loop">
                <div class="item-cards container centered-vertical-content" id="card-${loop.index}">
                    <div class="film-img-in-card"
                         style="background-image: url('<c:url value="${film.imgPath}"/>')"
                         id="film-img-card-${loop.index}"></div>
                    <div class="wrapper">
                        <h4>${film.filmName}</h4>
                        <p><fmt:message bundle="${msg}" key="ticketPrices"/>: ${film.filmPrice} VNĐ/người</p>
                        <p><fmt:message bundle="${msg}" key="director"/>: ${film.director}</p>
                    </div>
                    <form action="film-info" class="hidden-form" id="hidden-form" method="get">
                        <input type="hidden" name="film-id" value="${film.filmID}">
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="centered-vertical-content">

        <div class="centered-horizontal-content pagination-wrapper">
            <div class="previous centered-horizontal-content">
                <c:choose>
                    <c:when test="${param.page > 1}">
                        <div class="tooltip">
                            <a class="button rounded-button" href="home?page=${param.page - 1}"><span
                                    class="material-symbols-rounded">
                            navigate_before
                            </span>
                            </a>
                            <span class="tooltip-text"><fmt:message key="previousPage" bundle="${msg}"/></span>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="tooltip">
                            <a class="button rounded-button" href="home?page=${totalPages}"><span
                                    class="material-symbols-rounded">
                            navigate_before
                            </span>
                            </a>
                            <span class="tooltip-text"><fmt:message key="previousPage" bundle="${msg}"/></span>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="centered-vertical-content wrapper">
                <form action="home" name="page" method="get" class="pagination-form">
                    <input type="number" min="1" max="${totalPages}" name="page"
                           class="pagination-input" required/>
                    <input type="submit" class="button primary-filled-button rounded-button pagination-button"
                           value="<fmt:message key="goToPage" bundle="${msg}"/>">
                </form>
            </div>
            <div class="next centered-horizontal-content">
                <c:choose>
                    <c:when test="${param.page < totalPages}">
                        <div class="tooltip">

                            <a class="button rounded-button" href="home?page=${param.page + 1}"><span
                                    class="material-symbols-rounded">
                            navigate_next
                            </span>
                            </a>
                            <span class="tooltip-text"><fmt:message key="nextPage" bundle="${msg}"/></span>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="tooltip">
                            <a class="button rounded-button" href="home?page=1"><span class="material-symbols-rounded">
                            navigate_next
                            </span>
                            </a>
                            <span class="tooltip-text"><fmt:message key="nextPage" bundle="${msg}"/></span>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <p><fmt:message key="currentPage" bundle="${msg}"/> ${currentPage} / ${totalPages} <fmt:message key="pages"
                                                                                                        bundle="${msg}"/></p>
    </div>

</section>