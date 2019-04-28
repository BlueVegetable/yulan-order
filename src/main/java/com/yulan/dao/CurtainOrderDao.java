package com.yulan.dao;

import com.yulan.pojo.CurtainOrder;
import org.apache.ibatis.annotations.Param;

public interface CurtainOrderDao {

    /**
     * 查看窗帘
     * @param orderNo
     * @param lineNo
     * @return
     */
    CurtainOrder getCurtainOrder(@Param("orderNo")String orderNo,@Param("lineNo")String lineNo);

    String getTypeName(@Param("productType")String productType);


    Boolean insertCurtainOrder(CurtainOrder curtainOrder);

    /**
     * 修改窗帘信息
     * @param curtainOrder
     * @return
     */
    Boolean updateCurtainOrder(CurtainOrder curtainOrder);

    /**
     * 改变窗帘订单头状态
     * @param orderNo
     * @param curtainStatusId
     * @return
     */
    Boolean updateCurOrderStatus(@Param("orderNo")String orderNo,@Param("curtainStatusId")String curtainStatusId);




}
