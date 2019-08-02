package com.yulan.service;

import com.yulan.pojo.CtmOrderDetail;

import java.util.List;

public interface CtmOrderDetailService {
    List<CtmOrderDetail> getCtmOrderDetailAppoint(String itemNo, String orderId);
}
