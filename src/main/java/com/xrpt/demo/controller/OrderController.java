package com.xrpt.demo.controller;

import com.sun.deploy.net.HttpResponse;
import com.xrpt.demo.service.OrderService;
import com.xrpt.demo.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

/**
 * @author by wjx
 * @date 2020/7/15
 * @DESC:
 */
@Controller
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;


    /**
     * @author by wjx
     * @date 2020/7/15
     * @DESC: taker完成订单，提交本订单价格，同时改变订单状态为未支付
     */
    @RequestMapping("/commitOrder")
    public void commitOrder(Integer price, Integer oid, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        BigDecimal bigDecimal = new BigDecimal(price);
        int i = orderService.commitOrder(bigDecimal, oid);
        if(i!=0){
            out.write("<script>alert('提交成功');location.href='toTakerCenter'</script>");
        }else {
            out.write("<script>alert('提交失败')</script>");
        }
    }
}
