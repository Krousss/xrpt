package com.xrpt.demo.service.impl;

import com.xrpt.demo.dao.OrderDao;
import com.xrpt.demo.entity.Order;
import com.xrpt.demo.service.OrderService;
import com.xrpt.demo.vo.OrderInputVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
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
    public List<Order> queryTakerOrderByState(OrderInputVo orderInputVo) {
        return orderDao.queryTakerOrderByState(orderInputVo);
    }

    @Override
    public List<Order> queryUserOrderByState(OrderInputVo orderInputVo) {
        return orderDao.queryUserOrderByState(orderInputVo);
    }

    @Override
    public int commitOrder(BigDecimal price,int oid) {
        return orderDao.commitOrder(price,oid);
    }

    @Override
    public Order queryOneOrderByOid(int oid) {
        return orderDao.queryOneOrderByOid(oid);
    }

    @Override
    public int updateOrderState(int state, int oid) {
        return orderDao.updateOrderState(state,oid);
    }

    @Override
    public int addOrder(Order order) {
        return orderDao.addOrder(order);
    }

    @Override
    public int editPoster(int oid, String poster) {
        return orderDao.editPoster(oid,poster);
    }

    @Override
    public int editLid(int oid, Integer lid) {
        return orderDao.editLid(oid,lid);
    }

    @Override
    public int editAddress(int oid, String address) {
        return orderDao.editAddress(oid,address);
    }

    @Override
    public int editPhone(int oid, String phone) {
        return orderDao.editPhone(oid,phone);
    }

    @Override
    public int editDDL(int oid, Date ddl_date) {
        return orderDao.editDDL(oid,ddl_date);
    }

    @Override
    public int editCode(int oid, String code) {
        return orderDao.editCode(oid,code);
    }
}
