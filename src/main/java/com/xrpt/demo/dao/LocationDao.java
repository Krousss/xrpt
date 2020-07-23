package com.xrpt.demo.dao;

import com.xrpt.demo.entity.Location;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LocationDao {

    /**
     * @author by sxy
     * @date 2020/7/22
     * @DESC: 用于查询驿站名
     */
    List<Location> queryLocation();

    /**
     * @author by sxy
     * @date 2020/7/22
     * @DESC: 用驿站名获取驿站对象
     */
    Location queryOneLocation(String content);
}
