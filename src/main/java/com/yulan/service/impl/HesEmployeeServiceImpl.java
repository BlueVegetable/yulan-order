package com.yulan.service.impl;

import com.yulan.dao.HesEmployeeDao;
import com.yulan.service.HesEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HesEmployeeServiceImpl implements HesEmployeeService {
    @Autowired
    private HesEmployeeDao hesEmployeeDao;
    @Override
    public String getHesEmployeeNameByNO(String employeeNO) {
        return hesEmployeeDao.getHesEmployeeNameByNO(employeeNO);
    }
}
