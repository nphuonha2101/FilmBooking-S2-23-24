<%--
  Created by IntelliJ IDEA.
  User: QDang
  Date: 17-10-2023
  Time: 14:14
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

        <h1><fmt:message bundle="${adminMsg}" key="roomManagement"/></h1>

        <div class="centered-vertical-content wrapper">

            <%--        Status Code Messages--%>
            <jsp:include page="/views/components/statusCodeMessage.jsp"/>

            <div class="justify-right-row wrapper">
                <a href="add-room" class="primary-filled-button rounded-button button submit-button icon-button">
                    <span class="material-symbols-rounded">add</span>
                    <span class="hidden-span"><fmt:message bundle="${adminMsg}" key="addNewRoom"/></span>
                </a>
            </div>
            <table>
                <thead>
                <tr>
                    <th><fmt:message bundle="${adminMsg}" key="roomID"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="roomName"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="roomRows"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="roomCols"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="theaterAgency"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="totalSeats"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="actions"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="room" items="${roomData}" varStatus="loop">
                    <tr>
                        <td>${room.roomID}</td>
                        <td>${room.roomName}</td>
                        <td>${room.seatRows}</td>
                        <td>${room.seatCols}</td>
                        <td>${room.theater.theaterName}</td>
                        <td>${room.seatRows * room.seatCols}</td>

                        <td>
                            <form class="hide hidden-form" method="get">
                                <input type="hidden" name="room-id_hidden"
                                       value="${room.roomID}"/>
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

        <%--        Pagination--%>
        <jsp:include page="/views/components/pagination.jsp"/>

    </div>
</section>