package com.ordersys.controller;

import com.ordersys.UserToken;
import com.ordersys.commons.RexModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @PostMapping(params = "login")
    public RexModel login(@RequestBody UserToken token) {
        Subject anonymous = SecurityUtils.getSubject();
        anonymous.login(token);
        return new RexModel<>()
                .withMessage("success")
                .withData(anonymous.getPrincipal());
    }

    @GetMapping
    public Object currentPrincipal() {
        return SecurityUtils.getSubject().getPrincipal();
    }

    @PostMapping(params = "logout")
    public RexModel logout() {
        SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());
        return new RexModel().withMessage("success");
    }

}
