package com.yulan.controller;

import com.yulan.pojo.SalPromotion;
import com.yulan.service.SalPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.*;

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
                                                       @RequestParam("itemNo")String itemNo,@RequestParam("itemVersion")String itemVersion) throws UnsupportedEncodingException {
        return salPromotionService.selectSalPromotion(CID, customerType, itemNo, itemVersion);
    }

    @ResponseBody@RequestMapping("getSalPromotionsByIDs")
    public List<String> getSalPromotionsByIDs(@RequestBody List<String> salPromotionIDs) {
        Set<String> salPromotionIDsDeal = new LinkedHashSet<>();
        for (String salPromotion:salPromotionIDs) {
            salPromotionIDsDeal.add(salPromotion);
        }
        List<String> salPromotionIDsUse = new ArrayList<>();
        for (String salPromotionID:salPromotionIDsDeal) {
            salPromotionIDsUse.add(salPromotionID);
        }
        List<String> data = salPromotionService.getSalPromotionNamesByIDs(salPromotionIDsUse);
        List<String> result = new ArrayList<>();
        int j=0;
        for (String salPromotionID:salPromotionIDs) {
            String value = null;
            if(!salPromotionID.equals(salPromotionIDsUse.get(j))) {
                j++;
            }
            value = data.get(j);
            result.add(value);
        }
        return result;
    }

}
