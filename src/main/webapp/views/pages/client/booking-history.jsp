<%--
  Created by IntelliJ IDEA.
  User: nphuonha
  Date: 11/7/23
  Time: 9:21 PM
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

<section class="section centered-vertical-content">
    <c:set var="filmBookingsData" value="${filmBookings}"/>
    <c:set var="showtimeViewsData" value="${showtimeViewsMap}"/>
    <c:set var="theatersData" value="${theatersMap}"/>
    <c:set var="showtimesData" value="${showtimesMap}"/>

    <div class="container wrapper centered-vertical-content">
        <h1>${sectionTitle}</h1>

        <div class="wrapper centered-vertical-content">
            <c:choose>
                <c:when test="${empty filmBookingsData || empty showtimeViewsData }">
                    <div class="wrapper centered-vertical-content">
                        <h3><fmt:message bundle="${msg}" key="null"/></h3>
                    </div>
                </c:when>


                <c:otherwise>
                    <c:forEach var="filmBookingData" items="${filmBookingsData}">
                        <%--get showtime id for each filmbooking--%>
                        <c:set var="showtime" value="${showtimesData[filmBookingData.showtimeID]}"/>
                        <%--get theater for each showtime--%>
                        <c:set var="theater" value="${theatersData[showtime.showtimeID]}"/>

                        <div class="wrapper accordion-wrapper">
                            <button class="accordion wrapper"><span class="font-bold"><fmt:message bundle="${msg}" key="username"/>:</span> ${filmBookingData.username}
                                - <span class="font-bold"><fmt:message bundle="${msg}" key="date"/>:</span> ${filmBookingData.bookingDate}</button>
                            <div class="accordion-panel">
                                <div class="two-col__wrapper wrapper">
                                    <div class="wrapper">
                                        <p class="font-bold"><fmt:message bundle="${msg}" key="date"/>: <span>${filmBookingData.bookingDate}</span></p>
                                        <p class="font-bold"><fmt:message bundle="${msg}" key="seat"/>: <span>${filmBookingData.seatsData}</span></p>
                                        <p class="font-bold"><fmt:message bundle="${msg}" key="totalPrice"/>: <span>${filmBookingData.totalFee}</span> <span>VNĐ</span></p>
                                    </div>

                                    <div class="wrapper">
                                        <p class="font-bold"><fmt:message bundle="${msg}" key="filmName"/>:
                                            <span>${showtimeViewsData[filmBookingData.showtimeID].filmName}</span>
                                        </p>
                                        <p class="font-bold"><fmt:message bundle="${msg}" key="room"/>:
                                            <span>${showtimeViewsData[filmBookingData.showtimeID].roomName}</span></p>
                                        <p class="font-bold"><fmt:message bundle="${msg}" key="showtime"/>:
                                            <span>${showtimeViewsData[filmBookingData.showtimeID].showtimeDate}</span>
                                        </p>
                                        <p class="font-bold"><fmt:message bundle="${msg}" key="theater"/>:
                                            <span>${theatersData[theater.theaterID].theaterName}</span>
                                        </p>
                                        <p class="font-bold"><fmt:message bundle="${msg}" key="address"/>:
                                            <span>${theatersData[theater.theaterID].theaterAddress}</span>

                                        </p>
                                    </div>
                                </div>
                                <c:if test="${sessionScope.loginUser.accountRole eq 'admin'}">
                                    <div class="wrapper justify-right-row">
                                        <div class="justify-right-row wrapper">
                                            <a class="primary-filled-button button rounded-button" target="_blank" href="invoice-info?booking-id=${filmBookingData.filmBookingID}">
                                                <fmt:message bundle="${msg}" key="print"/>
                                            </a>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</section>