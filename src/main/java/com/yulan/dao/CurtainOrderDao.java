package com.yulan.dao;

import com.yulan.pojo.CurtainOrder;
import org.apache.ibatis.annotations.Param;

public interface CurtainOrderDao {
    CurtainOrder getCurtainOrder(@Param("orderNo")String orderNo,@Param("lineNo")String lineNO);

    Boolean insertCurtainOrder(CurtainOrder curtainOrder);

}
