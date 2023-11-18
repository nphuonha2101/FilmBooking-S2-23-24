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
<section class="section align-top admin-two-cols__wrapper centered-vertical-content">
    <div class="container ">
        <jsp:include page="/views/components/admin-panel.jsp"/>
    </div>
    <div class="container centered-vertical-content">

        <h1><fmt:message bundle="${adminMsg}" key="editFilm"/></h1>


        <form method="post" action="edit-film" enctype="multipart/form-data">
            <div class="centered-vertical-content">
                <div class="two-col__wrapper centered-vertical-content wrapper">
                    <!-- text form in left -->
                    <div class="left-col">
                        <div>
                            <label for="film-id"><fmt:message bundle="${adminMsg}" key="filmID"/></label>
                            <input class="readonly-input" type="text"
                                   placeholder="<fmt:message bundle="${adminMsg}" key="filmID"/>"
                                   name="film-id"
                                   id="film-id"
                                   value="${editFilm.filmID}"
                                   readonly/>

                            <label for="film-name"><fmt:message bundle="${adminMsg}" key="filmName"/></label>
                            <input type="text" placeholder="<fmt:message bundle="${adminMsg}" key="filmName"/>"
                                   name="film-name"
                                   id="film-name"
                                   value="${editFilm.filmName}" required/>

                            <label for="film-price"><fmt:message bundle="${adminMsg}" key="ticketPrices"/></label>
                            <input type="number" min="0"
                                   placeholder="<fmt:message bundle="${adminMsg}" key="ticketPrices"/>"
                                   name="film-price" id="film-price"
                                   value="${editFilm.filmPrice}"
                                   required/>

                            <label for="director"><fmt:message bundle="${adminMsg}" key="director"/></label>
                            <input type="text" placeholder="<fmt:message bundle="${adminMsg}" key="director"/>"
                                   name="director"
                                   id="director"
                                   value="${editFilm.director}"
                                   required/>
                        </div>
                    </div>

                    <!-- hidden form in right -->
                    <div class="centered-vertical-content right-col">
                        <input type="file" id="upload-img" name="upload-img"/>
                        <div class="film-img-in-card" id="film-img" style="background-image: url('<c:url
                                value="${editFilm.imgPath}"/>')"></div>
                        <input type="hidden" name="film-img-name" id="film-img-name" value="">
                        <label style="margin: 2rem;" for="upload-img"
                               class="primary-filled-button button rounded-button"><fmt:message
                                key="choosePhoto" bundle="${adminMsg}"/></label>
                    </div>

                    <div class="left-col">
                        <div>
                            <label for="actors"><fmt:message bundle="${adminMsg}" key="actors"/></label>
                            <input type="text" placeholder="<fmt:message bundle="${adminMsg}" key="actors"/>"
                                   name="actors" id="actors"
                                   value="${editFilm.cast}"
                                   required/>
                        </div>
                    </div>

                    <div class="right-col">
                        <div>
                            <label for="film-trailer-link"><fmt:message bundle="${adminMsg}"
                                                                        key="linkYouTubeTrailer"/></label>
                            <input type="text"
                                   placeholder="<fmt:message bundle="${adminMsg}" key="linkYouTubeTrailer"/>"
                                   name="film-trailer-link"
                                   id="film-trailer-link"
                                   value="${editFilm.filmTrailerLink}"/>
                        </div>
                    </div>


                    <div class="left-col">
                        <div>
                            <label for="film-length"><fmt:message bundle="${adminMsg}" key="filmLength"/></label>
                            <input type="number" min="0"
                                   placeholder="<fmt:message bundle="${adminMsg}" key="filmLength"/>" name="film-length"
                                   id="film-length"
                                   value="${editFilm.filmLength}"
                                   required/>
                        </div>
                    </div>
                    <div class="right-col">
                        <div>
                            <label for="genre-ids"><fmt:message bundle="${adminMsg}" key="genreCodes"/></label>
                            <input type="text" placeholder="<fmt:message bundle="${adminMsg}" key="genreCodes"/>"
                                   name="genre-ids" id="genre-ids"
                                   value="${filmGenreIDs}"
                                   required/>
                        </div>
                    </div>
                </div>

                <div class="wrapper centered-vertical-content">
                    <label for="film-description_textarea">
                        <fmt:message bundle="${adminMsg}" key="filmDescription"/>
                    </label>
                    <textarea class="none-resize_textarea" name="film-description" id="film-description_textarea"
                              placeholder="<fmt:message bundle="${adminMsg}" key="filmDescription"/>">${editFilm.filmDescription}</textarea>
                    <input type="submit" class="primary-filled-button button"
                           value="<fmt:message bundle="${adminMsg}" key="editFilm"/>">
                </div>
            </div>
        </form>

    </div>
</section>
