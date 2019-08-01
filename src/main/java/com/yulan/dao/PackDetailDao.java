package com.yulan.dao;

import com.yulan.pojo.PackDetail;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PackDetailDao {
    List<PackDetail> getPackDetailAppoint(@Param("lineNo") BigDecimal lineNo, @Param("orderId")String orderId);

    List<PackDetail> getCustomerBalancePackDetail(@Param("saleNO") String saleNO);
}
