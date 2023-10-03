<%@ page import="com.filmbooking.DAOservices.FilmDAOServicesImpl" %><%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 16-09-2023
  Time: 7:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<section class="section">
    <div class="wrapper">
        <h1 class="title">${title} </h1>
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
                        <p>Giá vé: ${film.filmPrice} VNĐ/người</p>
                        <p>Đạo diễn: ${film.director}</p>
                            <%--                    <p>Thể loại: ${film.genre}</p>--%>
                    </div>
                    <form action="book-film" class="hidden-form" id="hidden-form" method="get">
                        <input type="hidden" name="film-id" value="${film.filmID}">
                        <input type="submit" class="outlined-button button" value="Đặt phim"/>
                    </form>
                </div>
            </c:forEach>

        </div>
    </div>


</section>

