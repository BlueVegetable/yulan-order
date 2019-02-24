package com.yulan.service.impl;

import com.yulan.dao.Ctm_orderDao;
import com.yulan.pojo.Ctm_order;
import com.yulan.pojo.Ctm_order_detail;
import com.yulan.pojo.Sal_promotion;
import com.yulan.service.Ctm_orderService;
import com.yulan.utils.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Date;
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
        return ctm_orderDao.getResideMoney(cid);
    }

    @Override
    public Map orderCount(Map<String, Object> map) throws InvocationTargetException, IllegalAccessException {
        Map m=new HashMap();
        Ctm_order ctm_order=new Ctm_order();

        String cid= map.get("cid").toString();
        BeanUtils.populate(ctm_order,(Map<String, Object>)map.get("ctm_order"));
        List<Ctm_order_detail> list=(List) map.get("ctm_orders");

        BigDecimal promotion_cost=BigDecimal.valueOf((Double) map.get("promotion_cost"));//活动后总价
        //预留优惠券
        BigDecimal resideMoney=ctm_orderDao.getResideMoney(cid);
        if (1==1){//预留余额判断
            ctm_order.setStatusId("12");
        }
        ctm_order.setWebTjTime(new Date(System.currentTimeMillis()));//获取当前时间
        ctm_orderDao.insertOrderH(ctm_order);

        if (ctm_orderDao.insertOrderH(ctm_order)){
            for (Ctm_order_detail ctm_order_detail:list){
                if (!ctm_orderDao.insertOrderB(ctm_order_detail)){

                    m.put("code",1);
                    m.put("code","FLASE");
                    break;
                }
            }
            m.put("code",0);
            m.put("code","SUCCESS");

        }




        return m;
    }
}
