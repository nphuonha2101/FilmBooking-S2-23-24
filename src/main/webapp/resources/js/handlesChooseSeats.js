let handlesChooseSeats = {
    init: function () {
        let seats = $$('.seats');
        let seatHiddenInput = $('#seat');

        for (let i = 0; i < seats.length; i++) {
            seats[i].addEventListener('click', function () {


                if (this.classList.contains('seats-unavailable'))
                    alert('This seat is not available!');
                else if
                (this.classList.contains('seats-active')) {
                    this.classList.remove('seats-active')
                    seatHiddenInput.value = displaySelectedSeats();

                } else {

                    this.classList.add('seats-active')
                    seatHiddenInput.value = displaySelectedSeats();
                }
                displayTotalFee();
            });
        }
    },
}

handlesChooseSeats.init();

// get selectedSeats
let displaySelectedSeats = function () {
    let seats = $$('.seats-active');
    let selectedSeatElement = $('#selected-seat');

    selectedSeatElement.innerText = seats.length + " ghế";
}

let displayTotalFee = () => {
    let filmPriceHiddenInput = $('#film-price');
    let seats = $$('.seats-active');
    let numberOfSeat = seats.length;
    let filmPrice = filmPriceHiddenInput.value;
    let totalFeeElement = $('#total-fee');

    totalFeeElement.innerText = filmPrice * numberOfSeat + " VNĐ";
}

let displaySelectedSeat = function (){
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


let  handlesSelectShowtime = {
    init: function () {
     let selectElement = $('#select-showtime')
        let selectedShowtimeElement = $('#selected-showtime');
     let showtimeHiddenInput = $('#showtime-id');
     selectElement.addEventListener("change", function () {
         selectedShowtimeElement.innerText = selectElement.options[selectElement.selectedIndex].text;
        showtimeHiddenInput.value = selectElement.options[selectElement.selectedIndex].value;
     })
    }
}

handlesSelectShowtime.init();