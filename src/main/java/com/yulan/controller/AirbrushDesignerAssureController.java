package com.yulan.controller;

import com.yulan.service.AirbrushDesignerAssureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("AirbrushDesignerAssure")
public class AirbrushDesignerAssureController {
    @Autowired
    private AirbrushDesignerAssureService airbrushDesignerAssureService;

    /**
     * 获取委托喷绘书接口
     * @param data
     * @return
     */
    @RequestMapping(value = "getAirbrushDesignerAssure")
    @ResponseBody
    public Map getAirbrushDesignerAssure(@RequestBody Map<String,Object> data){
        String  createTs =(String)data.get("createTs");
        String cid = (String)data.get("cid");
        String state = (String)data.get("state");
        return airbrushDesignerAssureService.getAirbrushDesignerAssure(cid,createTs,state);
    }

}
