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
            String order_no=m.get("ORDER_NO").toString();
            List<Map<String,Object>> list2=ctm_orderDao.getOrdersB(order_no);
            int orderB_num=0;
            for (Map<String,Object> m2:list2) {//将订单具体内容转码
                List<Map<String,Object>> list3=ctm_orderDao.getPackDetail(cid,order_no,m2.get("ITEM_NO").toString());
                if(list3.size()!=0){
                    m2.put("pack_id",1);//是否可以查看物流判断，1可以，0不可以
                }else{
                    m2.put("pack_id",0);
                }
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
        ctm_order.setWebTjTime(new Date(System.currentTimeMillis()));//获取当前时间
        BigDecimal promotion_cost=BigDecimal.valueOf((Double) map.get("promotion_cost"));//活动后总价
        //预留优惠券
        BigDecimal resideMoney=ctm_orderDao.getResideMoney(cid);



        if (resideMoney.compareTo(promotion_cost)==-1){//余额不足
            ctm_order.setStatusId("12");//已提交
        }else{
            ctm_order.setStatusId("5");//欠款待提交
        }

        ctm_orderDao.insertOrderH(ctm_order);

        if (ctm_orderDao.insertOrderH(ctm_order)){//订单头录入
            for (Ctm_order_detail ctm_order_detail:list){//订单详情录入
                if (!ctm_orderDao.insertOrderB(ctm_order_detail)){

                    m.put("code",1);
                    m.put("msg","FLASE");
                    break;
                }
            }
            m.put("code",0);
            m.put("msg","SUCCESS");

        }




        return m;
    }

    @Override
    public Map getPack(Map<String, Object> m) throws UnsupportedEncodingException {
        String cid=m.get("cid").toString();
        String order_no=m.get("order_no").toString();
        String item_no=m.get("item_no").toString();
        BigDecimal allNum=ctm_orderDao.getNum(order_no,item_no);
        m.put("all_num",allNum);//总数
        List<Map<String,Object>> list=ctm_orderDao.getPackDetail(cid,order_no,item_no);
        for (Map<String,Object> m1:list){
            BigDecimal thisNum=(BigDecimal)m1.get("QTY_DELIVER");
            allNum=allNum.subtract(thisNum);
            for (Map.Entry<String, Object> entry : m1.entrySet()) {
                if (entry.getValue() instanceof String) {
                    String origin = StringUtil.getUtf8(String.valueOf(entry.getValue()));
                    entry.setValue(origin);
                }
            }
        }
        m.put("data",list);
        m.put("msg","SUCCESS");
        m.put("else_num",allNum);//剩余数量

        return m;
    }

    @Override
    public boolean updateOrderStatus(String orderNo, String customerCode,
                                     String statusId) {
        return ctm_orderDao.updateOrderStatus(orderNo,customerCode,statusId);
    }
}
