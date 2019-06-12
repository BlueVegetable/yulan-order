package com.yulan.dao;

import com.yulan.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
                                        @Param("finishTime") String finishTime ,@Param("orderType")String orderType,
                                        @Param("curtainStatusId")String curtainStatusId,@Param("users")List<String> users);

    //管理员获取所有订单
    List<Map<String,Object>> getOrdersManager(@Param("start")Integer start, @Param("number") Integer number,
                                        @Param("cid")String cid, @Param("state_id")String state_id,
                                        @Param("find")String find,@Param("beginTime") String beginTime,
                                        @Param("finishTime") String finishTime ,@Param("orderType")String orderType,
                                        @Param("curtainStatusId")String curtainStatusId);

    //获取订单具体内容
    List<Map<String,Object>> getOrdersB(@Param("order_no")String order_no);

    //查找是否有出货单
    List<PackDetail> findPackDetail(@Param("orderNo")String orderNo,@Param("itemNo")String itemNo );

    //获取订单提货单号及其订单运输详情
    List<Map<String,Object>> getPackDetail(@Param("order_no")String order_no,@Param("item_no") String item_no);

    //获取订单型号数量
    BigDecimal getNum(@Param("order_no")String order_no,@Param("item_no")String item_no);

    Integer countOrdersH(@Param("cid")String cid,@Param("state_id")String state_id,
                        @Param("find")String find,@Param("beginTime") String beginTime,
                         @Param("finishTime") String finishTime,@Param("orderType")String orderType,
                         @Param("curtainStatusId")String curtainStatusId,@Param("users")List<String> users);

    Integer countOrdersManager(@Param("cid")String cid,@Param("state_id")String state_id,
                         @Param("find")String find,@Param("beginTime") String beginTime,
                         @Param("finishTime") String finishTime,@Param("orderType")String orderType,
                         @Param("curtainStatusId")String curtainStatusId);

    /**
     * 获取订单详情
     */
    List<Map<String,Object>> getOrderContent(@Param("orderNO")String orderNO);

    /**
     * 获取订单页面活动
     */
    Sal_promotion getPromotion(@Param("pId") String pId);

    /**
     * 获取客户余额
     */
    BigDecimal getResideMoney(@Param("companyId") String companyId);

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

    /**
     * 获取订单头部字母
     * @param
     * @return
     */
    String getType_word(@Param("item_no")String item_no);//Y则是订单W

    /**
     * 修改订单状态
     * @param orderNo
     * @param customerCode
     * @param statusId
     * @return
     */
    boolean updateOrderStatus(@Param("ORDER_NO")String orderNo,
                              @Param("CUSTOMER_CODE")String customerCode,
                              @Param("STATUS_ID")String statusId,@Param("dateUpdate") Timestamp dateUpdate);

    /**
     * 获取最大订单号自增
     * @param s
     * @return
     */
    String getBigNum(@Param("s")String s);

    /**
     * 获取订单头经办人和电话
     * @param users
     * @return
     */
    Map<String,Object> getlinkpersonandTel(@Param("users")List<String> users);


    /**
     * 获取客户优惠券
     */
    List<Sal_rebate_certificate> getRebate(@Param("cid")String cid,@Param("currentTime")java.sql.Date currentTime,@Param("beforeTime")java.sql.Date beforeTime);

    /**
     * 获取特点优惠券
     * @param id
     * @return
     */
    Sal_rebate_certificate getRebateById(@Param("id")String id);

    /**
     * 更新优惠券剩余金额
     * @param id
     * @param rebateMoneyOver
     * @return
     */
    Boolean updateRebatemoney(@Param("id")String id,@Param("rebateMoneyOver")BigDecimal rebateMoneyOver);

    /**
     * 记录优惠券使用记录
     * @param sal_rebate_certificate_record
     * @return
     */
    Boolean insertRebateRecord(Sal_rebate_certificate_record sal_rebate_certificate_record);

    /**
     * 查找订单头
     * @param orderNo
     * @return
     */
    Ctm_order getOrderH(@Param("orderNo") String orderNo);

    /**
     * 查找订单详情
     * @param orderNo

     * @return
     */
    List<Ctm_order_detail> getOrderB(@Param("orderNo") String orderNo);

    /**
     * 取消订单时优惠券金额回复
     */

    /**
     * 更新订单头
     * @param ctm_order
     * @return
     */
    Boolean updateOrder(Ctm_order ctm_order);

    /**
     * 更新订单详情
     * @param ctm_order_detail
     * @return
     */
    Boolean updateOrderB(Ctm_order_detail ctm_order_detail);

    /**
     * 寻找优惠券使用记录，用于取消订单时优惠券金额返回
     * @param id
     * @return
     */
    List<Sal_rebate_certificate_record> findRecrod(@Param("id")String id);


    /**
     * 寻找优惠券
     * @param id
     * @return
     */
    Sal_rebate_certificate findRebate(@Param("id")String id);

    /**
     * 更新优惠券剩余金额
     * @param sal_rebate_certificate
     * @return
     */
    Boolean updateRebate(Sal_rebate_certificate sal_rebate_certificate);

    /**
     * 更新优惠券使用记录状态
     * @param statusId
     * @return
     */
    Boolean updateRecord(@Param("statusId")String statusId,@Param("orderNo")String orderNo);


    /**
     * 任务查询是获取月任务的订单
     * @param beginTime
     * @param finishTime
     * @param users
     * @return
     */
    List<Map<String,Object>> getOrderCusAss(@Param("beginTime") String beginTime,@Param("finishTime") String finishTime,@Param("users")List<String> users);


    /**
     * 获取所有欠款未提交的订单
     * @return
     */
    List<Ctm_order> getAllCtms();

    /**
     * 通过订单号获取客户号
     * @param orderNo
     * @return
     */
    String getCidByOrderNo(@Param("orderNo") String orderNo);




}
