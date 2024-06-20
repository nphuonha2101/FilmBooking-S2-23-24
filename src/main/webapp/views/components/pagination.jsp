<%--
  Created by IntelliJ IDEA.
  User: nphuonha
  Date: 12/10/2023
  Time: 3:40 PM
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

<fmt:setBundle basename="properties.message" var="msg"/>

<c:if test="${not empty pageUrl}">
    <div class="d-flex flex-column align-items-center">
        <div class="d-flex justify-content-center pagination-wrapper">
            <div class="previous d-flex justify-content-center">
                <c:choose>
                    <c:when test="${currentPage > 1}">

                            <a
                                    role="button"
                                    class="button rounded-button primary-color"
                                    href="<c:url value="${pageContext.request.contextPath}/${pageUrl}?page=${currentPage - 1}"/>"
                                    data-bs-toggle="tooltip"
                                    data-bs-title="<fmt:message key='previousPage' bundle='${msg}'/>"
                            >
                                <span class="material-symbols-rounded">navigate_before</span>
                            </a>

                    </c:when>
                    <c:otherwise>
                            <a
                                    role="button"
                                    class="button rounded-button primary-color"
                                    href="<c:url value="${pageContext.request.contextPath}/${pageUrl}?page=${totalPages}"/>"
                                    data-bs-toggle="tooltip"
                                    data-bs-title="<fmt:message key='previousPage' bundle='${msg}'/>"
                            >
                                <span class="material-symbols-rounded">navigate_before</span>
                            </a>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="d-flex flex-column align-items-center wrapper">
                <form action="<c:url value="${pageContext.request.contextPath}/${pageUrl}"/>" name="page" method="get"
                      class="pagination-form">
                    <input type="number" min="1" max="${totalPages}" name="page"
                           class="form-control" value="${currentPage}" required/>
                    <input type="submit" class="button primary-filled-button rounded-button"
                           value="<fmt:message key="goToPage" bundle="${msg}"/>">
                </form>
            </div>
            <div class="next d-flex justify-content-center">
                <c:choose>
                    <c:when test="${currentPage < totalPages}">
                            <a
                                    role="button"
                                    class="button rounded-button primary-color"
                                    href="<c:url value="${pageContext.request.contextPath}/${pageUrl}?page=${currentPage + 1}"/>"
                                    data-bs-toggle="tooltip"
                                    data-bs-title="<fmt:message key='nextPage' bundle='${msg}'/>"
                            >
                                <span class="material-symbols-rounded">navigate_next</span>
                            </a>
                    </c:when>
                    <c:otherwise>
                            <a
                                    role="button"
                                    class="button rounded-button primary-color"
                                    href="<c:url value="${pageContext.request.contextPath}/${pageUrl}?page=1"/>"
                                    data-bs-toggle="tooltip"
                                    data-bs-title="<fmt:message key='nextPage' bundle='${msg}'/>"
                            >
                                <span class="material-symbols-rounded">navigate_next</span>
                            </a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <p><fmt:message key="currentPage" bundle="${msg}"/>: ${currentPage} / ${totalPages} <fmt:message key="pages" bundle="${msg}"/></p>
    </div>
</c:if>