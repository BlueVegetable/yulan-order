package com.yulan.service.impl;

import com.yulan.dao.Ctm_orderDao;
import com.yulan.dao.CustomerAssignmentsDao;
import com.yulan.dao.Web_userDao;
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
    @Autowired
    private Web_userDao web_userDao;


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
        String companyId=map.get("companyId").toString();//公司id

        List<Map<String,Object>> userMaps=web_userDao.getAllUserByComId(companyId);//查找属于同个公司的用户
        List<String> users=new ArrayList<>();
        if (userMaps.size()!=0){
            for (Map<String,Object> map1:userMaps){
                users.add(map1.get("LOGINNAME").toString());
            }
        }

        Map m=new HashMap();
        Map m0=new HashMap();
        List<Map<String,Object>> datas=new ArrayList<>();
        CustomerAssignments customerAssignments=customerAssignmentsDao.getCustomerAssignments(users,year,month);//获取表头
        m0.put("assignments",customerAssignments);
        Integer finishNum=Integer.parseInt(month)+1;
        String beginTime=year+"-"+month+"-01 00:00:00";
        String finishTime=year+"-"+finishNum+"-01 00:00:00";
        List<Map<String,Object>> list=ctm_orderDao.getOrderCusAss(beginTime,finishTime,users);//获取月份订单
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
