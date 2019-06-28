package com.yulan.dao;

import com.yulan.pojo.PackDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PackDetailDao {
    List<PackDetail> getPackDetailAppoint(@Param("itemNo") String itemNo,@Param("orderId")String orderId);

    List<PackDetail> getCustomerBalancePackDetail(@Param("saleNO") String saleNO);
}
