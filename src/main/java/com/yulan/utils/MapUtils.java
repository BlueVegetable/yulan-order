package com.yulan.utils;

import com.google.common.collect.Maps;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.springframework.cglib.beans.BeanMap;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    /**
     * 将实体类转为map
     * @param obj
     * @return
     */

    public static Map<String, Object> beanToMaplin(Object obj) {
        Map<String, Object> params = new HashMap<String, Object>(0);
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!"class".equals(name)) {
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }



    /**
     * error 在转化为json时报错
     * javaBean 转 Map
     * 将对象装换为map
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key+"", beanMap.get(key));
            }
        }
        return map;
    }


    /**
     *
     * @param map
     *            需要转换的map
     *            目标javaBean的类对象
     * @return 目标类object
     * @throws Exception
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> class1) {
        T bean = null;
        try {
            bean = class1.newInstance();
            ConvertUtils.register(new org.apache.commons.beanutils.converters.DateConverter(null), java.util.Date.class);
            ConvertUtils.register(new BigDecimalConverter(BigDecimal.valueOf(-1)), java.math.BigDecimal.class);//当BigDecimal为null时转为-1
            BeanUtilEx.populate(bean,map);
         //   BeanUtils.populate(bean, map);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

}
