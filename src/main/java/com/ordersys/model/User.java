package com.ordersys.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
public class User {
    public enum UserRole {
        ADMIN, NORMAL
    }

    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private UserRole userRole;
    private String phone;
    private String avatar;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Enumerated(EnumType.STRING)
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public User(Integer id, String username, String nickname, String password, UserRole userRole, String phone, String avatar) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.userRole = userRole;
        this.phone = phone;
        this.avatar = avatar;
    }

    public User() {
    }
}