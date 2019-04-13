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


}
