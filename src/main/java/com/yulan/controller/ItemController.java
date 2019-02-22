package com.yulan.controller;

import com.yulan.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * 获取墙纸信息接口
     * @param data
     * @return
     */
    @RequestMapping(value = "getWallpaperInfo")
    @ResponseBody
    public Map getWallpaperInfo(@RequestBody Map<String,Object> data)throws IOException {
        String paperType = (String)data.get("paperType");
        String cid = (String)data.get("cid");
        return itemService.getWallpaperInfo(cid, paperType);
    }

    /**
     * 客户墙纸库房查询接口
     * @param data
     * @return
     */
    @RequestMapping(value = "getStockShow")
    @ResponseBody
    public Map getStockShow(@RequestBody Map<String,Object> data){
        String itemNo = (String)data.get("itemNo");
        return itemService.getStockShow(itemNo);
    }

    /**
     * 软装总信息查询接口
     * @param data
     * @return
     */
    @RequestMapping(value = "getSoftDecorationInfo")
    @ResponseBody
    public Map getSoftDecorationInfo(@RequestBody Map<String,Object> data){
        Map map = new HashMap();
        String itemType = (String)data.get("itemType");

        return map;
    }



}
