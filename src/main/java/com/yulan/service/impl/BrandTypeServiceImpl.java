package com.yulan.service.impl;

import com.yulan.encode.BrandTypeEncode;
import com.yulan.pojo.BrandType;
import com.yulan.service.BrandTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandTypeServiceImpl implements BrandTypeService {
    @Autowired
    private BrandTypeEncode brandTypeEncode;

    @Override
    public BrandType getBrandTypeByID(String brandTypeID) {
        return brandTypeEncode.getBrandTypeByID(brandTypeID);
    }
}
