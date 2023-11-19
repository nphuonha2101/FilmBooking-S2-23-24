<%--
  Created by IntelliJ IDEA.
  User: nphuonha
  Date: 11/9/23
  Time: 10:11 PM
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
<fmt:setBundle basename="properties.message" var="msg"/>
<fmt:setBundle basename="properties.pageTitle" var="pageTitle"/>

<!DOCTYPE html>
<c:choose>
    <c:when test="${empty sessionScope.lang || sessionScope.lang eq 'default'}">
        <html lang="vi">
    </c:when>
    <c:otherwise>
        <html lang="en">
    </c:otherwise>
</c:choose>
<head>
    <title><fmt:message key="invoiceInfoTitle" bundle="${pageTitle}"/> </title>

    <jsp:include page="/views/components/head-links.jsp"/>

</head>
<body>
<c:set var="filmBooking" value="${bookedFilmBooking}"/>
<c:set var="film" value="${bookedFilm}"/>
<c:set var="showtime" value="${bookedShowtime}"/>
<c:set var="room" value="${bookedRoom}"/>
<c:set var="theater" value="${bookedTheater}"/>
<main>
    <section>
        <c:forEach var="seat" items="${filmBooking.seats}" varStatus="loop">
            <div class="centered-vertical-content">
                <p>================================================</p>

                <p class="code"><fmt:message key="documentTypeNo" bundle="${msg}"/> : FBInvoice-0123</p>
                <h2 class="code bold">${theater.theaterName}</h2>
                <p class="code"><fmt:message key="address" bundle="${msg}"/>: ${theater.theaterAddress}</p>


                <p>-----------------------------------------------</p>

                <h2 class="code">${film.filmName}</h2> <span><h3>#${loop.count}</h3></span>
                <p class="code"><fmt:message key="ticketPrices" bundle="${msg}"/>: ${film.filmPrice} VNĐ</p>
                <p class="code"><fmt:message key="seat" bundle="${msg}"/>: ${seat}</p>
                <p class="code"><fmt:message key="room" bundle="${msg}"/>: ${room.roomName}</p>
                <p class="code"><fmt:message key="showtimeDate" bundle="${msg}"/>: ${showtime.showtimeDate}</p>


                <p>-----------------------------------------------</p>

                <p class="code"><fmt:message key="bookingDate" bundle="${msg}"/>: ${filmBooking.bookingDate}</p>

                <p class="code" style="text-align: center">
                    <fmt:message key="invoiceInfoThanks" bundle="${msg}"/>
                </p>

                <p>================================================</p>

            </div>
        </c:forEach>
    </section>
</main>
<script>
    window.onload = () => {
        window.print();
        setTimeout(() => window.close(), 5000);
    }
</script>
</body>
</html>
