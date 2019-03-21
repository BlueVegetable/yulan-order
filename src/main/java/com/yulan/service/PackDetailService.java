package com.yulan.service;

import com.yulan.pojo.PackDetail;

import java.util.List;

public interface PackDetailService {
    List<PackDetail> getPackDetailAppoint(String itemNo,String orderId);
}
