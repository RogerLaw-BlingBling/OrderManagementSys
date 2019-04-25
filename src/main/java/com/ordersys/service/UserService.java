package com.ordersys.service;

import com.ordersys.model.User;

import java.util.List;

public interface UserService {

    User getById(Integer id);

    Integer addUser(User user);

    List<User> list();

}
