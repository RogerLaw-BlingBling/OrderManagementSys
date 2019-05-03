package com.ordersys;

import com.ordersys.model.User;
import com.ordersys.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserRealm extends AuthorizingRealm {

    private final UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UserToken;
    }

    public UserRealm(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        return new SimpleAuthorizationInfo(Collections.singleton(user.getUserRole().name().toLowerCase()));
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UserToken token = (UserToken) authenticationToken;
        User user = userService.findByUsername(token.getUsername()).orElseThrow(() -> new AuthenticationException("token_invalid"));
        String password = token.getPassword();
        if (!user.getPassword().equals(password)) throw new AuthenticationException("token_invalid");
        return new SimpleAuthenticationInfo(user, password, this.getName());
    }
}
