package com.xrpt.demo.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author by wjx
 * @date 2020/7/15
 * @DESC: 订单实体类
 */
public class Order {
    // 订单ID
    private int oid;
    // 下单用户ID
    private int uid;
    // 下单用户姓名
    private String poster;
    // 交货地址
    private String address;
    // 下单用户手机号
    private String phone;
    // 取件码
    private String code;
    // 代取人ID
    private int takerId;
    // 驿站ID
    private int lid;
    // 限定时间
    private Date ddl;
    // 价格
    private BigDecimal price;
    // 订单日期
    private Date date;
    //  订单状态：【0（待接受） 1（已接受） 2（已完成） 3（已结束）】
    private int state;

    public Order() {
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getTakerId() {
        return takerId;
    }

    public void setTakerId(int takerId) {
        this.takerId = takerId;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public Date getDdl() {
        return ddl;
    }

    public void setDdl(Date ddl) {
        this.ddl = ddl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
