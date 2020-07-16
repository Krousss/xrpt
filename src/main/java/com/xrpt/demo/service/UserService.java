package com.xrpt.demo.service;

import com.xrpt.demo.entity.Location;
import com.xrpt.demo.entity.User;

import java.util.List;

public interface UserService {
    public List<User> queryAllUser();
    /**
     * @Author:cjs
     * @Description:用户登录
     * @Date:2020/7/15
     */
    public User userLogin(User user);
    /**
     * @Author:cjs
     * @Description:用户注册
     * @Date:2020/7/15
     */
    public int userRegister(User user);
    /**
     * @Author:cjs
     * @Description:通过id查询单条地址
     * @Date:2020/7/15
     */
    public Location queryOneLocation(int lid);
}
