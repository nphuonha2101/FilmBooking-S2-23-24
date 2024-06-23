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

<section class="section align-top d-flex flex-column align-items-center">

    <jsp:include page="/views/components/admin-panel.jsp"/>

    <div class="container d-flex flex-column align-items-center">

        <h2 class="mb-5"><fmt:message bundle="${adminMsg}" key="logManagement"/></h2>

        <div class="d-flex flex-column align-items-center wrapper">

            <%--        Status Code Messages--%>
            <jsp:include page="/views/components/status-code-message.jsp"/>

            <table id="myTable" class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>LogID</th>
                    <th>Username</th>
                    <th>Action</th>
                    <th>Level</th>
                    <th>Target table</th>
<%--                    <th>Before Value</th>--%>
<%--                    <th>After Value</th>--%>
<%--                    <th>Created At</th>--%>
<%--                    <th>Update At</th>--%>
                    <th></th>
                </tr>
                </thead>
            </table>
        </div>

        <%--        Pagination--%>
        <jsp:include page="/views/components/pagination.jsp"/>

    </div>
    <!-- Modal for Details -->
    <div class="modal fade" id="detailsModal" tabindex="-1" role="dialog" aria-labelledby="detailsModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="detailsModalLabel">Log Details</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <table id="modal-body" class="table table-striped table-bordered">
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
                            <th></th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <script type="module" src="<c:url value="/resources/js/handlesShowLog.js"/>"></script>
</section>
