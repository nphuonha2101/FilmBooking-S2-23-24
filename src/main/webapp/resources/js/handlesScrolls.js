import {$, $$} from "./utils.js";

let handlesNavigationBar = {
    start: () => {
        try {
            let navElement = $$(".top-nav")[0];

            if (window.scrollY > navElement.offsetHeight)
                navElement.classList.add("float");
            else
                navElement.classList.remove("float");
        } catch (e) {
            console.error(e);
        }
    }
}

window.addEventListener("scroll", handlesNavigationBar.start);


let handlesBackToTop = {
    start: function () {
        try {
            let backToTopElement = $("#back-to-top");

            console.log(scrollY)

            if (window.scrollY > 100) {
                backToTopElement.style.display = "block";
            } else {
                backToTopElement.style.display = "none";
            }
            backToTopElement.style.animationName = "fade";
            backToTopElement.style.animationDuration = "0.75s";

            backToTopElement.onclick = () => {
                window.scrollTo(0, 0);
            }
        } catch (e) {
            console.error(e);
        }
    }

}

window.addEventListener("scroll", handlesBackToTop.start)


// let handlesFloatingTabDynamic = {
//     start: function () {
//         let floatingElement = $("#float-tab");
//         console.log(floatingElement.offsetTop + floatingElement.offsetHeight, window.scrollY)
//         if (window.scrollY > (floatingElement.offsetTop + floatingElement.offsetHeight)) {
//             floatingElement.classList.add("float-tab-dynamic");
//         } else {
//             floatingElement.classList.remove("float-tab-dynamic");
//         }
//     }
// }
//
// window.addEventListener("scroll", handlesFloatingTabDynamic.start)