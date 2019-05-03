package com.ordersys.controller;

import com.ordersys.UserToken;
import com.ordersys.commons.RexModel;
import com.ordersys.service.UserService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiresUser
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


}
