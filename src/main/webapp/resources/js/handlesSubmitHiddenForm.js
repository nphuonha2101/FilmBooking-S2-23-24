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


let handlesEditDeleteButtons = (actionDeleteURI, actionEditURI) => {
    try {
        let hiddenForms = $$(".hidden-form");
        let deleteButtons = $$(".delete-button");
        let editButtons = $$(".edit-button");

        for (let i = 0; i < deleteButtons.length; i++) {
            deleteButtons[i].addEventListener("click", () => {
                hiddenForms[i].action = actionDeleteURI;
                hiddenForms[i].submit();
            })
        }

        for (let i = 0; i < editButtons.length; i++) {
            editButtons[i].addEventListener("click", () => {
                hiddenForms[i].action = actionEditURI;
                hiddenForms[i].submit();
            })
        }
    } catch (e) {
        console.log(e);
    }
}

let handlesEditDeleteObject = {
    start: () => {
        try {
            let navigationURL = window.location.href;

            if (navigationURL.includes("/film-management")) {
                handlesEditDeleteButtons("/delete-film", "/edit-film");
            }
            if (navigationURL.includes("/showtime-management")) {
                handlesEditDeleteButtons("/delete-showtime", "/edit-showtime");
            }
            if (navigationURL.includes("/room-management")) {
                handlesEditDeleteButtons("/delete-room", "/edit-room");
            }
        } catch (e) {
            console.log(e);
        }

    }
}

handlesEditDeleteObject.start();





