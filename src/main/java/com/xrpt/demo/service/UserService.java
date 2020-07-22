package com.xrpt.demo.service;

import com.xrpt.demo.entity.Admin;
import com.xrpt.demo.entity.Location;
import com.xrpt.demo.entity.Note;
import com.xrpt.demo.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    /**
     * @Author: wjx
     * @Description: 根据ID查询用户
     * @Date: 2020/7/17
     */
    public User queryUserByID(int uid);
    /**
     * @Author: wjh
     * @Description: 根据uname查询用户，模糊查询
     * @Date: 2020/7/19
     */
    public User queryUserByName(String uname);

    /**
     * @Author: wjx
     * @Description: 增加或减少用户信誉分
     * @Date: 2020/7/17
     */
    public int updateUserCredit(int credit,int uid);

    /**
     * @Author: wjx
     * @Description: 增加利润
     * @Date: 2020/7/18
     */
    public int updateAdminProfit(BigDecimal profit);

    /**
     * @Author:cjs
     * @Description:查询所有用户
     * @Date:2020/7/15
     */
    public List<User> queryAllUser();
    /**
     * @Author:cjs
     * @Description:查询单个用户
     * @Date:2020/7/15
     */
    public User queryOneUser(int uid);
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
     * @Description:删除用户
     * @Date:2020/7/15
     */
    public int delUser(int uid);
    /**
     * @Author:cjs
     * @Description:更新用户
     * @Date:2020/7/15
     */
    public int updUser(User user);
    /**
     * @Author:cjs
     * @Description:更新用户（用户）
     * @Date:2020/7/15
     */
    public int updMyself(User user);
    /**
     * @Author:cjs
     * @Description:添加地址
     * @Date:2020/7/15
     */
    public int addLocation(Location location);
    /**
     * @Author:cjs
     * @Description:删除地址
     * @Date:2020/7/15
     */
    public int delLocation(int lid);
    /**
     * @Author:cjs
     * @Description:通过id修改地址
     * @Date:2020/7/15
     */
    public int updLocation(Location location);
    /**
     * @Author:cjs
     * @Description:查询所有地址
     * @Date:2020/7/15
     */
    public List<Location> queryAllLocation();
    /**
     * @Author:cjs
     * @Description:通过id查询单条地址
     * @Date:2020/7/15
     */
    public Location queryOneLocation(int lid);

    /**
     * @Author:cjs
     * @Description:管理员修改用户状态
     * @Date:2020/7/16
     */
    public int updState(int uid);
    /**
     * @Author:cjs
     * @Description:管理员修改用户状态
     * @Date:2020/7/16
     */
    public int updRecover(int uid);
    /**
     * @Author:cjs
     * @Description:管理员登录
     * @Date:2020/7/17
     */
    public Admin adminLogin(Admin admin);
    /**
     * @Author:cjs
     * @Description:修改密码
     * @Date:2020/7/17
     */
    public int rePassword(int uid,String password);
    /**
     * @Author:cjs
     * @Description:修改昵称
     * @Date:2020/7/18
     */
    public int reName(int uid,String uname);
    /**
     * @Author:cjs
     * @Description:通过账号查询用户（用于注册账号验证）
     * @Date:2020/7/18
     */
    public User selectPhone(String phone);
    /**
     * @Author:cjs
     * @Description:管理员模糊查询账号
     * @Date:2020/7/18
     */
    public List<User> searchPhone(String phone);

    /**
     * @Author:cjs
     * @Description:管理员页面通过最大值最小值查询用户
     * @Date:2020/7/18
     */
    public List<User> searchValue(int credit1,int credit2);

    /**
     * @Author:yph
     * @Description:查询所有地址
     * @Date:不知道
     */
    public List<Location> queryLocations();

    /**
     * @Author:cjs
     * @Description:信誉中心的信用记录
     * @Date:2020/7/21
     */
    public List<Note> creditOrder(int uid,int type);

    /**
     * @Author:cjs
     * @Description:完成订单添加记录
     * @Date:2020/7/21
     */
    public List<Note> queryNotices(int uid,int type);

    /**
     * @Author:cjs
     * @Description:取消订单添加记录
     * @Date:2020/7/21
     */
    public int addNote(Note note);

}
