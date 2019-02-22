package com.yulan.service;

import com.yulan.pojo.AreaRegion;

import java.io.IOException;
import java.util.List;

public interface AreaRegionService {

    AreaRegion getAreaRegionByID(String areaRegionID);

    List<AreaRegion> getProvince()throws IOException;

    List<AreaRegion> getCity(String parentProvince)throws IOException;

    List<AreaRegion> getCountry(String parentCityID)throws IOException;
}
