package com.xrpt.demo.dao;

import com.xrpt.demo.entity.Order;
import com.xrpt.demo.vo.OrderInputVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
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

    /*
     * author:叶芃辉
     * date:7.16
     * 增加订单方法*/
    public int addOrder(Order order);

    public int editPoster(@Param("oid") int oid, @Param("poster") String poster);

    public int editLid(@Param("oid") int oid,@Param("lid") Integer lid);

    int editAddress(@Param("oid") int oid,@Param("address") String address);

    int editPhone(@Param("oid")int oid,@Param("phone") String phone);

    int editDDL(@Param("oid")int oid,@Param("ddl") Date ddl_date);

    int editCode(@Param("oid")int oid,@Param("code") String code);
}
