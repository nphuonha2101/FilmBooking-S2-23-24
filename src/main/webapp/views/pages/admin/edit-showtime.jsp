<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 22-10-2023
  Time: 7:53 PM
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

<section class="section align-top admin-two-cols__wrapper d-flex flex-column align-items-center">

    <jsp:include page="/views/components/admin-panel.jsp"/>

    <div class="container d-flex flex-column align-items-center">

        <h2><fmt:message bundle="${adminMsg}" key="editShowtime"/></h2>


        <div class="d-flex flex-column align-items-center wrapper">

            <%-- Status Code Messages--%>
            <jsp:include page="/views/components/status-code-message.jsp"/>


            <c:set var="editShowtime" value="${editShowtime}"/>
            <form class="w-30 mb-3" method="post"
                  action="<c:url value="${pageContext.request.contextPath}/admin/edit/showtime"/>">
                <div class="form-floating mb-3">
                    <input class="form-control readonly-input" type="text"
                           placeholder="<fmt:message bundle="${adminMsg}" key="showtimeID"/>" name="showtime-id"
                           id="showtime-id"
                           value="${editShowtime.showtimeID}"
                           readonly/>
                    <label for="showtime-id"><fmt:message bundle="${adminMsg}" key="showtimeID"/>:
                    </label>
                </div>

                <div class="form-floating mb-3">
                    <select class="form-control" name="film-id" id="film-id">
                        <c:forEach var="film" items="${filmData}" varStatus="loop">
                            <c:choose>
                                <c:when test="${editShowtime.film.filmID eq film.filmID}">
                                    <option selected value="${film.filmID}">${film.filmName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${film.filmID}">${film.filmName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    <label for="film-id"><fmt:message bundle="${adminMsg}" key="filmName"/>:
                        <span class="warning-color"> *</span>
                    </label>
                </div>

                <div class="form-floating mb-3">
                    <select class="form-control" name="room-id" id="room-id">
                        <c:forEach var="room" items="${roomData}" varStatus="loop">
                            <c:choose>
                                <c:when test="${editShowtime.room.roomID eq room.roomID}">
                                    <option selected value="${room.roomID}">${room.roomName}
                                        - ${room.theater.theaterName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${room.roomID}">${room.roomName}
                                        - ${room.theater.theaterName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    <label for="room-id"><fmt:message bundle="${adminMsg}" key="roomName"/>:
                        <span class="warning-color"> *</span>
                    </label>
                </div>

                <div class="form-floating mb-3">
                    <input class="form-control" type="datetime-local"
                           placeholder="<fmt:message bundle="${adminMsg}" key="showtimeDate"/>"
                           name="showtime-datetime" id="showtime-datetime"
                           value="${editShowtime.showtimeDate}"
                           required/>
                    <label for="showtime-datetime"><fmt:message bundle="${adminMsg}" key="showtimeDate"/>:
                        <span class="warning-color"> *</span>
                    </label>
                </div>

                <div class="d-flex flex-column align-items-center">
                    <input class="primary-filled-button rounded-button button" type="submit"
                           value="<fmt:message bundle="${adminMsg}" key="editShowtime"/>">
                </div>
            </form>
        </div>
    </div>
</section>
