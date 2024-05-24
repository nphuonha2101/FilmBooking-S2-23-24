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


<div class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <h3 class="modal-title">
                <fmt:message key="userManagementSectionTitle" bundle="${pageTitleMsg}"/>
            </h3>
            <span class="close-button">&times;</span>
        </div>
        <div class="modal-body">
            <div class="wrapper">
                <form id="user-info_form">
                    <label for="username">
                        <fmt:message key="username" bundle="${msg}"/>
                    </label>
                    <input type="text" name="username" id="username" class="readonly-input" readonly>

                    <label for="full-name">
                        <fmt:message key="fullname" bundle="${msg}"/>
                    </label>
                    <input type="text" name="full-name" id="full-name">

                    <label for="email">Email</label>
                    <input type="email" name="email" id="email" class="readonly-input" readonly>

                    <label for="role">
                        <fmt:message key="role" bundle="${msg}"/>
                    </label>
                    <select name="role" id="role">
                        <option value="admin">
                            <fmt:message key="admin" bundle="${msg}"/>
                        </option>
                        <option value="customer">
                            <fmt:message key="customer" bundle="${msg}"/>
                        </option>
                    </select>

                    <button type="submit" class="primary-filled-button rounded-button button submit-button icon-button">
                        <span class="material-symbols-rounded">save</span>
                        <span class="hidden-span">
                            <fmt:message key="save" bundle="${msg}"/>
                        </span>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/handlesCloseModal.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/handlesSubmitForm.js"></script>
<script>
    handlesSubmitForm('user-info_form', 'PATCH', '/api/v1/users');
</script>
