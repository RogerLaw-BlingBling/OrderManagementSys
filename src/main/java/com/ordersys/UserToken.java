package com.ordersys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ordersys.model.User;
import org.apache.shiro.authc.AuthenticationToken;

public class UserToken implements AuthenticationToken {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    @Override
    public Object getPrincipal() {
        return username;
    }

    @JsonIgnore
    @Override
    public Object getCredentials() {
        return password;
    }
}
