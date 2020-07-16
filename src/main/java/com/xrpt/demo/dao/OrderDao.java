package com.xrpt.demo.dao;

import com.xrpt.demo.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author by wjx
 * @date 2020/7/15
 * @DESC: 订单Dao
 */
@Mapper
@Repository
public interface OrderDao {
    /**
     * @author by wjx
     * @date 2020/7/16
     * @DESC: 根据takerID和订单状态查询订单
     */
    public List<Order> queryAcceptedOrder(int state,int takerID);

    /**
     * @author by wjx
     * @date 2020/7/16
     * @DESC: taker完成订单，提交本订单价格，同时改变订单状态为未支付
     */
    public int commitOrder(BigDecimal price,int oid);
}
