package com.yulan.dao;

import com.yulan.pojo.Item;
import com.yulan.pojo.ItemMLGY;
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
    //获取库存列表中最大的库存数
    StockShow getStockShowMax(@Param("ITEM_NO")String itemNo);
    //求某一类型库存总和
    Integer sumStockShow(@Param("ITEM_NO")String itemNo);
    //面料
    List<Item> getMLInfo(@Param("cid") String cid, @Param("start")Integer page, @Param("number")Integer lastNum);

    List<Item> getMLSingle(@Param("cid") String cid, @Param("itemNo") String itemNo, @Param("start")Integer page, @Param("number")Integer lastNum);
    //花边
    List<Item> getXHBInfo(@Param("cid") String cid, @Param("start")Integer page, @Param("number")Integer lastNum);

    List<Item> getXHBSingle(@Param("cid") String cid, @Param("itemNo") String itemNo, @Param("start")Integer page, @Param("number")Integer lastNum);
    //挂带/配件包
    List<Item> getPJBInfo(@Param("cid") String cid, @Param("start")Integer page, @Param("number")Integer lastNum);

    List<Item> getPJBSingle(@Param("cid") String cid, @Param("itemNo") String itemNo, @Param("start")Integer page, @Param("number")Integer lastNum);
    //抱枕
    List<Item> getBZInfo(@Param("cid") String cid, @Param("start")Integer page, @Param("number")Integer lastNum);

    List<Item> getBZSingle(@Param("cid") String cid, @Param("itemNo") String itemNo, @Param("start")Integer page, @Param("number")Integer lastNum);
    //挂画
    List<Item> getGHInfo(@Param("cid") String cid, @Param("start")Integer page, @Param("number")Integer lastNum);

    List<Item> getGHSingle(@Param("cid") String cid, @Param("itemNo") String itemNo, @Param("start")Integer page, @Param("number")Integer lastNum);
    //陶瓷
    List<Item> getTCInfo(@Param("cid") String cid, @Param("start")Integer page, @Param("number")Integer lastNum);

    List<Item> getTCSingle(@Param("cid") String cid, @Param("itemNo") String itemNo, @Param("start")Integer page, @Param("number")Integer lastNum);
    //其他
    List<Item> getOtherInfo(@Param("cid") String cid, @Param("start")Integer page, @Param("number")Integer lastNum);

    List<Item> getOtherSingle(@Param("cid") String cid, @Param("itemNo") String itemNo, @Param("start")Integer page, @Param("number")Integer lastNum);
    //获得窗帘型号
    List<Item> getCurtainType(@Param("start")Integer page, @Param("number")Integer lastNum);
    //窗帘型号模糊查询
    List<Item> getCurtainTypeBySearch(@Param("start")Integer page, @Param("number")Integer lastNum, @Param("itemNo") String itemNo);
   //获取同一型号窗帘下的系列产品
    List<ItemMLGY> getCurtainInfo(@Param("curtainNo") String curtainNo);
   //获取每个窗帘对应的工艺
    List<String> getItemGY(@Param("itemNO") String itemNo);

}
