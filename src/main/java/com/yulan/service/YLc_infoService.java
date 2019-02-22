package com.yulan.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface YLc_infoService {
    Map getAllYLc_info(Integer start, Integer number, String year,
                       String info_state,String ylc_state,String find) throws UnsupportedEncodingException;

    Map getAllinfo(Integer start, Integer number, String year,
                   String info_state,String find,String cid,String area_1,String area_2) throws UnsupportedEncodingException;

    //新的获取资料卡和网签列表
    Map getInfoandylc(Integer start, Integer number,
                      String find,String year,
                      String infoStat,String ylcState) throws UnsupportedEncodingException;
}
