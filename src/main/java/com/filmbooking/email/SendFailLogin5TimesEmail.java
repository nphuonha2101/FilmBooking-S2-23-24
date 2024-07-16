package com.filmbooking.email;

import com.filmbooking.enumsAndConstants.enums.LanguageEnum;
import com.filmbooking.model.LogModel;

import java.time.LocalDateTime;
import java.util.List;

public class SendFailLogin5TimesEmail extends AbstractSendEmail{

    public SendFailLogin5TimesEmail(){
        super();
    }
    @Override
    public AbstractSendEmail loadHTMLEmail(LanguageEnum language) {
        String currentYear = String.valueOf(LocalDateTime.now().getYear());
        this.loadHTMLEmail("failLogin5Times.html", language);
        this.putEmailInfo("currentYear", currentYear);
        return this;
    }

    @Override
    public AbstractSendEmail loadLogData(LogModel logModel) {
        return null;
    }

}
