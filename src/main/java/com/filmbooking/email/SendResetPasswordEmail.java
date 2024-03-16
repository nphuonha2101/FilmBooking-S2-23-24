package com.filmbooking.email;

import com.filmbooking.enumsAndConstant.enums.LanguageEnum;
import com.filmbooking.utils.PropertiesUtils;

import java.time.LocalDateTime;


public class SendResetPasswordEmail extends AbstractSendEmail {
    /**
     * This class is to use create instance to send "Reset Password Email" using token
     * <br>
     * List of key in this email
     * <ul>
     *     <li>userFullName</li>
     *     <li>username</li>
     *     <li>token</li>
     * </ul>
     */
    public SendResetPasswordEmail() {
        super();
    }
    @Override
    public AbstractSendEmail loadHTMLEmail(LanguageEnum language) {
        String resetPasswordUrlCallback = PropertiesUtils.getInstance().getProperty("reset_password.url");
        String currentYear = String.valueOf(LocalDateTime.now().getYear());

        // load email html template
        this.loadHTMLEmail("resetPasswordEmail.html", language);
        this.putEmailInfo("verifyUrl", resetPasswordUrlCallback);
        this.putEmailInfo("currentYear", currentYear);
        return this;
    }
}
