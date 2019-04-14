package com.yulan.service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface CurtainOrderService {

    /**
     * 窗帘审核提交
     * @param map
     * @return
     */
    Map insertCurtain(Map map) throws UnsupportedEncodingException, InvocationTargetException, IllegalAccessException;

    /**
     * 窗帘审核 修改/退回
     * @param map
     * @return
     */
    Map updateCurtainOrder(Map map) throws UnsupportedEncodingException, InvocationTargetException, IllegalAccessException;

    /**
     * 窗帘审核 通
     * @param map
     * @return
     */
    Map updateCurOrderStatus(Map map);


}
