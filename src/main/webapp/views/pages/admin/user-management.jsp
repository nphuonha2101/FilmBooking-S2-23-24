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
<fmt:setBundle basename="properties.message" var="msg"/>
<fmt:setBundle basename="properties.pageTitle" var="pageTitleMsg"/>

<section class="section align-top d-flex flex-column align-items-center">

    <jsp:include page="/views/components/admin-panel.jsp"/>

    <div class="container d-flex flex-column align-items-center">

        <h2>
            <fmt:message bundle="${pageTitleMsg}" key="userManagementSectionTitle"/>
        </h2>

        <div class="d-flex flex-column align-items-center wrapper">

            <%--        Status Code Messages--%>
            <jsp:include page="/views/components/status-code-message.jsp"/>

            <table id="myTable" class="display table-striped table-bordered table w-100 border-box">
                <thead>
                <tr>
                    <th></th>
                    <th>
                        <fmt:message key="username" bundle="${msg}"/>
                    </th>
                    <th>
                        <fmt:message key="fullname" bundle="${msg}"/>
                    </th>
                    <th>
                        <fmt:message key="email" bundle="${msg}"/>
                    </th>
                    <th>
                        <fmt:message key="role" bundle="${msg}"/>
                    </th>
                    <th>
                        <fmt:message key="accountType" bundle="${msg}"/>
                    </th>
                    <th>
                        <fmt:message key="edit" bundle="${msg}"/>
                    </th>
                </tr>
                </thead>
            </table>
        </div>

        <%--        Pagination--%>
        <jsp:include page="/views/components/pagination.jsp"/>

    </div>


    <jsp:include page="/views/components/modals/user-management-modal.jsp"/>


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
                        data: 'accountType',
                        render: function (data, type, row) {
                            if (data === 'normal')
                                return 'Normal';
                            if (data === 'google')
                                return 'Google';
                            if (data === 'facebook')
                                return 'Facebook';
                        }
                    },
                    {
                        data: null,
                        render: function (data, type, row) {
                            return '<button data-bs-toggle="modal" data-bs-target="#user-management-modal" class="rotate-icon edit-button button light-filled-button rounded-button"><span class="material-symbols-rounded primary-color">edit</span></button>';
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
            $('#email').attr('value', email);
            $('#role').attr('value', role);
        }
    </script>

</section>

