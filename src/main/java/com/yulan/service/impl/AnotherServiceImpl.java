package com.yulan.service.impl;

import com.yulan.dao.AnotherDao;
import com.yulan.service.AnotherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class AnotherServiceImpl implements AnotherService {
    @Autowired
    private AnotherDao anotherDao;
    @Override
    public boolean alterCommodityPrice(String commodityID, String price) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("id",commodityID);
        parameters.put("price",new BigDecimal(price));
        return anotherDao.alterCommodityPrice(parameters) > 0;
    }
}
