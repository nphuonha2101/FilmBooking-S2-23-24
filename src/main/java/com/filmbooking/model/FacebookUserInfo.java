package com.filmbooking.model;
import jakarta.persistence.*;
@Entity
@Table(name = "user_infos")
public class FacebookUserInfo {
    @Column(name = "username")
    @Id
    private String username;
    @Column(name = "user_fullname")
    private String fullName;
    @Column(name = "user_email")
    private String email;
    @Column(name = "account_role")
    private String role;

    public FacebookUserInfo(String username, String fullName, String email, String role) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }

    public FacebookUserInfo() {

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
