package com.filmbooking.model;

import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
import com.filmbooking.enumsAndConstants.enums.AccountRoleEnum;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@ToString
@TableName("user_infos")
@TableIdName("username")
public class User implements IModel {
    public static final String TABLE_NAME = "user_infos";
    @Expose
    private String username;
    @Expose
    private String userFullName;
    @Expose
    private String userEmail;
    private String userPassword;
    @Expose
    private String accountRole;
    @Expose
    private String accountType;
    @Expose
    private int accountStatus;



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


}
