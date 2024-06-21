<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 15-09-2023
  Time: 6:27 PM
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
<fmt:setBundle basename="properties.pageTitle" var="pageTitle"/>
<fmt:setBundle basename="properties.statusCode" var="statusCodeMsg"/>

<section>
    <div class="container d-flex flex-column align-items-center">
        <h2 class="title"><fmt:message key="signupSectionTitle" bundle="${pageTitle}"/></h2>

        <%--        Status Code Messages--%>
        <jsp:include page="/views/components/status-code-message.jsp"/>

        <form class="row g-3" action="<c:url value="${pageContext.request.contextPath}/signup"/>" method="post">
            <div class="form-floating">
                <input class="form-control px-3" type="text" name="username" id="username"
                       placeholder="<fmt:message bundle="${msg}" key="username"/>"
                       autocomplete="true" required>
                <label class="text-secondary" for="username">
                    <fmt:message bundle="${msg}" key="username"/>:
                    <span class="warning-color"> *</span>
                </label>
            </div>


            <div class="form-floating">
                <input class="form-control px-3" type="text" name="user-full-name" id="user-full-name"
                       placeholder="<fmt:message bundle="${msg}" key="fullname"/>" autocomplete="true"
                       required>
                <label class="text-secondary" for="user-full-name">
                    <fmt:message bundle="${msg}" key="fullname"/>:
                    <span class="warning-color"> *</span>
                </label>
            </div>


            <div class="form-floating">
                <input class="form-control px-3" type="email" name="email" id="email" placeholder="Email"
                       autocomplete="true" required>
                <label class="text-secondary" for="email">
                    <fmt:message bundle="${msg}" key="email"/>:
                    <span class="warning-color"> *</span>
                </label>
            </div>

            <div class="form-floating">
                <input class="form-control px-3" type="password" name="password" id="password"
                       placeholder="<fmt:message bundle="${msg}" key="password"/>" autocomplete="true" required>
                <label class="text-secondary" for="password">
                    <fmt:message bundle="${msg}" key="password"/>:
                    <span class="warning-color"> *</span>
                </label>
            </div>

            <div class="form-floating">
                <input class="form-control px-3" type="password" name="confirm-password" id="confirm-password"
                       placeholder="<fmt:message bundle="${msg}" key="confirmPassword"/>"
                       autocomplete="true" required>
                <label class="text-secondary" for="confirm-password">
                    <fmt:message bundle="${msg}" key="confirmPassword"/>:
                    <span class="warning-color"> *</span>
                </label>
            </div>
            <div>
                <input class="btn btn-primary form-control" type="submit"
                       value="<fmt:message bundle="${msg}" key="register"/>"
                >
            </div>
        </form>

        <div class="row">
            <p class="m-0 mt-1"><fmt:message bundle="${msg}" key="ifHaveAccount"/>
                <span>
                    <a
                            class="links"
                            href="<c:url value="${pageContext.request.contextPath}/login"/>">
                            <fmt:message
                                    bundle="${msg}" key="login"/>
                    </a>
                </span>
            </p>
        </div>
    </div>
</section>
