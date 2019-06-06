package com.yulan.service.impl;

import com.yulan.dao.SalReturnRecordDao;
import com.yulan.dao.Sal_rebate_certificateDao;
import com.yulan.pojo.SalReturnRecord;
import com.yulan.pojo.Sal_rebate_certificate;
import com.yulan.service.Sal_rebate_certificateService;
import com.yulan.utils.MapUtils;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class Sal_rebate_certificateServiceImpl implements Sal_rebate_certificateService {
    @Autowired
    private Sal_rebate_certificateDao sal_rebate_certificateDao;

    @Autowired
    private SalReturnRecordDao salReturnRecordDao;

    @Override
    public Map getRebate(Map<String, Object> map) throws UnsupportedEncodingException {
        String cid=map.get("cid").toString();
        String clompanyId=map.get("companyId").toString();
        Map m=new HashMap();
        List<Map<String,Object>> dateLate=new ArrayList<>();
        List<Map<String,Object>> dateBefore=new ArrayList<>();
        List<Map<String,Object>> dateUsefull=new ArrayList<>();
        List<Map<String,Object>> data=new ArrayList<>();






        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        java.sql.Date beforeDate=new java.sql.Date(currentDate.getTime()-(24*60*60*1000));//前一天
        List<Sal_rebate_certificate> list=sal_rebate_certificateDao.getRebate(clompanyId);
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
                dateBefore.add(map1);
            }else if (beforeDate.after(sal_rebate_certificate.getDateEnd())){//过期
                map1.put("dateId",2);
                dateLate.add(map1);
            }else {//生效
                map1.put("dateId",1);
                dateUsefull.add(map1);
            }


        }
        //按顺序排 生效、还没生效、过期
        data.addAll(dateUsefull);
        data.addAll(dateBefore);
        data.addAll(dateLate);

        m.put("data",data);
        m.put("code",0);
        m.put("msg","SUCCESSS");
        return m;
    }

    @Override
    public Map getReturnRecord(Map<String, Object> map) throws UnsupportedEncodingException {
        String id=map.get("id").toString();
        List<SalReturnRecord> list=salReturnRecordDao.getReturnRecord(id);
        Map m=new HashMap();
        List<Map<String,Object>> data=new ArrayList<>();
        for (SalReturnRecord salReturnRecord:list) {
            Map<String, Object> map1 = MapUtils.beanToMap(salReturnRecord);
            for (Map.Entry<String, Object> entry : map1.entrySet()) {//转码
                if (entry.getValue() instanceof String) {
                    String origin = StringUtil.getUtf8(String.valueOf(entry.getValue()));
                    entry.setValue(origin);
                }
            }
            data.add(map1);
        }
        m.put("data",data);
        m.put("code",0);
        m.put("msg","SUCCESSS");
        return m;
    }
}
