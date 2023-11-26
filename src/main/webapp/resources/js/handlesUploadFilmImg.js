import {$} from "./utils.js";

let handleImgInput = {
    start: () => {
        try {
            const uploadElement = $("#upload-img");

            uploadElement.addEventListener("change", handleImgInput.handleInput);
        } catch (e) {
            console.error(e);
        }
    },
    handleInput: function () {
        try {
            const filmImgElement = $("#film-img");
            const imgForm = $("#img-form");
            const hiddenImgFileName = $("#film-img-name");
            let imgFile = this.files[0];

            console.log(imgFile)
            let imgURL = URL.createObjectURL(imgFile);
            console.log(imgURL, imgFile.name)

            filmImgElement.style.backgroundImage = `url(${imgURL})`;
            filmImgElement.style.border = "2px solid #0556f3";
            hiddenImgFileName.value = imgFile.name;

        } catch (e) {
            console.error(e);
        }
    }
}

handleImgInput.start();


