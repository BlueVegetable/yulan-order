package com.yulan.service.impl;

import com.yulan.dao.Sal_rebate_certificate_recordDao;
import com.yulan.pojo.Sal_rebate_certificate_record;
import com.yulan.service.Sal_rebate_certificate_recordService;
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
public class Sal_rebate_certificate_recordServiceImpl implements Sal_rebate_certificate_recordService {
    @Autowired
    private Sal_rebate_certificate_recordDao sal_rebate_certificate_recordDao;

    @Override
    public Map findRecrods(Map<String, Object> map) throws UnsupportedEncodingException {
        String id=map.get("id").toString();
        Map m=new HashMap();
        List<Map<String,Object>> data=new ArrayList<>();
        List<Sal_rebate_certificate_record> list=sal_rebate_certificate_recordDao.findRecrods(id);
        for (Sal_rebate_certificate_record sal_rebate_certificate_record:list){
            Map<String,Object> map1= MapUtils.beanToMap(sal_rebate_certificate_record);
            for (Map.Entry<String, Object> entry : map1.entrySet()) {//转码
                if (entry.getValue() instanceof String) {
                    String origin = StringUtil.getUtf8(String.valueOf(entry.getValue()));
                    entry.setValue(origin);
                }
            }
            String itemNo=sal_rebate_certificate_recordDao.getItemNO(sal_rebate_certificate_record.getOrderNo(),sal_rebate_certificate_record.getLineNo());
            map1.put("itemNo",itemNo);
            data.add(map1);



        }

        m.put("data",data);
        m.put("code",0);
        m.put("msg","SUCCESSS");
        return m;
    }
}
