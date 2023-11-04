<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 04-11-2023
  Time: 9:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<section class="section centered-vertical-content">
    <div class="container centered-vertical-content wrapper">
        <c:set var="bookedFilm" value="${bookedFilm}"/>
        <c:set var="bookedShowtime" value="${bookedShowtime}"/>
        <c:set var="bookedRoom" value="${bookedRoom}"/>
        <c:set var="bookedTheater" value="${bookedTheater}"/>

        <h1>${sectionTitle}</h1>


        <div class="wrapper two-col__wrapper align-top">
            <div class="wrapper centered-vertical-content">
                <h3>Chọn ghế ngồi</h3>

                <div class="wrapper">
                    <div class="wrapper centered-vertical-content">

                        <%--Seats table--%>
                        <table class="seats-table">
                            <tbody>
                            <c:set var="roomSeats" value="${bookedRoom.seatMatrix}"/>
                            <c:forEach var="roomSeatsRow" items="${roomSeats}" varStatus="row">
                                <tr>
                                    <c:forEach var="roomSeat" items="${roomSeatsRow}" varStatus="seat">
                                        <td style="padding: 0; height: 2.5rem">
                                            <c:choose>
                                                <c:when test="${roomSeat eq '1'}">
                                                    <button class="seats seats-unavailable">${row.index}
                                                            ${seat.index}</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button class="seats">${row.index} ${seat.index}</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </c:forEach>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="wrapper centered-vertical-content">
                <h3>Thông tin đặt vé</h3>

                <div class="wrapper">
                    <p class="font-bold">Tên phim:
                        <span id="booked-film-name">${bookedFilm.filmName}</span>
                    </p>
                    <p class="font-bold">Giá vé (1 người):
                        <span id="booked-film-price">${bookedFilm.filmPrice}</span> <span> VNĐ</span>
                    </p>
                    <p class="font-bold">Thời gian:
                        <span id="booked-showtime-date">${bookedShowtime.showtimeDate}</span>
                    </p>
                    <p class="font-bold">Phòng:
                        <span id="booked-room-name">${bookedRoom.roomName}</span>
                    </p>
                    <p class="font-bold">Rạp:
                        <span id="booked-theater-name">${bookedTheater.theaterName}</span>
                    </p>
                    <p class="font-bold">Địa chỉ:
                        <span id="booked-theater-address">${bookedTheater.theaterAddress}</span>
                    </p>
                    <p class="font-bold">Tổng tiền:
                        <span id="total-fee">0 VNĐ</span>
                    </p>
                </div>
            </div>

        </div>

        <div class="wrapper centered-vertical-content">
            <form action="book-film" method="post">
                <input type="hidden" name="seats" id="seats">
                <input type="submit" class="primary-filled-button button" value="Đặt vé">
            </form>
        </div>
    </div>

</section>
