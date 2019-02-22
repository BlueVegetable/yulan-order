package com.yulan.controller;

import com.yulan.service.Area_ownerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@RequestMapping("Area_owner")
public class Area_ownerController {
    @Autowired
    private Area_ownerService area_ownerService;

    @RequestMapping("getArea_owners")
    @ResponseBody
    public Map<String,Object> getwebcontractwfmember(@RequestParam(name = "limit", required = false) Integer limit,
                                                     @RequestParam(name = "page", required = false) Integer page,

                                                     @RequestParam(name="owner",required = false) String owner) throws UnsupportedEncodingException {
        if(limit==null||page==null) {
            page=null;
            limit=null;
        } else {
            page=(page-1)*limit+1;
        }
        int lastNum=page+limit-1;
        Map map = area_ownerService.getebcontractwfmemberbyYear(page,lastNum,owner);
        map.put("code",0);
        map.put("msg","");
        return map;
    }
}
