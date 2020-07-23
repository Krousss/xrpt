package com.xrpt.demo.service.impl;

import com.xrpt.demo.dao.LocationDao;
import com.xrpt.demo.entity.Location;
import com.xrpt.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by sxy
 * @date 2020/7/22
 * @DESC:LocationService实现层
 */
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationDao locationDao;

    @Override
    public List<Location> queryLocation(){
        return locationDao.queryLocation();
    }

    @Override
    public Location queryOneLocation(String content) {
        return locationDao.queryOneLocation(content);
    }

}
