package com.xrpt.demo.controller;

import com.xrpt.demo.entity.Cookbooks;
import com.xrpt.demo.entity.Favorite;
import com.xrpt.demo.service.CookBooksService;
import com.xrpt.demo.service.FavoriteService;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LifeCenterController {
    @Autowired
    private CookBooksService cookBooksService;
    @Autowired
    private FavoriteService favoriteService;
    @RequestMapping("/lifeCenter")
    public ModelAndView lifecenter(){
        List<Cookbooks> cookbooks=cookBooksService.getCookBooks();
        ModelAndView model = new ModelAndView();
        model.setViewName("Life/LifeCenter");
        model.addObject("cookbooks",cookbooks);
        return model;
    }
    @RequestMapping("/cookbook_detail")
    public String cookbookContent(String id){
        String index="";
        Document doc= (Document) cookBooksService.cookBookContent(id);
        index+=doc;
        return index;
    }
    @RequestMapping("/search")
    public ModelAndView lifecenter(String query){
        List<Cookbooks> cookbooks=cookBooksService.searchCookBooks(query);
        ModelAndView model = new ModelAndView();
        model.setViewName("Life/LifeCenter");
        model.addObject("cookbooks",cookbooks);
        return model;
    }
    @RequestMapping("/collect")
    public String collectCookBook(String url,String img,String name,String uid){
        System.out.println(url+img+name+uid);
        int UID=Integer.parseInt(uid);
        Favorite flag= favoriteService.queryOneFavoriteBook(url,UID);
        if (flag!=null){
            return "issues";
        }else {
            int state=favoriteService.addFavoriteCookBook(url,img,name,UID);
            if (state!=0){
                return "true";
            }else {
                return "false";
            }
        }
    }
    @PostMapping("/myfavorite")
    public Map<String,Object> myFavorite(String id){

        int UID=Integer.parseInt(id);
        List<Favorite> favorites=favoriteService.queryAllFavoriteCookBook(UID);
        Map<String,Object> favoriteMap=new HashMap<>();
        favoriteMap.put("favoriteMap",favorites);
        return favoriteMap;
    }
    @RequestMapping("/deleteFavorite")
    public String deleteFavorite(String url,String id){
        System.out.println(id + url);
        int UID=Integer.parseInt(id);
        int index=favoriteService.deleteFavoriteCookBook(url,UID);
        if(index==1){
            return "true";
        }else {
            return "false";
        }
    }
}
