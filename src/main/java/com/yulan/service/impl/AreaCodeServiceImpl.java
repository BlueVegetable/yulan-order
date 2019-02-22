package com.yulan.service.impl;

import com.yulan.dao.AreaCodeDao;
import com.yulan.pojo.AreaCode;
import com.yulan.service.AreaCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaCodeServiceImpl implements AreaCodeService {
    @Autowired
    private AreaCodeDao areaCodeDao;
    @Override
    public AreaCode getAreaCodeByAreaCode(String areaCode) {
        return areaCodeDao.getAreaCodeByAreaCode(areaCode);
    }
}
