package com.yulan.service.impl;

import com.yulan.dao.PackDetailDao;
import com.yulan.pojo.PackDetail;
import com.yulan.service.PackDetailService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackDetailServiceImpl implements PackDetailService {
    @Autowired
    private PackDetailDao packDetailDao;
    @Override
    public List<PackDetail> getPackDetailAppoint(String itemNo, String orderId) {
        List<PackDetail> packDetails = packDetailDao.getPackDetailAppoint(itemNo, orderId);
        for (PackDetail packDetail:packDetails) {
            String company = packDetail.getTranscompany();
            company = StringUtil.GBKToUTF8(company);
            packDetail.setTranscompany(company);
        }
        return packDetails;
    }
}
