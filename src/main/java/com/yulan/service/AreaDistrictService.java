package com.yulan.service;

import com.yulan.pojo.AreaDistrict;

public interface AreaDistrictService {
    AreaDistrict getAreaDistrictByDistrictID(String districtID);
    AreaDistrict getAreaDistrictByName(String districtName);
}
