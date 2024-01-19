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
    <title><fmt:message key="invoiceInfoTitle" bundle="${pageTitle}"/></title>

    <jsp:include page="/views/components/head-links.jsp"/>

    <style>
        @media print {
            body {
                font-size: 10pt;
                width: 148mm;
                height: 210mm;
                margin: 0;
                padding: 0;
                background-color: white;
                page-break-after: always;
                line-height: 1.1;
            }
        }
    </style>
</head>
<body>
<c:set var="filmBooking" value="${bookedFilmBooking}"/>
<c:set var="showtime" value="${filmBooking.showtime}"/>
<c:set var="film" value="${showtime.film}"/>
<c:set var="room" value="${showtime.room}"/>
<c:set var="theater" value="${room.theater}"/>
<main>
    <section>
        <c:forEach var="seat" items="${filmBooking.seats}" varStatus="loop">
            <div class="centered-vertical-content">
                <p>================================================</p>

                <p><fmt:message key="documentTypeNo" bundle="${msg}"/> : FBInvoice-0123</p>
                <h3 class="font-Merriweather">${theater.theaterName}</h3>
                <p ><fmt:message key="address" bundle="${msg}"/>: ${theater.theaterAddress}</p>


                <p>-----------------------------------------------</p>

                <h3 >${film.filmName}</h3> <span><h3>#${loop.count}</h3></span>
                <p ><fmt:message key="ticketPrices" bundle="${msg}"/>: ${film.filmPrice} VNĐ</p>
                <p ><fmt:message key="seat" bundle="${msg}"/>: ${seat}</p>
                <p ><fmt:message key="room" bundle="${msg}"/>: ${room.roomName}</p>
                <p ><fmt:message key="showtimeDate" bundle="${msg}"/>: ${showtime.showtimeDate}</p>

                <p>-----------------------------------------------</p>

                <p ><fmt:message key="bookingDate" bundle="${msg}"/>: ${filmBooking.bookingDate}</p>

                <p  style="text-align: center">
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
