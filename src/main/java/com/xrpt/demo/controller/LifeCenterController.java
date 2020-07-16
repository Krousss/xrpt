package com.xrpt.demo.controller;

import com.xrpt.demo.entity.Cookbooks;
import com.xrpt.demo.service.CookBooksService;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/lifecenter")
public class LifeCenterController {
    @Autowired
    private CookBooksService cookBooksService;
    @RequestMapping("/culling")
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
        System.out.println(id);
        Document doc= (Document) cookBooksService.cookBookContent(id);
        index+=doc;
        return index;
    }
    @RequestMapping("search")
    public ModelAndView lifecenter(String query){
        List<Cookbooks> cookbooks=cookBooksService.searchCookBooks(query);

        System.out.println(query);
        ModelAndView model = new ModelAndView();
        model.setViewName("Life/LifeCenter");
        model.addObject("cookbooks",cookbooks);
        return model;
    }
}
