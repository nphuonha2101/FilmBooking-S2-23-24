package com.filmbooking.services.impls;

import com.filmbooking.dao.GenericDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.TokenModel;
import com.filmbooking.services.ITokenServices;
import com.filmbooking.services.serviceResult.ServiceResult;

public class TokenServicesImpl implements ITokenServices {
    IDAO<TokenModel> tokenDAO;

    public TokenServicesImpl(HibernateSessionProvider sessionProvider) {
        this.tokenDAO = new GenericDAOImpl<>(TokenModel.class);
//        setSessionProvider(sessionProvider);
    }

    @Override
    public ServiceResult saveToken(TokenModel tokenModel) {

        return null;
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
