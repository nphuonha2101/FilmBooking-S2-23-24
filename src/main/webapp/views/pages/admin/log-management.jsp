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
            <jsp:include page="/views/components/statusCodeMessage.jsp"/>

            <table id="data">
                <thead>
                <tr>
<%--                    <th><fmt:message bundle="${adminMsg}" key="logID"/></th>--%>
<%--                    <th><fmt:message bundle="${adminMsg}" key="logActions"/></th>--%>
<%--&lt;%&ndash;                    <th><fmt:message bundle="${adminMsg}" key="afterData"/></th>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <th><fmt:message bundle="${adminMsg}" key="beforeData"/></th>&ndash;%&gt;--%>
<%--                    <th><fmt:message bundle="${adminMsg}" key="logLevel"/></th>--%>
<%--                    <th><fmt:message bundle="${adminMsg}" key="logTable"/></th>--%>
<%--                    <th><fmt:message bundle="${adminMsg}" key="logTime"/></th>--%>
<%--&lt;%&ndash;                    <th><fmt:message bundle="${adminMsg}" key="logIP"/></th>&ndash;%&gt;--%>
    <th>ID</th>
    <th>Action</th>
    <th>Level</th>
    <th>Target</th>
    <th>Update</th>

                </tr>
                </thead>

            </table>
        </div>

        <%--        Pagination--%>
        <jsp:include page="/views/components/pagination.jsp"/>

    </div>
</section>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>

<link href="https://cdn.datatables.net/v/dt/jq-3.7.0/dt-2.0.5/af-2.7.0/b-3.0.2/datatables.min.css" rel="stylesheet">
<script src="https://cdn.datatables.net/v/dt/jq-3.7.0/dt-2.0.5/af-2.7.0/b-3.0.2/datatables.min.js"></script>
<script type="module" src="<c:url value="/resources/js/handlesShowFilmBookingHistory.js"/>"></script>
<script>
    $(document).ready(function (){
        $.ajax({
            url: "/api/v1/logs",
            type: "get",
            dataType: "json",
            success: function (data){
                $("#data").dataTable({
                    data : data.data,

                    columns:[
                        {
                            data: null,
                            render: function (data,type,row){
                                var checkboxId = data.logID;
                                var checkboxClass = "checkbox";
                                return '<input type="checkbox" id="' + checkboxId + '" class="'+checkboxClass+'">';
                            }
                        },
                        {data:'logID'},
                        {data:'action'},
                        {data:'level'},
                        {data:'targetTable'},
                        {data:'updatedAt'}
                    ]
                });
            },
            error: function (jqXHR, textStatus, errThrown){
                console.log("error: "+ errThrown)
            }
        })
    });
</script>