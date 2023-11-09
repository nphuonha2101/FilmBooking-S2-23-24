<%--
  Created by IntelliJ IDEA.
  User: nphuonha
  Date: 11/7/23
  Time: 9:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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
                        <h3>Không có dữ liệu!</h3>
                    </div>
                </c:when>


                <c:otherwise>
                    <c:forEach var="filmBookingData" items="${filmBookingsData}">
                        <%--get showtime id for each filmbooking--%>
                        <c:set var="showtime" value="${showtimesData[filmBookingData.showtimeID]}"/>
                        <%--get theater for each showtime--%>
                        <c:set var="theater" value="${theatersData[showtime.showtimeID]}"/>

                        <div class="wrapper accordion-wrapper">
                            <button class="accordion wrapper">${filmBookingData.username}
                                - ${filmBookingData.bookingDate}</button>
                            <div class="accordion-panel">
                                <div class="two-col__wrapper wrapper">
                                    <div class="wrapper">
                                        <p class="bold">Ngày đặt vé: <span>${filmBookingData.bookingDate}</span></p>
                                        <p class="bold">Các ghế đã đặt: <span>${filmBookingData.seatsData}</span></p>
                                        <p class="bold">Tổng tiền: <span>${filmBookingData.totalFee}</span> <span>VNĐ</span></p>
                                    </div>

                                    <div class="wrapper">
                                        <p class="bold">Tên phim:
                                            <span>${showtimeViewsData[filmBookingData.showtimeID].filmName}</span>
                                        </p>
                                        <p class="bold">Tên phòng:
                                            <span>${showtimeViewsData[filmBookingData.showtimeID].roomName}</span></p>
                                        <p class="bold">Giờ chiếu phim:
                                            <span>${showtimeViewsData[filmBookingData.showtimeID].showtimeDate}</span>
                                        </p>
                                        <p class="bold">Tên rạp:
                                            <span>${theatersData[theater.theaterID].theaterName}</span>
                                        </p>
                                        <p class="bold">Địa chỉ rạp:
                                            <span>${theatersData[theater.theaterID].theaterAddress}</span>

                                        </p>
                                    </div>
                                </div>
                                <c:if test="${sessionScope.loginUser.accountRole eq 'admin'}">
                                    <div class="wrapper justify-right-row">
                                        <div class="justify-right-row wrapper">
                                            <a class="primary-filled-button button rounded-button" target="_blank" href="invoice-info?booking-id=${filmBookingData.filmBookingID}">
                                                In hoá đơn
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
