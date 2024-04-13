import {$} from "./utils.js";

let handlesSelectShowtime = {
    init: function () {
        try {
            let selectElement = $('#select-showtime')
            let selectedShowtimeElement = $('#selected-showtime');
            let showtimeHiddenInput = $('#showtime-id');
            let showtimeIDHiddenInput = $('#showtime-id_hidden');

            selectedShowtimeElement.innerText = selectElement.options[0].text;
            showtimeHiddenInput.value = selectElement.options[0].value;
            showtimeIDHiddenInput = selectElement.options[0].value;

            selectElement.addEventListener("change", function () {
                selectedShowtimeElement.innerText = selectElement.options[selectElement.selectedIndex].text;
                showtimeHiddenInput.value = selectElement.options[selectElement.selectedIndex].value;
                showtimeIDHiddenInput = selectElement.options[selectElement.selectedIndex].value;
            })
        } catch (e) {
            console.error(e);
        }
    }
}

handlesSelectShowtime.init();