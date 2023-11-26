import {$} from "./utils.js"

let handlesShowSearchModal = {
    start: () => {
        try {
            const searchButton = $("#search-button");
            const modalElement = $("#modal");

            searchButton.addEventListener("click", () => {
                if (modalElement.style.display === "none")
                    modalElement.style.display = "block";
                else
                    modalElement.style.display = "none";
            });
        } catch (e) {
            console.error(e);
        }
    }
}

handlesShowSearchModal.start();

let handlesCloseModal = {
    start: () => {
        try {
            const modalElement = $("#modal");
            const closeModalButton = $("#close-modal-button");

            closeModalButton.addEventListener("click", () => {
                modalElement.style.display = "none";
            })

        } catch (e) {
            console.error(e);
        }
    }
}
handlesCloseModal.start();

let handlesSubmitSearchForm = {
    start: () => {
        try {
            const searchInputForm = $("#search-input-form");
            const searchForm = $("#search-form");

            searchInputForm.addEventListener("keydown", (e) => {
                if (e.key === "Enter") {
                    searchForm.submit();
                }
            });
        } catch (e) {
            console.error(e);
        }

    }
}

handlesSubmitSearchForm.start();