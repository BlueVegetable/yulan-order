package com.yulan.service;

import java.io.IOException;
import java.util.Map;

public interface ItemService {

    Map getWallpaperInfo(String cid ,String paperType)throws IOException;

    Map getStockShow(String itemNo);

    Map getSoftDecorationInfo(String itemType);


}
