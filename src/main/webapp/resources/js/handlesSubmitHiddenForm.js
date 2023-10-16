import {$$} from "./utils.js";


let handlesItemCardsClick = {
    start: () => {
        try {
            let itemCards = $$(".item-cards");
            let hiddenForms = $$(".hidden-form");

            for (let i = 0; i < itemCards.length; i++) {
                itemCards[i].addEventListener("click", () => {
                    hiddenForms[i].submit();
                })
            }
        } catch (e) {
            console.log(e);
        }


    }
}

handlesItemCardsClick.start();


let handlesFilmManagementHiddenForm = {
    start: () => {
        try {
            let hiddenForms = $$(".hidden-form");
            let deleteButtons = $$(".delete-button");
            let editButtons = $$(".edit-button");

            for (let i = 0; i < deleteButtons.length; i++) {
                deleteButtons[i].addEventListener("click", () => {
                    hiddenForms[i].action = 'delete-film';
                    hiddenForms[i].submit();
                })
            }

            for (let i = 0; i < editButtons.length; i++) {
                editButtons[i].addEventListener("click", () => {
                    hiddenForms[i].action = 'edit-film';
                    hiddenForms[i].submit();
                })
            }
        } catch (e) {
            console.log(e);
        }
    }
}
handlesFilmManagementHiddenForm.start();






