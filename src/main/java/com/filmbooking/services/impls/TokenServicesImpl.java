package com.filmbooking.services.impls;

import java.time.LocalDateTime;
import java.util.Map;

import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.model.TokenModel;
import com.filmbooking.repository.TokenModelRepository;
import com.filmbooking.services.AbstractService;
import com.filmbooking.services.serviceResult.ServiceResult;

public class TokenServicesImpl extends AbstractService<TokenModel> {

    public TokenServicesImpl() {
        super(new TokenModelRepository(TokenModel.class));
    }

    @Override
    public TokenModel getBySlug(String slug) {
        throw new UnsupportedOperationException("This method is not supported for TokenModel");
    }

    @Override
    public TokenModel select(Object id) {
        throw new UnsupportedOperationException("This method is not supported for TokenModel");
    }

    /**
     * Get token by token and tokenType
     *
     * @param token token
     * @param type  tokenType
     * @return TokenModel
     */
    public TokenModel getToken(String token, String username, String type) {
        Map<String, Object> filters = Map.of("token_=", token, "username_=", username, "tokenType_=", type);
        return this.selectAll(filters).get(0);
    }

    /**
     * Verify token by token, username and tokenType
     *
     * @param tokenModel token
     * @return ServiceResult with status
     *         <ul>
     *         <li>{@link StatusCodeEnum#TOKEN_EXPIRED}</li>
     *         <li>{@link StatusCodeEnum#TOKEN_NOT_FOUND}</li>
     *         <li>{@link StatusCodeEnum#TOKEN_VERIFIED}</li>
     *         </ul>
     */
    public ServiceResult verifyToken(TokenModel tokenModel) {

        if (tokenModel == null) {
            return new ServiceResult(StatusCodeEnum.TOKEN_NOT_FOUND);
        } else {
            if (tokenModel.getExpiryDate().isBefore(LocalDateTime.now())) {
                return new ServiceResult(StatusCodeEnum.TOKEN_EXPIRED);
            }
        }
        return new ServiceResult(StatusCodeEnum.TOKEN_VERIFIED, tokenModel);
    }


}
