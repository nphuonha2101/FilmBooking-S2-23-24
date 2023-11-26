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

