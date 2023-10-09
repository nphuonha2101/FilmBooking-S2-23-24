let handlesChooseSeats = {
    init: function () {
        let seats = $$('.seats');
        let seatHiddenInput = $('#seat');
        for (let i = 0; i < seats.length; i++) {
            seats[i].addEventListener('click', function () {
                if (this.classList.contains('seats-unavailable')) {
                    alert('This seat is not available!');
                } else if
                (this.classList.contains('seats-active')) {
                    this.classList.remove('seats-active')
                    seatHiddenInput.value = this.getSeats();
                } else {
                    this.classList.add('seats-active')
                    seatHiddenInput.value = this.getSeats();
                }
            });
        }
    },

    getSeats: function () {
        let seats = $$('.seats-active');
        let seatValue = '';
        for (let i = 0; i < seats.length; i++) {
            seatValue += seats[i].textContent + '-';
        }
        return seatValue;
    }
}

handlesChooseSeats.init();

alert("chọn ghế " + handlesChooseSeats.getSeats());