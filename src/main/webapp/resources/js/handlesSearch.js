$('.search-box').hide();

$('#search').on('click', function() {
    $('.search-box').toggle();
});

$(document).ready(function(){
    $.ajaxSetup({ cache: false });
    $('#search-input').keyup(function(){
        $('#result').html('');
        $('#state').val('');
        var searchField = $('#search-input').val();
        if (searchField) {
            var expression = new RegExp(searchField, "i");
            $.getJSON('http://localhost:8080/api/v1/films', function(data) {
                $.each(data, function(key, value){
                    if (value.filmName.search(expression) != -1)
                    {
                        $('#result').append('<a href="http://localhost:8080/film-info?film=' + value.slug + '" style="text-decoration: none"><li class="list-group-item link-class"><img src="'+value.imgPath+'"/> '+value.filmName+'</li></a>');
                    }
                });
            });
        }
    });
});

