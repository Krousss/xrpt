package com.xrpt.demo.controller;

import com.xrpt.demo.entity.Cookbooks;
import com.xrpt.demo.entity.Favorite;
import com.xrpt.demo.entity.User;
import com.xrpt.demo.service.CookBooksService;
import com.xrpt.demo.service.FavoriteService;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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
    public String collectCookBook(String url, String img, String name, HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        Favorite flag= favoriteService.queryOneFavoriteBook(url,currentUser.getUid());
        if (flag!=null){
            return "issues";
        }else {
            int state=favoriteService.addFavoriteCookBook(url,img,name,currentUser.getUid());
            if (state!=0){
                return "true";
            }else {
                return "false";
            }
        }
    }

    @PostMapping("/myfavorite")
    public Map<String,Object> myFavorite(HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        List<Favorite> favorites=favoriteService.queryAllFavoriteCookBook(currentUser.getUid());
        Map<String,Object> favoriteMap=new HashMap<>();
        favoriteMap.put("favoriteMap",favorites);
        return favoriteMap;
    }

    @RequestMapping("/deleteFavorite")
    public String deleteFavorite(String url,HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        int index=favoriteService.deleteFavoriteCookBook(url,currentUser.getUid());
        if(index==1){
            return "true";
        }else {
            return "false";
        }
    }
}
