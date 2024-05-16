package com.filmbooking.services.serviceResult;

import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;

public class ServiceResult {
    private final StatusCodeEnum status;
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

    @Override
    public String toString() {
        return "ServiceResult{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
