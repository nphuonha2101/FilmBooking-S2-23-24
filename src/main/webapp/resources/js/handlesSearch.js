function search() {
    $("#search-result").css("display", "block");
    $("#search-result").html("");
    const searchField = $("#search-input").val();
    if (searchField.length >= 2) {
        const expression = new RegExp(searchField, "i");
        $.getJSON("http://localhost:8080/api/v1/films?command=all", function (data) {
            $.each(data.data, function (key, value) {
                if (value.filmName.search(expression) != -1) {
                    console.log(value.filmName);
                    $("#search-result").append(

                        `<div class="card search-item-card p-0 col"">
                            <div class="row g-0">
                                <div class="col-md-3">
                                    <img src="${value.imgPath}" class="img-fluid rounded-start" alt="${value.filmName} thumbnail">
                                </div>
                                <div class="col-md-9">
                                    <div class="card-body p-3">
                                        <h5 class="card-title">${value.filmName}</h5>
                                        <dl>
                                            <dt>Diễn viên</dt>
                                            <dd>${value.cast}</dd>
                                            <dt>Giá</dt>
                                            <dd>${value.filmPrice} VND</dd>
                                        </dl>
                                       
                                        <a href="http://localhost:8080/film-info?film=${value.slug}"
                                            class="btn btn-primary">Chi tiết phim</a>
                                    </div>
                                </div>
                            </div>
                        </div>`
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






