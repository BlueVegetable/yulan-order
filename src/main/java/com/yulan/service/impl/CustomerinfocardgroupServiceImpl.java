package com.yulan.service.impl;

import com.yulan.dao.CustomerinfocardgroupDao;
import com.yulan.pojo.Customerinfocardgroup;
import com.yulan.service.CustomerinfocardgroupService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CustomerinfocardgroupServiceImpl implements CustomerinfocardgroupService {
    @Autowired
    private CustomerinfocardgroupDao customerinfocardgroupDao;

    @Override
    public boolean addCustomerInfoCardGroup(Customerinfocardgroup customerinfocardgroup) {
        return customerinfocardgroupDao.addCustomerInfoCardGroup(customerinfocardgroup)>0;
    }

    @Override
    public Map getCustomerinfocardgroups(Integer start, Integer number, String descp,int deleted) throws UnsupportedEncodingException {
        Map map=new HashMap<String,Object>(2);
        List<Customerinfocardgroup> list2=new ArrayList<>();
        List<Customerinfocardgroup> list=customerinfocardgroupDao.getCustomerinfocardgroups(start,number,descp,deleted);
        for (Customerinfocardgroup c:list){

            c.setDescp(StringUtil.getUtf8(c.getDescp()));
            c.setArea_codeName(getNames(c.getArea_code(),"area_code"));
            c.setArea_districtName(getNames(c.getArea_district(),"area_district"));
            c.setCustomer_typeName(getNames(c.getCustomer_type(),"customer_type"));
            list2.add(c);
        }

        map.put("data",list2);
        map.put("count",customerinfocardgroupDao.count(descp));
        return  map;

    }

    @Override
    public Customerinfocardgroup getCustomerInfoCardGroupByName(String descp) {
        return customerinfocardgroupDao.getCustomerInfoCardGroupByName(descp);
    }

    @Override
    public int updateDelete(String Id) {
        Integer deleted=1;
        return customerinfocardgroupDao.updateDelete(Id,deleted);
    }

    /**
     * 获取名
     * @param
     * @param diff
     * @return
     * @throws UnsupportedEncodingException
     */
    public  String getNames(String S,String diff) throws UnsupportedEncodingException {
        String string="";
        if(S==null){
            return "";
        }
        String[] strings = S.split(",");
        int length=strings.length;
        int i=0;
        for (String s:strings){
            i++;
            if (diff.equals("area_code")){
                if (customerinfocardgroupDao.getArea_codeName(s)==null||
                        customerinfocardgroupDao.getArea_codeName(s).equals("")){
                    continue;
                }else{
                    string+=StringUtil.getUtf8(customerinfocardgroupDao.getArea_codeName(s));
                }

            }else if (diff.equals("area_district")){
                if (customerinfocardgroupDao.getArea_districtName(s)==null||
                        customerinfocardgroupDao.getArea_districtName(s).equals("")){
                    continue;
                }else{
                    string+=StringUtil.getUtf8(customerinfocardgroupDao.getArea_districtName(s));
                }

            }else{
                if (customerinfocardgroupDao.getCustomer_typeName(s)==null||
                        customerinfocardgroupDao.getCustomer_typeName(s).equals("")){
                    continue;
                }else{
                    string+=StringUtil.getUtf8(customerinfocardgroupDao.getCustomer_typeName(s));
                }

            }
            if (i!=length){
                string+=",";
            }

        }
        return string;

    }
}
