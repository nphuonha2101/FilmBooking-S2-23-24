import {$$} from "./utils.js"

let handlesAccordions = {
    start: function () {
        try {
            let accordions = Array.from($$(".accordion"));
            let accordionPanes = $$(".accordion-panel");
            let accordionIcons = $$(".accordion-icon");

            for (let i = 0; i < accordions.length; i++) {
                accordions[i].addEventListener("click", (event) => {

                    let selectedAccordion = event.target;
                    let selectedIndex = accordions.indexOf(selectedAccordion);

                    if (!selectedAccordion.classList.contains("active")) {
                        closeAllAccordion();
                        openAccordion(selectedIndex);
                    } else {
                        closeAllAccordion();
                    }


                });
            }

            let closeAllAccordion = function () {
                // remove all active class
                accordions.forEach((accordion) => {
                    accordion.classList.remove("active");
                });
                // hide all accordion panel
                accordionPanes.forEach((panel) => {
                    panel.style.display = "none";
                });
                // change all accordion icon
                accordionIcons.forEach((icon) => {
                    icon.innerText = 'expand_more';
                });
            }

            let openAccordion = function (index) {
                console.log(accordions[index]);
                // add active class to selected accordion
                accordions[index].classList.add("active");
                // show selected accordion panel
                accordionPanes[index].style.display = "block";
                // change accordion icon
                accordionIcons[index].innerText = 'expand_less';
            }
        } catch (e) {
            console.error(e);
        }
    }
}

handlesAccordions.start();