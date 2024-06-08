package com.filmbooking.model;

import com.filmbooking.annotations.IdAutoIncrement;
import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@ToString
@TableIdName("req_ip")
@TableName("failed_logins")
@IdAutoIncrement
public class FailedLogin implements IModel {
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

    @Override
    public Object getIdValue() {
        return this.reqIp;
    }

    public Map<String, Object> mapToRow() {
        return Map.of(
                "req_ip", this.reqIp,
                "login_count", this.loginCount,
                "lock_time", this.lockTime
        );
    }

}
