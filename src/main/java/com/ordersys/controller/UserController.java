package com.ordersys.controller;

import com.ordersys.commons.BusinessException;
import com.ordersys.commons.RexModel;
import com.ordersys.controller.form.PasswordUpdateForm;
import com.ordersys.controller.form.UserUpdateForm;
import com.ordersys.model.User;
import com.ordersys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @RequiresRoles("admin")
    @RequiresAuthentication
    public User create(@RequestBody UserUpdateForm userUpdateForm) {
        if (userService.findByUsername(userUpdateForm.getUsername()).isPresent())
            throw new BusinessException("user_exists");

        User user = new User();
        BeanUtils.copyProperties(user, userUpdateForm);
        return userService.save(user);
    }

    @GetMapping
    @RequiresAuthentication
    public Page<User> get(@PageableDefault Pageable pageable) {
        return userService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @RequiresAuthentication
    public User get(@PathVariable("id") Integer id) {
        return userService.findOne(id).orElse(null);
    }

    @PostMapping("/{id}")
    @RequiresAuthentication
    public User updateUser(@PathVariable("id") Integer id, UserUpdateForm userUpdateForm) {
        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        boolean canModifyOthers = SecurityUtils.getSubject().hasRole("admin");
        if (!canModifyOthers && !currentUser.getId().equals(id))
            throw new AuthorizationException("permission_denied");

        User user = userService.findOne(id).orElseThrow(() -> new BusinessException("user_not_found"));
        if (!canModifyOthers) {
            BeanUtils.copyProperties(user, userUpdateForm, "username", "password", "userRole");
        } else {
            BeanUtils.copyProperties(user, userUpdateForm, "username", "password");
        }

        return userService.save(user);
    }

    @PostMapping("/{id}/password")
    @RequiresAuthentication
    public RexModel updatePassword(@PathVariable("id") Integer id, @RequestBody PasswordUpdateForm form) {
        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        boolean canModifyOthers = SecurityUtils.getSubject().hasRole("admin");
        if (!canModifyOthers && !currentUser.getId().equals(id))
            throw new AuthorizationException("permission_denied");

        User user = userService.findOne(id).orElseThrow(() -> new BusinessException("user_not_found"));
        if (!canModifyOthers && !form.getOldPassword().equals(user.getPassword()))
            throw new BusinessException("password_not_match");

        user.setPassword(form.getNewPassword());

        userService.save(user);
        return new RexModel().withMessage("success");
    }

    @DeleteMapping("/{id}")
    @RequiresAuthentication
    public RexModel delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return new RexModel().withMessage("success");
    }
}
