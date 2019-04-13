package com.yulan.service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface Ctm_orderService {
   //获取订单列表
   Map getOrders(Integer st,Integer number,String cid,String state_id,String find,String beginTime,String finishTime,String orderType,String curtainStatusId) throws UnsupportedEncodingException;

   //获取订单详情
   Map getOrderB_content(String order_no,String item_no) throws UnsupportedEncodingException;

   //获取活动价
   Map getPromotion(List<Map<String,Object>> list);

   //获取用户余额
   BigDecimal getResidemoney(String cid);

   boolean updateOrderStatus(String orderNo, String customerCode, String statusId);

   //订单结算
   Map orderCount(Map<String,Object> map) throws InvocationTargetException, IllegalAccessException, UnsupportedEncodingException;

   //查看物流
   Map getPack(Map<String,Object> m) throws UnsupportedEncodingException;

   //获取经办人/联系方式
   Map getlinkpersonandTel(String cid) throws UnsupportedEncodingException;

   //获取客户优惠券
   Map getRebate(Map<String,Object>map) throws UnsupportedEncodingException;

   //点击优惠券显示摊分明细
   Map showRebate(Map<String,Object>map) throws InvocationTargetException, IllegalAccessException;

   //订单取消
   Map cancelOrder(Map<String,Object>map);

   //订单重新提交
   Map putAgainOrder(Map<String, Object>map);
}
