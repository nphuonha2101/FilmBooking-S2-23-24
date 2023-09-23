function validateUsername() {
    let usernameField = $("#username");
    let usernameError = $("#username-error");
    if (usernameField != null) {
        let fieldValue = usernameField.value;
        if (fieldValue != null) {
            fieldValue = fieldValue.trim();
            return true;

        } else {
            usernameError.innerHTML = "Username cannot empty";
            return false;
        }


    } else
        return true;
}