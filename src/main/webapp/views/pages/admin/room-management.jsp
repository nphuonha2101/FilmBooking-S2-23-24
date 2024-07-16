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

<section class="section justify-content-start d-flex flex-column align-items-center">

    <jsp:include page="/views/components/admin-panel.jsp"/>

    <div class="container d-flex flex-column align-items-center">

        <h2><fmt:message bundle="${adminMsg}" key="roomManagement"/></h2>

        <div class="d-flex flex-column align-items-center wrapper">

            <%--        Status Code Messages--%>
            <jsp:include page="/views/components/status-code-message.jsp"/>

            <div class="d-flex justify-content-end wrapper">
                <a href="<c:url value="${pageContext.request.contextPath}/admin/add/room"/>"
                   class="rotate-icon primary-filled-button rounded-button button submit-button icon-button">
                    <span class="material-symbols-rounded">add</span>
                    <span class="hidden-span"><fmt:message bundle="${adminMsg}" key="addNewRoom"/></span>
                </a>
            </div>
            <table class="room-table">
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
                    <tr id="room-row-${room.roomID}">
                        <td>${room.roomID}</td>
                        <td>${room.roomName}</td>
                        <td>${room.seatRows}</td>
                        <td>${room.seatCols}</td>
                        <td>${room.theater.theaterName}</td>
                        <td>${room.seatRows * room.seatCols}</td>

                        <td>
                            <span onclick="deleteRoom('${room.roomID}','${room.slug}' )"
                                  class="rotate-icon material-symbols-rounded warning-color">delete</span>
                            <a href="<c:url value="${pageContext.request.contextPath}/admin/edit/room?room=${room.slug}"/>">
                                <span class="material-symbols-rounded primary-color rotate-icon">edit</span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <%--        Pagination--%>
        <jsp:include page="/views/components/pagination.jsp"/>

    </div>
    <script>
        function deleteRoom(roomId, roomSlug) {
            let id = getIdRoom(roomId);
            $.ajax({
                url: '${pageContext.request.contextPath}/admin/delete/room',
                method: 'POST',
                data: {room: roomSlug},
                success: function (data) {
                    alert('Xoá phòng thành công!');
                    $('#room-row-' + id).remove();
                    // $('.room-table tbody').reload();
                },
                error: function (xhr, status, error) {
                    alert('Có lỗi khi xóa phòng: ' + error);
                }
            });
        }

        function getIdRoom(roomId) {
            return roomId;
        }
    </script>
</section>