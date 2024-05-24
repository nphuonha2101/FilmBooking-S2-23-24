package com.filmbooking.model;


import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = FailedLogin.TABLE_NAME)
public class FailedLogin implements IModel {
    @Transient
    public static final String TABLE_NAME = "failed_logins";
    @Expose
    @Id
    @Column(name = "req_ip")
    private String reqIp;
    @Expose
    @Column(name = "login_count")
    private int loginCount;
    @Expose
    @Column(name = "lock_time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lockTime;

    public FailedLogin() {
    }



    @Override
    public String getStringID() {
        return this.reqIp;
    }

}
