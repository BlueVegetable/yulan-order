package com.yulan.service;

import com.yulan.pojo.Customer;

import java.util.List;

public interface CustomerService {
    /**
     * 通过客户代码，所属市场，客户类型，客户区域筛选获取数据
     * @param customerCode 客户代码
     * @param areaCode 所属市场
     * @param areaDistrict 客户区域
     * @param customerType 客户类型
     * @return
     */
    List<Customer> getCustomers(String customerCode,List<String> areaCode,List<String> areaDistrict,List<String> customerType);
    List<Customer> getCustomersByGroupID(String groupID);
}
