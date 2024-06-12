package com.filmbooking.model;

import com.filmbooking.annotations.StringID;
import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
import com.filmbooking.enumsAndConstants.enums.AccountRoleEnum;
import com.filmbooking.repository.FilmBookingRepository;
import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@TableName("user_infos")
@TableIdName("username")
@StringID
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

    private List<FilmBooking> filmBookingList;

    public User() {
    }

    public User(String username, String userEmail) {
        this.username = username;
        this.userEmail = userEmail;
    }

    public User(String username, String userFullName, String userEmail, String userPassword, AccountRoleEnum accountRoleEnum, String accountType, int accountStatus) {
        this.username = username;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.accountRole = accountRoleEnum.getAccountRole();
        this.accountType = accountType;
        this.accountStatus = accountStatus;
    }

    public List<FilmBooking> getFilmBookingList() {
        if (this.filmBookingList == null)
            this.filmBookingList = new FilmBookingRepository().sellectAllByUsername(this.username);
        return filmBookingList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User user) {
            return this.username.equals(user.getUsername())
                    && this.userFullName.equals(user.getUserFullName())
                    && this.userEmail.equals(user.getUserEmail())
                    && this.userPassword.equals(user.getUserPassword())
                    && this.accountRole.equals(user.getAccountRole());
        }
        return false;
    }

    @Override
    public Object getIdValue() {
        return this.username;
    }


}
