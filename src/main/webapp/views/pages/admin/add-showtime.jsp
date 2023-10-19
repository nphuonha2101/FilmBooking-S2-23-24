<%--
  Created by IntelliJ IDEA.
  User: QDang
  Date: 17-10-2023
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<section class="section align-top admin-two-cols__wrapper centered-vertical-content">
    <div class="container ">
        <jsp:include page="/views/components/admin-panel.jsp"/>
    </div>
    <div class="container centered-vertical-content">

        <h1>${sectionTitle}</h1>


        <div class="centered-vertical-content wrapper">
            <div>
                <form method="post" action="add-showtime">
                    <label for="showtime-id">ID Suất chiếu</label>
                    <input type="text" placeholder="ID Suất chiếu" name="showtime-id" id="showtime-id"
                           required/>
                    <label for="film-id">Phim</label>
                    <select name="film-id" id="film-id">
                        <c:forEach var="film" items="${filmData}" varStatus="loop">
                            <option value="${film.filmID}">${film.filmName}</option>
                        </c:forEach>
                    </select>
                    <label for="room-id">Phòng</label>
                    <select name="room-id" id="room-id">
                        <c:forEach var="room" items="${roomData}" varStatus="loop">
                            <option value="${room.roomID}">${room.roomName} - ${room.theaterName}</option>
                        </c:forEach>
                    </select>
                    <label for="showtime-datetime">Ngày chiếu</label>
                    <input type="datetime-local" placeholder="Ngày" name="showtime-datetime" id="showtime-datetime"
                           required/>
                    <div class="centered-vertical-content">
                        <input class="primary-filled-button button" type="submit" value="Thêm suất chiếu">
                    </div>
                </form>
            </div>
        </div>

    </div>
</section>

