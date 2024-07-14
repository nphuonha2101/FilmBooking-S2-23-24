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


<nav class="top-nav row">
    <div class="col-6 d-flex align-items-center justify-content-center ">
        <div class="d-flex align-items-center justify-content-center">
            <%--            Logo--%>
            <a class="site-logo" href="<c:url value="${pageContext.request.contextPath}/home"/>">FilmBooking</a>

            <%--            Search--%>
            <input
                    class="form-control ms-4"
                    style="width: calc(15vw);"
                    data-bs-toggle="modal"
                    data-bs-target="#search-modal"
                    type="text"
                    placeholder="<fmt:message key="search" bundle="${msg}"/>"
                    id="search-input-toggle"
                    readonly
            />

        </div>
    </div>


    <div class="col-6 d-flex align-items-center justify-content-center">
        <div class="w-100 d-flex align-items-center justify-content-center">
            <ul class="m-0 list-inline align-items-center">
                <li
                        class="list-inline-item m-0"
                        data-bs-toggle="tooltip"
                        data-bs-title="<fmt:message key="home" bundle="${msg}" />"
                >
                    <a class="nav-link"
                       id="home"
                       href="<c:url value="${pageContext.request.contextPath}/home"/>">
                        <span class="material-symbols-rounded">home</span>
                    </a>
                </li>

                <c:if test="${not empty sessionScope.loginUser}">
                    <li
                            class="list-inline-item m-0"
                           data-bs-toggle="modal"
                            data-bs-target="#booking-history-modal"
                    >
                        <a class="nav-link"
                           id="film-booking_menu"
                           href="#">
                            <span class="material-symbols-rounded">history</span>
                        </a>
                    </li>
                </c:if>

                <c:if test="${not empty sessionScope.loginUser}">
                    <li
                            class="list-inline-item m-0 dropdown"
                    >

                        <a class="nav-link dropdown-toggle"
                           href="#"
                           role="button"
                           data-bs-toggle="dropdown"
                           aria-expanded="false"
                           id="account-info">
                            <span class="material-symbols-rounded">person</span>
                        </a>

                        <ul class="dropdown-menu p-2">
                            <li>
                                <h6> ${sessionScope.loginUser.userFullName}</h6>
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
                                class="list-inline-item m-0"
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
                                class="list-inline-item m-0"
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
                                class="list-inline-item m-0"
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
                        class="list-inline-item ms-3 mb-0 dropdown "
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

                    <ul class="dropdown-menu p-2">
                        <li>
                            <a class="dropdown-item "
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

<jsp:include page="/views/components/modals/search-modal.jsp"/>
<jsp:include page="/views/components/modals/booking-history-modal.jsp"/>

<script type="module" src="<c:url value="/resources/js/handlesShowFilmBookingHistory.js"/>"></script>
