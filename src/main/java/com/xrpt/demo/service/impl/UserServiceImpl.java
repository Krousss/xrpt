package com.xrpt.demo.service.impl;

import com.xrpt.demo.dao.UserDao;
import com.xrpt.demo.entity.Location;
import com.xrpt.demo.entity.User;
import com.xrpt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryAllUser() {
        return userDao.queryAllUser();
    }

    @Override
    public User userLogin(User user) {
        return userDao.userLogin(user);
    }

    @Override
    public int userRegister(User user) {
        return userDao.userRegister(user);
    }

    /**
     * @Author:cjs
     * @Description:通过id查询单条地址
     * @Date:2020/7/16
     */
    @Override
    public Location queryOneLocation(int lid) {
        return userDao.queryOneLocation(lid);
    }
}
