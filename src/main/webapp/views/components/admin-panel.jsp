<%--
  Created by IntelliJ IDEA.
  User: QDang
  Date: 26-09-2023
  Time: 14:37
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


<div class="admin-panel">
    <div class="accordion-item">
            <button class="rotate-icon accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
              <span class="material-symbols-rounded">settings</span>
            </button>
        <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#myAccordion">
            <div class="accordion-body d-flex flex-column align-items-center mt-4">
                <h4><fmt:message key="managementTools" bundle="${adminMsg}"/></h4>

                <a
                        class="links button rounded-button"
                        href="<c:url value="${pageContext.request.contextPath}/admin/management/film"/>"
                >
                    <fmt:message key="filmManagement"
                                 bundle="${adminMsg}"/>
                </a>

                <a
                        class=" links button rounded-button"
                        href="<c:url value="${pageContext.request.contextPath}/admin/management/showtime"/>"
                >
                    <fmt:message key="showtimeManagement" bundle="${adminMsg}"/>
                </a>
                <a
                        class="links button rounded-button"
                        href="<c:url value="${pageContext.request.contextPath}/admin/management/room"/>"
                >
                    <fmt:message key="roomManagement" bundle="${adminMsg}"/>
                </a>

                <a
                        class="links button rounded-button"
                        href="<c:url value="${pageContext.request.contextPath}/admin/management/log"/>"
                >
                    <fmt:message key="logManagement" bundle="${adminMsg}"/>

                </a>

                <a
                        class="links button rounded-button"
                        href="<c:url value="${pageContext.request.contextPath}/admin/management/user"/>"
                ><fmt:message key="userManagement" bundle="${adminMsg}"/>
                </a>
            </div>
        </div>
    </div>

</div>

