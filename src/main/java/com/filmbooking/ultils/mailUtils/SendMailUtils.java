package com.filmbooking.ultils.mailUtils;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SendMailUtils {
    private static final String HOST_NAME = "smtp.gmail.com";
    private static final String USERNAME = "21130122@st.hcmuaf.edu.com";
    private static final String PASSWORD = "NongLam2023";
    private static final String FROM_ADDRESS = "filmbookingdn@gmail.com";

    private static Authenticator authentication() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", HOST_NAME);
        properties.put("mail.smtp.port", "587");

        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        };
    }

    public static void sendMailTo(String toAddress, String subject, String content) {
        Session session = Session.getInstance(new Properties(), authentication());

        try {
            // create MimeMessage object
            Message message = new MimeMessage(session);
            // set from address
            message.setFrom(new InternetAddress(FROM_ADDRESS));
            // set to address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            // set email subject
            message.setSubject(subject);
            // set message content
            message.setContent(content, "text/html; charset=utf-8");

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) {
        SendMailUtils.sendMailTo("21130122@st.hcmuaf.edu.vn", "Test", "Test");
    }
}
