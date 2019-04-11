package com.yulan.service.impl;

import com.yulan.dao.Ctm_orderDao;
import com.yulan.pojo.Ctm_order;
import com.yulan.pojo.Ctm_order_detail;
import com.yulan.service.CurtainOrderService;
import com.yulan.utils.MapUtils;
import com.yulan.utils.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurtainOrderServiceImpl implements CurtainOrderService {
    @Autowired
    private  Ctm_orderDao ctm_orderDao;

    @Override
    public Boolean insertCurtain(Map map) throws UnsupportedEncodingException, InvocationTargetException, IllegalAccessException {
        Map m=new HashMap();
        Ctm_order ctm_order=new Ctm_order();
        Map<String ,Object> ctm_ordermap=(Map<String, Object>) map.get("ctm_order");//订单头
        List<Map<String,Object>> list=(List) map.get("ctm_orders");
        for (Map.Entry<String, Object> entry : ctm_ordermap.entrySet()) {//转码
            if (entry.getValue() instanceof String) {
                String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                entry.setValue(origin);
            }
        }
        BeanUtils.populate(ctm_order,ctm_ordermap);//转为Ctm_order类
        if (ctm_orderDao.insertOrderH(ctm_order)) {//订单头录入

            int lineNo = 1;
            for (Map<String, Object> m2 : list) {//订单详情录入


                for (Map.Entry<String, Object> entry : m2.entrySet()) {
                    if (entry.getValue() instanceof String) {
                        String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                        entry.setValue(origin);
                    }
                }
                Ctm_order_detail ctm_order_detail = MapUtils.mapToBean(m2, Ctm_order_detail.class);


                ctm_order_detail.setLineNo(lineNo);
                lineNo++;
            }
        }
        return null;
    }

}
