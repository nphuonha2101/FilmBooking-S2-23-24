<%--
  Created by IntelliJ IDEA.
  User: QDang
  Date: 10-10-2023
  Time: 10:27
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
<fmt:setBundle basename="properties.messageAdmin" var="adminMsg"/>

<section class="section align-top admin-two-cols__wrapper centered-vertical-content">
    <div class="container ">
        <jsp:include page="/views/components/admin-panel.jsp"/>
    </div>
    <div class="container centered-vertical-content">

        <h1><fmt:message bundle="${adminMsg}" key="showtimeManagement"/></h1>

        <div class="centered-vertical-content wrapper">
            <div class="justify-right-row wrapper">
                <a href="add-showtime"
                   class="primary-filled-button rounded-button button submit-button icon-button">
                    <span class="material-symbols-rounded">add</span>
                    <span class="hidden-span"><fmt:message bundle="${adminMsg}" key="addNewShowtime"/></span>
                </a>
            </div>
            <table>
                <thead>
                <tr>
                    <th><fmt:message bundle="${adminMsg}" key="showtimeID"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="filmName"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="roomName"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="availableSeats"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="showtimeDate"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="actions"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="showtime" items="${showtimeList}" varStatus="loop">
                    <tr>
                        <td>${showtime.showtimeID}</td>
                        <td>${showtime.film.filmName}</td>
                        <td>${showtime.room.roomName}</td>
                        <td>${availableSeats[showtime.showtimeID]}</td>
                        <td>${showtime.showtimeDate}</td>

                        <td>
                            <form class="hide hidden-form" method="get">
                                <input type="hidden" name="showtime-id_hidden"
                                       value="${showtime.showtimeID}"/>
                            </form>
                            <button class="submit-button delete-button">
                                <span class="material-symbols-rounded warning-color">delete</span>
                            </button>
                            <button class="submit-button edit-button">
                                <span class="material-symbols-rounded primary-color">edit</span>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>