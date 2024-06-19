<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 27-09-2023
  Time: 10:34 PM
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
<fmt:setBundle basename="properties.messageAdmin" var="adminMsg"/>

<c:set var="editFilm" value='${editFilm}'/>
<c:set var="genreList" value="${genres}"/>

<section class="section align-top admin-two-cols__wrapper d-flex flex-column align-items-center">
    <div class="container ">
        <jsp:include page="/views/components/admin-panel.jsp"/>
    </div>
    <div class="container d-flex flex-column align-items-center">

        <h2><fmt:message bundle="${adminMsg}" key="editFilm"/></h2>

        <form method="post" action="<c:url value="${pageContext.request.contextPath}/admin/edit/film"/>"
              enctype="multipart/form-data">


            <div class="d-flex flex-column align-items-center">

                <%--        Status Code Messages--%>
                <jsp:include page="/views/components/status-code-message.jsp"/>

                <div class="two-col__wrapper d-flex flex-column align-items-center wrapper">
                    <!-- text form in left -->
                    <div class="left-col">
                        <div>
                            <label for="film-id"><fmt:message bundle="${adminMsg}" key="filmID"/></label>
                            <input class="form-control readonly-input" type="text"
                                   placeholder="<fmt:message bundle="${adminMsg}" key="filmID"/>"
                                   name="film-id"
                                   id="film-id"
                                   value="${editFilm.filmID}"
                                   readonly/>

                            <label for="film-name"><fmt:message bundle="${adminMsg}" key="filmName"/>:
                                <span class="warning-color"> *</span>
                            </label>
                            <input class="form-control" type="text"
                                   placeholder="<fmt:message bundle="${adminMsg}" key="filmName"/>"
                                   name="film-name"
                                   id="film-name"
                                   value="${editFilm.filmName}" required/>

                            <label for="film-price"><fmt:message bundle="${adminMsg}" key="ticketPrices"/>:
                                <span class="warning-color"> *</span>
                            </label>
                            <input class="form-control" type="number" min="0"
                                   placeholder="<fmt:message bundle="${adminMsg}" key="ticketPrices"/>"
                                   name="film-price" id="film-price"
                                   value="${editFilm.filmPrice}"
                                   required/>

                            <label for="director"><fmt:message bundle="${adminMsg}" key="director"/>:
                                <span class="warning-color"> *</span>
                            </label>
                            <input class="form-control" type="text"
                                   placeholder="<fmt:message bundle="${adminMsg}" key="director"/>"
                                   name="director"
                                   id="director"
                                   value="${editFilm.director}"
                                   required/>
                        </div>
                    </div>

                    <!-- hidden form in right -->
                    <div class="d-flex flex-column align-items-center right-col">
                        <input class="form-control" type="file" id="upload-img" name="upload-img"/>
                        <div class="film-img-box div-img" id="film-img" style="background-image: url('<c:url
                                value="${editFilm.imgPath}"/>')"></div>
                        <input type="hidden" name="film-img-name" id="film-img-name" value="">
                        <label style="margin: 2rem;" for="upload-img"
                               class="primary-filled-button button rounded-button"><fmt:message
                                key="choosePhoto" bundle="${adminMsg}"/>
                        </label>
                    </div>

                    <div class="left-col">
                        <div>
                            <label for="actors"><fmt:message bundle="${adminMsg}" key="actors"/>:
                                <span class="warning-color"> *</span>
                            </label>
                            <input class="form-control" type="text"
                                   placeholder="<fmt:message bundle="${adminMsg}" key="actors"/>"
                                   name="actors" id="actors"
                                   value="${editFilm.cast}"
                                   required/>
                        </div>
                    </div>

                    <div class="right-col">
                        <div>
                            <label for="film-trailer-link"><fmt:message bundle="${adminMsg}"
                                                                        key="linkYouTubeTrailer"/>:
                            </label>
                            <input class="form-control" type="text"
                                   placeholder="<fmt:message bundle="${adminMsg}" key="linkYouTubeTrailer"/>"
                                   name="film-trailer-link"
                                   id="film-trailer-link"
                                   value="${editFilm.filmTrailerLink}"/>
                        </div>
                    </div>


                    <div class="left-col">
                        <div>
                            <label for="film-length"><fmt:message bundle="${adminMsg}" key="filmLength"/>:
                                <span class="warning-color"> *</span>
                            </label>
                            <input class="form-control" type="number" min="0"
                                   placeholder="<fmt:message bundle="${adminMsg}" key="filmLength"/>" name="film-length"
                                   id="film-length"
                                   value="${editFilm.filmLength}"
                                   required/>
                        </div>
                    </div>
                </div>

                <div class="wrapper d-flex flex-column align-items-center">
                    <label for="genre-ids"><fmt:message bundle="${adminMsg}" key="genreCodes"/>:
                        <span class="warning-color"> *</span>
                    </label>
                    <p>
                        <span class="font-bold"><fmt:message key="selectedGenres" bundle="${adminMsg}"/>: </span>
                        <span id="selected-genres">${filmGenresStr}</span>
                    </p>
                    <select class="form-control" name="genre-ids"
                            id="genre-ids"
                            class="genre-ids-select"
                            multiple>
                        <c:forEach items="${genreList}" var="genre">
                            <c:choose>
                                <c:when test="${fn:contains(editFilm.genreList, genre)}">
                                    <option value="${genre.genreID}" selected>${genre.genreName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${genre.genreID}">${genre.genreName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>


                    <label for="film-description_textarea">
                        <fmt:message bundle="${adminMsg}" key="filmDescription"/>:
                        <span class="warning-color"> *</span>
                    </label>
                    <textarea class="form-control none-resize_textarea" name="film-description"
                              id="film-description_textarea"
                              placeholder="<fmt:message bundle="${adminMsg}" key="filmDescription"/>">${editFilm.filmDescription}</textarea>
                    <input type="submit" class="primary-filled-button button"
                           value="<fmt:message bundle="${adminMsg}" key="editFilm"/>">
                </div>
            </div>
        </form>
    </div>
</section>

<%--JavaScript--%>
<script type="module" src="<c:url value="/resources/js/handlesUploadFilmImg.js"/>"></script>
<script type="module" src="<c:url value="/resources/js/handlesDisplaySelectedGenres.js"/>"></script>