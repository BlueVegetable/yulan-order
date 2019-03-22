package com.yulan.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectUtils {
    /**
     * 递归算法给一个对象的所有字符串为空的成员变量赋值为空串
     * 时间为：2千万条61秒84毫秒
     * @param o 待处理对象
     */
    public static void setNullToNone(Object o) {
        if(o == null) {
            return ;
        }
        Class cl = o.getClass();
        Field[] fields = cl.getDeclaredFields();
        for (Field field:fields) {
            try {
                String type = field.getGenericType().toString();
                int location = type.lastIndexOf(".");
                type = type.substring(location+1);
                if(type.equals("String")) {
                    String fieldName = field.getName();
                    fieldName = Character.toUpperCase(fieldName.charAt(0))+fieldName.substring(1);
                    Method getMethod = cl.getDeclaredMethod("get"+fieldName);
                    getMethod.setAccessible(true);
                    Object value = getMethod.invoke(o,null);
                    if(value == null) {
                        Method setMethod = cl.getDeclaredMethod("set"+fieldName,String.class);
                        setMethod.setAccessible(true);
                        setMethod.invoke(o,"");
                    }
                } else {
                    String fieldName = field.getName();
                    fieldName = Character.toUpperCase(fieldName.charAt(0))+fieldName.substring(1);
                    Method getMethod = cl.getDeclaredMethod("get"+fieldName);
                    getMethod.setAccessible(true);
                    Object inlineObject = getMethod.invoke(o,null);
                    setNullToNone(inlineObject);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
