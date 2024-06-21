<%--
  Created by IntelliJ IDEA.
  User: nphuo
  Date: 6/20/2024
  Time: 2:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
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

<fmt:setBundle basename="properties.message" var="msg"/>

<div class="modal fade" id="search-modal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header d-flex align-items-center">
                <input
                        class="form-control w-100 me-3"
                        type="text"
                        id="search-input"
                        placeholder="<fmt:message key='search' bundle='${msg}'/>"
                />
                <button type="button" class="p-3 btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h4 class="text-center"><fmt:message key="searchResult" bundle="${msg}"/></h4>
                <div class="row ps-3 pe-3" id="search-result">

                </div>

            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    const myModal = document.getElementById('search-modal')
    const myInput = document.getElementById('search-input')

    myModal.addEventListener('shown.bs.modal', () => {
        myInput.focus()
    })
</script>
