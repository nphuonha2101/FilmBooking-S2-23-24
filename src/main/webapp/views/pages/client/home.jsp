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
                <div class="item-cards container" id="card-${loop.index}" onclick="">

                    <h2>${film.filmName}</h2>
                    <p>Phòng: ${film.roomID}</p>
                    <p>Giá vé: ${film.price} VNĐ/người</p>
                    <form action="" class="hidden-form" id="hidden-form" method="get">
                        <input type="hidden" name="filmID" value="${film.filmID}">
                    </form>
                        <button class="primary-filled-button button show-modal-button">Đặt phim</button>
                </div>
            </c:forEach>

        </div>
    </div>


</section>
