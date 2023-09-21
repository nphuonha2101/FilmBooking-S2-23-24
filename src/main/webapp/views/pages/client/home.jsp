<%@ page import="com.filmbooking.DAOservices.FilmDAOServicesImpl" %><%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 16-09-2023
  Time: 7:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="section">
    <div class="wrapper">
        <h1>Our new film: </h1>
        <div class="grid-items wrapper">

            <!-- create film cards -->
            <c:set var="filmsData" value="<%= new FilmDAOServicesImpl().getAll()%>"/>
            <c:forEach var="film" items="${filmsData}" varStatus="loop">
                <div class="item-cards container" id="card-${loop.index}" onclick="">

                    <h2>${film.filmName}</h2>
                    <p>Room: ${film.roomID}</p>
                    <p>Ticket Price: ${film.price} VND/person</p>
                    <form action="handles-data-modal" class="hidden-form" id="hidden-form" method="get">
                        <input type="hidden" name="filmID" value="${film.filmID}">
                    </form>
                        <button class="primary-filled-button button show-modal-button">Choose this film</button>
                </div>
            </c:forEach>

        </div>
    </div>

    <%@include file="../../../register-modal.jsp"%>
</section>

