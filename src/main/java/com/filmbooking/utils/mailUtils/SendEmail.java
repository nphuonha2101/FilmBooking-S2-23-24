package com.filmbooking.utils.mailUtils;

import com.filmbooking.configs.MailConfigs;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class SendEmail {
    private Properties properties;

    private static SendEmail instance;

    private SendEmail() {
    }

    public static SendEmail getInstance() {
        if (instance == null) {
            instance = new SendEmail();
        }
        return instance;
    }


    private Session getSession() {
        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", MailConfigs.EMAIL_HOST_NAME);
        properties.put("mail.smtp.socketFactory.port", MailConfigs.SSL_PORT);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.port", MailConfigs.SSL_PORT);

        return Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailConfigs.APP_EMAIL, MailConfigs.APP_EMAIL_PASSWD);
            }
        });
    }

    public void sendEmailToUser(String userEmail, String subject, String content) {
        try {
            MimeMessage mimeMessage = new MimeMessage(getSession());
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            mimeMessage.setSubject(subject);
            mimeMessage.setContent(content, "text/html; charset=utf-8");

            Transport.send(mimeMessage);
            System.out.println("Sent mail successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

    }
}
