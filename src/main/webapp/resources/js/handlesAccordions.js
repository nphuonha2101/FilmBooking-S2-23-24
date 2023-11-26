import {$, $$} from "./utils.js"

let handlesAccordions = {
    start: function () {
        try {
            let accordions = $$(".accordion");
            let accordionPanes = $$(".accordion-panel");

            for (let i = 0; i < accordions.length; i++) {
                accordions[i].addEventListener("click", () => {
                    if (accordions[i].classList.contains("active"))
                        accordions[i].classList.remove("active");
                    else
                        accordions[i].classList.add("active");

                    // this is get child panel of this accordions
                    let panel = accordionPanes[i];
                    if (panel.style.display === "block")
                        panel.style.display = "none";
                    else
                        panel.style.display = "block";
                });
            }
        } catch (e) {
            console.error(e);
        }
    }
}

handlesAccordions.start();