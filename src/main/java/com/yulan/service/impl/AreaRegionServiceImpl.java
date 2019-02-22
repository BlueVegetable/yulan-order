package com.yulan.service.impl;

import com.yulan.dao.AreaRegionDao;
import com.yulan.pojo.AreaRegion;
import com.yulan.service.AreaRegionService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AreaRegionServiceImpl implements AreaRegionService {
    @Autowired
    private AreaRegionDao areaRegionDao;

    private StringUtil stringUtil;

    @Override
    public AreaRegion getAreaRegionByID(String areaRegionID) {
        return areaRegionDao.getAreaRegionByID(areaRegionID);
    }

    @Override
    public List<AreaRegion> getProvince() throws IOException {
        List<AreaRegion> areaList = areaRegionDao.getProvince();
        for(AreaRegion a : areaList){
            String provinceName = stringUtil.getUtf8(a.getRegionName());
            a.setRegionName(provinceName);
        }
        return areaList;
    }

    @Override
    public List<AreaRegion> getCity(String parentProvince) throws IOException {
        List<AreaRegion> areaList = areaRegionDao.getCity(parentProvince);
        for(AreaRegion a : areaList){
            String provinceName = stringUtil.getUtf8(a.getRegionName());
            a.setRegionName(provinceName);
        }
        return areaList;
    }

    @Override
    public List<AreaRegion> getCountry(String parentCityID) throws IOException {
        List<AreaRegion> areaList = areaRegionDao.getCountry(parentCityID);
        for(AreaRegion a : areaList){
            String provinceName = stringUtil.getUtf8(a.getRegionName());
            a.setRegionName(provinceName);
        }
        return areaList;
    }
}
