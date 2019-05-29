package com.yulan.service.impl;

import com.yulan.dao.Ctm_orderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class SpringQtzJob {
    @Autowired
    private Ctm_orderDao ctmOrderDao;
    public void execute() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(simpleDateFormat.format(date));
    }
}
