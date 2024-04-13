import {$, $$} from "./utils.js";

let handlesChooseSeats = {
    start: function () {
        try {
            let seats = $$('.seats');
            let seatHiddenInput = $('#seats');

            for (let i = 0; i < seats.length; i++) {
                seats[i].addEventListener('click', function () {


                    if (this.classList.contains('seats-unavailable'))
                        alert('Ghế này đã có người chọn rồi! (This seat has already booked!)');
                    else if
                    (this.classList.contains('seats-active')) {
                        this.classList.remove('seats-active')
                        seatHiddenInput.value = displaySelectedSeat();

                    } else {

                        this.classList.add('seats-active')
                        seatHiddenInput.value = displaySelectedSeat();
                    }
                    displayTotalFee();
                });
            }
        } catch (e) {
            console.error(e);
        }
    }
}
handlesChooseSeats.start();


// get selectedSeats

let displayTotalFee = () => {
    let filmPrice = parseFloat($('#booked-film-price').innerText);
    let seats = $$('.seats-active');
    let numberOfSeat = seats.length;
    let totalFeeElement = $('#total-fee');

    totalFeeElement.innerText = filmPrice * numberOfSeat + " VNĐ";
}

let displaySelectedSeat = function () {
    let seats = $$('.seats-active');
    let seatValue = '';
    for (let i = 0; i < seats.length; i++) {
        if (seatValue === '')
            seatValue += seats[i].textContent.trim();
        else
            seatValue += ', ' + seats[i].textContent;
    }

    // alert(seatValue);
    return seatValue;
}

