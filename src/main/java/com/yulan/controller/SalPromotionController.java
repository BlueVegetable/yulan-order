package com.yulan.controller;

import com.yulan.pojo.Item;
import com.yulan.pojo.SalPromotion;
import com.yulan.pojo.Web_user;
import com.yulan.service.ItemService;
import com.yulan.service.SalPromotionService;
import com.yulan.service.Web_userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller@RequestMapping("salPromotion")
public class SalPromotionController {

    @Autowired
    private SalPromotionService salPromotionService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private Web_userService webUserService;

    @ResponseBody@RequestMapping("getSalPromotionByID")
    public SalPromotion getSalPromotionByID(String salPromotionID) {
        return salPromotionService.getSalPromotionByID(salPromotionID);
    }

    @ResponseBody@RequestMapping("selectSalPromotion")
    public List<Map<String,Object>> selectSalPromotion(@RequestParam("CID")String CID,@RequestParam("customerType")String customerType,
                                                       @RequestParam("itemNo")String itemNo) {
        Item item = itemService.getItemByItemNO(itemNo);
        String itemVersion = item.getItemVersion();
        String productType = item.getProductType();
        String productBrand = item.getProductBrand();
        Web_user webUser = webUserService.getWebUserByCID(CID);
        String companyId = webUser.getCompanyId();
        if(companyId == null) {
            companyId = CID;
        }
        return salPromotionService.selectSalPromotion(companyId, customerType, itemNo, itemVersion,productType,productBrand);
    }

    @ResponseBody@RequestMapping("getSalPromotionsByIDs")
    public List<String> getSalPromotionsByIDs(@RequestBody List<String> salPromotionIDs) {
        List<String> result = new ArrayList<>();
        for (String salPromotionID:salPromotionIDs) {
            result.add(salPromotionService.getSalPromotionByID(salPromotionID).getOrderName());
        }
        return result;
    }

}
