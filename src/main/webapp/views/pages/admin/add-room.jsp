<%--
  Created by IntelliJ IDEA.
  User: ConMuaXaDan
  Date: 10/24/2023
  Time: 7:54 AM
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

        <h2><fmt:message bundle="${adminMsg}" key="addRoom"/></h2>


        <div class="d-flex flex-column align-items-center wrapper">

            <%-- Status Code Messages--%>
            <jsp:include page="/views/components/status-code-message.jsp"/>


            <form class="w-30 mb-3" method="post" action="<c:url value="${pageContext.request.contextPath}/admin/add/room"/>">
                <div class="form-floating mb-3">
                    <input class="form-control" type="text"
                           placeholder="<fmt:message bundle="${adminMsg}" key="roomName"/>"
                           name="room-name"
                           id="room-name"
                           required/>
                    <label for="room-name"><fmt:message bundle="${adminMsg}" key="roomName"/>:
                        <span class="warning-color"> *</span>
                    </label>
                </div>

                <div class="form-floating mb-3">
                    <input class="form-control" type="number" min="0"
                           placeholder="<fmt:message bundle="${adminMsg}" key="roomRows"/>"
                           name="room-rows"
                           id="room-rows"
                           required/>
                    <label for="room-rows"><fmt:message bundle="${adminMsg}" key="roomRows"/>:
                        <span class="warning-color"> *</span>
                    </label>
                </div>

                <div class="form-floating mb-3">
                    <input class="form-control" type="number" min="0"
                           placeholder="<fmt:message bundle="${adminMsg}" key="roomCols"/>"
                           name="room-cols"
                           id="room-cols"
                           required/>
                    <label for="room-cols"><fmt:message bundle="${adminMsg}" key="roomCols"/>:
                        <span class="warning-color"> *</span>
                    </label>
                </div>

                <div class="form-floating mb-3">
                    <select class="form-control" id="theater-id" name="theater-id">
                        <c:forEach var="theater" items="${theaters}">
                            <option value="${theater.theaterID}">${theater.theaterName}
                                - ${theater.theaterAddress}</option>
                        </c:forEach>
                    </select>
                    <label for="theater-id"><fmt:message bundle="${adminMsg}" key="theaterAgency"/>:
                        <span class="warning-color"> *</span>
                    </label>
                </div>
                <div class="d-flex justify-content-center">
                    <input class="primary-filled-button rounded-button button" type="submit"
                           value="<fmt:message bundle="${adminMsg}" key="addRoom"/>">
                </div>

            </form>
        </div>
    </div>
</section>