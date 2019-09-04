package com.itedu.aspect.service;

import org.springframework.stereotype.Service;

import com.itedu.aspect.log.User;

@Service
public class UserServiceImpl implements UserService {

    public void addUser(User user) {
        System.out.println("添加成功");
    }
    public void deleteUser(String name) {
        System.out.println("删除成功");
    }

}