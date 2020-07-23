package com.xrpt.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author:cjs
 * @Description:用户类
 * @Date:Created in 9:53 2020/7/15
 * @Modified by:
 */
public class User implements Serializable {
    private Integer uid;//用户id
    private String phone;//手机号
    private String password;//密码
    private String uname;//用户名
    private int credit;//信用分
    private int state;//状态：0冻结、1正常
    private String real; //真实姓名
    private BigDecimal profit; //用户受益

    public User(Integer uid) {
        this.uid = uid;
    }

    public User() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("uid=").append(uid);
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", uname='").append(uname).append('\'');
        sb.append(", credit=").append(credit);
        sb.append(", state=").append(state);
        sb.append(", real='").append(real).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getReal() {
        return real;
    }

    public void setReal(String real) {
        this.real = real;
    }
}
