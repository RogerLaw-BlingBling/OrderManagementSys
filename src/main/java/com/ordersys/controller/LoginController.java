package com.ordersys.controller;

import com.ordersys.UserToken;
import com.ordersys.commons.RexModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiresUser
public class LoginController {

    @PostMapping(params = "login")
    public RexModel login(@RequestBody UserToken token) {
        SecurityUtils.getSecurityManager().login(SecurityUtils.getSubject(), token);
        return new RexModel<>()
                .withMessage("success")
                .withData(SecurityUtils.getSubject().getPrincipal());
    }

    @PostMapping(params = "logout")
    public RexModel logout() {
        SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());
        return new RexModel().withMessage("success");
    }

}
