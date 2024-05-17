$(function () {
    const filmBookingMenu = $('#film-booking_menu');
    const filmBookingMenuContent = $('#film-booking_menu-content');

    filmBookingMenu.on('click', function () {
        let html = '';

        const getCurrentFilmBooking = $.ajax({
            url: '/api/v1/film-bookings?command=current',
            type: 'GET',
            cache: true,
            dataType: 'json'
        });

        const getFilmBookingByOffset = $.ajax({
            url: '/api/v1/film-bookings?command=offset&offset=0&limit=5',
            type: 'GET',
            cache: true,
            dataType: 'json'
        });

        $.when(getCurrentFilmBooking, getFilmBookingByOffset)
            .done(function (currentFilmBookingResponse, filmBookingByOffsetResponse) {
                console.log(currentFilmBookingResponse)

                // Current film booking
                if (currentFilmBookingResponse[0].status === 200) {
                    console.log('currentFilmBookingResponse: ', currentFilmBookingResponse)
                    let filmBooking = currentFilmBookingResponse[0].data;
                    let showtime = filmBooking.showtime;

                    html = `<h4>Phim đang đặt</h4>`
                    if (showtime !== undefined) {
                        let film = showtime.film;
                        html += `    
                                <div class="fb-item_horizontal-card">
                                    <div class="fb-item_horizontal-card__img">
                                        <img src="${film.imgPath}" alt="poster">
                                    </div>
                                    
                                    <div class="fb-item_horizontal-card__content">
                                        <a href="http://localhost:8080/film-info?film=${film.slug}">
                                            <h5>${film.filmName}</h5>
                                        </a>
                                        <p>Ghế ngồi: ${filmBooking.seatsData.replace(' ', '')}</p>
                                        <p>Số tiền: ${filmBooking.totalFee} VNĐ</p>
                                        <p>Thời gian đặt: ${showtime.showtimeDate}</p>
                                    </div>
                                    
                                    <div class="fb-item_horizontal-card__status">
                                        <a href="http://localhost:8080/auth/checkout" class="button rounded-button outlined-warning-button">Chưa thanh toán</a>
                                    </div>
                                </div>
                        `;
                    } else {
                        html += '<p>Không có dữ liệu</p>';
                    }
                }

                try {
                    // 5 film-bookings history
                    if (filmBookingByOffsetResponse[0].status === 200) {
                        console.log('filmBookingByOffsetResponse: ', filmBookingByOffsetResponse)
                        let filmBookings = filmBookingByOffsetResponse[0].data;

                        html += `<h4>Lịch sử đặt vé</h4>`
                        if (filmBookings.length > 0) {
                            filmBookings.forEach(filmBooking => {
                                let showtime = filmBooking.showtime;
                                let film = showtime.film;

                                html += `    
                                <div class="fb-item_horizontal-card">
                                    <div class="fb-item_horizontal-card__img">
                                        <img src="${film.imgPath}" alt="poster">
                                    </div>
                                    
                                    <div class="fb-item_horizontal-card__content">
                                        <a href="http://localhost:8080/film-info?film=${film.slug}">
                                            <h5>${film.filmName}</h5>
                                        </a>
                                        <p>Ghế ngồi: ${filmBooking.seatsData.replace(' ', '')}</p>
                                        <p>Số tiền: ${filmBooking.totalFee} VNĐ</p>
                                        <p>Thời gian: ${showtime.showtimeDate}</p>
                                    </div>
                                    
                                    <div class="fb-item_horizontal-card__status">
                                        <div class="light-filled-button rounded-button button">Đã thanh toán</div>
                                    </div>
                                </div>
                            `;
                            });
                        } else {
                            html += '<p>Không có dữ liệu</p>';
                        }
                    }
                } catch (e) {
                    console.error(e)
                }

                filmBookingMenuContent.html(html);

            });

        filmBookingMenuContent.toggle();
    });
});