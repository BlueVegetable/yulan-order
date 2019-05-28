package com.yulan.service.impl;

import com.yulan.dao.Ctm_orderDao;
import com.yulan.dao.CustomerAssignmentsDao;
import com.yulan.service.CustomerAssignmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomerAssignmentsServiceImpl implements CustomerAssignmentsService {
    @Autowired
    private CustomerAssignmentsDao customerAssignmentsDao;
    @Autowired
    private Ctm_orderDao ctm_orderDao;


    @Override
    public Map getAssignments(Map<String, Object> map) {
        return null;
    }
}
