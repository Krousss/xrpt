package com.xrpt.demo.entity;

import java.math.BigDecimal;

/**
 * @author by wjx
 * @date 2020/7/15
 * @DESC:
 */
public class Admin {
    private int aid;
    private String phone;
    private String password;
    private BigDecimal profit;

    public Admin() {
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }
}
