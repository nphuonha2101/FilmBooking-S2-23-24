package com.filmbooking.services.serviceResult;

import com.filmbooking.statusEnums.StatusCodeEnum;

public class ServiceResult {
    private StatusCodeEnum status;
    private Object data;

    public ServiceResult(StatusCodeEnum status, Object data) {
        this.status = status;
        this.data = data;
    }

    public ServiceResult(StatusCodeEnum status) {
        this.status = status;
    }

    public StatusCodeEnum getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

}
