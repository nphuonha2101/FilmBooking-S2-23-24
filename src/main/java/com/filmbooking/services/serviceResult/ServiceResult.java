package com.filmbooking.services.serviceResult;

import com.filmbooking.statusEnums.StatusEnum;

public class ServiceResult {
    private StatusEnum status;
    private Object data;

    public ServiceResult(StatusEnum status, Object data) {
        this.status = status;
        this.data = data;
    }

    public ServiceResult(StatusEnum status) {
        this.status = status;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

}
