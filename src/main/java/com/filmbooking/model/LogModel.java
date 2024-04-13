package com.filmbooking.model;

import com.filmbooking.utils.GeoLite2IPUtils;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
@Getter
@Setter
@Entity
@Table(name = LogModel.TABLE_NAME)
public class LogModel{
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    @Column(name="log_id", updatable = false, insertable = false)
    private long logID;
    @ManyToOne
    @Expose
    @JoinColumn(name= "username")
    private User user;
    @Expose
    @Column(name = "req_ip")
    private String reqIP;
    @Expose
    @Column(name = "ip_country")
    private String ipCountry;
    @Expose
    @Column(name = "log_level")
    private String level;
    @Expose
    @Column(name = "target_table")
    private String targetTable;
    @Expose
    @Column(name = "actions")
    private String action;
    @Expose
    @Column(name = "is_action_success")
    private boolean isActionSuccess;
    @Expose
    @Column(name = "before_data")
    private String beforeValueJSON;
    @Expose
    @Column(name = "after_data")
    private String afterValueJSON;
    @Expose
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Expose
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public LogModel(User user, String reqIP, String level, String targetTable, String action, boolean isActionSuccess, String beforeValueJSON, String afterValueJSON, boolean isCreate) {
        this.user = user;
        this.reqIP = reqIP;
//        this.ipCountry = String.valueOf(GeoLite2IPUtils.getInstance().getCountry(reqIP));
        this.level = level;
        this.targetTable = targetTable;
        this.action = action;
        this.isActionSuccess = isActionSuccess;
        this.beforeValueJSON = beforeValueJSON;
        this.afterValueJSON = afterValueJSON;
        if (isCreate) {
            this.createdAt = LocalDateTime.now();
        } else {
            this.updatedAt = LocalDateTime.now();
        }
    }

    public LogModel() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogModel logModel = (LogModel) o;
        return logID == logModel.logID && isActionSuccess == logModel.isActionSuccess && Objects.equals(user, logModel.user) && Objects.equals(reqIP, logModel.reqIP) && Objects.equals(level, logModel.level) && Objects.equals(targetTable, logModel.targetTable) && Objects.equals(action, logModel.action) && Objects.equals(beforeValueJSON, logModel.beforeValueJSON) && Objects.equals(afterValueJSON, logModel.afterValueJSON) && Objects.equals(createdAt, logModel.createdAt) && Objects.equals(updatedAt, logModel.updatedAt);
    }

}
