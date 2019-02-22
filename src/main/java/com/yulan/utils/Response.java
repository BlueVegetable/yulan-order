package com.yulan.utils;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Response {
    public static void sendJSONObject(Object object, HttpServletResponse response) throws IOException {
        JSONObject json= JSONObject.fromObject(object);
        write(json,response);
    }
    public static void sendJSONObject(Map<String,Object> map , HttpServletResponse response) throws IOException {
        JSONObject json= JSONObject.fromObject(map);
        write(json,response);
    }
    public static void sendJSONArray(Object array, HttpServletResponse response) throws IOException {
        JSONArray jsonArray = JSONArray.fromObject(array);
        write(jsonArray,response);
    }
    public static void writeString(String value, HttpServletResponse response) throws IOException {
        write(value,response);
    }
    private static void write(JSON json, HttpServletResponse response) throws IOException {
        PrintWriter writer=response.getWriter();
        writer.println(json);
        writer.flush();
        writer.close();
    }
    private static void write(String value, HttpServletResponse response) throws IOException {
        PrintWriter writer=response.getWriter();
        writer.println(value);
        writer.flush();
        writer.close();
    }
    public static Map<String,Object> getResponseMap(int code,String msg,Object data) {
        Map<String,Object> responseMap=new HashMap<>(3);
        responseMap.put("code",code);
        responseMap.put("msg",msg);
        responseMap.put("data",data);
        return responseMap;
    }
}
