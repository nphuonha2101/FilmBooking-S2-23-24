$(document).ready(function () {
    let command = 'all';
    $.ajaxSetup({cache: false});
    $('#search-input').keyup(function () {
        $('#search-result').html('');
        const searchField = $('#search-input').val();
        if (searchField) {
            const expression = new RegExp(searchField, "i");
            $.getJSON(`/api/v1/films?command=${command}`, function (json) {
                if (json['status'] === 200)
                    $.each(json['data'], function (key, value) {
                        if (value.filmName.search(expression) !== -1) {
                            $('#search-result').append('<a href="http://localhost:80/film-info?film=' + value.slug + '" style="text-decoration: none; "><li class="list-group-item link-class"><img src="' + value.imgPath + '" style="width: 5rem;"/> ' + value.filmName + '<br> ' + value.director + '<br> ' + value.cast + '</li></a>');
                        }
                    });
                else
                    $('#search-result').append('<li class="list-group-item link-class">Không tìm thấy kết quả</li>');
            });

            $('#search-result').toggle();
        }
    });
});

