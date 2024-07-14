/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */

package com.filmbooking.email;

import com.filmbooking.enumsAndConstants.constants.PathConstant;
import com.filmbooking.enumsAndConstants.enums.LanguageEnum;
import com.filmbooking.model.LogModel;
import com.filmbooking.utils.PropertiesUtils;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * This class is used to send email to user
 * <br>
 * <ul>
 *     <li>
 *         This class is used to load HTML email file to {@link AbstractSendEmail#emailHtmls}
 *     </li>
 *     <li>
 *         This class is used to replace all /key/ in the email content with value
 *     </li>
 *     <li>
 *         This class is used to send email to user with content from emailHtmls
 *     </li>
 * </ul>
 */
public abstract class AbstractSendEmail {
    protected StringBuilder emailHtmls;
    protected Map<String, Object> emailInfo;
    protected Properties emailProperties;


    protected AbstractSendEmail() {
        this.emailInfo = new HashMap<>();
        this.emailHtmls = new StringBuilder();

        emailProperties = new Properties();
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.host", PropertiesUtils.getProperty("email.hostName"));
        emailProperties.put("mail.smtp.socketFactory.port", Integer.parseInt(PropertiesUtils.getProperty("email.sslPort")));
        emailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        emailProperties.put("mail.smtp.port", Integer.parseInt(PropertiesUtils.getProperty("email.sslPort")));
    }

    /**
     * Get session to send email
     *
     * @return a {@link Session}
     */
    private Session getSession() {
        return Session.getDefaultInstance(emailProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(PropertiesUtils.getProperty("email.appName"), PropertiesUtils.getProperty("email.appPassword"));
            }
        });
    }
    
    /**
     * <ul>
     *      <li>
     *          This method is use locally for Child Class load suitable HTML email file to {@link AbstractSendEmail#emailHtmls}
     *      </li>
     *      <li>
     *          <b>This method is used by {@link AbstractSendEmail#loadHTMLEmail(LanguageEnum)}</b>
     *      </li>
     * </ul>
     *
     * @param htmlFileName name of HTML file
     * @param language     language of email
     */
    protected void loadHTMLEmail(String htmlFileName, LanguageEnum language) {
        BufferedReader reader = null;
        try {
            String resourcePath = language == null || language.equals(LanguageEnum.VIETNAMESE) || language.equals(LanguageEnum.DEFAULT) ?
                    PathConstant.HTML_EMAILS_VI_PATH + htmlFileName :
                    PathConstant.HTML_EMAILS_EN_PATH + htmlFileName;

            System.out.println("Resource path: " + resourcePath);
            reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(this.getClass().getResourceAsStream(resourcePath))));

            String line;
            while ((line = reader.readLine()) != null) {
                emailHtmls.append(line).append("\n");
            }

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is to load HTML email file to {@link AbstractSendEmail#emailHtmls}
     *
     * @param language language of email
     * @return {@link AbstractSendEmail} for chaining
     */
    public abstract AbstractSendEmail loadHTMLEmail(LanguageEnum language);

    /**
     * Put email info to storage email content and using it to replace all /key/ in the email content with value
     * <br>
     * Example: putEmailInfo("username", "John")
     *
     * @param key   key to replace in email content
     * @param value value to replace
     * @return {@link AbstractSendEmail} for chaining
     */
    public AbstractSendEmail putEmailInfo(String key, Object value) {
        emailInfo.put("/" + key + "/", value);
        return this;
    }

    /**
     * Load email content from emailHtmls and replace all /key/ in the email content with value
     *
     * @return {@link AbstractSendEmail} for chaining
     */
    public AbstractSendEmail loadEmailContent() {
        for (Map.Entry<String, Object> entry : emailInfo.entrySet()) {
            String key = entry.getKey();
            String value = (String) entry.getValue();

            int indexOfKeyInSB;

            while ((indexOfKeyInSB = emailHtmls.indexOf(key)) != -1) {
                int keyLen = key.length();

                emailHtmls.replace(indexOfKeyInSB, indexOfKeyInSB + keyLen, value);
            }
        }
        return this;
    }

    /**
     * Send email to user with content from emailHtmls
     *
     * @param userEmailAddress email address of user
     * @param emailSubject     subject of email
     */
    public void sendEmailToUser(String userEmailAddress, String emailSubject) {
        String htmlContent = emailHtmls.toString();
        try {
            MimeMessage mimeMessage = new MimeMessage(getSession());
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmailAddress));
            mimeMessage.setSubject(emailSubject);

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlContent, "text/html; charset=utf-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);

            mimeMessage.setContent(multipart, "text/html; charset=utf-8");

            Transport.send(mimeMessage);
            System.out.println("Sent mail successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {

    }

    public abstract AbstractSendEmail loadLogData(LogModel logModel);
}
