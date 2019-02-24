package com.yulan.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface Ctm_orderService {
   //获取订单列表
   Map getOrders(Integer st,Integer number,String cid,String state_id,String find,String beginTime,String finishTime) throws UnsupportedEncodingException;

   //获取订单详情
   Map getOrderB_content(String order_no,String item_no) throws UnsupportedEncodingException;

   //获取活动价
   Map getPromotion(List<Map<String,Object>> list);

   //获取用户余额
   BigDecimal getResidemoney(String cid);

   //订单结算
//   Map
}
