
let showModalElements = $$(".show-modal-button");
let modalElement = $("#modal");
let hiddenForm = $("#hidden-form");
for (let showModalElement of showModalElements) {
    showModalElement.onclick = () => {
        // hiddenForm.submit();
        // modalElement.style.display = "block";
        hiddenForm.submit();
    }
}

function showModal() {

    modalElement.style.display = "block";
}

function hideModal() {
    let closeModalElement = $("#close-modal-button");
    closeModalElement.onclick = () => {
        modalElement.style.display = "none";
    }
}

hideModal();