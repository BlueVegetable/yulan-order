package com.yulan.controller;

import com.yulan.pojo.SalPromotion;
import com.yulan.service.SalPromotionService;
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

    @ResponseBody@RequestMapping("getSalPromotionByID")
    public SalPromotion getSalPromotionByID(String salPromotionID) {
        return salPromotionService.getSalPromotionByID(salPromotionID);
    }

    @ResponseBody@RequestMapping("selectSalPromotion")
    public List<Map<String,Object>> selectSalPromotion(@RequestParam("CID")String CID,@RequestParam("customerType")String customerType,
                                                       @RequestParam("itemNo")String itemNo,@RequestParam("itemVersion")String itemVersion,
                                                       @RequestParam("productType")String productType,@RequestParam("productBrand")String productBrand) {
        return salPromotionService.selectSalPromotion(CID, customerType, itemNo, itemVersion,productType,productBrand);
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
