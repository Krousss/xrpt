package com.xrpt.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xrpt.demo.entity.Location;
import com.xrpt.demo.entity.Order;
import com.xrpt.demo.entity.User;
import com.xrpt.demo.service.impl.OrderServiceImpl;
import com.xrpt.demo.service.impl.UserServiceImpl;
import com.xrpt.demo.vo.OrderInputVo;
import com.xrpt.demo.vo.OrderOutVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
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
     * @DESC: 去TakerCenter页面
     * @return ModelAndView
     */
    @RequestMapping("/toTakerCenter")
    public ModelAndView toTakerCenter(HttpSession session, @RequestParam(required = true,defaultValue = "1") Integer page, Integer to,OrderInputVo orderInputVo){
        User currentUser = (User) session.getAttribute("currentUser");
        ModelAndView model = new ModelAndView();
        orderInputVo.setTakerId(currentUser.getUid());//设置登陆人的uid
        // 待接受订单
//        List<Order> orders1 = orderService.queryTakerOrderByState(0);
        // 已接受订单
        orderInputVo.setState(1);
        List<Order> orders2 = orderService.queryTakerOrderByState(orderInputVo);
        // 待付款订单
        orderInputVo.setState(2);
        List<Order> orders3 = orderService.queryTakerOrderByState(orderInputVo);
        // 已结束订单
            //设置跳转分页也可以条件查询
        if (orderInputVo.getMinTime()!=null&&orderInputVo.getMaxTime()!=null){
            session.setAttribute("minTime",orderInputVo.getMinTime());
            session.setAttribute("maxTime",orderInputVo.getMaxTime());
            session.removeAttribute("poster");
        }else {
            orderInputVo.setMinTime((String) session.getAttribute("minTime"));
            orderInputVo.setMaxTime((String) session.getAttribute("maxTime"));
        }

        if (orderInputVo.getPoster()!=null){
            session.setAttribute("poster",orderInputVo.getPoster());
            session.removeAttribute("maxTime");
            session.removeAttribute("minTime");
        }else{
            orderInputVo.setPoster((String) session.getAttribute("poster"));
        }

        PageHelper.startPage(page,2);
        System.out.println(orderInputVo.getPoster());
        orderInputVo.setState(3);
        List<Order> orders4 = orderService.queryTakerOrderByState(orderInputVo);

        List<OrderOutVO> orders2VO = getOrdersVO(orders2);
        List<OrderOutVO> orders3VO = getOrdersVO(orders3);
        List<OrderOutVO> orders4VO = getOrdersVO(orders4);

        PageInfo<Order> pageInfo = new PageInfo<>(orders4);
        if (to !=null){ model.addObject("goto",1);}
        model.addObject("page",pageInfo);
        model.addObject("orders2VO",orders2VO);
        model.addObject("orders3VO",orders3VO);
        model.addObject("orders4VO",orders4VO);
        model.setViewName("Taker/TakerCenter");
        return model;
    }

    /**
     * @author by wjx
     * @date 2020/7/17
     * @DESC: 去PosterCenter页面
     * @return ModelAndView
     */
    @RequestMapping("/toPosterCenter")
    public ModelAndView toPosterCenter(HttpSession session, @RequestParam(required = true,defaultValue = "1") Integer page, Integer to, OrderInputVo orderInputVo, User user){
        User currentUser = (User) session.getAttribute("currentUser");
        ModelAndView model = new ModelAndView();
        orderInputVo.setUid(currentUser.getUid());//设置登陆人的uid
        // 待接受订单
        orderInputVo.setState(0);
        List<Order> orders1 = orderService.queryUserOrderByState(orderInputVo);
        // 已接受订单
        orderInputVo.setState(1);
        List<Order> orders2 = orderService.queryUserOrderByState(orderInputVo);
        // 待付款订单
        orderInputVo.setState(2);
        List<Order> orders3 = orderService.queryUserOrderByState(orderInputVo);
        // 已结束订单

        //设置跳转分页也可以条件查询
        if (user.getUname()!=null){
            session.setAttribute("uname",user.getUname());
            session.removeAttribute("minTime");
            session.removeAttribute("maxTime");
        }else {
            user.setUname((String) session.getAttribute("uname"));
        }
        if (orderInputVo.getMinTime()!=null&&orderInputVo.getMaxTime()!=null){
            session.setAttribute("minTime",orderInputVo.getMinTime());
            session.setAttribute("maxTime",orderInputVo.getMaxTime());
            session.removeAttribute("uname");
        }else {
            orderInputVo.setMinTime((String) session.getAttribute("minTime"));
            orderInputVo.setMaxTime((String) session.getAttribute("maxTime"));
        }
            //设置如果有代取人查询，则查出改takerId
        if (user.getUname()!=null){
            User taker = userService.queryUserByName(user.getUname());
            if(taker!=null){
                orderInputVo.setTakerId(taker.getUid());
            }
        }

        List<Location> locations = userService.queryLocations();
        model.addObject("locations",locations);

        orderInputVo.setState(3);
        PageHelper.startPage(page,3);//设置分页
        List<Order> orders4 = orderService.queryUserOrderByState(orderInputVo);

        List<OrderOutVO> orders1VO = getOrdersVO(orders1);
        List<OrderOutVO> orders2VO = getOrdersVO(orders2);
        List<OrderOutVO> orders3VO = getOrdersVO(orders3);
        List<OrderOutVO> orders4VO = getOrdersVO(orders4);

        PageInfo<Order> pageInfo = new PageInfo<>(orders4);
        if (to !=null){ model.addObject("goto",1);}//设置条件查询、分页时显示页面为已完成订单
        model.addObject("page",pageInfo);

        model.addObject("orders1VO",orders1VO);
        model.addObject("orders2VO",orders2VO);
        model.addObject("orders3VO",orders3VO);
        model.addObject("orders4VO",orders4VO);
        model.setViewName("Poster/PosterCenter");
        return model;
    }


    /**
     * @author by wjx
     * @date 2020/7/16
     * @DESC: 把订单列表拿来处理成VO
     * @param orders
     * @return 返回代取人已接受的订单VO
     */
    public List<OrderOutVO> getOrdersVO(List<Order> orders){
        List<OrderOutVO> orderOutVOs = new ArrayList<>();
        for (Order o:orders) {
            // 新建VO
            OrderOutVO orderVO = new OrderOutVO();
            // 通过订单的lid获取对应的location
            Location location = userService.queryOneLocation(o.getLid());

            // 如果订单状态为：有人待取，则查询待取人
            if(o.getState() != 0){
                // 通过订单的takerID获取对应的taker
                User taker = userService.queryUserByID(o.getTakerId());
                // 把taker注入vo
                orderVO.setTaker(taker);
            }

            // 把订单注入vo
            orderVO.setOrder(o);
            // 把location注入vo
            orderVO.setLocation(location);
            // 剩余时间倒计时
            Date now = new Date();
            long leftMills = o.getDdl().getTime() - now.getTime();
            orderVO.setLeftMills(leftMills);
            // 把新建vo加入vo列表
            orderOutVOs.add(orderVO);
        }
        return orderOutVOs;
    }

    @RequestMapping("/toUserCenter")
    public String toUserCenter(){
        return "User/UserCenter";
    }

    @RequestMapping("/toTakerDetail")
    public String toTakerDetail(){
        return "Taker/takerDetail";
    }

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index";
    }

    // 7.19 wjx 和 wjh的整合

    @RequestMapping("/toManage")
    public String toManage(){
        return "manage";
    }

    @RequestMapping("/toAdmin")
    public ModelAndView toAdmin(@RequestParam(required = true,defaultValue = "1")int page){
        PageHelper.startPage(page, 5);

        System.out.println("查询所有");
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.queryAllUser();

        PageInfo<User> pageInfo = new PageInfo<>(users);
        System.out.println(pageInfo+"------~~~~~~~~~~~~");
        modelAndView.addObject("users",users);
        modelAndView.addObject("page",pageInfo);
        modelAndView.setViewName("Admin/admin");
        return modelAndView;
    }

    // 7.20 wjx 和 cjs的整合

    //    叶芃辉修改于7.18
    @RequestMapping("/toOrderDetail")
    public ModelAndView toOrderDetail(@RequestParam("orderId") int oid){
        System.out.println(oid);

        Order order=orderService.queryOneOrderByOid(oid);

        OrderOutVO orderOutVO = new OrderOutVO();
        orderOutVO.setOrder(order);

        Location location = userService.queryOneLocation(order.getLid());
        orderOutVO.setLocation(location);

        List<Location> locations = userService.queryLocations();

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("targetOrder",orderOutVO);
        modelAndView.addObject("locations",locations);
        modelAndView.setViewName("Poster/orderDetail");
        return modelAndView;
    }
}
