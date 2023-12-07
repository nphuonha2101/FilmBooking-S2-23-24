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

<nav class="top-nav" id="navigation-bar">
    <div class="centered-horizontal-content wrapper" id="left-nav-elements">
        <a class="site-logo" href="home">FilmBooking</a>
    </div>


    <ul id="right-nav-link">

        <li>
            <a class="nav-links small-icon-button" href="home" id="home">
                <div class="tooltip ">
                    <span class="material-symbols-rounded">
                        home
                    </span>
                    <span class="tooltip-text">
                    <fmt:message key="home" bundle="${msg}"/>
                </span>
                </div>
            </a>
        </li>

        <li>
            <a class="nav-links small-icon-button" id="search">
                <div class="tooltip">
                     <span class="material-symbols-rounded">
                        search
                        </span>
                    <span class="tooltip-text">
                        <fmt:message key="search" bundle="${msg}"/>
                </span>
                </div>
            </a>
        </li>

        <c:if test="${not empty sessionScope.loginUser.username}">
            <li>
                <div class="drop-down-menu">

                    <a class="nav-links small-icon-button" href="account-info" id="account-info">
                        <span class="material-symbols-rounded">
                            person
                        </span>
                    </a>

                    <div class="drop-down-contents">
                        <p class="font-Merriweather"> ${sessionScope.loginUser.userFullName}</p>

                        <a class="drop-down-links" href="booking-history">
                            <fmt:message key="bookingHistory" bundle="${msg}"/>
                        </a>
                        <c:choose>
                            <c:when test="${sessionScope.loginUser.accountRole eq 'admin'}">
                                <a class="drop-down-links" href="admin">
                                    <fmt:message key="adminPage" bundle="${msg}"/>
                                </a>
                            </c:when>
                        </c:choose>
                        <a class="drop-down-links" href="account-info">
                            <fmt:message key="yourAccount" bundle="${msg}"/>
                        </a>
                    </div>
                </div>
            </li>
        </c:if>

        <c:choose>
            <c:when test="${not empty sessionScope.loginUser.username}">
                <li>
                    <a class="nav-links small-icon-button" href="logout">
                        <div class="tooltip">
                            <span class="material-symbols-rounded">
                                logout
                            </span>
                            <span class="tooltip-text">
                            <fmt:message key="logout" bundle="${msg}"/>
                        </span>
                        </div>
                    </a>
                </li>
            </c:when>
            <c:when test="${empty sessionScope.loginUser.username}">
                <li>
                    <a class="nav-links small-icon-button" href="signup" id="signup">
                        <div class="tooltip">
                            <span class="material-symbols-rounded">
                                person_add
                            </span>
                            <span class="tooltip-text">
                            <fmt:message key="register" bundle="${msg}"/>
                        </span>
                        </div>
                    </a>
                </li>
                <li>
                    <a class="nav-links small-icon-button" href="login" id="login">
                        <div class="tooltip">
                        <span class="material-symbols-rounded">
                            login
                        </span>
                            <span class="tooltip-text">
                            <fmt:message key="login" bundle="${msg}"/>
                        </span>
                        </div>
                    </a>
                </li>
            </c:when>
        </c:choose>

        <li>
            <div class="drop-down-menu">

                <c:choose>
                    <c:when test="${sessionScope.lang eq 'default' || empty sessionScope.lang}">
                        <p class="centered-horizontal-content mg-0">
                            <img class="img-language"
                                 src="<c:url value='/resources/images/icons8-vietnam-flag-48.png'/> "
                                 alt="default Vietnamese">
                            <span> &ensp; VI</span>
                        </p>
                    </c:when>

                    <c:otherwise>
                        <p class="centered-horizontal-content mg-0">
                            <img class="img-language" src="<c:url value='/resources/images/icons8-usa-flag-48.png'/>"
                                 alt="English"/>
                            <span> &ensp; EN</span>
                        </p>
                    </c:otherwise>
                </c:choose>


                <div class="drop-down-contents">
                    <a class="drop-down-links" href="lang?name=default"> <img class="img-language"
                                                                              src="<c:url value='/resources/images/icons8-vietnam-flag-48.png'/> "
                                                                              alt="default Vietnamese"> <span> &ensp; Tiếng Việt</span>
                    </a>
                    <a class="drop-down-links" href="lang?name=en_US"> <img class="img-language"
                                                                            src="<c:url value='/resources/images/icons8-usa-flag-48.png'/> "
                                                                            alt="default Vietnamese">
                        <span> &ensp; English</span>
                    </a>

                </div>
            </div>
        </li>
    </ul>
</nav>
