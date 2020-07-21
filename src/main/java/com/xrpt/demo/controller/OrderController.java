package com.xrpt.demo.controller;

import com.xrpt.demo.entity.Order;
import com.xrpt.demo.entity.User;
import com.xrpt.demo.service.UserService;
import com.xrpt.demo.service.impl.OrderServiceImpl;
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
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        Order order = orderService.queryOneOrderByOid(oid);
        if(i!=0){
            if(order.getState()==0){
                out.write("<script>alert('取消成功!');location.href='toPosterCenter'</script>");
            }else{
                // 扣除信誉分
                userService.updateUserCredit(-1,currentUser.getUid());
                out.write("<script>alert('取消成功!已有人接单，您的信誉分将被减少!');location.href='toPosterCenter'</script>");
            }
        }else {
            out.write("<script>alert('提交失败')</script>");
        }
    }

    // 7.19 wjx 和 wjh的整合

    /*yph
     * 添加订单响应方法,uid为用户id，poster为收货人姓名，lid为驿站的id，address为收货地址，phone为收货人电话，ddl为最晚送达时间，
     * 需要转为date，code为取货码*/
    @RequestMapping("/addOrder")
    public ModelAndView addOrder(@RequestParam("uid") int uid, @RequestParam("poster") String poster,
                                 @RequestParam("lid") int lid, @RequestParam("address") String address,
                                 @RequestParam("phone") String phone, @RequestParam("ddl") String ddl,
                                 @RequestParam("code") String code) throws ParseException {
        Date currentTime=new Date();
        System.out.println(currentTime);
        //        转化ddl为date
        ddl=ddl.replace("T"," ");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date ddl_date=simpleDateFormat.parse(ddl);
        Order order=new Order();
//        设置用户id
        order.setUid(uid);
//        设置收货人姓名
        order.setPoster(poster);
//        设置驿站id
        order.setLid(lid);
//        设置地址
        order.setAddress(address);
//        设置收货电话
        order.setPhone(phone);
//        设置最晚收货时间
        order.setDdl(ddl_date);
//        设置取件码
        order.setCode(code);
//        设置订单创建时间
        order.setDate(currentTime);
        ModelAndView modelAndView=new ModelAndView();
        if (orderService.addOrder(order)>0) {
            modelAndView.setViewName("redirect:/toPosterCenter");
        }
        return modelAndView;
    }
    //    修改收货人
    @ResponseBody
    @RequestMapping("editPoster")
    public Map editPoster(int oid, String poster){
//        创建返回的result的容器
        Map<String, Order> ret = new HashMap<String, Order>();
//        调用修改收货人方法
        int i=orderService.editPoster(oid,poster);
//        若成功修改则获取修改后的订单对象放入result中，若修改失败则放入空对象
        if (i>0){
            Order order=orderService.queryOneOrderByOid(oid);
            ret.put("order",order);
            return ret;
        }
        ret.put("order",null);
        return ret;
    }
    @ResponseBody
    @RequestMapping("editLid")
//    修改驿站，同上
    public Map editLid(int oid,Integer lid){
        Map<String, Order> ret = new HashMap<String, Order>();
        int i=orderService.editLid(oid,lid);
        if (i>0){
            Order order=orderService.queryOneOrderByOid(oid);
            ret.put("order",order);
            return ret;
        }
        ret.put("order",null);
        return ret;
    }
    //    修改收货地址同上
    @ResponseBody
    @RequestMapping("editAddress")
    public Map editAddress(int oid,String address){
        Map<String, Order> ret = new HashMap<String, Order>();
        int i=orderService.editAddress(oid,address);
        if (i>0){
            Order order=orderService.queryOneOrderByOid(oid);
            ret.put("order",order);
            return ret;
        }
        ret.put("order",null);
        return ret;
    }
    //修改电话，同上
    @ResponseBody
    @RequestMapping("editPhone")
    public Map editPhone(int oid,String phone){
        Map<String, Order> ret = new HashMap<String, Order>();
        int i=orderService.editPhone(oid,phone);
        if (i>0){
            Order order=orderService.queryOneOrderByOid(oid);
            ret.put("order",order);
            return ret;
        }
        ret.put("order",null);
        return ret;
    }
    //修改送达时间，同上，不过需要把修改后的date转化为string类型返回前端，方便前端实时显示
    @ResponseBody
    @RequestMapping("editDDL")
    public Map editDDL(int oid,String ddl) throws ParseException {
        Map<String, Object> ret = new HashMap<String, Object>();
        ddl=ddl.replace("T"," ");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date ddl_date=simpleDateFormat.parse(ddl);
        String ddl_string=simpleDateFormat.format(ddl_date);
        System.out.println(ddl_string);
        int i=orderService.editDDL(oid,ddl_date);
        if (i>0){
            Order order=orderService.queryOneOrderByOid(oid);
            ret.put("order",order);
            ret.put("ddl",ddl_string);
            return ret;
        }
        ret.put("order",null);
        return ret;
    }
    //    修改取货码，同上
    @ResponseBody
    @RequestMapping("editCode")
    public Map editCode(int oid,String code){
        Map<String, Order> ret = new HashMap<String, Order>();
        int i=orderService.editCode(oid,code);
        if (i>0){
            Order order=orderService.queryOneOrderByOid(oid);
            ret.put("order",order);
            return ret;
        }
        ret.put("order",null);
        return ret;
    }
}
