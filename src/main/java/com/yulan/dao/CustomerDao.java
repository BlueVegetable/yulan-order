package com.yulan.dao;

import com.yulan.pojo.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerDao {
    /**
     * 通过客户代码，所属市场，客户类型，客户区域筛选获取数据
     * @param customerCode 客户代码
     * @return
     */
    List<Customer> getCustomers(@Param("customerCode")String customerCode, @Param("areaCodes")List<String> areaCodes,
                                @Param("areaDistricts")List<String> areaDistricts, @Param("customerTypes")List<String> customerTypes);
    List<Customer> getCustomersByGroupID(@Param("groupID")String groupID);

    Customer getCustomerByID(@Param("CID")String cid);
}