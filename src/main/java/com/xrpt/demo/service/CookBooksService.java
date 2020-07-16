package com.xrpt.demo.service;

import com.xrpt.demo.entity.Cookbooks;

import java.util.List;

public interface CookBooksService {
    public List<Cookbooks> getCookBooks();
    public List<Cookbooks> searchCookBooks(String query);
    public Object cookBookContent(String url);
}
