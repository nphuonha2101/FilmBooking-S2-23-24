<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 04-11-2023
  Time: 9:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
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
<fmt:setBundle basename="properties.message" var="msg"/>
<fmt:setBundle basename="properties.pageTitle" var="pageTitle"/>
<fmt:setBundle basename="properties.statusCode" var="statusCodeMsg"/>


<section class="section d-flex flex-column align-items-center">
    <div class="container d-flex flex-column align-items-center wrapper">
        <c:set var="bookedShowtime" value="${bookedShowtime}"/>
        <c:set var="bookedFilm" value="${bookedShowtime.film}"/>
        <c:set var="bookedRoom" value="${bookedShowtime.room}"/>
        <c:set var="bookedTheater" value="${bookedRoom.theater}"/>

        <h2 class="title"><fmt:message key="bookingFilmSectionTitle" bundle="${pageTitle}"/> </h2>

        <%--        Status Code Messages--%>
        <jsp:include page="/views/components/status-code-message.jsp"/>

        <div class="wrapper row">
            <div class="col-8 d-flex flex-column align-items-center">
                <h3 class="mb-4"><fmt:message bundle="${msg}" key="chooseYourSeat"/></h3>

                <div class="wrapper">
                    <div class="wrapper d-flex flex-column align-items-center overflow-auto">

                        <%--Seats table--%>
                        <table class="seats-table">
                            <tbody>
                            <c:set var="roomSeats" value="${showtimeIDAndSeatMatrix[bookedShowtime.showtimeID]}"/>
                            <c:forEach var="roomSeatsRow" items="${roomSeats}" varStatus="row">
                                <tr>
                                    <c:forEach var="roomSeat" items="${roomSeatsRow}" varStatus="seat">
                                        <td style="padding: 0; height: 2.5rem">
                                            <c:choose>
                                                <c:when test="${roomSeat eq '1' || roomSeat eq '2'}">
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

            <div class="col-4 d-flex flex-column align-items-center">
                <h3 class="mb-4"><fmt:message bundle="${msg}" key="infoBooking"/></h3>

                <div class="wrapper">
                    <p class="font-bold"><fmt:message bundle="${msg}" key="filmName"/>:
                        <span id="booked-film-name">${bookedFilm.filmName}</span>
                    </p>
                    <p class="font-bold"><fmt:message bundle="${msg}" key="ticketPrices"/>:
                        <span id="booked-film-price">${bookedFilm.filmPrice}</span> <span> VNĐ</span>
                    </p>
                    <p class="font-bold"><fmt:message bundle="${msg}" key="time"/>:
                        <span id="booked-showtime-date">${bookedShowtime.showtimeDate}</span>
                    </p>
                    <p class="font-bold"><fmt:message bundle="${msg}" key="room"/>:
                        <span id="booked-room-name">${bookedRoom.roomName}</span>
                    </p>
                    <p class="font-bold"><fmt:message bundle="${msg}" key="theater"/>:
                        <span id="booked-theater-name">${bookedTheater.theaterName}</span>
                    </p>
                    <p class="font-bold"><fmt:message bundle="${msg}" key="address"/>:
                        <span id="booked-theater-address">${bookedTheater.theaterAddress}</span>
                    </p>
                    <p class="font-bold"><fmt:message bundle="${msg}" key="totalPrice"/>:
                        <span id="total-fee">0 VNĐ</span>
                    </p>
                </div>
            </div>

        </div>

        <div class="wrapper d-flex flex-column align-items-center">
            <form action="<c:url value="${pageContext.request.contextPath}/auth/book-film"/>" method="post">
                <input type="hidden" name="seats" id="seats">
                <input type="submit" class="primary-filled-button rounded-button button" value="<fmt:message bundle="${msg}" key="booking"/>">
            </form>
        </div>
    </div>
</section>

<%--JavaScript--%>
<script type="module" src="<c:url value="/resources/js/handlesChooseSeats.js"/>"></script>
