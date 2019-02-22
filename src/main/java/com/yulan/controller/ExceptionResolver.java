package com.yulan.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 异常处理器
 */
public class ExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        Map result = new LinkedHashMap(2);
        String msg = "";
        result.put("code",1);
        //设置状态码
        response.setStatus(HttpStatus.OK.value());
        //设置ContentType
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        //避免乱码
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        ex.printStackTrace();
        File file = Paths.get("C:\\玉兰异常错误.txt").toFile();
        try {
            Files.deleteIfExists(Paths.get("C:\\玉兰异常错误.txt"));
            file.createNewFile();
            Writer writer = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(writer);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
            bw.append(simpleDateFormat.format(new Date(System.currentTimeMillis()))+"\r\n");
            StackTraceElement[] errors = ex.getStackTrace();
            for (StackTraceElement error:errors) {
                bw.append(error.toString()+"\r\n");
            }
            bw.append("\r\n");
            bw.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (ex instanceof SQLException) {
            msg = "数据库访问出错";
        } else {
            msg = "服务器发生错误";
        }
        result.put("msg",msg);
        try {
            response.getWriter().println(JSONObject.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
}