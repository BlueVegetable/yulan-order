package com.yulan.service.impl;

import com.yulan.dao.Ctm_orderDao;
import com.yulan.dao.CustomerAssignmentsDao;
import com.yulan.pojo.CustomerAssignments;
import com.yulan.service.CustomerAssignmentsService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerAssignmentsServiceImpl implements CustomerAssignmentsService {
    @Autowired
    private CustomerAssignmentsDao customerAssignmentsDao;
    @Autowired
    private Ctm_orderDao ctm_orderDao;


    /**
     * 任务查询
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     */
    @Override
    public Map getAssignments(Map<String, Object> map) throws UnsupportedEncodingException {
        String year=map.get("year").toString();
        String month=map.get("month").toString();
        String cid=map.get("cid").toString();
        Map m=new HashMap();
        Map m0=new HashMap();
        List<Map<String,Object>> datas=new ArrayList<>();
        CustomerAssignments customerAssignments=customerAssignmentsDao.getCustomerAssignments(cid,year,month);//获取表头
        m0.put("assignments",customerAssignments);
        Integer finishNum=Integer.parseInt(month)+1;
        String beginTime=year+"-"+month+"-01 00:00:00";
        String finishTime=year+"-"+finishNum+"-01 00:00:00";
        List<Map<String,Object>> list=ctm_orderDao.getOrderCusAss(beginTime,finishTime,cid);//获取月份订单
        for (Map<String,Object> map1:list){
            for (Map.Entry<String, Object> entry : map1.entrySet()) {//转码
                if (entry.getValue() instanceof String) {
                    String origin = StringUtil.getUtf8(String.valueOf(entry.getValue()));
                    entry.setValue(origin);
                }
            }
            datas.add(map1);
        }
        m0.put("orders",datas);
        m.put("msg","SUCCESS");
        m.put("code",0);
        m.put("data",m0);
        return m;
    }
}
