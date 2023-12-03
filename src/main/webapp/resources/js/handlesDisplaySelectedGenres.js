import {$} from "./utils.js";

let handlesDisplaySelectedGenres = {
    start: function () {
        try {
            let genresSelectMultiple = $('#genre-ids');
            let genresSelectedSpan = $('#selected-genres');
            let result = '';

            genresSelectMultiple.addEventListener('change', function () {
                result = '';

                for (const genreOption of this.options) {
                    if (genreOption.selected)
                        result += genreOption.text + ' ';
                }
                genresSelectedSpan.innerHTML = result.trim();
            });
        } catch (e) {
            console.error(e);
        }
    }
}

handlesDisplaySelectedGenres.start();