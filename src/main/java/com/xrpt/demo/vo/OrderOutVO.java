package com.xrpt.demo.vo;

import com.xrpt.demo.entity.Location;
import com.xrpt.demo.entity.Order;
import com.xrpt.demo.entity.User;

/**
 * @author by wjx
 * @date 2020/7/15
 * @DESC: 输出型VO，订单输出需要代取人的信息，以及驿站信息
 */
public class OrderOutVO {
    private Order order;
    private User taker;
    private Location location;
}
