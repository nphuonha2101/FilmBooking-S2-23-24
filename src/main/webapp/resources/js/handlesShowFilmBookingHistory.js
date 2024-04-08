$(function () {
    const filmBookingMenu = $('#film-booking_menu');
    const filmBookingMenuContent = $('#film-booking_menu-content');

    filmBookingMenu.on('click', function () {
        $.ajax({
            url: '/api/v1/film-bookings',
            type: 'GET',
            success: function (result) {
                filmBookingMenuContent.empty();
                if (result.length > 0) {
                    result.forEach(filmBooking => {
                        filmBookingMenuContent.append(`
                            <div class="menu-items_row">
                                <div><span class="film-booking__label"><img style="width: 2rem; height: 2rem" src="${filmBooking.showtime.film.imgPath}" alt="film-img"></div>
                                <div><span class="film-booking__label"></span> ${filmBooking.showtime.film.filmName}</div>
                                <div><span class="film-booking__label"></span> ${filmBooking.showtime.showtimeDate}</div> 
                                <div><span class="film-booking__label"></span> ${filmBooking.totalFee}</div>
                                <div><span class="film-booking__label"></span> ${filmBooking.paymentStatus}</div>
                            </div>
                        `);
                    });

                    console.log(result);
                } else {
                    filmBookingMenuContent.append('<p>No film booking history</p>');
                }
            },
            error: function (error) {
                console.log(error);
            }
        });

        filmBookingMenuContent.toggle();
    });
})