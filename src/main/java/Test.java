/*
 *  @created 13/01/2024 - 8:06 PM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.email.AbstractSendEmail;
import com.filmbooking.email.SendResetPasswordEmail;
import com.filmbooking.enumsAndConstant.enums.LanguageEnum;
import com.filmbooking.payment.VNPay;
import com.filmbooking.utils.PropertiesUtils;
import com.filmbooking.utils.StringUtils;

import java.io.UnsupportedEncodingException;

public class Test {
    public static void main(String[] args) {
        AbstractSendEmail resetEmail = new SendResetPasswordEmail();
        resetEmail
                .loadHTMLEmail(LanguageEnum.ENGLISH)
                .putEmailInfo("userFullName", "Nguyen Phuong Nha")
                .loadEmailContent();

        String htmls = resetEmail.testToString();

        System.out.println(htmls);


    }
}
