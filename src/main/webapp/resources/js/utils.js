export {$, $$};
export {handlesCloseButton};
const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);


let handlesCloseButton = (containerElement) => {
    try {
        let closeButton = containerElement.querySelector(".close-button");
        closeButton.addEventListener("click", () => {
            containerElement.style.display = "none";
        })
    } catch (e) {
        console.error(e);
    }
}

let handlesActiveButton = {
    start: () => {
        const navLinks = $$(".nav-links");

        for (const navLink of navLinks) {
            if (navLink.id !== null) {
                navLink.addEventListener("click", () => {
                    localStorage.setItem("navElementActive", navLink.id);
                });
            }
        }

        window.onload = () => {
            for (const navLink of navLinks) {
                navLink.classList.remove("nav-active");

                let navLinkActiveElement = $("#"+ navLink.id);
                let url = window.location.href;

                if (url.includes(navLink.id))
                    navLinkActiveElement.classList.add("nav-active");
            }
        }
    }
}

handlesActiveButton.start();

