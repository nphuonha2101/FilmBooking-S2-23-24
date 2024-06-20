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

<section class="section justify-content-start admin-two-cols__wrapper d-flex flex-column align-items-center">
    <div class="container ">
        <jsp:include page="/views/components/admin-panel.jsp"/>
    </div>
    <div class="container d-flex flex-column align-items-center">

        <h2><fmt:message bundle="${adminMsg}" key="addShowtime"/></h2>


        <div class="d-flex flex-column align-items-center wrapper">

            <%-- Status Code Messages--%>
            <jsp:include page="/views/components/status-code-message.jsp"/>

            <div>
                <form method="post" action="<c:url value="${pageContext.request.contextPath}/admin/add/showtime"/>">
                    <div class="form-floating mb-3">
                        <label for="film-id"><fmt:message bundle="${adminMsg}" key="filmName"/>:
                            <span class="warning-color"> *</span>
                        </label>
                        <select class="form-control" name="film-id" id="film-id">
                            <c:forEach var="film" items="${filmData}" varStatus="loop">
                                <option value="${film.filmID}">${film.filmName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-floating mb-3">
                        <label for="room-id"><fmt:message bundle="${adminMsg}" key="roomName"/>:
                            <span class="warning-color"> *</span>
                        </label>
                        <select class="form-control" name="room-id" id="room-id">
                            <c:forEach var="room" items="${roomData}" varStatus="loop">
                                <option value="${room.roomID}">${room.roomName} - ${room.theater.theaterName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-floating mb-3">
                        <label for="showtime-datetime"><fmt:message bundle="${adminMsg}" key="showtimeDate"/>:
                            <span class="warning-color"> *</span>
                        </label>
                        <input class="form-control" type="datetime-local"
                               placeholder="<fmt:message bundle="${adminMsg}" key="showtimeDate"/>"
                               name="showtime-datetime" id="showtime-datetime"
                               required/>
                    </div>

                    <div class="d-flex flex-column align-items-center">
                        <input class="primary-filled-button button" type="submit"
                               value="<fmt:message bundle="${adminMsg}" key="addShowtime"/>">
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>