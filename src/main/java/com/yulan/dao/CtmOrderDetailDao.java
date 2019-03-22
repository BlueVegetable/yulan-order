package com.yulan.dao;

import com.yulan.pojo.CtmOrderDetail;
import org.apache.ibatis.annotations.Param;

public interface CtmOrderDetailDao {
    CtmOrderDetail getCtmOrderDetailAppoint(@Param("itemNo") String itemNo, @Param("orderId") String orderId);
}
