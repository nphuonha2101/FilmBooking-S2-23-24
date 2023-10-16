import {$$} from "./utils.js";

let handlesItemCardsFadeIn = {
    start: function () {
        try {
            let itemCards = $$(".item-cards");

            for (const itemCard of itemCards) {
                let elementTop = itemCard.offsetTop;
                let elementBottom = itemCard.offsetTop + itemCard.offsetHeight;
                let limitTop = window.scrollY + 100;
                let limitBottom = window.scrollY + window.innerHeight - 100;

                // if element offset is not in the limit of window scrollY
                if ((elementTop < limitTop) || (elementBottom > limitBottom)) {
                    // itemCard.classList.add("fade-in");
                    console.log("scrollY: " + window.scrollY + " itemCard.offsetTop: " + itemCard.offsetTop + " " + itemCard.id);
                    itemCard.classList.add("fade-in");
                } else
                    itemCard.classList.remove("fade-in");
            }
        } catch (e) {
            console.log(e);
        }
    }
}


// window.addEventListener("scroll", handlesItemCardsFadeIn.init);