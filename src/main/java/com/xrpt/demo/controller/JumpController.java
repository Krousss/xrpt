package com.xrpt.demo.controller;

import com.xrpt.demo.entity.Order;
import com.xrpt.demo.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author by wjx
 * @date 2020/7/13
 * @DESC:这个Controller用来控制A标签的跳转。
 */
@Controller
public class JumpController {

    @Autowired
    private OrderServiceImpl orderService;

    @RequestMapping("/toPosterCenter")
    public String toPosterCenter(){
        return "Poster/PosterCenter";
    }

    @RequestMapping("/toUserCenter")
    public String toUserCenter(){
        return "User/UserCenter";
    }

    @RequestMapping("/toOrderDetail")
    public String toOrderDetail(){
        return "Poster/orderDetail";
    }

    @RequestMapping("/toTakerCenter")
    public ModelAndView toTakerCenter(){
        ModelAndView model = new ModelAndView();
        // 待接受订单
//        List<Order> orders1 = orderService.queryOrderByState(0);
        // 已接受订单
        List<Order> orders2 = orderService.queryOrderByState(1);
        // 待付款订单
//        List<Order> orders3 = orderService.queryOrderByState(2);
        // 已结束订单
//        List<Order> orders4 = orderService.queryOrderByState(3);

//        model.addObject(orders1);
        model.addObject("orders2",orders2);
//        model.addObject(orders3);
//        model.addObject(orders4);
        model.setViewName("Taker/TakerCenter");
        return model;
    }

    @RequestMapping("/toAdmin")
    public String toAdmin(){
        return "Admin/admin";
    }

    @RequestMapping("/toTakerDetail")
    public String toTakerDetail(){
        return "Taker/takerDetail";
    }

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index";
    }
}
