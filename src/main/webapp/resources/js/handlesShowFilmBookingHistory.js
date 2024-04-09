$(function () {
    const filmBookingMenu = $('#film-booking_menu');
    const filmBookingMenuContent = $('#film-booking_menu-content');

    filmBookingMenu.on('click', function () {
        $.ajax({
            url: '/api/v1/film-bookings',
            type: 'GET',
            cache: true,
            success: function (result) {
                filmBookingMenuContent.empty();
                if (result.length > 0) {
                    result.forEach(filmBooking => {
                        let itemHref = filmBooking.paymentStatus === 'pending' ? `/film-booking/${filmBooking.id}` : '#';
                        filmBookingMenuContent.append(`
                            <a class="menu-items_row" href=${itemHref}>
                                <div class="menu-items_img-container">
                                    <div><img style="width: 3.5rem; height: 3.5rem" src="${filmBooking.showtime.film.imgPath}" alt="film-img"></div>
                                </div>
                                <table class="menu-items_table">
                                    <tr>
                                        <td>
                                            ${filmBooking.showtime.film.filmName}
                                        </td>
                                         <td><span class="film-booking__label">Trang thai: </span> ${filmBooking.paymentStatus}</td>
                                    </tr>
                                    <tr>
                                        <td><span class="film-booking__label">Ngay chieu: </span> ${filmBooking.showtime.showtimeDate}</td>
                                        <td><span class="film-booking__label">Tong gia </span> ${filmBooking.showtime.showtimeTime}</td>   
                                    </tr>               
                                </table>
                            </a>
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