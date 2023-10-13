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
                    <p>Đạo diễn: ${film.director} </p>
                    <p>Diễn viên: ${film.cast}</p>
                    <p>Độ dài phim: ${film.filmLength} phút</p>
                    <br>
                    <p>Mô tả: ${film.filmDescription}</p>

                </div>
                </div>

            <br>
            <hr>
            <br>

            <div class="wrapper two-col__wrapper">
                <div class="wrapper">
                    <h3>Chọn ghế:</h3>
                    <table class="seats-table">
                        <tbody>
                        <c:forEach var="row" begin="0" end="10" varStatus="loop">
                            <tr>
                                <c:forEach var="col" begin="0" end="8" varStatus="loop">
                                    <td style="padding: 0; height: 2.5rem">
                                        <c:choose>
                                            <c:when test="${col eq '5'}">
                                                <button class="seats seats-unavailable">${row} ${col}</button>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="seats">${row} ${col}</button>
                                            </c:otherwise>
                                        </c:choose>

                                    </td>
                                </c:forEach>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="wrapper">
                    <h3>Chi tiết đặt vé</h3>
                    <p>Số ghế: <span id="selected-seat"></span> </p>
                    <p>Suất chiếu: <span id="selected-showtime"></span></p>
                    <p>Tổng tiền: <span id="total-fee"></span></p>
                    <select name="select-showtime" id="select-showtime">
                        <c:forEach var="test" begin="1" end="10" varStatus="loop">
                            <option value="test ${test}"> ${test} Option</option>
                        </c:forEach>
                    </select>
                </div>

            </div>

            <div class="wrapper centered-vertical-content">
                <form action="book-film" method="post">
                    <input type="hidden" name="film-price" id="film-price" value="${film.filmPrice}">
                    <input type="hidden" name="seats" id="seat">
                    <input type="hidden" name="showtime-id" id="showtime-id">
                    <input class="outlined-button button" type="submit" value="Đặt vé">
                </form>
            </div>
        </div>
    </div>

</section>

