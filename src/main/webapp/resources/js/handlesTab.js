import {$$} from "./utils.js";

let handlesTab = {
    start: function () {
        try {
            let tabLabels = $$(".tab-label");
            let tabContents = $$(".tab-content");

            for (let i = 0; i < tabLabels.length; i++) {
                let tabLabel = tabLabels[i];
                tabLabel.addEventListener("click", () => {
                    for (let j = 0; j < tabLabels.length; j++) {
                        tabLabels[j].classList.remove("active");
                        tabContents[j].classList.remove("active");
                    }
                    tabLabel.classList.add("active");
                    tabContents[i].classList.add("active");

                })
            }

        } catch (e) {
            console.log(e);
        }
    }
}

handlesTab.start();

