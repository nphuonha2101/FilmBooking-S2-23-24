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

        <h2><fmt:message bundle="${adminMsg}" key="showtimeManagement"/></h2>

        <div class="centered-vertical-content wrapper">

            <%--        Status Code Messages--%>
            <jsp:include page="/views/components/statusCodeMessage.jsp"/>

            <div class="justify-right-row wrapper">
                <a href="<c:url value="${pageContext.request.contextPath}/admin/add/showtime"/>"
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
                            <a href="<c:url value="${pageContext.request.contextPath}/admin/delete/showtime?showtime=${showtime.slug}"/>">
                                <span class="material-symbols-rounded warning-color">delete</span>
                            </a>
                            <a href="<c:url value="${pageContext.request.contextPath}/admin/edit/showtime?showtime=${showtime.slug}"/>">
                                <span class="material-symbols-rounded primary-color">edit</span>
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
</section>