package com.xrpt.demo.dao;

import com.xrpt.demo.entity.Order;

import java.util.List;

/**
 * @author by wjx
 * @date 2020/7/15
 * @DESC: 订单Dao
 */
public interface OrderDao {
    // 根据state查询相应订单
    public List<Order> queryOrderByState(int state);
}
