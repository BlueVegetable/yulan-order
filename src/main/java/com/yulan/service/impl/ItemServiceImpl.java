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
                item.setItemVersion(stringUtil.getUtf8(itemDao.getProductVersion(item.getItemVersion())));
                item.setProductBrand(stringUtil.getUtf8(itemDao.getProductBrand(item.getProductBrand())));
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
    public Map getSoftDecorationInfo(String itemType){
        Map<String,Object> map = new HashMap<>();
        List<Item> itemList = new ArrayList<>();
        if(itemType.equals("ML")){
            itemList = itemDao.getMLInfo();

        }else if(itemType.equals("XHB")){
            itemList = itemDao.getXHBInfo();
        }else if(itemType.equals("PJB")){

        }else if(itemType.equals("BZ")){

        }else if(itemType.equals("GH")){

        }else if(itemType.equals("TC")){

        }else if(itemType.equals("other")){

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


}
