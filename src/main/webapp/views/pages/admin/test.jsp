<%--
  Created by IntelliJ IDEA.
  User: QDang
  Date: 09-10-2023
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<section class="section container centered-vertical-content">
    <h1>${sectionTitle}</h1>
    <div class="slide wrapper">
        <%--        <c:forEach var="film" items="${filmsData}" varStatus="loop">--%>
        <%--            <div class="film-card two-col__wrapper" id="card-${loop.index}">--%>
        <%--                <div class="row-align wrapper">--%>
        <%--                    <h4>${film.filmName}</h4>--%>
        <%--                    <p>${film.price}</p>--%>
        <%--                    <p>${film.genre}</p>--%>
        <%--                    <form class="hide hidden-form" method="get">--%>
        <%--                        <input type="hidden" name="film-id_hidden"--%>
        <%--                               value="${film.filmID}"/>--%>
        <%--                    </form>--%>
        <%--                </div>--%>
        <%--                <div class="wrapper align-center">--%>
        <%--                   --%>
        <%--                    <button class="primary-filled-button rounded-button button submit-button">In hoá đơn--%>
        <%--                    </button>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </c:forEach>--%>
<%--        <table>--%>
<%--            <c:forEach var="row" begin="1" end="10" varStatus="loop">--%>
<%--                <tr>--%>
<%--                    <c:forEach var="col" begin="1" end="8" varStatus="loop">--%>
<%--                        <td>--%>
<%--                            <button class="primary-filled-button button" onclick="alert(this.textContent)">${row}${col}</button>--%>
<%--                        </td>--%>
<%--                    </c:forEach>--%>

<%--                </tr>--%>
<%--            </c:forEach>--%>

<%--        </table>--%>

        <form action="test">
<%--            <label for="date">Choose a date for your booking:</label>--%>
<%--            <input type="date" name="date" id="date">--%>
<%--            <label for="time">Choose a time for your booking:</label>--%>
<%--            <input type="time" name="time" id="time">--%>
<%--            <input type="submit" value="submit">--%>
            <label for="date-time">Choose a datetime for your booking</label>
            <input type="datetime-local" name="date-time" id="date-time">
            <input type="submit" value="submit">
        </form>
    </div>
</section>

