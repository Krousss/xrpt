package com.xrpt.demo.controller;

import com.xrpt.demo.entity.User;
import com.xrpt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ModelAndView userLogin(User user, HttpSession session,HttpServletResponse response) throws IOException {
        System.out.println("用户登录");
        User currentUser = userService.userLogin(user);
        ModelAndView model = new ModelAndView();
        PrintWriter out = response.getWriter();
        System.out.println(currentUser);
        if(currentUser != null){
            model.setViewName("index");
            model.addObject("currentUser",currentUser);
            session.setAttribute("currentUser",currentUser);
        }else{
            out.write("<script>alert('用户名或密码错误')</script>");
        }
        return model;
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
}