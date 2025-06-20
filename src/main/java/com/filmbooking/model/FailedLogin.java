package com.filmbooking.model;

import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@TableIdName("req_ip")
@TableName("failed_logins")
public class FailedLogin extends AbstractModel implements IModel {
    public static final String TABLE_NAME = "failed_logins";
    private String reqIp;
    private int loginCount;
    private LocalDateTime lockTime;

    public FailedLogin() {
    }

    public FailedLogin(String reqIp, int loginCount, LocalDateTime lockTime) {
        this.reqIp = reqIp;
        this.loginCount = loginCount;
        this.lockTime = lockTime;
    }

    public FailedLogin(String reqIp, int loginCount, LocalDateTime lockTime, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.reqIp = reqIp;
        this.loginCount = loginCount;
        this.lockTime = lockTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public Object getIdValue() {
        return this.reqIp;
    }
}
