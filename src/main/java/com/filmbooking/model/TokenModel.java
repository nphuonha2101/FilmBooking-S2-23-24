/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */

package com.filmbooking.model;

import com.filmbooking.utils.PropertiesUtils;
import com.filmbooking.utils.StringUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class TokenModel {
    @Id
    @Column(name = "token")
    private String token;
    @Column(name = "username")
    private String username;
    @Column(name = "expiry_date")
    private LocalDateTime expiryAt;

    public TokenModel() {
    }

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
     * @param expiryAt          expiry date of token
     */
    public TokenModel(String dataToGenerateToken, String username, LocalDateTime expiryAt) {
        tokenModelConstruct(dataToGenerateToken, username);
        this.expiryAt = expiryAt;
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
        this.expiryAt = LocalDateTime.now().plusMinutes(tokenExpiryTime);
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

    public LocalDateTime getExpiryAt() {
        return expiryAt;
    }

    public void setExpiryAt(LocalDateTime expiryDate) {
        this.expiryAt = expiryDate;
    }
}