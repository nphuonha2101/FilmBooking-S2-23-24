package com.filmbooking.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_info")
public class User {
    @Column(name = "username")
    @Id
    private String username;
    @Column(name = "user_fullname")
    private String userFullName;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "account_role")
    private String accountRole;
    @OneToMany
    List<FilmBooking> filmBookingList;

    public User() {}

    public User(String username, String userFullName, String userEmail, String userPassword, String accountRole) {
        this.username = username;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.accountRole = accountRole;
    }

    public User(String username, String userFullName, String userEmail, String userPassword, String accountRole, List<FilmBooking> filmBookingList) {
        this.username = username;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.accountRole = accountRole;
        this.filmBookingList = filmBookingList;
    }

    // ------ GETTER AND SETTER ------ //
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getAccountRole() {
        return accountRole;
    }

    public void setAccountRole(String accountRole) {
        this.accountRole = accountRole;
    }

    public List<FilmBooking> getFilmBookingList() {
        return filmBookingList;
    }

    public void setFilmBookingList(List<FilmBooking> filmBookingList) {
        this.filmBookingList = filmBookingList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User user = (User) obj;
            return this.username.equals(user.getUsername())
                    && this.userFullName.equals(user.getUserFullName())
                    && this.userEmail.equals(user.getUserEmail())
                    && this.userPassword.equals(user.getUserPassword())
                    && this.accountRole.equals(user.getAccountRole())
                    && this.filmBookingList.equals(user.getFilmBookingList());
        }
        return false;
    }
}
