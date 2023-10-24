import {$} from "./utils.js";

const uploadElement = $("#upload-img");
const filmImgElement = $("#film-img");
const imgForm = $("#img-form");
const hiddenImgFileName = $("#film-img-name");


let handleImgInput = {
    start: () => {
        uploadElement.addEventListener("change", handleImgInput.handleInput);
    },
    handleInput: function () {
        try {
            let imgFile = this.files[0];
            console.log(imgFile)
            let imgURL = URL.createObjectURL(imgFile);
            console.log(imgURL, imgFile.name)

            filmImgElement.style.backgroundImage = `url(${imgURL})`;
            filmImgElement.style.border = "2px solid #0556f3";
            hiddenImgFileName.value = imgFile.name;

        } catch (e) {
            console.log(e);
        }
    }
}

handleImgInput.start();


