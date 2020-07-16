package com.xrpt.demo.service.impl;

import com.xrpt.demo.dao.OrderDao;
import com.xrpt.demo.entity.Order;
import com.xrpt.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author by wjx
 * @date 2020/7/15
 * @DESC:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> queryAcceptedOrder(int state, int takerID) {
        return orderDao.queryAcceptedOrder(state,takerID);
    }

    @Override
    public int commitOrder(BigDecimal price,int oid) {
        return orderDao.commitOrder(price,oid);
    }
}
