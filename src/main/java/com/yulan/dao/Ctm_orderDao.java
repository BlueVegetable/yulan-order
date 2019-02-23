package com.yulan.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Ctm_orderDao {
    List<Map<String,Object>> getOrders(@Param("start")Integer start, @Param("number") Integer number,
                                       @Param("cid")String cid, @Param("state_id")String state_id,
                                       @Param("find")String find);

    Integer countOrders(@Param("cid")String cid,@Param("state_id")String state_id,
                        @Param("find")String find);


    /**
     * 新需求 获取加统计
     */
    //获取订单头
    List<Map<String,Object>> getOrdersH(@Param("start")Integer start, @Param("number") Integer number,
                                       @Param("cid")String cid, @Param("state_id")String state_id,
                                       @Param("find")String find);
    //获取订单具体内容
    List<Map<String,Object>> getOrdersB(@Param("order_no")String order_no);
    Integer countOrdersH(@Param("cid")String cid,@Param("state_id")String state_id,
                        @Param("find")String find);

    /**
     * 获取订单详情
     */
    Map<String,Object> getOrderB_content(@Param("order_no")String order_no,@Param("item_on")String item_no);

}
