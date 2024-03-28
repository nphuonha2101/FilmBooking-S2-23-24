/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */

package com.filmbooking.model;

import com.filmbooking.enumsAndConstants.enums.TokenStateEnum;
import com.filmbooking.enumsAndConstants.enums.TokenTypeEnum;
import com.filmbooking.utils.PropertiesUtils;
import com.filmbooking.utils.StringUtils;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "user_tokens")
public class TokenModel {
    @Expose
    @Id
    @Column(name = "token")
    private String token;
    @Expose
    @Column(name = "username")
    private String username;
    @Expose
    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime expiryDate;
    @Expose
    @Column(name = "token_type")
    private String tokenType;
    @Expose
    @Setter
    @Column(name="token_state")
    private String tokenState;

    public TokenModel() {
    }

    private void tokenModelConstruct(String dataToGenerateToken, String username, TokenTypeEnum tokenType) {
        String SECRET_KEY = PropertiesUtils.getInstance()
                .getProperty("token.hash_secret_key");
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
        int tokenExpiryTime = Integer.parseInt(PropertiesUtils.getInstance()
                .getProperty("token.expiry_time"));
        tokenModelConstruct(dataToGenerateToken, username, tokenType);
        this.expiryDate = LocalDateTime.now().plusSeconds(tokenExpiryTime);
    }

}
