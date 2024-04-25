<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

            <c:choose>
                <c:when test="${empty sessionScope.lang || sessionScope.lang eq 'default'}">
                    <fmt:setLocale value="default" />
                </c:when>
                <c:otherwise>
                    <fmt:setLocale value="${sessionScope.lang}" />
                </c:otherwise>
            </c:choose>

            <fmt:setBundle basename="properties.message" var="msg" />
            <fmt:setBundle basename="properties.pageTitle" var="pageTitle" />
            <fmt:setBundle basename="properties.statusCode" var="statusCodeMsg" />

            <section class="content section centered-vertical-content">
                <div class="centered-vertical-content container form__container">
                    <h2 class="title">
                        <fmt:message key="loginSectionTitle" bundle="${pageTitle}" />
                    </h2>

                    <%-- Status Code Messages--%>
                        <jsp:include page="/views/components/statusCodeMessage.jsp" />

                        <form action="<c:url value=" ${pageContext.request.contextPath}/login" />" method="post">
                        <label for="username">
                            <span class="material-symbols-rounded">person</span>
                            <fmt:message key="usernameOrEmail" bundle="${msg}" />:
                            <span class="warning-color"> *</span>
                        </label>
                        <input type="text" name="username" id="username" placeholder=" <fmt:message key="
                            usernameOrEmail" bundle="${msg}" />"
                        autocomplete="true" required>

                        <label for="password">
                            <span class="material-symbols-rounded">password</span>
                            <fmt:message key="password" bundle="${msg}" />:
                            <span class="warning-color"> *</span>
                        </label>
                        <input type="password" name="password" id="password" placeholder=" <fmt:message key=" password"
                            bundle="${msg}" />"
                        autocomplete="true" required>
                        <input type="submit" class="primary-filled-button button" value=" <fmt:message key=" login"
                            bundle="${msg}" />">

                        </form>
                        <p>
                            <fmt:message key="dontHaveAccount" bundle="${msg}" /> <span>
                                <a class="links" href="<c:url value=" ${pageContext.request.contextPath}/signup" />">
                                <fmt:message key="signupNow" bundle="${msg}" /> </a>
                            </span>
                        </p>
                        <p>
                            <fmt:message key="Or" bundle="${msg}" /> <span>
                                <a class="links" href="<c:url value="
                                    ${pageContext.request.contextPath}/forgot-password" />">
                                <fmt:message key="youForgotPassword" bundle="${msg}" /></a>
                            </span>
                        </p>
                        <div>
                            <fmt:message key="loginWith" bundle="${msg}" />
                        </div>
                        <%-- facebook login--%>
                            <fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
                            </fb:login-button>
                            <div id="status">
                            </div>
                            <%-- <div id="status">--%>
                                <%-- </div>--%>
                                    <%-- google login--%>
                                        <p>
                                            <a href=${google}><button class="gsi-material-button">
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
                                                </button></a>
                                        </p>
                </div>


                <script>
                    function statusChangeCallback(response) {  // Called with the results from FB.getLoginStatus().
                        console.log('statusChangeCallback');
                        console.log(response);                   // The current login status of the person.
                        if (response.status === 'connected') {   // Logged into your webpage and Facebook.
                            testAPI();
                        } else {                                 // Not logged into your webpage or we are unable to tell.
                            document.getElementById('status').innerHTML = 'Please log ' +
                                'into this webpage.';
                        }
                    }


                    function checkLoginState() {               // Called when a person is finished with the Login Button.
                        FB.getLoginStatus(function (response) {   // See the onlogin handler
                            statusChangeCallback(response);
                        });
                    }


                    window.fbAsyncInit = function () {
                        FB.init({
                            appId: '2705451709613164',
                            cookie: true,                     // Enable cookies to allow the server to access the session.
                            xfbml: true,                     // Parse social plugins on this webpage.
                            version: 'v19.0'           // Use this Graph API version for this call.
                        });


                        FB.getLoginStatus(function (response) {   // Called after the JS SDK has been initialized.
                            statusChangeCallback(response);        // Returns the login status.
                        });
                    };

                    function testAPI() {
                        console.log("Welcome! Đang lấy thông tin của bạn từ Facebook....");
                        FB.api("/me", function (response) {
                            console.log("Successful login for: " + response.name);
                            console.log("email" + response.email);
                            document.getElementById('status').innerHTML =
                                 'Thanks for logging in, ' + response.name + '!';
                            // Lấy dữ liệu của người dùng và gửi nó đến servlet
                            sendDataToServlet(response);
                        });
                    }
                    function sendDataToServlet(userData) {
                        // Tạo một yêu cầu AJAX
                        var xhr = new XMLHttpRequest();

                        // Thiết lập phương thức và URL của yêu cầu
                        xhr.open("POST", "/login/facebook", true);

                        // Thiết lập tiêu đề của yêu cầu
                        xhr.setRequestHeader("Content-Type", "application/json");

                        // Xử lý sự kiện khi yêu cầu hoàn thành
                        xhr.onreadystatechange = function () {
                            if (xhr.readyState === 4 && xhr.status === 200) {
                                console.log("Dữ liệu đã được gửi thành công đến servlet");
                            }
                        };

                        // Chuyển đổi đối tượng userData thành chuỗi JSON và gửi nó
                        xhr.send(JSON.stringify(userData));
                    }

                </script>
                <!-- Load the JS SDK asynchronously -->
                <script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js"></script>
            </section>