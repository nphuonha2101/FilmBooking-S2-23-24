<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 15-09-2023
  Time: 9:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<body>
<section class="content section centered-content">
    <div class="centered-content container form__container">
        <h2 class="title">Reset your password</h2>
        <span class="successful-span">${successfulMessage}</span>
        <form action="handles-reset-password" method="post">
            <label for="new-password">New password</label> <span class="error-span" id="password-error">${passwordError}</span>
            <input type="password" name="new-password" id="new-password" placeholder="New password" required>
            <label for="confirm-new-password">Confirm new password</label> <span class="error-span" id="confirm-password-error">${confirmPasswordError}</span>
            <input type="password" name="confirm-new-password" id="confirm-new-password" placeholder="Confirm new password" required>
            <input type="submit" class="primary-filled-button button" value="Reset password">
        </form>
    <span>${additionElement}</span>
    </div>

</section>
