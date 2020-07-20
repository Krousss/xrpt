package com.xrpt.demo.dao;

import com.xrpt.demo.entity.Order;
import com.xrpt.demo.vo.OrderInputVo;
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
     * @author by wjh
     * @date 2020/7/18
     * @DESC: 根据takerID和订单状态查询taker的订单
     */
    public List<Order> queryTakerOrderByState(OrderInputVo orderInputVo);


    /**
     * @author by wjh
     * @date 2020/7/18
     * @DESC: 根据userID和订单状态查询user的订单，条件查询
     */
    public List<Order> queryUserOrderByState(OrderInputVo orderInputVo);

    /**
     * @author by wjx
     * @date 2020/7/16
     * @DESC: taker完成订单，提交本订单价格，同时改变订单状态为未支付
     */
    public int commitOrder(BigDecimal price,int oid);

    /**
     * @author by wjx
     * @date 2020/7/17
     * @DESC: 根据oid查询单个订单
     */
    public Order queryOneOrderByOid(int oid);

    /**
     * @author by wjx
     * @date 2020/7/18
     * @DESC: 更新订单状态
     */
    public int updateOrderState(int state,int oid);
}
