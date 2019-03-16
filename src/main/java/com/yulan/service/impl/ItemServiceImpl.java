package com.yulan.service.impl;

import com.yulan.dao.ItemDao;
import com.yulan.pojo.Item;
import com.yulan.pojo.StockShow;
import com.yulan.service.ItemService;
import com.yulan.utils.MapUtils;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;

    private StringUtil stringUtil;

    private MapUtils mapUtils;

    @Override
    public Item getItemByItemNO(String itemNO) {
        return itemDao.getItemByItemNO(itemNO);
    }

    @Override
    public Map getWallpaperInfo(String cid, String paperType) throws IOException {
        Map<String,Object> map = new HashMap<>();
        Item item = new Item();
        item = itemDao.getWallpaperInfo(paperType);
        if (item != null) {
            if(itemDao.userBrandAuthority(cid,item.getItemNo()) == null ||itemDao.userBrandAuthority(cid,item.getItemNo()).equals("")){
                map.put("data","没有查询权限");
                map.put("code",1);
            }else{
                item.getItemType().setNote(stringUtil.getUtf8(item.getItemType().getNote()));
                item.setUnit(stringUtil.getUtf8(itemDao.getUnit(item.getUnit())));
                item.setItemVersion(stringUtil.getUtf8(itemDao.getProductVersion(item.getItemVersion())));
                item.setProductBrand(stringUtil.getUtf8(itemDao.getProductBrand(item.getProductBrand())));
                item.setRzStyle(stringUtil.getUtf8(item.getRzStyle()));
                map.put("data",item);
                map.put("code",0);
            }


        }else{
            map.put("data","没有找到相关产品");
            map.put("code",1);
        }
        return map;
    }

    @Override
    public Map getStockShow(String itemNo) {
        Map<String,Object> map = new HashMap<>();
        List<StockShow> stockList = itemDao.getStockShow(itemNo);
        if(null == stockList || stockList.size() == 0){
            map.put("data","没有查询到数据");
            map.put("code",1);
        }else{
            map.put("data",stockList);
            map.put("code",0);
        }
        return map;
    }

    @Override
    public Map getSoftDecorationInfo(String itemType,String cid, Integer page, Integer lastNum)throws IOException{
        Map<String,Object> map = new HashMap<>();
        List<Item> itemList = new ArrayList<>();
        if(itemType.equals("ML")){
            itemList = itemDao.getMLInfo(cid, page, lastNum);
        }else if(itemType.equals("XHB")){
            itemList = itemDao.getXHBInfo(cid, page, lastNum);
        }else if(itemType.equals("PJB")){
            itemList = itemDao.getPJBInfo(cid, page, lastNum);
        }else if(itemType.equals("BZ")){
            itemList = itemDao.getBZInfo(cid, page, lastNum);
        }else if(itemType.equals("GH")){
            itemList = itemDao.getGHInfo(cid, page, lastNum);
        }else if(itemType.equals("TC")){
            itemList = itemDao.getTCInfo(cid, page, lastNum);
        }else if(itemType.equals("other")){
            itemList = itemDao.getOtherInfo(cid, page, lastNum);
        }

        for(int i=0 ; i<itemList.size() ; i++){
            Item item = itemList.get(i);
            if(null != item.getNote()){
                item.setNote(stringUtil.getUtf8(item.getNote()));
            }
        }

        if(null == itemList || itemList.size() == 0){
            map.put("data","没有查询到数据");
            map.put("code",1);
        }else{
            map.put("data",itemList);
            map.put("code",0);
        }
        return map;
    }

    @Override
    public Map getSoftInfoSingle(String itemType, String cid, String itemNo, Integer page, Integer lastNum) throws IOException {
        Map<String,Object> map = new HashMap<>();
        List<Item> itemList = new ArrayList<>();
        if(itemType.equals("ML")){
            itemList = itemDao.getMLSingle(cid,itemNo, page, lastNum);
        }else if(itemType.equals("XHB")){
            itemList = itemDao.getXHBSingle(cid,itemNo, page, lastNum);
        }else if(itemType.equals("PJB")){
            itemList = itemDao.getPJBSingle(cid,itemNo, page, lastNum);
        }else if(itemType.equals("BZ")){
            itemList = itemDao.getBZSingle(cid,itemNo, page, lastNum);
        }else if(itemType.equals("GH")){
            itemList = itemDao.getGHSingle(cid,itemNo, page, lastNum);
        }else if(itemType.equals("TC")){
            itemList = itemDao.getTCSingle(cid,itemNo, page, lastNum);
        }else if(itemType.equals("other")){
            itemList = itemDao.getOtherSingle(cid,itemNo, page, lastNum);
        }

        for(int i=0 ; i<itemList.size() ; i++){
            Item item = itemList.get(i);
            if(null != item.getNote()){
                item.setNote(stringUtil.getUtf8(item.getNote()));
            }
            if(null != item.getItemVersion()) {
                item.setItemVersion(stringUtil.getUtf8(itemDao.getProductVersion(item.getItemVersion())));
            }
            if(null != item.getProductBrand()) {
                item.setProductBrand(stringUtil.getUtf8(itemDao.getProductBrand(item.getProductBrand())));
            }
            if(null != item.getRzStyle()) {
                item.setRzStyle(stringUtil.getUtf8(item.getRzStyle()));
            }
            if(null != item.getUnit()){
                item.setUnit(stringUtil.getUtf8(itemDao.getUnit(item.getUnit())));
            }

        }

        if(null == itemList || itemList.size() == 0){
            map.put("data","没有查询到数据");
            map.put("code",1);
        }else{
            map.put("data",itemList);
            map.put("code",0);
        }
        return map;
    }

    @Override
    public Map judgeStockShow(Double stockShowNum, String itemNo) {
        Map<String,Object> map = new HashMap<>();
        Double stockShowNumMax = 0.0;
        StockShow stockShow = new StockShow();
        stockShow = itemDao.getStockShowMax(itemNo);
        Integer sumStockShow = itemDao.sumStockShow(itemNo);
        if(null != stockShow) {
            stockShowNumMax = stockShow.getQty();
            if (stockShowNum <= stockShowNumMax) {
                map.put("msg", "SUCCESS");
                map.put("code", 0);
            }else if (stockShowNumMax < stockShowNum && stockShowNum <= sumStockShow){
                    //允许分批出货
                    map.put("msg", "splitShipment");
                    map.put("code", 0);
            }else if(stockShowNum > sumStockShow){
                    //等待制作
                    map.put("msg", "waitForProduction");
                    map.put("code", 0);
                }
            } else{
            //库存为空
            map.put("msg", "null");
            map.put("code", 0);
        }
        return map;
    }

    @Override
    public Map getCurtainType(Integer page,Integer lastNum) {
        Map map = new HashMap();
        List<String> curtainList = itemDao.getCurtainType(page, lastNum);
        map.put("data",curtainList);
        map.put("code",0);
        return map;
    }


}
