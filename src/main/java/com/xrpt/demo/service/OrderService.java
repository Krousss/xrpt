package com.xrpt.demo.service;

import com.xrpt.demo.entity.Order;
import com.xrpt.demo.entity.User;

import java.util.List;

public interface OrderService {
    // 根据state查询相应订单
    public List<Order> queryOrderByState(int state);
}
