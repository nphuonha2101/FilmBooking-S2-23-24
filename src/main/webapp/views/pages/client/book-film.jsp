<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 03-Oct-23
  Time: 2:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<section class="section centered-vertical-content">
    <c:set var="film" value="${filmData}"/>

    <h1>${sectionTitle}</h1>

    <div id="film-details" class="wrapper">
        <div class="wrapper two-col__wrapper">
            <div class="film-img-in-card" style="background-image: url('<c:url value="${film.imgPath}"/>')"
                 id="film-img"></div>
            <div class="wrapper">
                <h1> ${film.filmName}</h1>
                <p>Giá vé: ${film.filmPrice} VNĐ/người</p>
                <p>Đạo diễn: ${film.actors} </p>
                <p>Độ dài phim: ${film.filmLength} phút</p>
            </div>
        </div>
        <div id="film-description" class="wrapper">
            <h3>Mô tả:</h3>
            <p> ${film.filmDescription}</p>
        </div>
    </div>

</section>

