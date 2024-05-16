function search() {
  $("#result").html("");
  var searchField = $("#search-input").val();
  if (searchField.length >= 2) {
    var expression = new RegExp(searchField, "i");
    $.getJSON("http://localhost:8080/api/v1/films?command=all", function (data) {
      var listFilm = data.data;
      console.log(listFilm);
       for (let i = 0; i < listFilm.length; i++) {
         if (listFilm[i].filmName.search(expression) != -1) {
           console.log(listFilm[i].filmName)
           $("#result").append(
               '<a href="http://localhost:8080/film-info?film=' +
               listFilm[i].slug +
               '" style="text-decoration: none"><li class="list-group-item link-class"><img src="' +
               listFilm[i].imgPath +
               '" style="width: 60px; padding-right: 10px"/> ' +
               listFilm[i].filmName +
               "<br> " +
               listFilm[i].director +
               "<br> " +
               listFilm[i].cast +
               "</li></a>"
           );
         }
       }
    });
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






