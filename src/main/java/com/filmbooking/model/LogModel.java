package com.filmbooking.model;

import com.filmbooking.annotations.IdAutoIncrement;
import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
import com.filmbooking.utils.GeoLite2IPUtils;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
@Getter
@Setter
@ToString
@TableName("logs")
@TableIdName("log_id")
@IdAutoIncrement
@AllArgsConstructor
public class LogModel implements IModel {
    // log actions
    public static final String TABLE_NAME = "logs";
    public static final String INSERT = "INSERT";
    public static final String UPDATE = "UPDATE";
    public static final String DELETE = "DELETE";
    public static final String SERVICE = "SERVICE";
    public static final String LOGIN_SERVICE = "LOGIN";
    public static final String FORGOT_PASSWORD_SERVICE = "FORGOT_PASSWORD_SERVICE";
    public static final String CHANGE_PASSWORD_SERVICE = "CHANGE_PASSWORD_SERVICE" ;

    // log levels
    public static final String LOG_LVL_INFO = "INFO";
    public static final String LOG_LVL_WARN = "WARN";
    public static final String LOG_LVL_ALERT = "ALERT";


    @Expose
    private long logID;
    @Expose
    private String username;
    @Expose
    private String reqIP;
    @Expose
    private String ipCountry;
    @Expose
    private String level;
    @Expose
    private String targetTable;
    @Expose
    private String action;
    @Expose
    private boolean isActionSuccess;
    @Expose
    @Column(name = "before_data")
    private String beforeValueJSON;
    @Expose
    @Column(name = "after_data")
    private String afterValueJSON;
    @Expose
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Expose
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public LogModel(User user, String reqIP, String level, String targetTable, String action, boolean isActionSuccess, String beforeValueJSON, String afterValueJSON, boolean isCreate) {
        this.username = user.getUsername();
        this.reqIP = reqIP;
//        this.ipCountry = String.valueOf(GeoLite2IPUtils.getInstance().getCountry(reqIP));
        this.level = level;
        this.targetTable = targetTable;
        this.action = action;
        this.isActionSuccess = isActionSuccess;
        this.beforeValueJSON = beforeValueJSON;
        this.afterValueJSON = afterValueJSON;
        if (isCreate) {
            this.createdAt = new Timestamp(new Date().getTime());
        } else {
            this.updatedAt = new Timestamp(new Date().getTime());
        }
    }

    public LogModel() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogModel logModel = (LogModel) o;
        return logID == logModel.logID && isActionSuccess == logModel.isActionSuccess && Objects.equals(username, logModel.username) && Objects.equals(reqIP, logModel.reqIP) && Objects.equals(level, logModel.level) && Objects.equals(targetTable, logModel.targetTable) && Objects.equals(action, logModel.action) && Objects.equals(beforeValueJSON, logModel.beforeValueJSON) && Objects.equals(afterValueJSON, logModel.afterValueJSON) && Objects.equals(createdAt, logModel.createdAt) && Objects.equals(updatedAt, logModel.updatedAt);
    }

    public Object getIdValue() {
        return this.logID;
    }

    public Map<String, Object> mapToRow() {
        return Map.ofEntries(
                Map.entry("log_id", this.logID),
                Map.entry("username", this.username),
                Map.entry("req_ip", this.reqIP),
                Map.entry("ip_country", this.ipCountry),
                Map.entry("log_level", this.level),
                Map.entry("target_table", this.targetTable),
                Map.entry("action", this.action),
                Map.entry("is_action_success", this.isActionSuccess),
                Map.entry("before_data", this.beforeValueJSON),
                Map.entry("after_data", this.afterValueJSON),
                Map.entry("created_at", this.createdAt),
                Map.entry("updated_at", this.updatedAt)
        );
    }
}
