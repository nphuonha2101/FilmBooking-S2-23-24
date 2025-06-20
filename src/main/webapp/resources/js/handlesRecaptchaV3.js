// Created by IntelliJ IDEA.
// User: nphuo
// Date: 5/4/2024
// Time: 2:22 PM
// To change this template use File | Settings | File Templates.

/**
 * Handles reCAPTCHA v3 form submission.
 * @param formId The ID of the form element. E.g., 'contact-form'
 * @param action The reCAPTCHA action to execute. E.g., 'submit'
 */
function handleRecaptchaV3(formId, action) {
    const SERVER_VERIFY_ENDPOINT = '/verify-recaptcha-v3';
    const SITE_KEY = '6LdDfMspAAAAAPEeao2SrUavtknu5jxAi1E4d_Wr';
    const formElement = document.getElementById(formId);
    formElement.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent default form submission

        // Check if grecaptcha is ready
        if (typeof grecaptcha === 'undefined' || !grecaptcha.execute) {
            alert('reCAPTCHA is not ready. Please try again later.');
            return;
        }

        console.log(grecaptcha)

        grecaptcha.execute(SITE_KEY, {action: action})
            .then(function (token) {
                // Send the token to the server for verification
                const formData = new FormData(formElement);
                formData.append('g-recaptcha-response', token);
                formData.append('action', action);


                console.log(SERVER_VERIFY_ENDPOINT)
                fetch(SERVER_VERIFY_ENDPOINT, {
                    method: 'POST', body: formData
                })
                    .then(async function (response) {
                        const responseText = await response.text()

                        // response send redirect url
                        if (response.headers.get('Content-Type').includes('text/plain')) {
                            window.location.href = responseText;
                            return;
                        }

                        console.log("response", response.headers.get('Content-Type'))
                        // response send html
                        const mainElement = document.querySelector('main');
                        mainElement.innerHTML = responseText;
                        console.log("html", responseText)
                        // }

                    })
            });
    });
}
