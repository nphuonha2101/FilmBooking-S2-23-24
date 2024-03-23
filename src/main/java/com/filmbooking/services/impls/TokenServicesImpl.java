package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.enumsAndConstant.enums.StatusCodeEnum;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.TokenModel;
import com.filmbooking.services.AbstractServices;
import com.filmbooking.services.IServices;
import com.filmbooking.services.serviceResult.ServiceResult;

public class TokenServicesImpl extends AbstractServices<TokenModel> {

    public TokenServicesImpl(HibernateSessionProvider sessionProvider) {
        super.decoratedDAO = new DataAccessObjects<>(TokenModel.class);
        super.setSessionProvider(sessionProvider);
    }

    @Override
    public TokenModel getBySlug(String slug) {
        throw new UnsupportedOperationException("This method is not supported for TokenModel");
    }

    @Override
    public TokenModel getByID(String id) {
        return null;
    }

    @Override
    public boolean update(TokenModel tokenModel) {
        throw new UnsupportedOperationException("This method is not supported for TokenModel");
    }


}

