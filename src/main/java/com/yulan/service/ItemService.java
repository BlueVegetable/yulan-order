package com.yulan.service;

import com.yulan.pojo.Item;

import java.io.IOException;
import java.util.Map;

public interface ItemService {

    Item getItemByItemNO(String itemNO);

    Map getWallpaperInfo(String cid ,String paperType)throws IOException;

    Map getStockShow(String itemNo);

    Map getSoftDecorationInfo(String itemType, String cid, Integer page, Integer lastNum)throws IOException;

    Map getSoftInfoSingle(String itemType, String cid, String itemNo, Integer page, Integer lastNum)throws IOException;

    Map judgeStockShow(Double stockShowNum, String itemNo);

    Map getCurtainType(Integer page,Integer lastNum)throws IOException;

    Map getCurtainTypeBySearch(Integer page,Integer lastNum, String itemNo)throws IOException;

    Map getCurtainInfo(Double width,Double height,Double WBH,Double multiple,String location,String curtainNo)throws IOException;

    Map changeCurtainItem(Double width,Double height,Double WBH,Double multiple,String itemNO, String itemType, String parentItemNo, String fixType) throws IOException;

    Map getCurtainItemTypeAll(Integer page,Integer lastNum, String itemNO)throws IOException;

    Map searchCurtainItemTypeAll(Integer page,Integer lastNum, String itemType, String itemNO)throws IOException;

    Map getGYList(String itemNO);
}
