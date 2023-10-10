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
                    seatHiddenInput.value = getSelectedSeats();
                } else {
                    this.classList.add('seats-active')
                    seatHiddenInput.value = getSelectedSeats();
                }
            });
        }
    },
}

handlesChooseSeats.init();

// get selectedSeats
let getSelectedSeats = function () {
    let seats = $$('.seats-active');
    let seatValue = '';
    for (let i = 0; i < seats.length; i++) {
        if (seatValue === '')
            seatValue += seats[i].textContent;
        else
            seatValue += '-' + seats[i].textContent;
    }

    alert(seatValue);
    return seatValue;
}