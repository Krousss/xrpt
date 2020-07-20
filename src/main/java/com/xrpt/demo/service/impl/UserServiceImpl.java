package com.xrpt.demo.service.impl;

import com.xrpt.demo.dao.UserDao;
import com.xrpt.demo.entity.Admin;
import com.xrpt.demo.entity.Location;
import com.xrpt.demo.entity.User;
import com.xrpt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User queryUserByID(int uid) {
        return userDao.queryUserByID(uid);
    }

    @Override
    public User queryUserByName(String uname) {
        return userDao.queryUserByName(uname);
    }

    @Override
    public int updateUserCredit(int credit,int uid) {
        return userDao.updateUserCredit(credit,uid);
    }

    @Override
    public int updateAdminProfit(BigDecimal profit) {
        return userDao.updateAdminProfit(profit);
    }
    /**
     * @Author:cjs
     * @Description:查询所有用户
     * @Date:2020/7/15
     */
    @Override
    public List<User> queryAllUser() {
        return userDao.queryAllUser();
    }

    /**
     * @Author:cjs
     * @Description:查询单个用户
     * @Date:2020/7/15
     */
    @Override
    public User queryOneUser(int uid) {
        return userDao.queryOneUser(uid);
    }

    /**
     * @Author:cjs
     * @Description:用户登录
     * @Date:2020/7/15
     */
    @Override
    public User userLogin(User user) {
        return userDao.userLogin(user);
    }

    /**
     * @Author:cjs
     * @Description:用户注册
     * @Date:2020/7/15
     */
    @Override
    public int userRegister(User user) {
        return userDao.userRegister(user);
    }

    /**
     * @Author:cjs
     * @Description:用户删除
     * @Date:2020/7/15
     */
    @Override
    public int delUser(int uid) {
        return userDao.delUser(uid);
    }

    /**
     * @Author:cjs
     * @Description:用户更新（管理员）
     * @Date:2020/7/15
     */
    @Override
    public int updUser(User user) {
        return userDao.updUser(user);
    }

    /**
     * @Author:cjs
     * @Description:用户更新（用户）
     * @Date:2020/7/15
     */
    @Override
    public int updMyself(User user) {
        return userDao.updMyself(user);
    }

    /**
     * @Author:cjs
     * @Description:添加地址
     * @Date:2020/7/16
     */
    @Override
    public int addLocation(Location location) {
        return userDao.addLocation(location);
    }

    /**
     * @Author:cjs
     * @Description:删除地址
     * @Date:2020/7/16
     */
    @Override
    public int delLocation(int lid) {
        return userDao.delLocation(lid);
    }

    /**
     * @Author:cjs
     * @Description:更新地址
     * @Date:2020/7/16
     */
    @Override
    public int updLocation(Location location) {
        return userDao.updLocation(location);
    }

    /**
     * @Author:cjs
     * @Description:查询所有地址
     * @Date:2020/7/16
     */
    @Override
    public List<Location> queryAllLocation() {
        return userDao.queryAllLocation();
    }

    /**
     * @Author:cjs
     * @Description:
     * @Date:2020/7/16
     */
    @Override
    public Location queryOneLocation(int lid) {
        return userDao.queryOneLocation(lid);
    }

    /**
     * @Author:cjs
     * @Description:管理员修改用户状态
     * @Date:2020/7/16
     */
    @Override
    public int updState(int uid) {
        return userDao.updState(uid);
    }

    /**
     * @Author:cjs
     * @Description:管理员修改用户状态
     * @Date:2020/7/16
     */
    @Override
    public int updRecover(int uid) {
        return userDao.updRecover(uid);
    }

    /**
     * @Author:cjs
     * @Description:管理员登录
     * @Date:2020/7/17
     */
    @Override
    public Admin adminLogin(Admin admin) {
        return userDao.adminLogin(admin);
    }

    /**
     * @Author:cjs
     * @Description:修改密码
     * @Date:2020/7/17
     */
    @Override
    public int rePassword(int uid, String password) {
        return userDao.rePassword(uid, password);
    }

    /**
     * @Author:cjs
     * @Description:修改昵称
     * @Date:2020/7/18
     */
    @Override
    public int reName(int uid, String uname) {
        return userDao.reName(uid, uname);
    }

    /**
     * @Author:cjs
     * @Description:通过账号查询用户（用于注册账号验证）
     * @Date:2020/7/18
     */
    @Override
    public User selectPhone(String phone) {
        return userDao.selectPhone(phone);
    }
    /**
     * @Author:cjs
     * @Description:管理员模糊查询账号
     * @Date:2020/7/18
     */
    @Override
    public List<User> searchPhone(String phone) {
        return userDao.searchPhone(phone);
    }

    @Override
    public List<User> searchValue(int credit1, int credit2) {
        return userDao.searchValue(credit1,credit2);
    }
}
