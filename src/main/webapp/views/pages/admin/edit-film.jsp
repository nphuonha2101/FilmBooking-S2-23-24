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

    <jsp:include page="/views/components/admin-panel.jsp"/>

    <div class="container d-flex flex-column align-items-center">

        <h2><fmt:message bundle="${adminMsg}" key="editFilm"/></h2>


        <div class="d-flex flex-column align-items-center">

            <%--        Status Code Messages--%>
            <jsp:include page="/views/components/status-code-message.jsp"/>
            <form class="mt-5" method="post"
                  action="<c:url value="${pageContext.request.contextPath}/admin/edit/film"/>"
                  enctype="multipart/form-data">

                <div class="w-100 row g-4">
                    <div class="col">
                        <div class="film-img-box div-img" id="film-img" style="background-image: url('<c:url
                                value="${editFilm.imgPath}"/>')"></div>
                        <input type="hidden" name="film-img-name" id="film-img-name" value="">
                        <input class="mt-3 form-control" type="file" id="upload-img" name="upload-img"/>
                    </div>

                    <div class="col">
                        <div class="form-floating mb-3">
                            <input class="form-control readonly-input" type="text"
                                   placeholder="<fmt:message bundle="${adminMsg}" key="filmID"/>"
                                   name="film-id"
                                   id="film-id"
                                   value="${editFilm.filmID}"
                                   readonly/>
                            <label for="film-id"><fmt:message bundle="${adminMsg}" key="filmID"/></label>
                        </div>

                        <div class="form-floating mb-3">
                            <input class="form-control" type="text"
                                   placeholder="<fmt:message bundle="${adminMsg}" key="filmName"/>"
                                   name="film-name"
                                   id="film-name"
                                   value="${editFilm.filmName}"
                                   required/>
                            <label for="film-name"><fmt:message bundle="${adminMsg}" key="filmName"/>:
                                <span class="warning-color"> *</span>
                            </label>
                        </div>

                        <div class="form-floating mb-3">
                            <input class="form-control" type="number" min="0"
                                   placeholder="<fmt:message bundle="${adminMsg}" key="ticketPrices"/>"
                                   name="film-price" id="film-price"
                                   value="${editFilm.filmPrice}"
                                   required/>

                            <label for="film-price"><fmt:message bundle="${adminMsg}" key="ticketPrices"/>:
                                <span class="warning-color"> *</span>
                            </label>
                        </div>

                        <div class="form-floating mb-3">
                            <input class="form-control" type="text"
                                   placeholder="<fmt:message bundle="${adminMsg}" key="director"/>" name="director"
                                   id="director"
                                   value="${editFilm.director}"
                                   required/>

                            <label for="director"><fmt:message bundle="${adminMsg}" key="director"/>:
                                <span class="warning-color"> *</span>
                            </label>
                        </div>

                        <div class="form-floating mb-3">
                            <input class="form-control" type="text"
                                   placeholder="<fmt:message bundle="${adminMsg}" key="actors"/>" name="actors"
                                   id="actors"
                                   value="${editFilm.cast}"
                                   required/>

                            <label for="actors"><fmt:message bundle="${adminMsg}" key="actors"/>:
                                <span class="warning-color"> *</span>
                            </label>
                        </div>

                        <div class="form-floating mb-3">
                            <input class="form-control" type="url"
                                   placeholder="<fmt:message bundle="${adminMsg}" key="linkYouTubeTrailer"/>"
                                   name="film-trailer-link"
                                   value="${editFilm.filmTrailerLink}"
                                   id="film-trailer-link"/>

                            <label for="film-trailer-link"><fmt:message bundle="${adminMsg}"
                                                                        key="linkYouTubeTrailer"/>:</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input class="form-control" type="number" min="0"
                                   placeholder="<fmt:message bundle="${adminMsg}" key="filmLength"/>"
                                   name="film-length"
                                   id="film-length"
                                   value="${editFilm.filmLength}"
                                   required/>

                            <label for="film-length"><fmt:message bundle="${adminMsg}" key="filmLength"/>:
                                <span class="warning-color"> *</span>
                            </label>
                        </div>

                        <div class="mb-3">
                            <label for="genre-ids"><fmt:message bundle="${adminMsg}" key="genreCodes"/>:
                                <span class="warning-color"> *</span>
                            </label>

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


                            <p>
                                <span class="font-bold"><fmt:message key="selectedGenres"
                                                                     bundle="${adminMsg}"/>: </span>
                                <span id="selected-genres">${filmGenresStr}</span>
                            </p>
                        </div>
                    </div>

                    <div class="col">
                        <div class="form-floating">
                            <textarea class="form-control" id="film-description_textarea"
                                      name="film-description"
                                      required
                                      placeholder="<fmt:message bundle="${adminMsg}" key="filmDescription"/>">${editFilm.filmDescription}</textarea>
                            <label for="film-description_textarea">
                                <fmt:message bundle="${adminMsg}" key="filmDescription"/>:
                                <span class="warning-color"> *</span>
                            </label>
                        </div>

                    </div>
                    <div class="w-100 d-flex justify-content-center">
                        <input type="submit" class="primary-filled-button rounded-button button"
                               value="<fmt:message bundle="${adminMsg}" key="editFilm"/>">
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>

<%--JavaScript--%>
<script type="module" src="<c:url value="/resources/js/handlesUploadFilmImg.js"/>"></script>
<script type="module" src="<c:url value="/resources/js/handlesDisplaySelectedGenres.js"/>"></script>