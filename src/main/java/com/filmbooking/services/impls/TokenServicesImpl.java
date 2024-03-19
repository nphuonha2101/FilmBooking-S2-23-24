package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.enumsAndConstant.enums.StatusCodeEnum;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.TokenModel;
import com.filmbooking.services.ITokenServices;
import com.filmbooking.services.serviceResult.ServiceResult;

public class TokenServicesImpl implements ITokenServices {
    DataAccessObjects<TokenModel> tokenDataAccessObjects;

    public TokenServicesImpl(HibernateSessionProvider sessionProvider) {
        this.tokenDataAccessObjects = new DataAccessObjects<>(TokenModel.class);
        this.tokenDataAccessObjects.setSessionProvider(sessionProvider);
    }

    @Override
    public ServiceResult saveToken(TokenModel tokenModel) {
        if (tokenDataAccessObjects.save(tokenModel)) {
            return new ServiceResult(StatusCodeEnum.SUCCESSFUL);
        }
        return new ServiceResult(StatusCodeEnum.FAILED);
    }

    @Override
    public ServiceResult verifyToken(String token) {
        return null;
    }

    @Override
    public ServiceResult deleteToken(String token) {
        return null;
    }
}
