const $ = document.querySelector.bind(document);

console.log("Test");

let handlesBacktoTop = {
    start: function() {
        let backToTopElement = $("#back-to-top");

        if (scrollY > 100) {
            backToTopElement.style.display = "block";
        } else {
            backToTopElement.style.animationName = "fade";
            backToTopElement.style.animationDuration = "0.75s";
            backToTopElement.style.display = "none";
        }

        backToTopElement.onclick = () => {
            window.scrollTo(0, 0);
        }
    }

}

window.addEventListener("scroll", handlesBacktoTop.start)