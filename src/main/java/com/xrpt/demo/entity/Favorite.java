package com.xrpt.demo.entity;

import org.springframework.stereotype.Component;

/**
 *
 * @author by lsz
 * @date 2020/7/19
 * @DESC: 收藏实体类
 * */
@Component
public class Favorite {
    //收藏食谱的url
    private String furl;
    //图片
    private String img;
    //菜谱名
    private String fname;
    //收藏者
    private Integer uid;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
    @Override
    public String toString() {
        return "Favorite{" +
                "furl='" + furl + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
