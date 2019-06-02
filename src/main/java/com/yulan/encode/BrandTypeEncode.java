package com.yulan.encode;

import com.yulan.dao.BrandTypeDao;
import com.yulan.pojo.BrandType;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BrandTypeEncode {
    @Autowired
    private BrandTypeDao brandTypeDao;
    public BrandType getBrandTypeByID(String brandTypeID) {
        BrandType brandType = brandTypeDao.getBrandTypeByID(brandTypeID);
        if(brandType!=null) {
            brandType.setBrandName(StringUtil.GBKToUTF8(brandType.getBrandName()));
        }
        return brandType;
    }
}
