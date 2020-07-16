package com.xrpt.demo.entity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;

public class Cookbooks implements Serializable {
    private String name,//菜谱名
            url,//菜谱地址
            imgurl;//图片地址
    @Override
    public String toString() {
        return "Cookbooks{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", imgurl='" + imgurl + '\'' +
                '}';
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Cookbooks(String name, String imgurl,String url) {
        this.name = name;
        this.imgurl = imgurl;
        this.url=url;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

}
