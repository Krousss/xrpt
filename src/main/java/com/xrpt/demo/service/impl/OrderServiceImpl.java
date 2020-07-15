package com.xrpt.demo.service.impl;

import com.xrpt.demo.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by wjx
 * @date 2020/7/15
 * @DESC:
 */
@Service
public class OrderServiceImpl {
    @Autowired
    private OrderDao orderDao;


}
