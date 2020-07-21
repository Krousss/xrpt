package com.xrpt.demo.service;

import com.xrpt.demo.entity.Favorite;

import java.util.List;

/**
 * @author by lsz
 * @date 2020/7/19
 * @DESC: 菜谱收藏service层
 */
public interface FavoriteService {
    /**
     * @author by lsz
     * @date 2020/7/19
     * @DESC: 收藏菜谱
     */
    int addFavoriteCookBook(String url,String img,String name,int uid);
    /**
     * @author by lsz
     * @date 2020/7/19
     * @DESC: 取消收藏
     */
    int deleteFavoriteCookBook(String url,int id);
    /**
     * @author by lsz
     * @date 2020/7/19
     * @DESC: 返回所有收藏的菜谱
     */
    List<Favorite> queryAllFavoriteCookBook(int id);
    /**
     * @author by lsz
     * @date 2020/7/19
     * @DESC: 返回一条收藏的菜谱，作为校验
     */
    Favorite queryOneFavoriteBook(String url, int id);
}
