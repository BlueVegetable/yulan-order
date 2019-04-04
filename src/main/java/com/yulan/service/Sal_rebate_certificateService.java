package com.yulan.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface Sal_rebate_certificateService {
    /**
     * 获取用户优惠券
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     */
    Map getRebate(Map<String,Object> map) throws UnsupportedEncodingException;

    /**
     * 获取优惠券返利集合
     * @param map
     * @return
     */
    Map getReturnRecord(Map<String,Object>map) throws UnsupportedEncodingException;

}
