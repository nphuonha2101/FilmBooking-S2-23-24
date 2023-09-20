const $ = document.querySelector.bind(document);

let handlesBackToTop = {
    start: function () {
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
    }

}

window.addEventListener("scroll", handlesBackToTop.start)