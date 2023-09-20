let handlesNavigationBar = {
    start: () => {
        let navElement = $$(".top-nav")[0];

        if (window.scrollY > navElement.offsetHeight)
            navElement.classList.add("float");
        else
            navElement.classList.remove("float");
    }
}

window.addEventListener("scroll", handlesNavigationBar.start);