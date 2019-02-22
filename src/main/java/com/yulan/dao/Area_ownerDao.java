package com.yulan.dao;

import com.yulan.pojo.Area_owner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Area_ownerDao {

    /**
     * 通过地区代码获取地区所有者列表
     * @param areaCode
     * @return
     */
    List<Area_owner> getAreaOwnerByAreaCode(@Param("areaCode")String areaCode);

    //获取所有Area_owner集合
    List<Area_owner> getArea_owners(@Param("start")Integer start, @Param("number") Integer number, @Param("owner") String owner);

    //统计
    int count(@Param("owner") String owner);

    //获取市场
    String getArea_code(String area_code);

    //获取区域
    String getArea(String area);

    //获取用户名
    String getOwnerName(String owner);

    //获取区域经理名
    String getManageName(String center_manager);

    //是否


}
