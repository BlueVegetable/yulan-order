package com.yulan.dao;

import com.yulan.pojo.AreaCode;
import com.yulan.pojo.AreaRegion;
import com.yulan.pojo.AreaUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AreaRegionDao {

    AreaRegion getAreaRegionByID(String areaRegionID);

    List<AreaRegion> getProvince();

    List<AreaRegion> getCity(@Param("PARENT_PROVINCE") String parentProvince);

    List<AreaRegion> getCountry(@Param("PARENT_CITY_ID") String parentCityID);

    /**
     * 根据业务员ID得到管理的片区代码
     * @param userID
     * @return
     */
    List<AreaUser> getAreaUserCode(@Param("USERID") String userID);

    /**
     * 这里面的string是List<AreaUser> getAreaUserCode中的areaUser.getCode()
     * @param list
     * @return
     */
    List<AreaCode> getAreaCodeName(@Param("list") List<String> list);
}
