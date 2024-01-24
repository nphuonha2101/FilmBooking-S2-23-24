<%--
  Created by IntelliJ IDEA.
  User: QDang
  Date: 17-10-2023
  Time: 13:05
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

        <h2><fmt:message bundle="${adminMsg}" key="addShowtime"/></h2>


        <div class="centered-vertical-content wrapper">

            <%-- Status Code Messages--%>
            <jsp:include page="/views/components/statusCodeMessage.jsp"/>

            <div>
                <form method="post" action="${pageContext.request.contextPath}/admin/add/showtime">

                    <label for="film-id"><fmt:message bundle="${adminMsg}" key="filmName"/>:
                        <span class="warning-color"> *</span>
                    </label>
                    <select name="film-id" id="film-id">
                        <c:forEach var="film" items="${filmData}" varStatus="loop">
                            <option value="${film.filmID}">${film.filmName}</option>
                        </c:forEach>
                    </select>

                    <label for="room-id"><fmt:message bundle="${adminMsg}" key="roomName"/>:
                        <span class="warning-color"> *</span>
                    </label>
                    <select name="room-id" id="room-id">
                        <c:forEach var="room" items="${roomData}" varStatus="loop">
                            <option value="${room.roomID}">${room.roomName} - ${room.theater.theaterName}</option>
                        </c:forEach>
                    </select>

                    <label for="showtime-datetime"><fmt:message bundle="${adminMsg}" key="showtimeDate"/>:
                        <span class="warning-color"> *</span>
                    </label>
                    <input type="datetime-local" placeholder="<fmt:message bundle="${adminMsg}" key="showtimeDate"/>"
                           name="showtime-datetime" id="showtime-datetime"
                           required/>

                    <div class="centered-vertical-content">
                        <input class="primary-filled-button button" type="submit"
                               value="<fmt:message bundle="${adminMsg}" key="addShowtime"/>">
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>