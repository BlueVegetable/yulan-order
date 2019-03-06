package com.yulan.service.impl;

import com.yulan.dao.UnitDao;
import com.yulan.pojo.Unit;
import com.yulan.service.UnitService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitDao unitDao;
    @Override
    public Unit getUnitByID(String id) {
        Unit unit = unitDao.getUnitByID(id);
        if(unit!=null) {
            unit.setNote(StringUtil.GBKToUTF8(unit.getNote()));
        }
        return unit;
    }
}
