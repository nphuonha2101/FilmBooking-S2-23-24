<%--
  Created by IntelliJ IDEA.
  User: nphuonha
  Date: 18-Jan-24
  Time: 7:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@taglib prefix="fn" uri="jakarta.tags.functions" %>

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

<c:set var="filmBooking" value="${sessionScope.filmBooking}"/>
<c:set var="showtime" value="${filmBooking.showtime}"/>
<c:set var="film" value="${showtime.film}"/>
<c:set var="room" value="${showtime.room}"/>
<c:set var="theater" value="${room.theater}"/>
<c:set var="bookedSeatsNumber" value="${fn:length(fn:split(filmBooking.seatsData, ','))}"/>


<section class="section" id="checkout_two-cols">
    <div class="container wrapper centered-vertical-content">
        <h2><fmt:message bundle="${pageTitle}" key="boookingInfoSectionTitle"/></h2>
        <div class="wrapper">
            <div class="two-col__wrapper">
                <div class="film-img-box div-img" style="background-image: url('<c:url value="${film.imgPath}"/>')"
                     id="film-img"></div>
                <div class="wrapper">
                    <a href="${pageContext.request.contextPath}/film-info?film=${film.slug}"> <h3>${film.filmName}</h3></a>


                    <p class="font-bold"><fmt:message bundle="${msg}" key="ticketPrices"/>: <span>${film.filmPrice} VNĐ/<fmt:message
                            bundle="${msg}" key="person"/></span></p>
                    <p class="font-bold"><fmt:message bundle="${msg}" key="showtime"/>:
                        <span>${showtime.showtimeDate}</span></p>
                    <p class="font-bold"><fmt:message bundle="${msg}" key="seat"/>:
                        <span>${filmBooking.seatsData}</span></p>
                    <p class="font-bold"><fmt:message bundle="${msg}" key="room"/>:
                        <span>${room.roomName}</span></p>
                    <p class="font-bold"><fmt:message bundle="${msg}" key="theater"/>:
                        <span>${theater.theaterName}</span></p>
                    <p class="font-bold"><fmt:message bundle="${msg}" key="address"/>:
                        <span>${theater.theaterAddress}</span></p>
                </div>
            </div>
        </div>
    </div>

    <div class="container wrapper centered-vertical-content">
        <h2><fmt:message bundle="${pageTitle}" key="paymentSectionTitle"/></h2>

        <div class="wrapper">
            <table>
                <tr>
                    <td>${bookedSeatsNumber}x</td>
                    <td>${film.filmPrice}</td>

                </tr>
                <tr>
                    <td>
                        <fmt:message bundle="${msg}" key="totalPrice"/>
                    </td>
                    <td>
                        <b>${filmBooking.totalFee}</b>
                    </td>
                </tr>
            </table>

            <h3><fmt:message bundle="${msg}" key="paymentMethod"/></h3>

            <form action="${pageContext.request.contextPath}/auth/checkout" method="post">
                <div class="centered-horizontal-content wrapper justify-left-row">
                    <input type="radio" name="payment-method" value="cash" checked>
                    &nbsp;&nbsp;
                    <img src="<c:url value="/resources/images/icons8-cash-35.png"/>" alt="cash">
                    &nbsp;&nbsp;
                    <span><fmt:message bundle="${msg}" key="cash"/></span>
                </div>
                <div class="centered-horizontal-content wrapper justify-left-row">
                    <input type="radio" name="payment-method" value="vnpay">
                    &nbsp;&nbsp;
                    <img src="<c:url value="/resources/images/logo-vnpay.png"/>" alt="VNPay">
                    &nbsp;&nbsp;
                    <span>VNPAY</span>
                </div>

                <input type="submit" class="primary-filled-button button rounded-button" value="<fmt:message bundle="${msg}" key="payment"/>"/>
            </form>
        </div>
    </div>
</section>
