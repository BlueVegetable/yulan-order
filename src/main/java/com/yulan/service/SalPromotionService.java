package com.yulan.service;

import com.yulan.pojo.SalPromotion;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface SalPromotionService {

    SalPromotion getSalPromotionByID(String orderType);

    List<Map<String,Object>> selectSalPromotion(String CID,String customerType,String itemNo, String itemVersion) throws UnsupportedEncodingException;

}
