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

}
