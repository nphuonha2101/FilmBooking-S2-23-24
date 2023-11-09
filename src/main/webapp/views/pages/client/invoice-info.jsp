<%--
  Created by IntelliJ IDEA.
  User: nphuonha
  Date: 11/9/23
  Time: 10:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html lang="vi">
<head>
    <title>${pageTitle}</title>

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

                <p class="code">Mẫu số: FBInvoice-0123</p>
                <h1 class="code bold">${theater.theaterName}</h1>
                <p class="code">Địa chỉ: ${theater.theaterAddress}</p>


                <p>-----------------------------------------------</p>

                <h2 class="code">${film.filmName}</h2> <span><h3>#${loop.count}</h3></span>
                <p class="code">Giá vé: ${film.filmPrice} VNĐ</p>
                <p class="code">Ghế: ${seat}</p>
                <p class="code">Tên phòng: ${room.roomName}</p>
                <p class="code">Ngày chiếu: ${showtime.showtimeDate}</p>


                <p>-----------------------------------------------</p>

                <p class="code">Ngày đặt vé: ${filmBooking.bookingDate}</p>

                <p class="code" style="text-align: center">
                    Hoá đơn này có giá trị sử dụng thay cho vé xem phim và chỉ có hiệu lực trong ngày.
                    <br>
                    Mọi khiếu nại xin vui lòng liên hệ chúng tôi qua email: <a href="mailto:filmbookingdn@gmail.com">filmbookingdn@gmail.com</a>
                    <br>
                    Xin cảm ơn quý khách!
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
