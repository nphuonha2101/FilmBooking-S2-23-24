<%--
  Created by IntelliJ IDEA.
  User: nphuo
  Date: 5/16/2024
  Time: 12:30 PM
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

            <table id="myTable" class="display dataTable w-100 border-box">
                <thead>
                <tr>
                    <th></th>
                    <th>Username</th>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Edit</th>
                </tr>
                </thead>
            </table>
        </div>

        <%--        Pagination--%>
        <jsp:include page="/views/components/pagination.jsp"/>

    </div>


    <jsp:include page="/views/components/modals/user-management-modal.jsp" />


    <script>
        $(function () {
            $('#myTable').DataTable({
                ajax: {
                    url: '/api/v1/users?command=all',
                    dataSrc: 'data'
                },
                columns: [
                    {
                        data: null,
                        render: function (data, type, row) {
                            return '<input type="checkbox" name="select[]" value="' + data.logID + '">';
                        }
                    },
                    {data: 'username', id: 'username'},
                    {data: 'userFullName'},
                    {data: 'userEmail'},
                    {data: 'accountRole'},
                    {
                        data: null,
                        render: function (data, type, row) {
                            return '<button class="edit-button button light-filled-button rounded-button"><span class="material-symbols-rounded primary-color">edit</span></button>';
                        }
                    }
                ]
            });
        });

        $('#myTable').on('click', '.edit-button', function () {
            const data = $('#myTable').DataTable().row($(this).parents('tr')).data();
            handleEditUserModal(data.username, data.userFullName, data.userEmail, data.accountRole);
            console.log(data)
        })

        function handleEditUserModal(username, fullName, email, role) {
            $('#username').attr('value', username);
            $('#full-name').attr('value', fullName);
            $('#email').attr('value',email);
            $('#role').attr('value', role);
            $('.modal').css('display', 'block');
        }


    </script>

</section>

