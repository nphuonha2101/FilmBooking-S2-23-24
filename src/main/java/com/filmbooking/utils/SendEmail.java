package com.filmbooking.utils;

import com.filmbooking.model.User;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;

public class SendEmail {
    private static SendEmail instance;
    private final PropertiesUtils propertiesUtils;


    private SendEmail() {
        propertiesUtils = PropertiesUtils.getInstance();
    }

    public static SendEmail getInstance() {
        if (instance == null) {
            instance = new SendEmail();
        }
        return instance;
    }

    /**
     * Get session to send email
     *
     * @return a {@link Session}
     */
    private Session getSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", propertiesUtils.getProperty("email.hostName"));
        properties.put("mail.smtp.socketFactory.port", Integer.parseInt(propertiesUtils.getProperty("email.sslPort")));
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.port", Integer.parseInt(propertiesUtils.getProperty("email.sslPort")));

        return Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(propertiesUtils.getProperty("email.appName"), propertiesUtils.getProperty("email.appPassword"));
            }
        });
    }

    public void sendEmailToUser(String userEmail, String subject, String content) {
        try {
            MimeMessage mimeMessage = new MimeMessage(getSession());
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            mimeMessage.setSubject(subject);

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(content, "text/html; charset=utf-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);

            mimeMessage.setContent(multipart, "text/html; charset=utf-8");

            Transport.send(mimeMessage);
            System.out.println("Sent mail successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    public String loadResetEmailFromHTML(User userInfo, String newPassword, String language) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = null;
        try {
            if (language == null || language.equals("default")) {
                reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(this.getClass().getResourceAsStream("/emailTemplates/resetPasswordEmail_vi.html"))));

            } else
                reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(this.getClass().getResourceAsStream("/emailTemplates/resetPasswordEmail_en.html"))));

            String line = null;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("/userFullName/", userInfo.getUserFullName());
                line = line.replaceAll("/newPassword/", newPassword);
                stringBuilder.append(line).append("\n");
            }

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();

    }

    public static void main(String[] args) throws IOException {
        SendEmail sendEmail = SendEmail.getInstance();
//        sendEmail.createResetPasswordEmail("123456");
//        Properties properties = PropertiesUtils.getInstance().getProperties();
//        System.out.println(properties.getProperty("email.appPassword"));
//        sendEmail.sendEmailToUser("21130122@st.hcmuaf.edu.vn", "Test", "Test");

        User user = new User();
        System.out.println(sendEmail.loadResetEmailFromHTML(user, "123456", "default"));
    }
}
