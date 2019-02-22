package com.yulan.dao;

import com.yulan.pojo.AreaDistrict;

public interface AreaDistrictDao {
    AreaDistrict getAreaDistrictByDistrictID(String districtID);
    AreaDistrict getAreaDistrictByName(String districtName);
}