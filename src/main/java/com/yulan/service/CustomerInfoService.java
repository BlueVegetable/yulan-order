package com.yulan.service;

import com.yulan.pojo.CustomerInfoCard;
import com.yulan.pojo.YLcontract_v2015_paa;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface CustomerInfoService {

    CustomerInfoCard getCustomerInfo(String cID)throws IOException;

    CustomerInfoCard getCustomerInfoByYear(String cID,Integer year) throws IOException;

    Map<String, Object> showStateEchart(String year);

    Map getInfobyStateandmarketName(Integer start,Integer number,String year) throws UnsupportedEncodingException;

    List<Map<String,Object>> getAllStates();


    //获取委托书
    YLcontract_v2015_paa getYLcontract(String ccid, Integer ccyear) throws IOException;

    //   boolean customer(String cID);
    // 更新客户资料卡
    Map updateCustomerInfo(CustomerInfoCard customerInfoCard)throws IOException;

    boolean createYLcontract(YLcontract_v2015_paa yLcontract_v2015_paa) throws IOException;

    boolean updateYLcontract(YLcontract_v2015_paa yLcontract_v2015_paa) throws IOException;

    String getXAreaDistrictName(String getXAreaDistrict3Name)throws IOException;

    String getXDistrict(String xDistrict)throws IOException;

    List<Map> getAllStatisticsInfo(String userCID,String userCName,String managerCID);

    List<Map<String,Object>> getAllCustomerInfoCardState(String year);

    List<Map<String,Object>> getCustomerInfoCardStateByArea(String year)throws IOException;

    //获取登录用户管理地区
    List<Map<String,Object>> getUserArea(String cid,String position) throws UnsupportedEncodingException;

    //获取登录用户管理资料卡
    Map getUserCustomerinfo(Integer start,Integer number,Integer year,String cid,String area_1,String area_2,String find,String state,String position,String ylcstate,Integer legalchecked) throws UnsupportedEncodingException;


}
