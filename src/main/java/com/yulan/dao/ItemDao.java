package com.yulan.dao;

import com.yulan.pojo.Item;
import com.yulan.pojo.StockShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemDao {

    Item getItemByItemNO(String itemNO);

    Item getWallpaperInfo(@Param("paperType") String paperType);

    String getProductVersion(@Param("itemVersion") String itemVersion);

    String getProductBrand(@Param("productBrand") String productBrand);
    //客户品牌查询权限
    String userBrandAuthority(@Param("CID") String cid, @Param("ITEM_NO") String itemNo);

    String getUnit(@Param("unit") String unit);

    List<StockShow> getStockShow(@Param("ITEM_NO")String itemNo);
    //面料
    List<Item> getMLInfo(@Param("cid") String cid, @Param("start")Integer page, @Param("number")Integer lastNum);

    List<Item> getMLSingle(@Param("cid") String cid, @Param("itemNo") String itemNo);
    //花边
    List<Item> getXHBInfo(@Param("cid") String cid, @Param("start")Integer page, @Param("number")Integer lastNum);

    List<Item> getXHBSingle(@Param("cid") String cid, @Param("itemNo") String itemNo);
    //挂带/配件包
    List<Item> getPJBInfo(@Param("cid") String cid, @Param("start")Integer page, @Param("number")Integer lastNum);

    List<Item> getPJBSingle(@Param("cid") String cid, @Param("itemNo") String itemNo);
    //抱枕
    List<Item> getBZInfo(@Param("cid") String cid, @Param("start")Integer page, @Param("number")Integer lastNum);

    List<Item> getBZSingle(@Param("cid") String cid, @Param("itemNo") String itemNo);
    //挂画
    List<Item> getGHInfo(@Param("cid") String cid, @Param("start")Integer page, @Param("number")Integer lastNum);

    List<Item> getGHSingle(@Param("cid") String cid, @Param("itemNo") String itemNo);
    //陶瓷
    List<Item> getTCInfo(@Param("cid") String cid, @Param("start")Integer page, @Param("number")Integer lastNum);

    List<Item> getTCSingle(@Param("cid") String cid, @Param("itemNo") String itemNo);
    //其他
    List<Item> getOtherInfo(@Param("cid") String cid, @Param("start")Integer page, @Param("number")Integer lastNum);

    List<Item> getOtherSingle(@Param("cid") String cid, @Param("itemNo") String itemNo);

}
