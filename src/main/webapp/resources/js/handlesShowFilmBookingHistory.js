$(function () {
    const bookingHistoryModal = $('#booking-history-modal');
    const filmBookingMenuContent = $('#film-booking_menu-content');

    console.log('bookingHistoryModal: ', bookingHistoryModal)

    bookingHistoryModal.on('show.bs.modal', function () {
        let html = '';

        const getLoginUser = $.ajax({
            url: '/api/v1/users?command=loginUser',
            type: 'GET',
            cache: true,
            dataType: 'json'
        });

        $.when(getLoginUser).done(function (loginUserResponse) {
            console.log('loginUserResponse: ', loginUserResponse)
            if (loginUserResponse.status === 401) {
                filmBookingMenuContent.html(`<h4>Vui lòng đăng nhập</h4>`);
                return;
            } else {
                const user = loginUserResponse.data;

                const getCurrentFilmBooking = $.ajax({
                    url: '/api/v1/film-bookings?command=current',
                    type: 'GET',
                    cache: true,
                    dataType: 'json'
                });

                const getFilmBookingByOffset = $.ajax({
                    url: '/api/v1/film-bookings?command=byUsername&username=' + user.username,
                    type: 'GET',
                    cache: true,
                    dataType: 'json'
                });

                $.when(getCurrentFilmBooking, getFilmBookingByOffset)
                    .done(function (currentFilmBookingResponse, filmBookingByOffsetResponse) {
                        console.log('current filmbooking resp: ',  currentFilmBookingResponse)

                        // Current film booking
                        if (currentFilmBookingResponse[0]["status"] === 200) {
                            console.log('currentFilmBookingResponse: ', currentFilmBookingResponse)
                            const data = currentFilmBookingResponse[0].data;

                            if (data === null || data === undefined) {
                                filmBookingMenuContent.html('' +
                                    '<h4>Đặt vé hiện tại</h4><p>Không có dữ liệu</p>');
                                return;
                            }

                            const filmBooking = data.filmBooking;
                            const showtime = data.showtime;
                            const film = data.film;
                            html += `<h4>Đặt vé hiện tại</h4>`
                            html += `
                            <div class="fb-item_horizontal-card">
                                <div class="fb-item_horizontal-card__img">
                                <img src="${film.imgPath}" alt="poster">
                                </div>
    
                                <div class="fb-item_horizontal-card__content">
                                    <a href="http://localhost:8080/film-info?film=${film.slug}">
                                        <h5>${film.filmName}</h5>
                                    </a>
<!--                                    <p>Ghế ngồi: ${filmBooking.seatsData.replace(' ', '')}</p>-->
                                    <p>Số tiền: ${filmBooking.totalFee} VNĐ</p>
                                    <p>Thời gian đặt: ${showtime.showtimeDate}</p>
                                </div>
        
                                <div class="fb-item_horizontal-card__status">
                                      <p class="badge bg-danger"><a class="text-decoration-none text-light fw-bold" style="font-size: 0.95rem" href="/auth/checkout">Chưa thanh toán</a></p>
                                </div>
                            </div>
                        `;
                        } else {
                            filmBookingMenuContent.html('' +
                                '<h4>Đặt vé hiện tại</h4><p>Không có dữ liệu</p>');
                        }

                        try {
                            // 5 film-bookings history
                            if (filmBookingByOffsetResponse[0]["status"] === 200) {
                                console.log('filmBookingByOffsetResponse: ', filmBookingByOffsetResponse)
                                let data = filmBookingByOffsetResponse[0].data;

                                if (data !== null && data !== undefined) {

                                    data = data.filter(item => item.filmBooking.paymentStatus === 'paid')

                                    data.map(item => {
                                        const filmBooking = item.filmBooking;
                                        const showtime = item.showtime;
                                        const film = item.film;

                                        html += `
                                    <h4>Đặt vé trước</h4>
                                    <div class="fb-item_horizontal-card" xmlns="http://www.w3.org/1999/html">
                                        <div class="fb-item_horizontal-card__img">
                                        <img src="${film.imgPath}" alt="poster">
                                        </div>
        
                                        <div class="fb-item_horizontal-card__content">
                                            <a href="http://localhost:8080/film-info?film=${film.slug}">
                                                <h5>${film.filmName}</h5>
                                            </a>
<!--                                            <p>Ghế ngồi: ${filmBooking.seatsData.replace(' ', '')}</p>-->
                                            <p>Số tiền: ${filmBooking.totalFee} VNĐ</p>
                                            <p>Thời gian đặt: ${showtime.showtimeDate}</p>
                                        </div>
            
                                        <div class="fb-item_horizontal-card__status">
                                            <p class="badge bg-success" style="font-size: 0.95rem">Đã thanh toán</p>
                                        </div>
                                    </div>
                                `;

                                    })
                                } else {
                                    html = `<h4>Đặt vé hiện tại</h4><p>Không có dữ liệu</p>`
                                }


                            } else {
                                html = `<h4>Đặt vé hiện tại</h4><p>Không có dữ liệu</p>`
                            }
                        } catch (e) {
                            console.error(e)
                        }

                        console.log(html)

                        filmBookingMenuContent.html(html);

                    })
            }
        })
    });
});





