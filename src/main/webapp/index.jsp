<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 16-09-2023
  Time: 7:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Film Booking</title>
    <%@include file="head-links.jsp" %>
    <%@include file="already-login-nav.jsp" %>
</head>
<style>
    <%@include file="resources/css/style.css" %>
</style>
<body>

<section class="slides-show section">
    <div class="slides-show wrapper">
        <h1>Our new films: </h1>
        <div class="slides wrapper">
            <!-- Card 1 -->
            <div class="slide-cards container" id="card-1">
                <h2>ABC1</h2>
                <p>P.201</p>
                <p>04:30</p>
            </div>

            <!-- Card 2 -->
            <div class="slide-cards container" id="card-2">
                <h2>ABC2</h2>
                <p>P.201</p>
                <p>04:30</p>
            </div>

            <!-- Card 1 -->
            <div class="slide-cards container" id="card-3">
                <h2>ABC3</h2>
                <p>P.201</p>
                <p>04:30</p>
            </div>

            <!-- Card 4 -->
            <div class="slide-cards container" id="card-4" onclick="">

                <h2>ABC4</h2>
                <p>P.201</p>
                <p>04:30</p>
                <form class="hidden-form" action="" method="get">
                    <input type="hidden" name="film-id" value="F110">
                    <input class="light-filled-button button" type="submit" value="Booking now">
                </form>
            </div>
        </div>
    </div>
</section>

<%@include file="footer.jsp" %>
</body>
</html>
