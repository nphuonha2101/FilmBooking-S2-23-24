<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 15-09-2023
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${empty sessionScope.lang || sessionScope.lang eq 'default'}">
        <fmt:setLocale value="default"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${sessionScope.lang}"/>
    </c:otherwise>
</c:choose>

<fmt:setBundle basename="properties.message" var="msg"/>

<nav class="navbar navbar-expand-lg navbar-light bg-light top-nav" id="navigation-bar">
    <div class="d-flex w-100">
        <div class="flex-grow-1 d-flex align-items-center justify-content-center" id="left-nav">
            <a class="site-logo" href="<c:url value="${pageContext.request.contextPath}/home"/>">FilmBooking</a>
            <div class="search-box">
                <input type="text" class="form-control ms-3" id="search-input"
                       placeholder="<fmt:message key="search" bundle="${msg}"/>">
                <ul class="list-group" id="search-result"></ul>
            </div>
        </div>


        <div class="flex-grow-1 d-flex align-items-center justify-content-center" id="right-nav">
            <ul class="navbar-nav d-flex me-auto mb-2 mb-lg-0">

                <%--Home--%>
                <li
                        class="nav-item"
                        data-bs-toggle="tooltip"
                        data-bs-title="<fmt:message key="home" bundle="${msg}"/>"
                >
                    <a class="nav-link"
                       id="home"
                       href="<c:url value="${pageContext.request.contextPath}/home"/>">
                        <span class="material-symbols-rounded">home</span>
                    </a>
                </li>

                <%--Booking History--%>
                <c:if test="${not empty sessionScope.loginUser}">
                    <li
                            class="nav-item"
                            data-bs-toggle="tooltip"
                            data-bs-title="<fmt:message key="bookingHistory" bundle="${msg}"/>"
                    >
                        <a
                                class="nav-link"
                                id="film-booking_menu"
                                href="#"
                        >
                            <span class="material-symbols-rounded">history</span>

                            <div class="drop-down-contents" id="film-booking_menu-content">
                            </div>
                        </a>
                    </li>
                </c:if>

                <%--Account Info--%>
                <c:if test="${not empty sessionScope.loginUser}">
                    <li
                            class="nav-item dropdown"
                            data-bs-toggle="tooltip"
                            data-bs-title="<fmt:message key="accountInfo" bundle="${msg}"/>"
                    >

                        <a class="nav-link dropdown-toggle"
                           href="#"
                           role="button"
                           data-bs-toggle="dropdown"
                           aria-expanded="false"
                           id="account-info">

                        </a>

                        <ul class="dropdown-menu">
                            <li>
                                <p class="dropdown-item font-FiraSans"> ${sessionScope.loginUser.userFullName}</p>
                            </li>
                            <li>
                                <a class="dropdown-item"
                                   href="<c:url value="${pageContext.request.contextPath}/auth/booking-history"/>">
                                    <fmt:message key="bookingHistory" bundle="${msg}"/>
                                </a>
                            </li>

                            <c:choose>
                                <c:when test="${sessionScope.loginUser.accountRole eq 'admin'}">
                                    <li>
                                        <a class="dropdown-item"
                                           href="<c:url value="${pageContext.request.contextPath}/admin/management/film"/>">
                                            <fmt:message key="adminPage" bundle="${msg}"/>
                                        </a>
                                    </li>
                                </c:when>
                            </c:choose>
                            <li>
                                <a class="dropdown-item"
                                   href="<c:url value="${pageContext.request.contextPath}/auth/account-info"/>">
                                    <fmt:message key="yourAccount" bundle="${msg}"/>
                                </a>
                            </li>
                        </ul>
                    </li>
                </c:if>


                <c:choose>
                    <c:when test="${empty sessionScope.loginUser.username}">
                        <%--Register--%>
                        <li
                                class="nav-item"
                                data-bs-toggle="tooltip"
                                data-bs-title="<fmt:message key="register" bundle="${msg}"/>"
                        >
                            <a class="nav-link"
                               href="<c:url value="${pageContext.request.contextPath}/signup"/>" id="signup">
                                <span class="material-symbols-rounded">person_add</span>
                            </a>
                        </li>

                        <%--Login--%>
                        <li
                                class="nav-item"
                                data-bs-toggle="tooltip"
                                data-bs-title="<fmt:message key="login" bundle="${msg}"/>"
                        >
                            <a class="nav-link"
                               href="<c:url value="${pageContext.request.contextPath}/login"/>" id="login">
                                <span class="material-symbols-rounded">login</span>
                            </a>
                        </li>
                    </c:when>
                </c:choose>

                <c:choose>
                    <c:when test="${not empty sessionScope.loginUser.username}">
                        <%--Logout--%>
                        <li
                                class="nav-item"
                                data-bs-toggle="tooltip"
                                data-bs-title="<fmt:message key="logout" bundle="${msg}"/>"
                        >
                            <a class="nav-link"
                               href="<c:url value="${pageContext.request.contextPath}/logout"/>" id="logout">
                                <span class="material-symbols-rounded">logout</span>
                            </a>
                        </li>
                    </c:when>
                </c:choose>

                <%--Change Language--%>
                <li
                        class="nav-item dropdown ms-3"
                >
                    <c:choose>
                        <c:when test="${sessionScope.lang eq 'default' || empty sessionScope.lang}">
                            <a class="nav-link dropdown-toggle"
                               href="#"
                               role="button"
                               data-bs-toggle="dropdown"
                               aria-expanded="false"
                               id="language">
                                <img class="img-language"
                                     src="<c:url value='/resources/images/icons8-vietnam-flag-48.png'/> "
                                     alt="default Vietnamese">
                                <span> &ensp; VI</span>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link dropdown-toggle"
                               href="#"
                               role="button"
                               data-bs-toggle="dropdown"
                               aria-expanded="false"
                               id="language">
                                <img class="img-language"
                                     src="<c:url value='/resources/images/icons8-usa-flag-48.png'/> "
                                     alt="English">
                                <span> &ensp; EN</span>
                            </a>
                        </c:otherwise>
                    </c:choose>

                    <ul class="dropdown-menu">
                        <li>
                            <a class="dropdown-item"
                               href="<c:url value="${pageContext.request.contextPath}/lang?name=default"/>">
                                <img
                                        class="img-language"
                                        src="<c:url value='/resources/images/icons8-vietnam-flag-48.png'/> "
                                        alt="default Vietnamese">
                                <span> &ensp; Tiếng Việt</span>
                            </a>
                        </li>

                        <li>
                            <a class="dropdown-item"
                               href="<c:url value="${pageContext.request.contextPath}/lang?name=en_US"/>">
                                <img
                                        class="img-language"
                                        src="<c:url value='/resources/images/icons8-usa-flag-48.png'/> "
                                        alt="default Vietnamese">
                                <span> &ensp; English</span>
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<script type="module" src="<c:url value="/resources/js/handlesShowFilmBookingHistory.js"/>"></script>
