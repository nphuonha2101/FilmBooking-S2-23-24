<%--
  Created by IntelliJ IDEA.
  User: nphuo
  Date: 5/17/2024
  Time: 8:02 AM
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

<div class="modal fade" id="user-management-modal" tabindex="-1" aria-labelledby="user-management-title"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5 m-0" id="user-management-title"><fmt:message key="userManagementSectionTitle"
                                                                                     bundle="${pageTitleMsg}"/></h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="user-info_form" enctype="multipart/form-data">
                    <div class="form-floating mb-3">
                        <input type="text" name="username" id="username" class="form-control readonly-input" readonly>
                        <label for="username">
                            <fmt:message key="username" bundle="${msg}"/>
                        </label>
                    </div>

                    <div class="form-floating mb-3">
                        <input class="form-control readonly-input" type="email" name="email" id="email" readonly>
                        <label for="email">Email</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input class="form-control" type="text" name="full-name" id="full-name">
                        <label for="full-name">
                            <fmt:message key="fullname" bundle="${msg}"/>
                        </label>
                    </div>

                    <div>
                        <label for="role">
                            <fmt:message key="role" bundle="${msg}"/>
                        </label>
                        <select class="form-control" name="role" id="role">
                            <option value="admin">
                                <fmt:message key="admin" bundle="${msg}"/>
                            </option>
                            <option value="customer">
                                <fmt:message key="customer" bundle="${msg}"/>
                            </option>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="submit" id="save-button"
                        class="btn btn-primary">
                    <fmt:message key="save" bundle="${msg}"/>
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/handlesSubmitForm.js"></script>
<script>
    const form = document.getElementById('user-info_form');
    const saveButton = document.getElementById('save-button');
    saveButton.addEventListener('click', () => {
        const submitEvent = new Event('submit');

        form.dispatchEvent(submitEvent);
    });
    handlesSubmitForm('user-info_form', 'PATCH', '/api/v1/users');
</script>
