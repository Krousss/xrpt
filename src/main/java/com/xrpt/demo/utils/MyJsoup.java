package com.xrpt.demo.utils;

import com.xrpt.demo.entity.Cookbooks;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Component
public class MyJsoup {
    /**
     * 每日推荐获取菜谱
     * 作者：林颂忠
     * */
    public List<Cookbooks> getCookBooks(){
        List<Cookbooks> data = new ArrayList<>();//返回数据
        try {
            Document doc = Jsoup.connect(Constans.COOKBOOK_URL).get();
            Elements cookname = doc.getElementsByTag("div").select("a[class=cookname text-lips]");
            Elements urls = doc.select("a[class=cover]");
            Elements imgurl = doc.getElementsByAttributeValue("class","cover").select("img[src~=^((?!video_icon.png).)*$]");
            for (int i=0;i<cookname.size();i++){
                data.add(new Cookbooks(cookname.get(i).text(),imgurl.get(i).attr("src"),
                        Constans.BASE_URL+urls.get(i).attr("href")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    /**
     * 每日推荐搜索菜谱
     * 作者：林颂忠
     * */
    public List<Cookbooks> searchCookBooks(String query){
        List<Cookbooks> data = new ArrayList();//返回数据
        try {
            Document doc = Jsoup.connect(Constans.SEARCH_URL+query).get();
            Elements name =doc.select("a[class=cookname text-lips]");
            Elements imgurl = doc.
                    select("a[class=cook-img]").
                    select("img[height=125]");//src~=(?i)\.(jpg|jpe?g)这个正则表达式不够严谨，干脆用匹配宽高

            Elements urls =doc.select("a[class=cook-img]");
            for (int i=0;i<name.size();i++){
                data.add(new Cookbooks(name.get(i).text(),
                        imgurl.get(i).attr("src"),
                        Constans.BASE_URL+urls.get(i).attr("href")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    /**
     * 返回菜谱详情，过滤完数据的
     * 作者：林颂忠
     * */
    public Document cookBookContent(String url){
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            doc.select("ul[class=art-list]").remove();
            doc.select("div[class=cght]").remove();
            doc.select("div[class=foot]").remove();
            doc.select("div[class=copyright]").remove();
            doc.select("div[class=addmenu]").remove();
            doc.select("div[class=note clearfix]").remove();
            doc.select("div[class=note artmod]").remove();
            doc.select("div[class=bot-list]").remove();
            doc.select("div[class=artmod]").remove();
            doc.select("div[class=clearfix left]").remove();
            doc.select("div[class=vcnum relative]").remove();
            doc.select("p[class=fcc text-lips]").remove();
            doc.getElementById("right").remove();
            doc.select("script").remove();
            doc.getElementById("comment").remove();
            doc.getElementById("header").remove();
            doc.getElementById("footer").remove();
            doc.getElementById("create_menu").remove();
            doc.getElementById("coll-box").remove();
            doc.getElementById("module").remove();
            return doc;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        MyJsoup jsoup=new MyJsoup();
         System.out.println(jsoup.searchCookBooks("黄焖鸡"));
    }
}
