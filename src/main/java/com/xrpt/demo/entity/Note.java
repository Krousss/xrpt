package com.xrpt.demo.entity;

import java.util.Date;

/**
 * @Author:cjs
 * @Description:
 * @Date:Created in 15:34 2020/7/21
 * @Modified by:
 */
public class Note {
    private int nid;
    private int uid;//用户id
    private Date date;//记录时间
    private String msg;//通知内容
    private int state;//0:减积分类型；1：加积分类型
    private int type;//0:积分通知；1:催单通知

    public static final String cancelMSG = "您违规取消订单，积分-1";
    public static final String completeMSG = "您按时完成了订单，积分+1";
    public static final String lateOrdMSG = "您未在规定时间内完成订单，积分-1";
    public static final String payOrdMSG = "您有待付款订单催促，请及时支付款项";
    public static final String cancelOrdMSG = "您的订单被接单人取消，正在等待新的接单人";

    public Note() {
    }

    public Note(int uid, Date date, String msg, int state, int type) {
        this.uid = uid;
        this.date = date;
        this.msg = msg;
        this.state = state;
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Note{");
        sb.append("nid=").append(nid);
        sb.append(", uid=").append(uid);
        sb.append(", date=").append(date);
        sb.append(", state=").append(state);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}