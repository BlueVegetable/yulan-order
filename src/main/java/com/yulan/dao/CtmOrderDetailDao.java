package com.yulan.dao;

import com.yulan.pojo.CtmOrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CtmOrderDetailDao {
    List<CtmOrderDetail> getCtmOrderDetailAppoint(@Param("itemNo") String itemNo, @Param("orderId") String orderId);
}
