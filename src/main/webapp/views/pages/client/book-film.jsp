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
    <div class="container centered-vertical-content wrapper">
        <c:set var="film" value="${filmData}"/>

        <h1>${sectionTitle}</h1>

        <div id="film-details" class="wrapper">
            <div class="wrapper two-col__wrapper">
                <div class="film-img-in-card" style="background-image: url('<c:url value="${film.imgPath}"/>')"
                     id="film-img"></div>
                <div class="wrapper">
                    <h3> ${film.filmName}</h3>
                    <br>
                    <p>Giá vé: ${film.filmPrice} VNĐ/người</p>
                    <p>Đạo diễn: ${film.actors} </p>
                    <p>Độ dài phim: ${film.filmLength} phút</p>
                    <br>
                    <p>Mô tả: ${film.filmDescription}</p>

                </div>
            </div>

        </div>
    </div>

</section>

