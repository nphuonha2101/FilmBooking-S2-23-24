function handlesSubmitForm(formId, method, endpoint) {
    const form = document.getElementById(formId);




    form.addEventListener("submit", (event) => {
        event.preventDefault();

        let formData = new FormData(form);

        formData.forEach((value, name) => {
            console.log(name, value);
        });

        fetch(endpoint, {
            method: method,
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                alert(data.message);
            })
    });
}