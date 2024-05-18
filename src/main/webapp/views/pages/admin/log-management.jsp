    <%--
  Created by IntelliJ IDEA.
  User: Moc Lan
  Date: 4/14/2024
  Time: 8:36 PM
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

        <h2><fmt:message bundle="${adminMsg}" key="logManagement"/></h2>

        <div class="centered-vertical-content wrapper">

            <%--        Status Code Messages--%>
            <jsp:include page="/views/components/status-code-message.jsp"/>

                <table id="myTable" class="display dataTable">
                    <thead>
                    <tr>
                        <th>LogID</th>
                        <th>Username</th>
                        <th>Action</th>
                        <th>Level</th>
                        <th>Target table</th>
                        <th>Before Value</th>
                        <th>After Value</th>
                        <th>Created At</th>
                        <th>Update At</th>
                    </tr>
                    </thead>
                </table>
        </div>

        <%--        Pagination--%>
        <jsp:include page="/views/components/pagination.jsp"/>

    </div>

    <script type="module" src="<c:url value="/resources/js/handlesShowLog.js"/>"></script>

</section>
