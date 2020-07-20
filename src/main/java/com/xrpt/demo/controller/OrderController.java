package com.xrpt.demo.controller;

import com.xrpt.demo.entity.Order;
import com.xrpt.demo.entity.User;
import com.xrpt.demo.service.UserService;
import com.xrpt.demo.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author by wjx
 * @date 2020/7/15
 * @DESC:
 */
@Controller
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private UserService userService;

    /**
     * @author by wjx
     * @date 2020/7/15
     * @DESC: taker完成订单，提交本订单价格，计算是否超时进行信誉分增减，同时改变订单状态为未支付
     */
    @RequestMapping("/commitOrder")
    public void commitOrder(Integer price, Integer oid, HttpServletResponse response, HttpSession session) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 获取当前用户
        User currentUser = (User) session.getAttribute("currentUser");
        // 查询当前订单
        Order order = orderService.queryOneOrderByOid(oid);
        // 计算订单剩余时间
        long leftMills = order.getDdl().getTime() - new Date().getTime();
        String message = "";
        // 若剩余时间>0，说明taker在规定时间完成订单
        if(leftMills>0){
            // 增加信誉分
            userService.updateUserCredit(1,currentUser.getUid());
            message = "恭喜!您在规定时间内完成订单!已经奖励信誉分咯!";
        }else{
            userService.updateUserCredit(-1,currentUser.getUid());
            message = "您未在规定时间内完成订单!已扣除相应信誉分!还请诚信交易";
        }
        // 价格转化成bigdecimal
        BigDecimal bigDecimal = new BigDecimal(price);
        // 插入金额，并更新订单状态
        int i = orderService.commitOrder(bigDecimal, oid);
        if(i!=0){
            out.write("<script>alert('"+message+"');location.href='toTakerCenter'</script>");
        }else {
            out.write("<script>alert('提交失败')</script>");
        }
    }

    /**
     * @author by wjx
     * @date 2020/7/18
     * @DESC: taker取消订单，减信誉分
     */
    @RequestMapping("/takerCancle")
    public void takerCancle(Integer oid,HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        // 获取当前用户
        User currentUser = (User) session.getAttribute("currentUser");
        // 更新订单状态为：未接单
        int i = orderService.updateOrderState(0, oid);
        if(i!=0){
            // 扣除信誉分
            userService.updateUserCredit(-1,currentUser.getUid());
            out.write("<script>alert('取消成功!您的信誉分将被减少!');location.href='toTakerCenter'</script>");
        }else {
            out.write("<script>alert('提交失败')</script>");
        }
    }

    /**
     * @author by wjx
     * @date 2020/7/18
     * @DESC: user取消订单，减信誉分
     */
    @RequestMapping("/userCancle")
    public void userCancle(Integer oid,HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        // 获取当前用户
        User currentUser = (User) session.getAttribute("currentUser");
        // 更新订单状态为：未接单
        int i = orderService.updateOrderState(0, oid);
        if(i!=0){
            // 扣除信誉分
            userService.updateUserCredit(-1,currentUser.getUid());
            out.write("<script>alert('取消成功!您的信誉分将被减少!');location.href='toPosterCenter'</script>");
        }else {
            out.write("<script>alert('提交失败')</script>");
        }
    }

    // 7.19 wjx 和 wjh的整合
}
