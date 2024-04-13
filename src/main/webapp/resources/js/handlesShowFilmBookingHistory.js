$(function () {
    const filmBookingMenu = $('#film-booking_menu');
    const filmBookingMenuContent = $('#film-booking_menu-content');
    let html = '';

    const getCurrentFilmBooking = $.ajax({
        url: '/api/v1/film-bookings?current=true',
        type: 'GET',
        cache: true,
        dataType: 'json'
    });

    const getFilmBookingByOffset = $.ajax({
        url: '/api/v1/film-bookings?offset=0&limit=5',
        type: 'GET',
        cache: true,
        dataType: 'json'
    });


    filmBookingMenu.on('click', function () {
        $.when(getCurrentFilmBooking, getFilmBookingByOffset)
            .done(function (currentFilmBookingResponse, filmBookingByOffsetResponse) {
                if (currentFilmBookingResponse.length > 0) {
                    console.log('currentFilmBookingResponse: ', currentFilmBookingResponse)
                    let filmBooking = currentFilmBookingResponse[0].data;
                    let showtime = filmBooking.showtime;
                    let film = showtime.film;
                    html = `<div class="film-item_contents"> 
                              <div class="film-img-contents_container" style="width: 4rem; height: 5rem; overflow: hidden;">
                                    <img src="${film.imgPath}" style="width: 4rem; height: 5rem; object-fit: cover;"  alt="poster" class="film-img-contents">
                               </div>
                                 <div class="film-info-contents">
                                        <p class="film-title font-bold">${film.filmName}</p>
                                        <div class="two-col__wrapper">
                                            <div class="two-col__left">
                                                <p>${filmBooking.bookingDate}</p>
                                            </div>
                                            
                                            <div class="two-col__right">
                                                <a href="http://localhost/auth/checkout">Cho thanh toan</a>
                                            </div>
                                        </div>
                                 </div>      
                            </div>`

                    filmBookingMenuContent.html(html);
                }
            });
        filmBookingMenuContent.toggle();

    });
});