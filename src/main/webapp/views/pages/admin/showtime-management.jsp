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

<section class="section align-top d-flex flex-column align-items-center">

    <jsp:include page="/views/components/admin-panel.jsp"/>

    <div class="container d-flex flex-column align-items-center">

        <h2><fmt:message bundle="${adminMsg}" key="showtimeManagement"/></h2>

        <div class="d-flex flex-column align-items-center wrapper">

            <%--        Status Code Messages--%>
            <jsp:include page="/views/components/status-code-message.jsp"/>

            <div class="d-flex justify-content-end wrapper">
                <a href="<c:url value="${pageContext.request.contextPath}/admin/add/showtime"/>"
                   class="rotate-icon primary-filled-button rounded-button button submit-button icon-button">
                    <span class="material-symbols-rounded">add</span>
                    <span class="hidden-span"><fmt:message bundle="${adminMsg}" key="addNewShowtime"/></span>
                </a>
            </div>
            <table class="showtime-table">
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
                    <tr id="showtime-row-${showtime.showtimeID}">
                        <td>${showtime.showtimeID}</td>
                        <td>${showtime.film.filmName}</td>
                        <td>${showtime.room.roomName}</td>
                        <td>${availableSeats[showtime.showtimeID]}</td>
                        <td>${showtime.showtimeDate}</td>

                        <td>
                            <span onclick="deleteShowtime('${showtime.showtimeID}','${showtime.slug}')"
                                  class="rotate-icon material-symbols-rounded warning-color">delete</span>
                            <a href="<c:url value="${pageContext.request.contextPath}/admin/edit/showtime?showtime=${showtime.slug}"/>">
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
        function deleteShowtime(showtimeId, showtimeSlug) {
            var id = getIdShowtime(showtimeId);
            $.ajax({
                url: '${pageContext.request.contextPath}/admin/delete/showtime',
                method: 'POST',
                data: {showtime: showtimeSlug},
                success: function (data) {
                    alert('Showtime deleted successfully');
                    $('#showtime-row-' + id).remove();
                    // $('.room-table tbody').reload();
                },
                error: function (xhr, status, error) {
                    alert('Error deleting showtime: ' + error);
                }
            });
        }

        function getIdShowtime(showtimeId) {
            return showtimeId;
        }
    </script>
</section>