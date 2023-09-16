<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 15-09-2023
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>
<nav class="top-nav" id="already-login-nav">
    <div class="centered-content wrapper" id="left-welcome">
        <p>Welcome ${welcome-user}</p>
    </div>
    <ul id="centered-nav-links">
        <li><a class="nav-button button" href="login.jsp">Film Project</a></li>
        <li><a class="nav-button button" href="https://github.com/nphuonha2101/FilmBooking-WebProgramming"
               target="_blank">GitHub</a></li>
    </ul>
    <ul id="right-nav-link">
        <li>
            <div class="drop-down-menu">
            <a>Feature</a>
                <div class="drop-down-contents">
                    <a>Registration history</a>
                </div>
            </div>
        </li>
        <li><a>Logout</a></li>
    </ul>
</nav>