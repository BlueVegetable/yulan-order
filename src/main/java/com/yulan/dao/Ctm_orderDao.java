package com.yulan.dao;

import com.yulan.pojo.Ctm_order;
import com.yulan.pojo.Ctm_order_detail;
import com.yulan.pojo.Sal_promotion;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
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
                                       @Param("find")String find,@Param("beginTime") String beginTime,
                                        @Param("finishTime") String finishTime );
    //获取订单具体内容
    List<Map<String,Object>> getOrdersB(@Param("order_no")String order_no);
    Integer countOrdersH(@Param("cid")String cid,@Param("state_id")String state_id,
                        @Param("find")String find,@Param("beginTime") String beginTime,
                         @Param("finishTime") String finishTime);

    /**
     * 获取订单详情
     */
    Map<String,Object> getOrderB_content(@Param("order_no")String order_no,@Param("item_on")String item_no);

    /**
     * 获取订单页面活动
     */
    Sal_promotion getPromotion(@Param("order_type") String order_type);

    /**
     * 获取客户余额
     */
    BigDecimal getResideMoney(@Param("cid") String cid);

    /**
     * 新增订单头
     * @param ctm_order
     * @return
     */
    boolean insertOrderH(Ctm_order ctm_order);

    /**
     * 新增订单
     * @param ctm_order_detail
     * @return
     */
    boolean insertOrderB(Ctm_order_detail ctm_order_detail);
}
