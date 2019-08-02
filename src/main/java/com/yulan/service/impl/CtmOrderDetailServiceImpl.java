package com.yulan.service.impl;

import com.yulan.dao.CtmOrderDetailDao;
import com.yulan.pojo.CtmOrderDetail;
import com.yulan.service.CtmOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CtmOrderDetailServiceImpl implements CtmOrderDetailService {
    @Autowired
    private CtmOrderDetailDao ctmOrderDetailDao;
    @Override
    public List<CtmOrderDetail> getCtmOrderDetailAppoint(String itemNo, String orderId) {
        return ctmOrderDetailDao.getCtmOrderDetailAppoint(itemNo, orderId);
    }
}
