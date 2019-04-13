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

    /**
     * 加入购物车时的库存判断
     * @param data
     * @return
     */
    @RequestMapping("judgeStockShow")
    @ResponseBody
    public Map judgeStockShow(@RequestBody Map<String,Object> data){
        String itemNo = (String)data.get("itemNo");
        String stockShow = (String) data.get("stockShowNum");
        Double stockShowNum = Double.parseDouble(stockShow);
        return itemService.judgeStockShow(stockShowNum,itemNo);
    }

    /**
     * 获得窗帘的所有类型
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "getCurtainType")
    @ResponseBody
    public Map getCurtainType(@RequestBody Map<String,Object> data)throws IOException{
        Integer limit = (Integer) data.get("limit");
        Integer page = (Integer)data.get("page");
        if(limit==null||page==null) {
            page=null;
            limit=null;
        } else {
            page=(page-1)*limit+1;
        }
        int lastNum=page+limit-1;
       return itemService.getCurtainType(page,lastNum);
    }

    /**
     * 窗帘模糊查询
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "getCurtainBySearch")
    @ResponseBody
    public Map getCurtainBySearch(@RequestBody Map<String,Object> data)throws IOException{
        Integer limit = (Integer) data.get("limit");
        Integer page = (Integer)data.get("page");
        String itemNo = (String)data.get("itemNo");
        if(limit==null||page==null) {
            page=null;
            limit=null;
        } else {
            page=(page-1)*limit+1;
        }
        int lastNum=page+limit-1;
        return itemService.getCurtainTypeBySearch(page,lastNum,itemNo);
    }

    /**
     * 获取单个类型窗帘的所有信息
     * @param data
     * @return
     */
    @RequestMapping(value = "getCurtainInfo")
    @ResponseBody
    public Map getCurtainInfo(@RequestBody Map<String,Object> data)throws IOException{
        String itemNO = (String)data.get("itemNO");
        //成品宽度
        Double width =  Double.valueOf((String)data.get("width"));
        //成品高度
        Double height  = Double.valueOf((String)data.get("height"));
        //帘头外包盒宽度
        Double WBH =  Double.valueOf((String)data.get("WBH"));
        //褶皱倍数
        Double multiple =  Double.valueOf((String)data.get("multiple"));
        String location = (String)data.get("location");

        return  itemService.getCurtainInfo(width,height,WBH,multiple,location,itemNO);
    }

    /**
     * 更改窗帘产品下的单个产品型号，更改变量
     * @param data
     * @return
     */
    @RequestMapping(value = "changeCurtainItem")
    @ResponseBody
    public Map changeCurtainItem(@RequestBody Map<String,Object> data) throws IOException{
        String itemNO = (String) data.get("itemNO");
        String itemType = (String) data.get("itemType");
        String parentItemNo = (String) data.get("parentItemNo");
        //成品宽度
        Double width = Double.valueOf((String) data.get("width"));
        //成品高度
        Double height = Double.valueOf((String) data.get("height"));
        //帘头外包盒宽度
        Double WBH = Double.valueOf((String) data.get("WBH"));
        //褶皱倍数
        Double multiple = Double.valueOf((String) data.get("multiple"));

        return itemService.changeCurtainItem(width, height, WBH, multiple, itemNO, itemType, parentItemNo);
    }

    /**
     * 获取可以更换的GY类型
     * @param data
     * @return
     */
    @RequestMapping(value = "getGYList")
    @ResponseBody
    public Map getGYList(@RequestBody Map<String,Object> data){
        String itemNO = (String)data.get("itemNO");
        return itemService.getGYList(itemNO);
    }

}
