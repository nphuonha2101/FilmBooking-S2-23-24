package com.filmbooking.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
@Getter
@Setter
public class LogModel {
    private long logID;
    private User user;
    private String reqIP;
    private String level;
    private String targetTable;
    private String action;
    private String beforeValueJSON;
    private String afterValueJSON;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public LogModel(User user, String reqIP, String level, String targetTable, String action, String beforeValueJSON, String afterValueJSON) {
        this.user = user;
        this.reqIP = reqIP;
        this.level = level;
        this.targetTable = targetTable;
        this.action = action;
        this.beforeValueJSON = beforeValueJSON;
        this.afterValueJSON = afterValueJSON;
    }
}
