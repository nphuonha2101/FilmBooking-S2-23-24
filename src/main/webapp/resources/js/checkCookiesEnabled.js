import {$, handlesCloseButton} from './utils';

let checkCookiesEnabled = {
    start: () => {
        try {
            if (navigator.cookieEnabled === false) {
                let cookiesDisabledPopup = $("#cookies-disabled-popup");
                cookiesDisabledPopup.style.display = "flex";
                cookiesDisabledPopup.classList.add("float");
            }
        } catch (e) {
            console.error(e);
        }
    }
}

// checkCookiesEnabled.start();

let handlesCookiesDisabledPopup = {
    start: () => {
        try {
            let cookiesDisabledPopup = $("#cookies-disabled-popup");
            handlesCloseButton(cookiesDisabledPopup);

        } catch (e) {
            console.error(e)
        }
    }
}
// handlesCookiesDisabledPopup.start();
