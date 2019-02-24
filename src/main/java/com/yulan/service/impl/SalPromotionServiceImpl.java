package com.yulan.service.impl;

import com.yulan.dao.SalPromotionDao;
import com.yulan.pojo.SalPromotion;
import com.yulan.service.SalPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalPromotionServiceImpl implements SalPromotionService {
    @Autowired
    private SalPromotionDao salPromotionDao;
    @Override
    public SalPromotion getSalPromotionByID(String orderType) {
        return salPromotionDao.getSalPromotionByID(orderType);
    }
}
