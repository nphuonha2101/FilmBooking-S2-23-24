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