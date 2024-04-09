package com.filmbooking.model;

import com.filmbooking.utils.GeoLite2IPUtils;
import jakarta.persistence.*;
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
@Entity
@Table(name = LogModel.TABLE_NAME)
public class LogModel {
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
    @Column(name="log_id", updatable = false, insertable = false)
    private long logID;
    @ManyToOne
    @JoinColumn(name= "username")
    private User user;
    @Column(name = "req_ip")
    private String reqIP;
    @Column(name = "ip_country")
    private String ipCountry;
    @Column(name = "log_level")
    private String level;
    @Column(name = "target_table")
    private String targetTable;
    @Column(name = "actions")
    private String action;
    @Column(name = "is_action_success")
    private boolean isActionSuccess;
    @Column(name = "before_data")
    private String beforeValueJSON;
    @Column(name = "after_data")
    private String afterValueJSON;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public LogModel(User user, String reqIP, String level, String targetTable, String action, boolean isActionSuccess, String beforeValueJSON, String afterValueJSON, boolean isCreate) {
        this.user = user;
        this.reqIP = reqIP;
        this.ipCountry = String.valueOf(GeoLite2IPUtils.getInstance().getCountry(reqIP));
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
}
