package com.yulan.service.impl;

import com.yulan.dao.CustomerDao;
import com.yulan.pojo.Customer;
import com.yulan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> getCustomers(String customerCode, List<String> areaCode, List<String> areaDistrict, List<String> customerType) {
        return customerDao.getCustomers(customerCode,areaCode,areaDistrict,customerType);
    }

    @Override
    public List<Customer> getCustomersByGroupID(String groupID) {
        return customerDao.getCustomersByGroupID(groupID);
    }
}
