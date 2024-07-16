function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    if (response.status === 'connected') {
        testAPI();
    } else {
        document.getElementById('status').innerHTML = 'Please log ' +
            'into this webpage.';
    }
}


function checkLoginState() {
    FB.getLoginStatus(function (response) {
        statusChangeCallback(response);
    });
}


window.fbAsyncInit = function () {
    FB.init({
        appId: '2705451709613164',
        cookie: true,
        xfbml: true,
        version: 'v19.0'
    });


    FB.getLoginStatus(function (response) {
        statusChangeCallback(response);
    });
};

function testAPI() {
    console.log("Welcome! Đang lấy thông tin của bạn từ Facebook....");
    FB.api("/me", function (response) {
        console.log("Successful login for: " + response.name);
        console.log("email" + response.email);
        document.getElementById('status').innerHTML =
            'Thanks for logging in, ' + response.name + '!';

        sendDataToServlet(response);
    });
}

function sendDataToServlet(userData) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/login/facebook", true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                console.log("Dữ liệu đã được gửi thành công đến servlet");
                window.location.href = "/home";
            } else {
                console.error("Có lỗi xảy ra khi gửi dữ liệu đến servlet");
            }
        }
    };

    console.log(JSON.stringify(userData));
    xhr.send(JSON.stringify(userData));
}
