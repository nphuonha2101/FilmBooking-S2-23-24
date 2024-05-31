/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */

package com.filmbooking.model;

import com.filmbooking.annotations.StringID;
import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
import com.filmbooking.enumsAndConstants.enums.TokenStateEnum;
import com.filmbooking.enumsAndConstants.enums.TokenTypeEnum;
import com.filmbooking.utils.PropertiesUtils;
import com.filmbooking.utils.StringUtils;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@ToString
@TableName("user_tokens")
@TableIdName("token")
@AllArgsConstructor
@StringID
public class TokenModel implements IModel {
    public static final String TABLE_NAME = "user_tokens";

    @Expose
    private String token;
    @Expose
    private String username;
    @Expose
    private LocalDateTime expiryDate;
    @Expose
    private String tokenType;
    @Expose
    @Setter
    private String tokenState;

    public TokenModel() {
    }

    private void tokenModelConstruct(String dataToGenerateToken, String username, TokenTypeEnum tokenType) {
        String SECRET_KEY = PropertiesUtils.getProperty("token.hash_secret_key");
        String currentSystemMillis = String.valueOf(System.currentTimeMillis());
        this.token = StringUtils.generateSHA256String(dataToGenerateToken + currentSystemMillis + SECRET_KEY);
        this.username = username;
        this.tokenType = tokenType.getTokenType();
        this.tokenState = TokenStateEnum.ACTIVE.getTokenState();
    }

    /**
     * Constructor (custom expiry date)
     *
     * @param dataToGenerateToken data to generate token
     * @param username            username has the token
     * @param expiryDate          expiry date of token
     */
    public TokenModel(String dataToGenerateToken, String username, TokenTypeEnum tokenType, LocalDateTime expiryDate) {
        tokenModelConstruct(dataToGenerateToken, username, tokenType);
        this.expiryDate = expiryDate;
    }

    /**
     * Constructor (default expiry date)
     *
     * @param dataToGenerateToken data to generate token
     * @param username            username has the token
     */
    public TokenModel(String dataToGenerateToken, String username, TokenTypeEnum tokenType) {
        int tokenExpiryTime = Integer.parseInt(PropertiesUtils.getProperty("token.expiry_time"));
        tokenModelConstruct(dataToGenerateToken, username, tokenType);
        this.expiryDate = LocalDateTime.now().plusSeconds(tokenExpiryTime);
    }

    @Override
    public Object getIdValue() {
        return this.token;
    }

    public Map<String, Object> mapToRow() {
        return Map.of(
                "token", this.token,
                "username", this.username,
                "expiry_date", Timestamp.valueOf(this.expiryDate),
                "token_type", this.tokenType,
                "token_state", this.tokenState
        );
    }
}
