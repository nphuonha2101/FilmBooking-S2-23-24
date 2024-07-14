package com.filmbooking.email;

import com.filmbooking.enumsAndConstants.enums.LanguageEnum;
import com.filmbooking.model.LogModel;

import java.time.LocalDateTime;
import java.util.List;

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
    public AbstractSendEmail loadLogData(LogModel logModel) {
        this.putEmailInfo("logID", String.valueOf(logModel.getLogID()));
        this.putEmailInfo("reqIP", logModel.getReqIP());
        this.putEmailInfo("level", logModel.getLevel());
        this.putEmailInfo("targetTable", logModel.getTargetTable());
        this.putEmailInfo("action", logModel.getAction());
        this.putEmailInfo("isActionSuccess", String.valueOf(logModel.isActionSuccess()));
        this.putEmailInfo("beforeValueJSON", logModel.getBeforeValueJSON());
        this.putEmailInfo("afterValueJSON", logModel.getAfterValueJSON());
        this.putEmailInfo("createdAt", logModel.getCreatedAt() == null ? LocalDateTime.now().toString() : logModel.getCreatedAt().toString());
        return this;
    }


}
