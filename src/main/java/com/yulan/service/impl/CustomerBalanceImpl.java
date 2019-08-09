package com.yulan.service.impl;

import com.yulan.dao.*;
import com.yulan.pojo.Customer;
import com.yulan.pojo.CustomerBalancePeriod;
import com.yulan.pojo.CustomerBalancePeriodDetail;
import com.yulan.pojo.PackDetail;
import com.yulan.service.CustomerBalanceService;
import com.yulan.utils.MapUtils;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerBalanceImpl implements CustomerBalanceService {

    @Autowired
    private CustomerBalancePeriodDao customerBalancePeriodDao;
    @Autowired
    private CustomerBalancePeriodDetailDao customerBalancePeriodDetailDao;
    @Autowired
    private PackDetailDao packDetailDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private Web_userDao web_userDao;

    private StringUtil stringUtil;

    private static MapUtils mapUtils;

    @Override
    public Map getCustomerBalanceInfo(String cid, Integer page, Integer lastNum) {
        cid = changeLoginNameToCompanyID(cid);
        Map<String, Object> map = new HashMap<>();
        Customer customerInfo = customerDao.getCustomerByID(cid);
        customerInfo.setCustomerName(stringUtil.GBKToUTF8(customerInfo.getCustomerName()));
        customerInfo.setCustomerAgent(stringUtil.GBKToUTF8(customerInfo.getCustomerAgent()));
        customerInfo.setCustomerAgent1(stringUtil.GBKToUTF8(customerInfo.getCustomerAgent1()));
        customerInfo.setJuridicPerson(stringUtil.GBKToUTF8(customerInfo.getJuridicPerson()));

        List<CustomerBalancePeriod> customerBalancePeriodList = customerBalancePeriodDao.getCustomerBalanceInfo(cid, page, lastNum);
        for(int i = 0; i < customerBalancePeriodList.size() ; i++){
            Map<String, Object> mapChange= mapUtils.beanToMap(customerBalancePeriodList.get(i));
            for (Map.Entry<String,Object> entry : mapChange.entrySet()) {
                if(entry.getValue() instanceof String){
                    String origin = stringUtil.GBKToUTF8(String.valueOf(entry.getValue()));
                    entry.setValue(origin);
                }
//                  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }
             customerBalancePeriodList.set(i,mapUtils.mapToBean(mapChange, CustomerBalancePeriod .class)) ;
        }
        map.put("code",0);
        map.put("customerInfo",customerInfo);
        map.put("customerBalancePeriodList",customerBalancePeriodList);
        return map;
    }

    @Override
    public Map getCustomerBalancePeriodDetailInfo(String cid, String startDate, String endDate, Integer page, Integer lastNum){
        cid = changeLoginNameToCompanyID(cid);
        Map<String, Object> map = new HashMap<>();
        List<CustomerBalancePeriodDetail> customerBalancePeriodDetailList = customerBalancePeriodDetailDao.getCustomerBalancePeriodDetailInfo(cid,startDate,endDate, page, lastNum);
        for(int i = 0; i<customerBalancePeriodDetailList.size() ; i++){
            CustomerBalancePeriodDetail customerBalancePeriodDetail = customerBalancePeriodDetailList.get(i);
            if(customerBalancePeriodDetail.getNotes() != null) {
                customerBalancePeriodDetail.setNotes(stringUtil.GBKToUTF8(customerBalancePeriodDetail.getNotes()));
            }
            if(customerBalancePeriodDetail.getBillNo() != null){
                customerBalancePeriodDetail.setBillNo(stringUtil.GBKToUTF8(customerBalancePeriodDetail.getBillNo()));
            }
        }
        map.put("customerBalancePeriodDetailList",customerBalancePeriodDetailList);
        map.put("code",0);
        return map;
    }

    @Override
    public Map customerCheck(String cid, String startDate,
                             String customerCheckState,
                             String customerCheckComment) {
        cid = changeLoginNameToCompanyID(cid);
        Map<String, Object> map = new HashMap<>();
        customerCheckState = stringUtil.UTF8ToGBK(customerCheckState);
        customerCheckComment = stringUtil.UTF8ToGBK(customerCheckComment);
        if(customerBalancePeriodDao.customerCheck(cid,startDate,customerCheckState,customerCheckComment)){
            map.put("msg","SUCCESS");
        }else{
            map.put("msg","FALSE");
        }
        map.put("code",0);
        return map;
    }

    @Override
    public Map getCustomerBalancePackDetail(String saleNO) {
        Map<String, Object> map = new HashMap<>();
        List<PackDetail> packDetailList = packDetailDao.getCustomerBalancePackDetail(saleNO);
        for(int i = 0; i<packDetailList.size() ; i++){
            PackDetail packDetail = packDetailList.get(i);
            if(packDetail.getTranscompany() != null){
                packDetail.setTranscompany(stringUtil.GBKToUTF8(packDetail.getTranscompany()));
            }
            if(packDetail.getItemNote() != null){
                packDetail.setItemNote(stringUtil.GBKToUTF8(packDetail.getItemNote()));
            }
            if(packDetail.getItemVersion() != null){
                packDetail.setItemVersion(stringUtil.GBKToUTF8(packDetail.getItemVersion()));
            }
        }
        map.put("packDetailList",packDetailList);
        map.put("code",0);
        return map;
    }

    private String changeLoginNameToCompanyID(String cid){
        return web_userDao.changeLoginNameToCompanyID(cid);
    }
}
