package com.yulan.dao;

import com.yulan.pojo.YLcontract_v2015;
import com.yulan.pojo.YLcontractentry;

import com.yulan.pojo.YLcontractentryShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface YLcontractentryDao {
    List<Map<String,Object>> getYLcbyStateandCID(@Param("start")Integer start, @Param("number") Integer number,@Param("year")String year);

    List<Map> getYLcBySate(@Param("year")String year);

    String getArea_code(String CID);//关联customer

    String getAreaName(String area_code);//关联Area_code

    String getCenter_mangerId(@Param("area_code")String area_code);//关联Area_owner

    String getName(String owner);//HES_EMPLOYEE

    int count(@Param("year")String year);

    List<Map<String,Object>> getAllStates();

    //获取状态
    String getState(@Param("CID")String CID,@Param("year")Integer year,@Param("state")String state);


    YLcontract_v2015 getYLcontract_v2015(@Param("CCID") String ccid);

    YLcontract_v2015 getYLcontract_v2015ByYear(@Param("CCID") String ccid,@Param("CCYEAR") Integer ccyear);

    boolean createYLcontract_v2015(YLcontract_v2015 yLcontract_v2015);

    boolean createYLcontractentry(YLcontractentry yLcontractentry);

    boolean updateYLcontract_v2015(YLcontract_v2015 yLcontract_v2015);

    boolean updateYLcontractentry(YLcontractentry yLcontractentry);

    YLcontractentryShow getYLcontractHTML(@Param("ID") Integer ID);

    YLcontractentry getYLcontractentry(@Param("CID") String cid);

    YLcontractentry getYLcontractentryByYear(@Param("CID") String cid,@Param("CYEAR") Integer cyear);

    boolean checkYLcontractentry(@Param("CID") String cid,
                                 @Param("STATE") String state,
                                 @Param("WFMEMO") String wfmemo,
                                 @Param("SIGNED") Integer signed,
                                 @Param("market")String market,
                                 @Param("csa")String csa);

    boolean lawCheckYLcontractentryState(@Param("CID") String cid,@Param("STATE") String state,@Param("WFMEMO") String wfmemo,@Param("SIGNED") Integer signed,@Param("LEGALCHECKED")Integer legalchecked);

    List<YLcontractentry> getAllYlcs(@Param("start")Integer start,@Param("number") Integer number,@Param("signed")String signed);
    int countYlcs(@Param("signed")String signed);

    //联表查询获取所需协议书集合，内部审核人员
    List<Map<String,Object>> getAllYs(@Param("start")Integer start,@Param("number") Integer number,
                                     @Param("cid")String cid,
                                      @Param("year")Integer year,@Param("area_1")String area_1,
                                      @Param("area_2")String area_2,@Param("find")String find,
                                      @Param("states")List states);
    int countYs(@Param("cid")String cid,
                @Param("year")Integer year,@Param("area_1")String area_1,
                @Param("area_2")String area_2,@Param("find")String find, @Param("states")List states);

    //销售中心经理联表查询获取所需审核协议书集合
    List<Map<String,Object>> getAllys_area(@Param("start")Integer start,@Param("number") Integer number,
                                           @Param("cid")String cid,
                                           @Param("year")Integer year,@Param("area_1")String area_1,
                                           @Param("area_2")String area_2,@Param("find")String find,
                                           @Param("state")String state,@Param("position")String position);
    int countYs_area(@Param("cid")String cid,
                @Param("year")Integer year,@Param("area_1")String area_1,
                @Param("area_2")String area_2,@Param("find")String find, @Param("state")String state,@Param("position")String position);

    //销售中心经理联表查询获取所需已经审核协议书集合

    //法务员审核
    List<Map<String,Object>> getAllys_areaOver(@Param("start")Integer start,@Param("number") Integer number,
                                           @Param("cid")String cid,
                                           @Param("year")Integer year,@Param("area_1")String area_1,
                                           @Param("area_2")String area_2,@Param("find")String find,
                                           @Param("states")List states,@Param("position")String position);

    List<Map<String,Object>> getAllys_LEGALCHECK(@Param("start")Integer start,@Param("number") Integer number,
                                                 @Param("cid")String cid,@Param("year")Integer year,
                                                 @Param("find")String find,@Param("legalchecked")Integer legalchecked);

    int count_LEGALCHECK(@Param("cid")String cid,@Param("year")Integer year,
                         @Param("find")String find,@Param("legalchecked")Integer legalchecked);

    int countYs_areaOver(@Param("cid")String cid,
                     @Param("year")Integer year,@Param("area_1")String area_1,
                     @Param("area_2")String area_2,@Param("find")String find, @Param("states")List states,@Param("position")String position);


    //协议书执行状态汇总
    List<Map<String,Object>> getAllYLcontractentryState(@Param("CYEAR") String year);

    List<Map<String,Object>> getYLcontractentryStateByArea(@Param("CYEAR") String year);

    List<YLcontractentry> getYLcontractentryLegalChecked(@Param("start") Integer start,
                                                          @Param("number") Integer number,
                                                          @Param("LEGALCHECKED") Integer legalChecked,
                                                          @Param("CYEAR")Integer year,
                                                          @Param("STATE")String state);

    YLcontractentry getYLcontractentryLegalCheckedSingle(@Param("start") Integer start,
                                                         @Param("number") Integer number,
                                                         @Param("LEGALCHECKED") Integer legalChecked,
                                                         @Param("CYEAR")Integer year,
                                                         @Param("CID")String cid,
                                                         @Param("STATE")String state);

    boolean getAPPVersion(@Param("VERSION") String version);

    String getAPPVersionPresent();
}
