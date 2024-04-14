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

            <table id="logTable">
                <thead>
                <tr>
                    <th><fmt:message bundle="${adminMsg}" key="logID"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="logActions"/></th>
<%--                    <th><fmt:message bundle="${adminMsg}" key="afterData"/></th>--%>
<%--                    <th><fmt:message bundle="${adminMsg}" key="beforeData"/></th>--%>
                    <th><fmt:message bundle="${adminMsg}" key="logLevel"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="logTable"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="logTime"/></th>
<%--                    <th><fmt:message bundle="${adminMsg}" key="logIP"/></th>--%>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>

        <%--        Pagination--%>
        <jsp:include page="/views/components/pagination.jsp"/>

    </div>
    <script>
        $(document).ready(function (){
            $.ajax({
                        url: "./admin/management/log",
                        type: "GET",
                        dataType: "json",
                        success: function (data){
                            $('$logTable').DataTable({
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
</section>
