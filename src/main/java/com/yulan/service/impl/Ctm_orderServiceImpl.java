package com.yulan.service.impl;

import com.yulan.dao.Ctm_orderDao;
import com.yulan.pojo.Sal_promotion;
import com.yulan.service.Ctm_orderService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Ctm_orderServiceImpl implements Ctm_orderService {
    @Autowired
    private Ctm_orderDao ctm_orderDao;


    @Override
    public Map getOrders(Integer start, Integer number, String cid, String state_id, String find,String beginTime,String finishTime) throws UnsupportedEncodingException {
        Map<String,Object> map=new HashMap<>();
        List<Map<String,Object>> list=ctm_orderDao.getOrdersH(start,number,cid,state_id,find,beginTime,finishTime);
        List<Map<String,Object>> data=new ArrayList<>();
        map.put("count",ctm_orderDao.countOrdersH(cid,state_id,find,beginTime,finishTime));
        for (Map<String,Object> m:list) {

            for (Map.Entry<String, Object> entry : m.entrySet()) {//将订单头内容转码
                if (entry.getValue() instanceof String) {
                    String origin = StringUtil.getUtf8(String.valueOf(entry.getValue()));
                    entry.setValue(origin);
                }
            }
            List<Map<String,Object>> list2=ctm_orderDao.getOrdersB(m.get("ORDER_NO").toString());
            int orderB_num=0;
            for (Map<String,Object> m2:list2) {//将订单具体内容转码
                for (Map.Entry<String, Object> entry : m2.entrySet()) {
                    if (entry.getValue() instanceof String) {
                        String origin = StringUtil.getUtf8(String.valueOf(entry.getValue()));
                        entry.setValue(origin);
                    }
                }
                orderB_num++;
            }
            m.put("OREDERB_NUM",orderB_num);//订单商品数量
            m.put("ORDERBODY",list2);
            data.add(m);
        }


        map.put("data",data);

        return map;
    }

    @Override
    public Map getOrderB_content(String order_no, String item_no) throws UnsupportedEncodingException {
        Map<String,Object> map=ctm_orderDao.getOrderB_content(order_no,item_no);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof String) {
                String origin = StringUtil.getUtf8(String.valueOf(entry.getValue()));
                entry.setValue(origin);
            }
        }
        return map ;
    }

    @Override
    public Map getPromotion(List<Map<String, Object>> list) {
        Map<String,Object> map=new HashMap<>();
        List<Map<String,Object>> data=new ArrayList<>();
        for (Map<String, Object> m:list){
            BigDecimal promotion_cost=BigDecimal.valueOf(0.0);
            BigDecimal num=BigDecimal.valueOf((int)m.get("num"));
            String order_type=m.get("order_type").toString();
            BigDecimal prime_cost= BigDecimal.valueOf((int) m.get("prime_cost"));
            Sal_promotion sal_promotion=ctm_orderDao.getPromotion(order_type);
            BigDecimal discount=sal_promotion.getDiscount();
            BigDecimal price=sal_promotion.getPrice();
            if (discount!=null){
                promotion_cost= discount.multiply(prime_cost).setScale(2);//乘法保留两位小数
            }else{
                promotion_cost= price.multiply(num).setScale(2);
            }
            m.put("promotion_cost",promotion_cost);
            data.add(m);

        }
        map.put("data",data);
        return map;
    }

    @Override
    public BigDecimal getResidemoney(String cid) {
        return ctm_orderDao.getResidemoney(cid);
    }

    @Override
    public boolean updateOrderStatus(String orderNo, String customerCode,
                                     String statusId) {
        return ctm_orderDao.updateOrderStatus(orderNo,customerCode,statusId);
    }
}
