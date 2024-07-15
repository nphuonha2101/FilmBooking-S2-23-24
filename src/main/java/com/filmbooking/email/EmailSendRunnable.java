package com.filmbooking.email;

import com.filmbooking.enumsAndConstants.enums.LanguageEnum;

public class EmailSendRunnable implements Runnable {
    LanguageEnum language;
    String username;
    String lockTime;
    String reqIp;
    String email;

    public EmailSendRunnable(String username, String lockTime, String reqIp, String email, LanguageEnum language) {
        this.language = language;
        this.username = username;
        this.lockTime = lockTime;
        this.reqIp = reqIp;
        this.email = email;
    }

    @Override
    public void run() {
        new SendFailLogin5TimesEmail()
                .loadHTMLEmail(this.language)
                .putEmailInfo("username", this.username)
                .putEmailInfo("lockTime", this.lockTime)
                .putEmailInfo("reqIp", this.reqIp)
                .loadEmailContent()
                .sendEmailToUser(this.email, "Your account has been locked");
    }
}
