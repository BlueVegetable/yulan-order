package com.yulan.service.impl;

import com.yulan.dao.AreaDistrictDao;
import com.yulan.pojo.AreaDistrict;
import com.yulan.service.AreaDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaDistrictServiceImpl implements AreaDistrictService {
    @Autowired
    private AreaDistrictDao areaDistrictDao;
    @Override
    public AreaDistrict getAreaDistrictByDistrictID(String districtID) {
        return areaDistrictDao.getAreaDistrictByDistrictID(districtID);
    }

    @Override
    public AreaDistrict getAreaDistrictByName(String districtName) {
        return areaDistrictDao.getAreaDistrictByName(districtName);
    }
}
