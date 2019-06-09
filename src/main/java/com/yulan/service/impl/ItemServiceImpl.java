package com.yulan.service.impl;

import com.yulan.dao.ItemDao;
import com.yulan.dao.Web_userDao;
import com.yulan.pojo.Item;
import com.yulan.pojo.ItemMLGY;
import com.yulan.pojo.StockShow;
import com.yulan.service.ItemService;
import com.yulan.utils.Arith;
import com.yulan.utils.MapUtils;
import com.yulan.utils.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private Web_userDao web_userDao;

    private StringUtil stringUtil;

    private static Arith arith;

    private MapUtils mapUtils;

    @Override
    public Item getItemByItemNO(String itemNO) {
        return itemDao.getItemByItemNO(itemNO);
    }

    @Override
    public Map getWallpaperInfo(String cid, String paperType) throws IOException {
        cid = changeLoginNameToCompanyID(cid);
        Map<String, Object> map = new HashMap<>();
        Item item = new Item();
        item = itemDao.getWallpaperInfo(paperType);
        if (item != null) {
            if (itemDao.userBrandAuthority(cid, item.getItemNo()) == null || itemDao.userBrandAuthority(cid, item.getItemNo()).equals("")) {
                map.put("data", "没有查询权限");
                map.put("code", 1);
            } else {
                item.getItemType().setNote(stringUtil.getUtf8(item.getItemType().getNote()));
                item.setUnit(stringUtil.getUtf8(itemDao.getUnit(item.getUnit())));
                item.setItemVersion(stringUtil.getUtf8(itemDao.getProductVersion(item.getItemVersion())));
                item.setProductBrand(stringUtil.getUtf8(itemDao.getProductBrand(item.getProductBrand())));
                item.setRzStyle(stringUtil.getUtf8(item.getRzStyle()));
                if (null != item.getRzGrade()) {
                    item.setRzGrade(stringUtil.getUtf8(item.getRzGrade()));
                }
                map.put("data", item);
                map.put("code", 0);
            }

        } else {
            map.put("data", "没有找到相关产品");
            map.put("code", 1);
        }
        return map;
    }

    @Override
    public Map getStockShow(String itemNo) {
        Map<String, Object> map = new HashMap<>();
        List<StockShow> stockList = itemDao.getStockShow(itemNo);
        if (null == stockList || stockList.size() == 0) {
            map.put("data", "没有查询到数据");
            map.put("code", 1);
        } else {
            map.put("data", stockList);
            map.put("code", 0);
        }
        return map;
    }

    @Override
    public Map getSoftDecorationInfo(String itemType, String cid,
                                     Integer page, Integer lastNum) throws IOException {

        cid = changeLoginNameToCompanyID(cid);

        Map<String, Object> map = new HashMap<>();
        List<Item> itemList = new ArrayList<>();
        if (itemType.equals("ML")) {
            itemList = itemDao.getMLInfo(cid, page, lastNum);
        } else if (itemType.equals("XHB")) {
            itemList = itemDao.getXHBInfo(cid, page, lastNum);
        } else if (itemType.equals("PJB")) {
            itemList = itemDao.getPJBInfo(cid, page, lastNum);
        } else if (itemType.equals("BZ")) {
            itemList = itemDao.getBZInfo(cid, page, lastNum);
        } else if (itemType.equals("GH")) {
            itemList = itemDao.getGHInfo(cid, page, lastNum);
        } else if (itemType.equals("TC")) {
            itemList = itemDao.getTCInfo(cid, page, lastNum);
        } else if (itemType.equals("other")) {
            itemList = itemDao.getOtherInfo(cid, page, lastNum);
        }

        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            changeItemToUTF8(item);
        }

        if (null == itemList || itemList.size() == 0) {
            map.put("data", "没有查询到数据");
            map.put("code", 1);
        } else {
            map.put("data", itemList);
            map.put("code", 0);
        }
        return map;
    }

    @Override
    public Map getSoftInfoSingle(String itemType, String cid, String itemNo,
                                 Integer page, Integer lastNum) throws IOException {

        cid = changeLoginNameToCompanyID(cid);

        Map<String, Object> map = new HashMap<>();
        List<Item> itemList = new ArrayList<>();
        if (itemType.equals("ML")) {
            itemList = itemDao.getMLSingle(cid, itemNo, page, lastNum);
        } else if (itemType.equals("XHB")) {
            itemList = itemDao.getXHBSingle(cid, itemNo, page, lastNum);
        } else if (itemType.equals("PJB")) {
            itemList = itemDao.getPJBSingle(cid, itemNo, page, lastNum);
        } else if (itemType.equals("BZ")) {
            itemList = itemDao.getBZSingle(cid, itemNo, page, lastNum);
        } else if (itemType.equals("GH")) {
            itemList = itemDao.getGHSingle(cid, itemNo, page, lastNum);
        } else if (itemType.equals("TC")) {
            itemList = itemDao.getTCSingle(cid, itemNo, page, lastNum);
        } else if (itemType.equals("other")) {
            itemList = itemDao.getOtherSingle(cid, itemNo, page, lastNum);
        }

        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);

            if (null != item.getNote()) {
                item.setNote(stringUtil.getUtf8(item.getNote()));
            }
            if (null != item.getItemVersion()) {
                item.setItemVersion(stringUtil.getUtf8(itemDao.getProductVersion(item.getItemVersion())));
            }
            if (null != item.getProductBrand()) {
                item.setProductBrand(stringUtil.getUtf8(itemDao.getProductBrand(item.getProductBrand())));
            }
            if (null != item.getRzStyle()) {
                item.setRzStyle(stringUtil.getUtf8(item.getRzStyle()));
            }
            if (null != item.getUnit()) {
                item.setUnit(stringUtil.GBKToUTF8(itemDao.getUnit(item.getUnit())));
            }
            if (null != item.getRzGrade()) {
                item.setRzGrade(stringUtil.getUtf8(item.getRzGrade()));
            }

        }

        if (null == itemList || itemList.size() == 0) {
            map.put("data", "没有查询到数据");
            map.put("code", 1);
        } else {
            map.put("data", itemList);
            map.put("code", 0);
        }
        return map;
    }

    @Override
    public Map judgeStockShow(Double stockShowNum, String itemNo) {
        Map<String, Object> map = new HashMap<>();
        Double stockShowNumMax = 0.0;
        StockShow stockShow = new StockShow();
        stockShow = itemDao.getStockShowMax(itemNo);
        Integer sumStockShow = itemDao.sumStockShow(itemNo);
        if (null != stockShow) {
            stockShowNumMax = stockShow.getQty();
            if (stockShowNum <= stockShowNumMax) {
                map.put("msg", "SUCCESS");
                map.put("code", 0);
            } else if (stockShowNumMax < stockShowNum && stockShowNum <= sumStockShow) {
                //允许分批出货
                map.put("msg", "splitShipment");
                map.put("code", 0);
            } else if (stockShowNum > sumStockShow) {
                //等待制作
                map.put("msg", "waitForProduction");
                map.put("code", 0);
            }
        } else {
            //库存为空
            map.put("msg", "null");
            map.put("code", 0);
        }
        return map;
    }

    @Override
    public Map getCurtainType(Integer page, Integer lastNum) throws IOException {
        Map<String, Object> map = new HashMap<>();
        List<Item> curtainList = itemDao.getCurtainType(page, lastNum);
        for (int i = 0; i < curtainList.size(); i++) {
            Item item = curtainList.get(i);
            if (null != item.getNote()) {
                item.setNote(stringUtil.getUtf8(item.getNote()));
            }
            if (null != item.getItemVersion()) {
                item.setItemVersion(stringUtil.getUtf8(itemDao.getProductVersion(item.getItemVersion())));
            }
            if (null != item.getProductBrand()) {
                item.setProductBrand(stringUtil.getUtf8(itemDao.getProductBrand(item.getProductBrand())));
            }
            if (null != item.getRzStyle()) {
                item.setRzStyle(stringUtil.getUtf8(item.getRzStyle()));
            }
            if (null != item.getUnit()) {
                item.setUnit(stringUtil.getUtf8(itemDao.getUnit(item.getUnit())));
            }
            if (null != item.getRzGrade()) {
                item.setRzGrade(stringUtil.getUtf8(item.getRzGrade()));
            }
        }
        map.put("data", curtainList);
        map.put("code", 0);
        return map;
    }

    /**
     * 窗帘模糊查询
     * @param page
     * @param lastNum
     * @return
     * @throws IOException
     */
    @Override
    public Map getCurtainTypeBySearch(Integer page, Integer lastNum, String itemNo) throws IOException {
        Map<String, Object> map = new HashMap<>();
        List<Item> curtainList = itemDao.getCurtainTypeBySearch(page, lastNum, itemNo);
        for (int i = 0; i < curtainList.size(); i++) {
            Item item = curtainList.get(i);
            if (null != item.getNote()) {
                item.setNote(stringUtil.getUtf8(item.getNote()));
            }
            if (null != item.getItemVersion()) {
                item.setItemVersion(stringUtil.getUtf8(itemDao.getProductVersion(item.getItemVersion())));
            }
            if (null != item.getProductBrand()) {
                item.setProductBrand(stringUtil.getUtf8(itemDao.getProductBrand(item.getProductBrand())));
            }
            if (null != item.getRzStyle()) {
                item.setRzStyle(stringUtil.getUtf8(item.getRzStyle()));
            }
            if (null != item.getUnit()) {
                item.setUnit(stringUtil.getUtf8(itemDao.getUnit(item.getUnit())));
            }
            if (null != item.getRzGrade()) {
                item.setRzGrade(stringUtil.getUtf8(item.getRzGrade()));
            }
        }
        map.put("data", curtainList);
        map.put("code", 0);
        return map;
    }

    /**
     * 获取窗帘详细信息
     *
     * @param width
     * @param height
     * @param WBH
     * @param multiple
     * @param location
     * @param curtainNo
     * @return
     * @throws IOException
     */
    @Override
    public Map getCurtainInfo(Double width, Double height, Double WBH,
                              Double multiple, String location,
                              String curtainNo) throws IOException {
        Map map = new HashMap<>();
        List<ItemMLGY> curtainItemList = itemDao.getCurtainInfo(curtainNo);

        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < curtainItemList.size(); i++) {
            ItemMLGY itemMLGY = curtainItemList.get(i);
            Item curtainItem =
                    itemDao.getItemByItemNO(itemMLGY.getItemNo());
            if(curtainItem.getHighJia()== null){
                curtainItem.setHighJia(BigDecimal.valueOf(0));
            }
            Double usage;

            if (itemMLGY.getItemType().equals("lt")) {
                //帘头
                if (height < 3) {
                    usage = width + WBH * 2;
                } else if (height < 4) {
                    usage = (width + WBH * 2) * 1.1;
                } else if (height < 5) {
                    usage = (width + WBH * 2) * 1.3;
                } else {
                    usage = (width + WBH * 2) * 1.5;
                }
                BigDecimal ltUsage = new BigDecimal(usage);
                map.put("lt", ltUsage.setScale(2,BigDecimal.ROUND_HALF_UP));
            } else if (itemMLGY.getItemType().equals("ls")) {
                BigDecimal lsUsage = BigDecimal.valueOf(0);
                //判断是否是里衬布
                if (itemMLGY.getProductType().equals("LCB")) {
                    Double LCBUsage = width * multiple + 0.2;
                    BigDecimal LCB = new BigDecimal(LCBUsage);
                    map.put("LCB", LCB.setScale(2,BigDecimal.ROUND_HALF_UP));
                } else {
                    //帘身
                    //特殊款式
                    if (itemMLGY.getParentItemNo().equals("Z340004") || itemMLGY.getParentItemNo().equals("U310111")) {
                        if (itemMLGY.getProductType().equals("ML")) {
                            {
                                if (curtainItem.getWidthHh() == null) {
                                    map.put("ls ", itemMLGY.getItemNo() +
                                            " has null values and can not be " +
                                            "calculated,please checkout WidthHh");
                                } else {
                                    lsUsage = arith.add( arith.dbToBD(width),curtainItem.getWidthHh());
                                    map.put("ls", lsUsage.setScale(2,BigDecimal.ROUND_HALF_UP));
                                }
                            }
                        }

                    } else {
                        if (itemMLGY.getProductType().equals("ML")) {
                            if (curtainItem.getWidthHh() == null || curtainItem.getFixGrade()  == null || curtainItem.getDuihuaLoss() == null || curtainItem.getHighJia() == null) {

                                map.put("ls", itemMLGY.getItemNo() +
                                        " has null values and can not be " +
                                        "calculated,please checkout WidthHh," +
                                        "FixType,DuihuaLoss,HighJia");

                            } else {
                                lsUsage = usageCalculation(width, multiple,height, curtainItem);
                                //获取每种产品的用量
                                map.put("ls", lsUsage);
                            }

                        }

                    }
                }
            } else if (itemMLGY.getItemType().equals("sha")) {
                BigDecimal shaUsage ;
                //纱
                if (itemMLGY.getProductType().equals("ML")) {
                    if (curtainItem.getWidthHh() == null || curtainItem.getFixGrade() == null || curtainItem.getDuihuaLoss() == null || curtainItem.getHighJia() == null) {

                        map.put("sha", itemMLGY.getItemNo() +
                                " has null values and can not be " +
                                "calculated,please checkout WidthHh," +
                                "FixGrade(),DuihuaLoss,HighJia");

                    } else {

                        shaUsage = usageCalculation(width, multiple,height, curtainItem);
                        map.put("sha", shaUsage);
                    }
                }
            } else if (itemMLGY.getItemType().equals("pjb")) {
                //配件
                map.put("pjb", 1.00);
            }

            //工艺用量
            if (itemMLGY.getProductType().equals("GY")) {
                //直接返回GY=003的有用量，因为其他的工艺用量等于帘身用量
                    Double GYusage = width * height;
                    map.put("GY", GYusage);

            }
            //绣花边
            if (itemMLGY.getProductType().equals("XHB")) {
                Double XHBusage ;
                if (itemMLGY.getItemType().equals("lt")) {
                    XHBusage = width * multiple + 0.3;
                    map.put("XHBlt", XHBusage);
                } else if (itemMLGY.getItemType().equals("ls")) {
                    XHBusage = height * 2 + 0.4;
                    map.put("XHBls", XHBusage);
                }
            }
            //这行代码有问题是因为item如果型号一样的话，就会地址一样，然后后面的itemMLGY就会覆盖掉前面的，就会产生重复数据
            Item item;
            Item selectItem = itemDao.getItemByItemNO(itemMLGY.getItemNo());
            JSONObject jsonObject = JSONObject.fromObject(selectItem);
            item = (Item)JSONObject.toBean(jsonObject,Item.class);
            item.setItemMLGY(itemMLGY);
            if (null != item.getNote()) {
                item.setNote(stringUtil.getUtf8(item.getNote()));
            }
            if (null != item.getItemVersion()) {
                item.setItemVersion(stringUtil.getUtf8(itemDao.getProductVersion(item.getItemVersion())));
            }
            if (null != item.getProductBrand()) {
                item.setProductBrand(stringUtil.getUtf8(itemDao.getProductBrand(item.getProductBrand())));
            }
            if (null != item.getRzStyle()) {
                item.setRzStyle(stringUtil.getUtf8(item.getRzStyle()));
            }
            if (null != item.getRzGrade()) {
                item.setRzGrade(stringUtil.getUtf8(item.getRzGrade()));
            }
            if (null != item.getUnit()) {
                item.setUnit(stringUtil.getUtf8(itemDao.getUnit(item.getUnit())));
            }
            itemList.add(item);
        }
        map.put("itemList", itemList);
        map.put("code",0);
        return map;
    }

    /**
     * 专门用来计算帘身和纱的用量公式
     * @param width
     * @param multiple
     * @param curtainItem
     * @return
     * getHighJia这里不需要转换单位，是因为在类里面已经转换了
     */
    private BigDecimal usageCalculation(Double width, Double multiple,Double height, Item curtainItem){
        BigDecimal usage = BigDecimal.valueOf(0);
        //定高
        if ("02".equals(curtainItem.getFixType())) {
            usage =
                    arith.add(arith.dbToBD(width * multiple),
                            curtainItem.getDuihuaLoss());
        } else {
            //定宽
            if (curtainItem.getHighHh() == null || curtainItem.getHighHh().doubleValue() == 0) {
                usage =
                        arith.mul(arith.round(arith.div(arith.dbToBD(width * multiple), arith.div(curtainItem.getFixGrade(), arith.dbToBD(1000.0))), 2), arith.sub(arith.dbToBD(height + 0.2), curtainItem.getHighJia()));
            } else if (curtainItem.getHighHh().doubleValue() > 0) {
                //花回
                usage =
                        arith.mul(arith.mul(arith.round(arith.div(arith.dbToBD(width * multiple), arith.div(curtainItem.getFixGrade(), arith.dbToBD(1000.0))), 2),
                                arith.roundup(arith.div(arith.sub(arith.dbToBD(height + 0.2), curtainItem.getHighJia()), arith.div( curtainItem.getHighHh(),arith.dbToBD(1000.0))), 2)),
                                arith.div( curtainItem.getHighHh(),arith.dbToBD(1000.0)));
            }
        }

        return usage.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public Map changeCurtainItem(Double width, Double height, Double WBH,
                                 Double multiple, String itemNO,
                                 String itemType, String parentItemNo, String fixType)  throws IOException{
        Map map = new HashMap<>();
        Item curtainItem =
                itemDao.getItemByItemNO(itemNO);
        if(curtainItem.getHighJia()== null){
            curtainItem.setHighJia(BigDecimal.valueOf(0));
        }
        //绣花边
        if (curtainItem.getProductType().equals("XHB")) {
            Double XHBusage ;
            if (itemType.equals("lt")) {
                XHBusage = width * multiple + 0.3;
                map.put("XHBlt", XHBusage);
            } else if (itemType.equals("ls")) {
                XHBusage = height * 2 + 0.4;
                map.put("XHBls", XHBusage);
            }
        }else {

            if (itemType.equals("ls")) {
                BigDecimal lsUsage;

                if (parentItemNo.equals("Z340004") || parentItemNo.equals("U310111")) {
                    if (curtainItem.getWidthHh() != null) {
                        lsUsage = arith.add(arith.dbToBD(width), curtainItem.getWidthHh());
                        map.put("ls", lsUsage.setScale(2, BigDecimal.ROUND_HALF_UP));
                    } else {
                        map.put("ls ", itemNO +
                                " has null values and can not be " +
                                "calculated,please checkout WidthHh");
                    }
                } else {
                    //定高
                    if (curtainItem.getWidthHh() == null || curtainItem.getFixGrade() == null || curtainItem.getDuihuaLoss() == null || curtainItem.getHighJia() == null) {
                        map.put("ls", itemNO +
                                " has null values and can not be " +
                                "calculated,please checkout WidthHh," +
                                "FixType,DuihuaLoss,HighJia");

                    } else {
                        curtainItem.setFixType(fixType);
                        lsUsage = usageCalculation(width, multiple, height, curtainItem);
                        //获取每种产品的用量
                        map.put("ls", lsUsage);
                    }


                }
            } else if (itemType.equals("sha")) {
                BigDecimal shaUsage = BigDecimal.valueOf(0);
                //纱
                if (curtainItem.getWidthHh() == null || curtainItem.getFixGrade() == null || curtainItem.getDuihuaLoss() == null || curtainItem.getHighJia() == null) {

                    map.put("sha", itemNO +
                            " has null values and can not be " +
                            "calculated,please checkout WidthHh," +
                            "FixGrade(),DuihuaLoss,HighJia");

                } else {
                    curtainItem.setFixType(fixType);
                    shaUsage = usageCalculation(width, multiple, height, curtainItem);
                    map.put("sha", shaUsage);
                }

            }
        }

        changeItemToUTF8(curtainItem);
        map.put("item",curtainItem);
        map.put("code",0);
        return map;
    }

    @Override
    public Map getCurtainItemTypeAll(Integer page,Integer lastNum, String itemNO) throws IOException {
        Map map = new HashMap<>();
        List<Item> itemList = itemDao.getCurtainItemTypeAll(page, lastNum,itemNO);
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            changeItemToUTF8(item);
        }
        map.put("code",0);
        map.put("data",itemList);
        return map;
    }

    @Override
    public Map searchCurtainItemTypeAll(Integer page, Integer lastNum,
                                        String itemType, String itemNO) throws IOException {
        Map map = new HashMap<>();
        List<Item> itemList = itemDao.searchCurtainItemTypeAll(page, lastNum,itemType,itemNO);
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            changeItemToUTF8(item);
        }
        map.put("data",itemList);
        map.put("code",0);
        return map;
    }


    @Override
    public Map getGYList(String itemNO) throws IOException{
        Map map = new HashMap<>();
        List<String> GYList = itemDao.getItemGY(itemNO);
        List<Item> GYItemList  = new ArrayList<>();
        for(int i = 0; i < GYList.size(); i++){
            Item GYItem = itemDao.getItemByItemNO(GYList.get(i));
            changeItemToUTF8(GYItem);
            GYItemList.add(GYItem);
        }
        map.put("GYLis",GYList);
        map.put("itemList",GYItemList);
        map.put("code",0);
        return map;
    }

    private void changeItemToUTF8(Item item)throws IOException {
        if (null != item.getNote()) {
            item.setNote(stringUtil.getUtf8(item.getNote()));
        }
        if (null != item.getItemVersion()) {
            item.setItemVersion(stringUtil.getUtf8(itemDao.getProductVersion(item.getItemVersion())));
        }
        if (null != item.getProductBrand()) {
            item.setProductBrand(stringUtil.getUtf8(itemDao.getProductBrand(item.getProductBrand())));
        }
        if (null != item.getRzStyle()) {
            item.setRzStyle(stringUtil.getUtf8(item.getRzStyle()));
        }
        if (null != item.getUnit()) {
            item.setUnit(stringUtil.GBKToUTF8(itemDao.getUnit(item.getUnit())));
        }
        if (null != item.getRzGrade()) {
            item.setRzGrade(stringUtil.getUtf8(item.getRzGrade()));
        }
    }

    private String changeLoginNameToCompanyID(String cid){
        return web_userDao.changeLoginNameToCompanyID(cid);
    }


}
