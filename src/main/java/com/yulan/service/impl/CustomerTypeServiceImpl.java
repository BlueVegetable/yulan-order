package com.yulan.service.impl;

import com.yulan.dao.CustomerTypeDao;
import com.yulan.pojo.CustomerType;
import com.yulan.service.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {
    @Autowired
    private CustomerTypeDao customerTypeDao;
    @Override
    public CustomerType getCustomerTypeByCID(String CID) {
        return customerTypeDao.getCustomerTypeByCID(CID);
    }
}
