package com.xrpt.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xrpt.demo.entity.Admin;
import com.xrpt.demo.entity.User;
import com.xrpt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author by wjx
 * @date 2020/7/12
 * @DESC: 测试mybaits整合情况,可直接用来做UserController
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/queryAllUser")
    public ModelAndView queryAllUser(){
        System.out.println("查询所有");
        List<User> users = userService.queryAllUser();
        System.out.println(users);
        ModelAndView model = new ModelAndView();
        model.setViewName("list");
        model.addObject("users",users);
        return model;
    }
    /**
     * @Author:cjs
     * @Description:用户登录具体实现
     * @Date:2020/7/15
     */
    @RequestMapping("/userLogin")
    public String userLogin(User user, HttpSession session,HttpServletResponse response) throws IOException {
        User currentUser = userService.userLogin(user);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println(currentUser);
        if(currentUser != null){
            if(currentUser.getState()== 1){
                session.setAttribute("currentUser",currentUser);
            }else {
                out.write("<script>alert('您的账户已被冻结')</script>");
            }
        }else{
            out.write("<script>alert('用户名或密码错误')</script>");
        }
        return "index";
    }

    /**
     * @Author:cjs
     * @Description:用户注册具体实现
     * @Date:2020/7/15
     */
    @RequestMapping("/userRegister")
    public ModelAndView userRegister(@RequestParam("phone") String phone,@RequestParam("name") String name,
                                     @RequestParam("password") String password
    ) throws IOException {
        System.out.println("用户注册");
        User user = new User();
        user.setPhone(phone);
        user.setUname(name);
        user.setPassword(password);
        int i = userService.userRegister(user);
        ModelAndView model = new ModelAndView();
        if(i>0){
            model.setViewName("redirect:/index.html");
        }else{

        }
        return model;
    }

    /**
     * @Author:cjs
     * @Description:退出登录
     * @Date:2020/7/15
     */
    @RequestMapping("loginOut")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "index";
    }

    /**
     * @Author:cjs
     * @Description:管理员冻结用户
     * @Date:2020/7/16
     */
    @GetMapping("/updState/{uid}")
    public void updState(@PathVariable("uid")int uid,HttpServletResponse response) throws IOException {
        User user = userService.queryOneUser(uid);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if(user.getState() == 1){
            System.out.println("修改用户状态");
            int id = userService.updState(uid);
            out.write("<script>location.href='../toAdmin'</script>");
        }else{
            out.write("<script>alert('该账户已被冻结，无需操作');location.href='../toAdmin'</script>");
        }

    }
    /**
     * @Author:cjs
     * @Description:管理员恢复用户
     * @Date:2020/7/16
     */
    @GetMapping("/updRecover/{uid}")
    public void updRecover(@PathVariable("uid")int uid,HttpServletResponse response) throws IOException {
        System.out.println("修改用户状态");
        User user = userService.queryOneUser(uid);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if(user.getState() == 0){
            System.out.println("修改用户状态");
            int id = userService.updRecover(uid);
            out.write("<script>location.href='../toAdmin'</script>");
        }else{
            out.write("<script>alert('该账户状态正常，无需操作');location.href='../toAdmin'</script>");
        }
    }

    /**
     * @Author:cjs
     * @Description:管理员登录具体实现
     * @Date:2020/7/15
     */
    @RequestMapping("/adminLogin")
    public ModelAndView adminLogin(Admin admin, HttpSession session, HttpServletResponse response) throws IOException {
        Admin currentUser = userService.adminLogin(admin);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        System.out.println(currentUser);
        ModelAndView modelAndView = new ModelAndView();
        if(currentUser != null){
            session.setAttribute("currentUser",currentUser);
        }else{
            out.write("<script>alert('用户名或密码错误')</script>");
        }
        modelAndView.setViewName("manage");
        return modelAndView;
    }
    /**
     * @Author:cjs
     * @Description:用户修改密码
     * @Date:2020/7/17
     */
    @RequestMapping("rePassword")
    public void rePassword(@RequestParam("password1") String password1,@RequestParam("uid")String uid
            , HttpServletResponse response,HttpSession session) throws IOException {
        System.out.println("修改密码");
        System.out.println(password1+"---------"+uid);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        int id = Integer.parseInt(uid);
        int i = userService.rePassword(id,password1);
        PrintWriter out = response.getWriter();
        if(i != 0){
            User currentUser = userService.queryOneUser(id);
            session.setAttribute("currentUser",currentUser);
            System.out.println("---------------修改成功");
            out.write("<script>alert('修改成功');location.href='toUserCenter'</script>");
        }else{
            System.out.println("----------------修改失败");
            out.write("<script>alert('修改失败');location.href='toUserCenter'</script>");
        }
    }
    /**
     * @Author:cjs
     * @Description:修改昵称
     * @Date:2020/7/18
     */
    @RequestMapping("reName")
    public void reName(@RequestParam("newname") String newname,@RequestParam("nameid") String nameid
            , HttpServletResponse response,HttpSession session) throws IOException{
        System.out.println("修改昵称");
        System.out.println(newname+"--------"+nameid);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        int id = Integer.parseInt(nameid);
        int i = userService.reName(id,newname);

        PrintWriter out = response.getWriter();
        if(i != 0){
            User currentUser = userService.queryOneUser(id);
            session.setAttribute("currentUser",currentUser);
            System.out.println("---------------修改成功");
            out.write("<script>alert('修改成功');location.href='toUserCenter'</script>");
        }else{
            System.out.println("----------------修改失败");
            out.write("<script>alert('修改失败');location.href='toUserCenter'</script>");
        }
    }
    /**
     * @Author:cjs
     * @Description:注册前查询账是否已存在
     * @Date:2020/7/18
     */
    @RequestMapping(value = "selectPhone",method = RequestMethod.POST)
    @ResponseBody
    public void selectPhone(String phone,HttpServletResponse response) throws IOException {
        User user = userService.selectPhone(phone);
        PrintWriter out = response.getWriter();
        System.out.println("-------"+user);
        if(user != null){
            out.write("0");
        }else{
            out.write("1");
        }
    }
    /**
     * @Author:cjs
     * @Description:管理员页面搜索账号
     * @Date:2020/7/18
     */
    @RequestMapping("searchPhone")
    public ModelAndView searchPhone(@RequestParam("inphone")String phone ,@RequestParam(required = true,defaultValue = "1")int page){
        System.out.println("管理员搜索账号");
        PageHelper.startPage(page, 5);
        List<User> users = userService.searchPhone(phone);
        System.out.println("----"+users);
        ModelAndView modelAndView = new ModelAndView();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        modelAndView.addObject("users",users);
        modelAndView.addObject("page",pageInfo);
        modelAndView.setViewName("Admin/admin");
        return modelAndView;
    }
    /**
     * @Author:cjs
     * @Description:管理员页面通过最大值最小值查询用户
     * @Date:2020/7/19
     */
    @RequestMapping("searchValue")
    public ModelAndView searchValue(@RequestParam("credit1")int credit1,@RequestParam("credit2")int credit2,@RequestParam(required = true,defaultValue = "1")int page){
        System.out.println("管理员页面通过最大值最小值查询用户");
        PageHelper.startPage(page, 5);
        System.out.println(credit1+"---------"+credit2);
        List<User> users = userService.searchValue(credit1,credit2);
        System.out.println("----"+users);
        ModelAndView modelAndView = new ModelAndView();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        modelAndView.addObject("page",pageInfo);
        modelAndView.addObject("users",users);
        modelAndView.setViewName("Admin/admin");
        return modelAndView;
    }
}