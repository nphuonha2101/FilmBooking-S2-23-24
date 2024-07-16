package com.filmbooking.services.serviceResult;

import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
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

}
