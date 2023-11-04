<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 03-Oct-23
  Time: 2:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<section class="section centered-vertical-content">
    <div class="container centered-vertical-content wrapper">
        <c:set var="film" value="${filmData}"/>

        <h1>${sectionTitle}</h1>

        <div id="film-details" class="centered-vertical-content">
            <div class="two-col__wrapper">
                <div class="film-img-in-card" style="background-image: url('<c:url value="${film.imgPath}"/>')"
                     id="film-img"></div>
                <div class="wrapper">
                    <h3> ${film.filmName}</h3>
                    <br>
                    <p class="font-bold">Giá vé: <span>${film.filmPrice} VNĐ/người</span></p>
                    <p class="font-bold">Đạo diễn: <span>${film.director} </span></p>
                    <p class="font-bold">Diễn viên: <span>${film.cast}</span></p>
                    <p class="font-bold">Độ dài phim: <span>${film.filmLength} phút</span></p>
                    <p class="font-bold">Thể loại: <span>${filmGenreNames}</span></p>
                </div>
            </div>

            <div class="wrapper centered-vertical-content" id="film-description">
                <div class="wrapper title-filled_wrapper centered-vertical-content">
                    <h3>Mô tả</h3>
                </div>
                <div class="wrapper">
                    <p>${film.filmDescription}</p>
                </div>
            </div>

            <br>

            <div class="wrapper centered-vertical-content">
                <c:if test="${not empty film.filmTrailerLink}">
                    <div class="wrapper title-filled_wrapper centered-vertical-content">
                        <h3>Trailer</h3>
                    </div>
                    <div class="centered-vertical-content wrapper">
                        <iframe class="trailer-frame"
                                src="${film.filmTrailerLink}"
                                title="${film.filmName} Trailer" frameborder="0"
                                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                                allowfullscreen></iframe>
                    </div>
                </c:if>
            </div>
            <br>
            <div class="wrapper centered-vertical-content">
                <div class="wrapper title-filled_wrapper centered-vertical-content">
                    <h3>Chọn suất chiếu</h3>
                </div>

                <div class="wrapper two-col__wrapper">
                    <div class="wrapper">
                        <c:set var="showtimeDetailsViews" value="${showtimeViewDetails}"/>
                        <select name="select-showtime" id="select-showtime">
                            <c:forEach var="showtimeDetails" items="${showtimeDetailsViews}" varStatus="loop">
                                <option value="${showtimeDetails.showtimeID}">${showtimeDetails.roomName}
                                    - ${showtimeDetails.theaterName} - ${showtimeDetails.showtimeDate}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="wrapper centered-vertical-content">
                        <p>Suất chiếu: <span id="selected-showtime"></span></p>
                    </div>
                </div>
            </div>

            <%--            </div>--%>

            <div class="wrapper centered-vertical-content">
                <form action="film-info" method="post">
                    <input type="hidden" name="showtime-id" id="showtime-id">
                    <input class="primary-filled-button button" type="submit" value="Tiếp tục">
                </form>
            </div>
        </div>
    </div>

</section>

