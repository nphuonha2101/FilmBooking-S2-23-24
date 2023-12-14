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
            console.error(e);
        }


    }
}

handlesItemCardsClick.start();






