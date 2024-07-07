package com.filmbooking.email;

import com.filmbooking.enumsAndConstants.enums.LanguageEnum;

import java.time.LocalDateTime;

public class SendLogEmail extends AbstractSendEmail{
    public  SendLogEmail() {
        super();
    }
    @Override
    public AbstractSendEmail loadHTMLEmail(LanguageEnum language) {
        String currentYear = String.valueOf(LocalDateTime.now().getYear());
        this.loadHTMLEmail("alertLogEmail.html", language);
        this.putEmailInfo("currentYear", currentYear);
        return this;
    }
}
