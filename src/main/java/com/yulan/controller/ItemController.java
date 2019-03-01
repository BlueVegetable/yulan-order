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
    public Map getSoftDecorationInfo(@RequestBody Map<String,Object> data)throws IOException{
        Map map = new HashMap();
        String cid = (String)data.get("cid");
        String itemType = (String)data.get("itemType");
        Integer limit = (Integer) data.get("limit");
        Integer page = (Integer)data.get("page");
        if(limit==null||page==null) {
            page=null;
            limit=null;
        } else {
            page=(page-1)*limit+1;
        }
        int lastNum=page+limit-1;
        map = itemService.getSoftDecorationInfo(itemType,cid,page,lastNum);
        return map;
    }

    /**
     * 软装单个产品模糊查询
     * @param data
     * @return
     */
    @RequestMapping(value = "getSoftInfoSingle")
    @ResponseBody
    public Map getSoftInfoSingle(@RequestBody Map<String,Object> data)throws IOException{
        Map map = new HashMap();
        String cid = (String)data.get("cid");
        String itemType = (String)data.get("itemType");
        String itemNo = (String)data.get("itemNo");
        Integer limit = (Integer) data.get("limit");
        Integer page = (Integer)data.get("page");
        if(limit==null||page==null) {
            page=null;
            limit=null;
        } else {
            page=(page-1)*limit+1;
        }
        int lastNum=page+limit-1;
        map = itemService.getSoftInfoSingle(itemType,cid,itemNo,page,lastNum);
        return map;
    }



}
