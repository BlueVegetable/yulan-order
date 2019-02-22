package com.yulan.service.impl;

import com.yulan.dao.Area_ownerDao;
import com.yulan.pojo.Area_owner;
import com.yulan.service.Area_ownerService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class Area_ownerServiceImpl implements Area_ownerService {

    @Autowired
    private Area_ownerDao area_ownerDao;

    @Override
    public List<Area_owner> getAreaOwnerByAreaCode(String areaCode) {
        return area_ownerDao.getAreaOwnerByAreaCode(areaCode);
    }

    @Override
    public Map getebcontractwfmemberbyYear(Integer start, Integer number, String owner) throws UnsupportedEncodingException {
        Map map=new HashMap<String,Object>(2);
        List<Area_owner> list=area_ownerDao.getArea_owners(start,number,owner);
        List<Area_owner> list2=new ArrayList<>();
        for (Area_owner a:list){
            a.setName(StringUtil.getUtf8(area_ownerDao.getOwnerName(a.getOwner())));
            a.setCenter_managerName(StringUtil.getUtf8(area_ownerDao.getManageName(a.getCenter_manager())));
            if (a.getPosition()!=null){
                a.setPosition(StringUtil.getUtf8(a.getPosition()));
            }

            if (area_ownerDao.getArea_code(a.getArea_code())!=null){
                a.setArea_codeName(StringUtil.getUtf8(area_ownerDao.getArea_code(a.getArea_code())));
            }
            if (area_ownerDao.getArea(a.getArea())!=null){
                a.setAreaName(StringUtil.getUtf8(area_ownerDao.getArea(a.getArea())));
            }


            list2.add(a);
        }
        map.put("data",list2);
        map.put("count",area_ownerDao.count(owner));
        return  map;

    }
}
