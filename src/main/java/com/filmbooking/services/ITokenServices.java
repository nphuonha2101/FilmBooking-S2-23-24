package com.filmbooking.services;

import com.filmbooking.model.TokenModel;
import com.filmbooking.services.serviceResult.ServiceResult;

import java.security.Provider;

public interface ITokenServices {
    ServiceResult saveToken(TokenModel tokenModel);
    ServiceResult verifyToken(String token);
    ServiceResult deleteToken(String token);
}
