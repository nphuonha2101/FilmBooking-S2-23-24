package com.filmbooking.model;

import com.filmbooking.enumsAndConstants.enums.AccountRoleEnum;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_infos")
public class User {
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
    @Expose
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "account_role")
    @Expose
    private String accountRole;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.PERSIST})
    List<FilmBooking> filmBookingList;

    public User() {
    }

    public User(String username, String userFullName, String userEmail, String userPassword, AccountRoleEnum accountRole) {
        this.username = username;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.accountRole = accountRole.getAccountRole();
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
}
