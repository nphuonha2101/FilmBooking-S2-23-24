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
                    <div class=" film-img-in-card"
                         style="background-image: url('<c:url value="/resources/images/DoanQuocDang.png"/>')"
                         id="film-img-card-${loop.index}"></div>
                    <div class="wrapper">
                        <h4>${film.filmName}</h4>
                            <%--                    <p>Phòng: ${film.roomID}</p>--%>
                        <p>Giá vé: ${film.filmPrice} VNĐ/người</p>
                            <%--                    <p>Thể loại: ${film.genre}</p>--%>
                        <form action="" class="hidden-form" id="hidden-form" method="get">
                            <input type="hidden" name="filmID" value="${film.filmID}">
                        </form>
                    </div>
                    <button class="outlined-button button show-modal-button">Đặt phim</button>
                </div>
            </c:forEach>

        </div>
    </div>


</section>

