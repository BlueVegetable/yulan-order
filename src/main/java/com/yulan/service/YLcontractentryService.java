package com.yulan.service;

import com.yulan.pojo.YLcontract_v2015;
import com.yulan.pojo.YLcontractentry;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface YLcontractentryService {
    Map<String, Object> showStateEchartYCl(String year);

    Map getYClbyStateandCID(Integer start,Integer number,String year) throws UnsupportedEncodingException;

    List<Map<String,Object>> getAllStates();

    YLcontract_v2015 getYLcontract_v2015(String ccid)throws IOException;

    boolean createYLcontract_v2015(YLcontract_v2015 yLcontract_v2015)throws IOException;

    boolean updateYLcontract_v2015(YLcontract_v2015 yLcontract_v2015)throws IOException;

    String getYLcontractHTML(String cid)throws IOException;

    Map<String,Object> getYLcontractAPP(String cid)throws IOException;

    Map getAllYlcs(Integer start,Integer number,String signed,Integer year,String cid) throws UnsupportedEncodingException;

    YLcontractentry getYLcontractentry(String cid) throws IOException;

    YLcontractentry getYLcontractentryByYear(String cid,Integer cyear) throws IOException;

    //联表获取协议书
    Map getYlcsbySigned(Integer start,Integer number,Integer year,String cid,String area_1,String area_2,String find,String need,String position,Integer legalchecked) throws UnsupportedEncodingException;

    List<Map<String,Object>> getAllYLcontractentryState(String year);

    List<Map<String,Object>> getYLcontractentryStateByArea(String year)throws IOException;
}
