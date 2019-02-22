package com.yulan.service.impl;

import com.yulan.dao.CustomerInfoCardDao;
import com.yulan.pojo.CustomerInfoCard;
import com.yulan.service.CustomerInfoCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerInfoCardServiceImpl implements CustomerInfoCardService {
    @Autowired private CustomerInfoCardDao customerInfoCardDao;
    @Override
    public boolean addCustomerInfoCard(CustomerInfoCard customerInfoCard) {
        return customerInfoCardDao.addCustomerInfoCard(customerInfoCard)>0;
    }
}
