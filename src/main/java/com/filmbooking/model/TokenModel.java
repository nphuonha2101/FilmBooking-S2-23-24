package com.filmbooking.model;

import com.filmbooking.utils.PropertiesUtils;
import com.filmbooking.utils.StringUtils;

import java.time.LocalDateTime;

public class TokenModel {
    private String token;
    private String username;
    private LocalDateTime expiryDate;

    private void tokenModelConstruct(String dataToGenerateToken, String username) {
        String SECRET_KEY = PropertiesUtils.getInstance()
                .getProperty("token.hash_secret_key");
        String currentSystemMillis = String.valueOf(System.currentTimeMillis());
        this.token = StringUtils.generateSHA256String(dataToGenerateToken + currentSystemMillis + SECRET_KEY);
        this.username = username;
    }

    /**
     * Constructor (custom expiry date)
     *
     * @param dataToGenerateToken data to generate token
     * @param username            username has the token
     * @param expiryDate          expiry date of token
     */
    public TokenModel(String dataToGenerateToken, String username, LocalDateTime expiryDate) {
        tokenModelConstruct(dataToGenerateToken, username);
        this.expiryDate = expiryDate;
    }

    /**
     * Constructor (default expiry date)
     *
     * @param dataToGenerateToken data to generate token
     * @param username            username has the token
     */
    public TokenModel(String dataToGenerateToken, String username) {
        int tokenExpiryTime = Integer.parseInt(PropertiesUtils.getInstance()
                .getProperty("token.expiry_time"));
        tokenModelConstruct(dataToGenerateToken, username);
        this.expiryDate = LocalDateTime.now().plusMinutes(tokenExpiryTime);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
