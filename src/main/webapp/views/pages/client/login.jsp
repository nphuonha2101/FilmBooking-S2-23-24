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
        <h2 class="title">
            <fmt:message key="loginSectionTitle" bundle="${pageTitle}"/>
        </h2>

        <%--        Status Code Messages--%>
        <jsp:include page="/views/components/status-code-message.jsp"/>

        <form class="w-30 mb-3 row g-3" action="<c:url value="${pageContext.request.contextPath}/login" />" method="post"
              id="login-form">
            <div class="form-floating">
                <input class="form-control" type="text" name="usernameOrEmail" id="usernameOrEmail"
                       placeholder=" <fmt:message key="usernameOrEmail" bundle="${msg}" />"
                       autocomplete="true" required>
                <label class="text-secondary" for="usernameOrEmail">
                    <fmt:message key="usernameOrEmail" bundle="${msg}"/>:
                    <span class="warning-color"> *</span>
                </label>
            </div>
            <div class="form-floating">
                <input class="form-control" type="password" name="password" id="password" placeholder="<fmt:message key="password"
                            bundle="${msg}" />"
                       autocomplete="true" required>
                <label class="text-secondary" for="password">
                    <fmt:message key="password" bundle="${msg}"/>:
                    <span class="warning-color"> *</span>
                </label>
            </div>
            <div>
                <input
                        class="form-control btn btn-primary" type="submit"
                        value="<fmt:message key="login" bundle="${msg}"/>"
                />
            </div>
        </form>

        <div class="d-flex flex-column align-items-center">
            <p class="m-0 mt-1">
                <fmt:message key="dontHaveAccount" bundle="${msg}"/> <span>
                                <a class="links" href="<c:url value=" ${pageContext.request.contextPath}/signup" />">
                                <fmt:message key="signupNow" bundle="${msg}"/> </a>
                            </span>
            </p>
            <p class="m-0 mt-1">
                <fmt:message key="Or" bundle="${msg}"/> <span>
                                <a class="links" href="<c:url value="
                                    ${pageContext.request.contextPath}/forgot-password" />">
                                <fmt:message key="youForgotPassword" bundle="${msg}"/></a>
                            </span>
            </p>
            <p class="m-0 mt-1">
                <fmt:message key="loginWith" bundle="${msg}"/>
            </p>


            <%-- facebook login--%>
            <fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
            </fb:login-button>
            <div id="status">
            </div>
            <%-- <div id="status">--%>
            <%-- google login--%>
            <p>
                <a href=${google}>
                    <button class="gsi-material-button">
                        <div class="gsi-material-button-state"></div>
                        <div class="gsi-material-button-content-wrapper">
                            <div class="gsi-material-button-icon">
                                <svg version="1.1" xmlns="http://www.w3.org/2000/svg"
                                     viewBox="0 0 48 48"
                                     xmlns:xlink="http://www.w3.org/1999/xlink"
                                     style="display: block;">
                                    <path fill="#EA4335"
                                          d="M24 9.5c3.54 0 6.71 1.22 9.21 3.6l6.85-6.85C35.9 2.38 30.47 0 24 0 14.62 0 6.51 5.38 2.56 13.22l7.98 6.19C12.43 13.72 17.74 9.5 24 9.5z">
                                    </path>
                                    <path fill="#4285F4"
                                          d="M46.98 24.55c0-1.57-.15-3.09-.38-4.55H24v9.02h12.94c-.58 2.96-2.26 5.48-4.78 7.18l7.73 6c4.51-4.18 7.09-10.36 7.09-17.65z">
                                    </path>
                                    <path fill="#FBBC05"
                                          d="M10.53 28.59c-.48-1.45-.76-2.99-.76-4.59s.27-3.14.76-4.59l-7.98-6.19C.92 16.46 0 20.12 0 24c0 3.88.92 7.54 2.56 10.78l7.97-6.19z">
                                    </path>
                                    <path fill="#34A853"
                                          d="M24 48c6.48 0 11.93-2.13 15.89-5.81l-7.73-6c-2.15 1.45-4.92 2.3-8.16 2.3-6.26 0-11.57-4.22-13.47-9.91l-7.98 6.19C6.51 42.62 14.62 48 24 48z">
                                    </path>
                                    <path fill="none" d="M0 0h48v48H0z"></path>
                                </svg>
                            </div>
                        </div>
                    </button>
                </a>
            </p>
        </div>
    </div>


    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/handlesFBLogin.js"></script>
    <!-- Load the JS SDK asynchronously -->
    <script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/handlesRecaptchaV3.js"></script>
    <script type="text/javascript">
        handleRecaptchaV3('login-form', 'login')
    </script>

</section>