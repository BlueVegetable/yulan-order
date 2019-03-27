package com.yulan.service.impl;

import com.yulan.dao.Sal_rebate_certificateDao;
import com.yulan.pojo.Sal_rebate_certificate;
import com.yulan.service.Sal_rebate_certificateService;
import com.yulan.utils.MapUtils;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Sal_rebate_certificateServiceImpl implements Sal_rebate_certificateService {
    @Autowired
    private Sal_rebate_certificateDao sal_rebate_certificateDao;

    @Override
    public Map getRebate(Map<String, Object> map) throws UnsupportedEncodingException {
        String cid=map.get("cid").toString();
        Map m=new HashMap();
        List<Map<String,Object>> data=new ArrayList<>();
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        List<Sal_rebate_certificate> list=sal_rebate_certificateDao.getRebate(cid);
        for (Sal_rebate_certificate sal_rebate_certificate:list){
            Map<String,Object> map1= MapUtils.beanToMap(sal_rebate_certificate);
            for (Map.Entry<String, Object> entry : map1.entrySet()) {//转码
                if (entry.getValue() instanceof String) {
                    String origin = StringUtil.getUtf8(String.valueOf(entry.getValue()));
                    entry.setValue(origin);
                }
            }
            if (currentDate.before(sal_rebate_certificate.getDateStart())){//还没生效
                map1.put("dateId",0);
            }else if (currentDate.after(sal_rebate_certificate.getDateEnd())){//过期
                map1.put("dateId",2);
            }else {//生效
                map1.put("dateId",1);
            }
            data.add(map1);

        }
        m.put("data",data);
        m.put("code",0);
        m.put("msg","SUCCESSS");
        return m;
    }
}
