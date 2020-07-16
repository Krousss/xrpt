package com.xrpt.demo.service.impl;

import com.xrpt.demo.entity.Cookbooks;
import com.xrpt.demo.service.CookBooksService;
import com.xrpt.demo.utils.MyJsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CookBooksServiceImpl implements CookBooksService {
    @Autowired
    private MyJsoup jsoup;
    @Override
    public List<Cookbooks> getCookBooks() {
        return jsoup.getCookBooks();
    }

    @Override
    public List<Cookbooks> searchCookBooks(String query) {
        return jsoup.searchCookBooks(query);
    }

    @Override
    public Object cookBookContent(String url) {
        return jsoup.cookBookContent(url);
    }

}
