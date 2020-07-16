package com.xrpt.demo.controller;

import com.xrpt.demo.entity.Location;
import com.xrpt.demo.entity.Order;
import com.xrpt.demo.entity.User;
import com.xrpt.demo.service.impl.OrderServiceImpl;
import com.xrpt.demo.service.impl.UserServiceImpl;
import com.xrpt.demo.vo.OrderOutVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @Autowired
    private UserServiceImpl userService;

    /**
     * @author by wjx
     * @date 2020/7/16
     * @DESC: 去taker已接受的订单页面
     * @return ModelAndView
     */
    @RequestMapping("/toTakerCenter")
    public ModelAndView toTakerCenter(HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        ModelAndView model = new ModelAndView();
        // 待接受订单
//        List<Order> orders1 = orderService.queryOrderByState(0);
        // 已接受订单
        List<Order> orders2 = orderService.queryAcceptedOrder(1,currentUser.getUid());
        // 待付款订单
//        List<Order> orders3 = orderService.queryOrderByState(2);
        // 已结束订单
//        List<Order> orders4 = orderService.queryOrderByState(3);

        List<OrderOutVO> orders2VO = getOrders2VO(orders2);
        model.addObject("orders2VO",orders2VO);
        model.setViewName("Taker/TakerCenter");

        return model;
    }

    /**
     * @author by wjx
     * @date 2020/7/16
     * @DESC: 把当前taker已接受的订单拿来处理成VO
     * @param orders2
     * @return 返回代取人已接受的订单VO
     */
    public List<OrderOutVO> getOrders2VO(List<Order> orders2){
        List<OrderOutVO> orderOutVOs = new ArrayList<>();
        for (Order o:orders2) {
            // 新建VO
            OrderOutVO orderVO = new OrderOutVO();
            // 通过订单的lid获取对应的location
            Location location = userService.queryOneLocation(o.getLid());
            // 把订单注入vo
            orderVO.setOrder(o);
            // 把location注入vo
            orderVO.setLocation(location);
            // 把新建vo加入vo列表
            orderOutVOs.add(orderVO);
        }
        return orderOutVOs;
    }

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
