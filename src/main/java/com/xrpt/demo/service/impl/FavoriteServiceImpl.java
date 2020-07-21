package com.xrpt.demo.service.impl;

import com.xrpt.demo.dao.FavoriteDao;
import com.xrpt.demo.entity.Favorite;
import com.xrpt.demo.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by lsz
 * @date 2020/7/19
 * @DESC:FavoriteService实现层
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteDao favoriteDao;
    @Autowired
    private Favorite favorite;
    @Override
    public int addFavoriteCookBook(String url,String img,String name, int uid) {
        favorite.setFname(name);
        favorite.setImg(img);
        favorite.setFurl(url);
        favorite.setUid(uid);
        return favoriteDao.addFavoriteCookBook(favorite);
    }

    @Override
    public int deleteFavoriteCookBook(String url, int id) {
        return favoriteDao.deleteFavoriteCookBook(url,id);
    }

    @Override
    public List<Favorite> queryAllFavoriteCookBook(int id) {
        return favoriteDao.queryAllFavoriteCookBook(id);
    }

    @Override
    public Favorite queryOneFavoriteBook(String url, int id) {
        return favoriteDao.queryOneFavoriteBook(url,id);
    }
}
