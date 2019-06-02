package com.yulan.service.impl;

import com.yulan.service.Ctm_orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SpringQtzJob {
    @Autowired
    private Ctm_orderService ctmOrderService;
    public void execute() {
        ctmOrderService.autoCancelOrder();
    }
}
