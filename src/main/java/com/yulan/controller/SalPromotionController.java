package com.yulan.controller;

import com.yulan.service.SalPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Controller@RequestMapping("salPromotion")
public class SalPromotionController {

    @Autowired
    private SalPromotionService salPromotionService;

    @ResponseBody@RequestMapping("selectSalPromotion")
    public List<Map<String,Object>> selectSalPromotion(@RequestParam("CID")String CID,@RequestParam("customerType")String customerType,
                                                       @RequestParam("itemNo")String itemNo,@RequestParam("itemVersion")String itemVersion) throws UnsupportedEncodingException {
        return salPromotionService.selectSalPromotion(CID, customerType, itemNo, itemVersion);
    }

}
