package com.yulan.service.impl;

import com.yulan.dao.SalPromotionDao;
import com.yulan.pojo.SalPromotion;
import com.yulan.service.SalPromotionService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class SalPromotionServiceImpl implements SalPromotionService {
    @Autowired
    private SalPromotionDao salPromotionDao;
    @Override
    public SalPromotion getSalPromotionByID(String orderType) {
        return salPromotionDao.getSalPromotionByID(orderType);
    }

    @Override
    public List<Map<String, Object>> selectSalPromotion(String CID, String customerType, String itemNo, String itemVersion) throws UnsupportedEncodingException {
        List<Map<String,Object>> result = new ArrayList<>();
        List<SalPromotion> salPromotions = salPromotionDao.selectSalPromotions(CID, itemNo);
        List<SalPromotion> salPromotionsDeal = new ArrayList<>();
        for (SalPromotion salPromotion:salPromotions) {
            if((salPromotion.getCustomerType().equals("%")||salPromotion.getCustomerType().equals(customerType))&&
                    (salPromotion.getItemVersion().equals("%")||salPromotion.getItemVersion().equals(itemVersion))) {
                salPromotionsDeal.add(salPromotion);
            } else {
                continue;
            }
        }
        Set<String> firsts = new HashSet<>();
        for (SalPromotion salPromotion:salPromotionsDeal) {
            firsts.add(salPromotion.getGroupType());
        }
        for (String first:firsts) {
            Map<String,Object> item = new HashMap<>();
            item.put("first",first);
            salPromotions = new ArrayList<>();
            for (SalPromotion salPromotion:salPromotionsDeal) {
                if (salPromotion.getGroupType().equals(first)) {
                    salPromotion.setOrderName(StringUtil.getUtf8(salPromotion.getOrderName()));
                    salPromotions.add(salPromotion);
                }
            }
            item.put("second",salPromotions);
            result.add(item);
        }
        return result;
    }
}
