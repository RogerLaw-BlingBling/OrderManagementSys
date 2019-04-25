package com.ordersys.service.impl;


import com.ordersys.mapper.UserMapper;
import com.ordersys.model.User;
import com.ordersys.model.UserExample;
import com.ordersys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceIml implements UserService {
    @Autowired
    private UserMapper mapper;


    @Override
    public User getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer addUser(User user) {
        return mapper.insertSelective(user);
    }

    @Override
    public List<User> list() {
        UserExample example=new UserExample();

        return mapper.selectByExample(example);
    }
}
