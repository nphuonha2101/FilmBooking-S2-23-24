<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 22-10-2023
  Time: 7:53 PM
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
                <c:set var="editShowtime" value="${editShowtime}"/>
                <form method="post" action="edit-showtime">
                    <label for="showtime-id">ID Suất chiếu</label>
                    <input class="readonly-input" type="text" placeholder="ID Suất chiếu" name="showtime-id"
                           id="showtime-id"
                           value="${editShowtime.showtimeID}"
                           readonly/>
                    <label for="film-id">Phim</label>
                    <select name="film-id" id="film-id">

                        <c:forEach var="film" items="${filmData}" varStatus="loop">
                            <c:choose>
                                <c:when test="${editShowtime.filmID eq film.filmID}">
                                    <option selected value="${film.filmID}">${film.filmName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${film.filmID}">${film.filmName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    <label for="room-id">Phòng</label>
                    <select name="room-id" id="room-id">
                        <c:forEach var="room" items="${roomData}" varStatus="loop">
                            <c:choose>
                                <c:when test="${editShowtime.roomID eq room.roomID}">
                                    <option selected value="${room.roomID}">${room.roomName}
                                        - ${room.theaterName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${room.roomID}">${room.roomName} - ${room.theaterName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    <label for="showtime-datetime">Ngày chiếu</label>
                    <input type="datetime-local" placeholder="Ngày" name="showtime-datetime" id="showtime-datetime"
                           value="${editShowtime.showtimeDate}"
                           required/>
                    <div class="centered-vertical-content">
                        <input class="primary-filled-button button" type="submit" value="Sửa suất chiếu">
                    </div>
                </form>
            </div>
        </div>

    </div>
</section>


