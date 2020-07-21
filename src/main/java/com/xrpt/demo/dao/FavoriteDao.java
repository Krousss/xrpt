package com.xrpt.demo.dao;

import com.xrpt.demo.entity.Favorite;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author by lsz
 * @date 2020/7/19
 * @DESC: 订单Dao
 */
@Mapper
@Repository
public interface FavoriteDao {
    int addFavoriteCookBook(Favorite favorite);
    int deleteFavoriteCookBook(String url,int id);
    List<Favorite> queryAllFavoriteCookBook(int id);
    Favorite queryOneFavoriteBook(String url,int id);
}
