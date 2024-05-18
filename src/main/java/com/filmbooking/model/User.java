package com.filmbooking.model;

import com.filmbooking.enumsAndConstants.enums.AccountRoleEnum;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity

@Table(name = User.TABLE_NAME)
public class User implements IModel {
    @Transient
    public static final String TABLE_NAME = "user_infos";

    @Expose
    @Column(name = "username")
    @Id
    private String username;
    @Expose
    @Column(name = "user_fullname")
    private String userFullName;
    @Expose
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "account_role")
    @Expose
    private String accountRole;
    @Expose
    @Column(name = "account_type")
    private String accountType;
    @Expose
    @Column(name = "account_status")
    private int accountStatus;



    @OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.PERSIST }, fetch = FetchType.LAZY)
    List<FilmBooking> filmBookingList;

    public User() {
    }

    public User(String username, String userEmail) {
        this.username = username;
        this.userEmail = userEmail;
    }

    public User(String username, String userFullName, String userEmail, String userPassword,
            AccountRoleEnum accountRole, String accountType, int accountStatus) {
        this.username = username;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.accountRole = accountRole.getAccountRole();
        this.accountType = accountType;
        this.accountStatus = accountStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User user) {
            return this.username.equals(user.getUsername())
                    && this.userFullName.equals(user.getUserFullName())
                    && this.userEmail.equals(user.getUserEmail())
                    && this.userPassword.equals(user.getUserPassword())
                    && this.accountRole.equals(user.getAccountRole())
                    && this.filmBookingList.equals(user.getFilmBookingList());
        }
        return false;
    }

    @Override
    public String getStringID() {
        return this.username;
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", userFullName='" + userFullName + '\'' + ", userEmail='"
                + userEmail + '\'' + ", userPassword='" + userPassword + '\'' + ", accountRole='" + accountRole + '\''
                + '}';
    }
}
