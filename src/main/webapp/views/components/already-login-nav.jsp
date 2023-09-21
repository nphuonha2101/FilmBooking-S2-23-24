<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 15-09-2023
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<nav class="top-nav" id="already-login-nav">
    <div class="centered-content wrapper" id="left-welcome">
        <p>Welcome ${sessionScope.userFullName} (${sessionScope.accountRole})</p>
    </div>
    <ul id="centered-nav-links">
        <li><a class="nav-links" href="../../login.jsp">Film Project</a></li>
        <li><a class="nav-links" href="https://github.com/nphuonha2101/FilmBooking-WebProgramming"
               target="_blank">GitHub</a></li>
    </ul>
    <ul id="right-nav-link">
        <li>
            <div class="drop-down-menu">
                <a>Feature</a>
                <div class="drop-down-contents">
                    <a class="drop-down-links">Registration history</a>
                    <c:choose>
                        <c:when test="${sessionScope.accountRole eq 'admin'}">
                            <a class="drop-down-links" href="../../admin.jsp">Admin page</a>
                        </c:when>
                    </c:choose>

                </div>
            </div>
        </li>
        <li><a href="handles-logout">Logout</a></li>
    </ul>
</nav>