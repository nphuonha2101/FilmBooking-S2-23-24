function search() {
    $("#search-result").css("display", "block");
    $("#search-result").html("");
    var searchField = $("#search-input").val();
    if (searchField.length >= 2) {
        var expression = new RegExp(searchField, "i");
        $.getJSON("http://localhost:8080/api/v1/films?command=all", function (data) {
            $.each(data.data, function (key, value) {
                if (value.filmName.search(expression) != -1) {
                    console.log(value.filmName);
                    $("#search-result").append(
                        '<a href="http://localhost:8080/film-info?film=' +
                        value.slug +
                        '" style="text-decoration: none"><li class="list-group-item link-class"><img src="' +
                        value.imgPath +
                        '" style="width: 60px; padding-right: 10px"/> ' +
                        value.filmName +
                        "<br> " +
                        value.director +
                        "</li></a>"
                    );
                }
            });
        });
    }else{
        $("#search-result").css("display", "none");
    }
}

function throttle(func, delay) {
    let lastCall = 0;
    return function (...args) {
        const now = new Date().getTime();
        if (now - lastCall < delay) {
            return;
        }
        lastCall = now;
        func(...args);
    };
}


$(document).ready(function () {
  $.ajaxSetup({ cache: false });
  var throttledSearch = throttle(search, 1000);
  $("#search-input").keyup(throttledSearch);
});






