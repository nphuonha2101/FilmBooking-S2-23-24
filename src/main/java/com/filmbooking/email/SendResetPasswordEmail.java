/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */

package com.filmbooking.email;

import com.filmbooking.enumsAndConstants.enums.LanguageEnum;
import com.filmbooking.enumsAndConstants.enums.TokenTypeEnum;
import com.filmbooking.utils.PropertiesUtils;

import java.time.LocalDateTime;
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
        String tokenVerifyUrl = PropertiesUtils.getProperty("verify_token.url");
        String currentYear = String.valueOf(LocalDateTime.now().getYear());

        // load email html template
        this.loadHTMLEmail("resetPasswordEmail.html", language);
        this.putEmailInfo("verifyUrl", tokenVerifyUrl);
        this.putEmailInfo("currentYear", currentYear);
        this.putEmailInfo("tokenType", TokenTypeEnum.PASSWORD_RESET.getTokenType());
        return this;
    }
}
