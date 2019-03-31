package com.yulan.service.impl;

import com.yulan.dao.SalPromotionDao;
import com.yulan.pojo.SalPromotion;
import com.yulan.service.SalPromotionService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SalPromotionServiceImpl implements SalPromotionService {
    @Autowired
    private SalPromotionDao salPromotionDao;
    @Override
    public SalPromotion getSalPromotionByID(String orderType) {
        SalPromotion salPromotion = salPromotionDao.getSalPromotionByID(orderType);
        if(salPromotion!=null)
            salPromotion.setOrderName(StringUtil.GBKToUTF8(salPromotion.getOrderName()));
        return salPromotion;
    }

    @Override
    public List<String> getSalPromotionNamesByIDs(List<String> IDs) {
        List<String> datas = salPromotionDao.getSalPromotionNamesByIDs(IDs);
        List<String> value = new ArrayList<>();
        for (String data:datas) {
            value.add(StringUtil.GBKToUTF8(data));
        }
        return value;
    }

    @Override
    public List<Map<String, Object>> selectSalPromotion(String CID, String customerType, String itemNo, String itemVersion,
                                                        String productType,String productBrand) {
        List<Map<String,Object>> result = new ArrayList<>();
        List<SalPromotion> salPromotions = salPromotionDao.selectSalPromotions(CID, itemNo,productType,productBrand);
        List<SalPromotion> salPromotionsDeal = new ArrayList<>();
        for (SalPromotion salPromotion:salPromotions) {
            if((salPromotion.getCustomerType().equals("%")||salPromotion.getCustomerType().equals(customerType))&&
                    (salPromotion.getItemVersion().equals("%")||salPromotion.getItemVersion().equals(itemVersion))) {
                long current = System.currentTimeMillis();
                java.sql.Date before = salPromotion.getDateStart();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.sql.Date end = salPromotion.getDateEnd();
                Timestamp beforeTimestamp,endTimestamp;
                beforeTimestamp = Timestamp.valueOf(simpleDateFormat.format(before)+" 00:00:00");
                endTimestamp = Timestamp.valueOf(simpleDateFormat.format(end)+" 23:59:59");
                long beforeTime = beforeTimestamp.getTime();
                long endTime = endTimestamp.getTime();
                if(current>=beforeTime&&current<=endTime) {
                    salPromotionsDeal.add(salPromotion);
                }
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
                    salPromotion.setOrderName(StringUtil.GBKToUTF8(salPromotion.getOrderName()));
                    salPromotions.add(salPromotion);
                }
            }
            item.put("second",salPromotions);
            result.add(item);
        }
        return result;
    }
}
