package com.xrpt.demo.service;

import com.xrpt.demo.entity.Location;

import java.util.List;

/**
 * @author by sxy
 * @date 2020/7/22
 * @DESC: 驿站service层
 */
public interface LocationService {

    /**
     * @author by sxy
     * @date 2020/7/22
     * @DESC: 查询所有驿站
     */
    List<Location> queryLocation();

    /**
     * @author by sxy
     * @date 2020/7/22
     * @DESC: 通过驿站名获取驿站对象
     */
    Location queryOneLocation(String content);
}
