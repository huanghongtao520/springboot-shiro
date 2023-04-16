package com.example.springbootshiro.service;

import com.example.springbootshiro.mapper.UserMapper;
import com.example.springbootshiro.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSericeImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
