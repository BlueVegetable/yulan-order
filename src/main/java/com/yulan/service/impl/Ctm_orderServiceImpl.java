package com.yulan.service.impl;

import com.yulan.dao.Ctm_orderDao;
import com.yulan.pojo.Ctm_order;
import com.yulan.pojo.Ctm_order_detail;
import com.yulan.pojo.Sal_promotion;
import com.yulan.pojo.Sal_rebate_certificate;
import com.yulan.service.Ctm_orderService;
import com.yulan.utils.MapUtils;
import com.yulan.utils.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
                BigDecimal unit_price=(BigDecimal)m2.get("UNIT_PRICE");
                BigDecimal num=(BigDecimal)m2.get("QTY_REQUIRED");
                m2.put("all_cost",unit_price.multiply(num));
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
            String numString=m.get("num").toString();
            BigDecimal num=BigDecimal.valueOf(Double.valueOf(numString));
            String order_type=m.get("order_type").toString();
            if (order_type==null||order_type.equals("")){
                m.put("promotion_cost",m.get("prime_cost"));
                data.add(m);
                continue;
            }
            String prime_costString=m.get("prime_cost").toString();
            BigDecimal prime_cost= BigDecimal.valueOf(Double.valueOf(prime_costString));
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
    public Map orderCount(Map<String, Object> map) throws InvocationTargetException, IllegalAccessException, UnsupportedEncodingException {
        String product_group_tpye=map.get("product_group_tpye").toString();

//        String rebateY=map.get("rebateY").toString();//年优惠券流水号
//        String rebateM=map.get("rebateM").toString();//月优惠券流水号

        Map m=new HashMap();
        Ctm_order ctm_order=new Ctm_order();

        String order=this.getOrderNumber(product_group_tpye);
        ctm_order.setOrderNo(order);//生成订单号

        String cid= map.get("cid").toString();

        Map<String ,Object> ctm_ordermap=(Map<String, Object>) map.get("ctm_order");
        for (Map.Entry<String, Object> entry : ctm_ordermap.entrySet()) {//转码
            if (entry.getValue() instanceof String) {
                String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                entry.setValue(origin);
            }
        }


        BeanUtils.populate(ctm_order,ctm_ordermap);//转为Ctm_order类
        Map<String,Object> linkpersonandTelmap=ctm_orderDao.getlinkpersonandTel(cid);

        ctm_order.setLinkperson(linkpersonandTelmap.get("CUSTOMER_AGENT").toString());//经办人

        ctm_order.setTelephone(linkpersonandTelmap.get("OFFICE_TEL").toString());//经办人电话

        List<Map<String,Object>> list=(List) map.get("ctm_orders");
        ctm_order.setWebTjTime(new Timestamp(System.currentTimeMillis()));//获取当前时间
        ctm_order.setDateCre(new Timestamp(System.currentTimeMillis()));//获取当前时间
        ctm_order.setDateUpdate(new Timestamp(System.currentTimeMillis()));//获取当前时间
        ctm_order.setCurrencyId("RMB");
        ctm_order.setCustomerCode(cid);
        String promotion_costString=map.get("promotion_cost").toString();//先变字符串
        BigDecimal promotion_cost=BigDecimal.valueOf(Double.valueOf(promotion_costString));//活动后总价


        //初始为零
        BigDecimal money_m=BigDecimal.valueOf(0);
        BigDecimal money_y=BigDecimal.valueOf(0);
        BigDecimal money=BigDecimal.valueOf(0);
//        if(!(rebateY.equals("")&&rebateM.equals(""))){//同时选了年和月
//            Sal_rebate_certificate rebate_y=ctm_orderDao.getRebateById(rebateY);//年优惠券
//            Sal_rebate_certificate rebate_m=ctm_orderDao.getRebateById(rebateM);//月优惠券
//            money_m=rebate_m.getRebateMoneyOver();
//            money_y=rebate_y.getRebateMoneyOver();
//        }else if (rebateY.equals("")&&!rebateM.equals("")){//月
//            Sal_rebate_certificate rebate_m=ctm_orderDao.getRebateById(rebateM);//月优惠券
//            money_m=rebate_m.getRebateMoneyOver();
//        }else if(!rebateY.equals("")&&rebateM.equals("")){//年
//            Sal_rebate_certificate rebate_y=ctm_orderDao.getRebateById(rebateY);//年优惠券
//            money_y=rebate_y.getRebateMoneyOver();
//        }
//        money=money_y.add(money_m);//优惠券+总额






//        BigDecimal resideMoney=ctm_orderDao.getResideMoney(cid).add(money);//加上优惠券
        BigDecimal resideMoney=ctm_orderDao.getResideMoney(cid);

        String statusId=" ";
        if (resideMoney.compareTo(promotion_cost)==-1){//余额不足
            statusId="12";
            ctm_order.setStatusId(statusId);//已提交
        }else{
            statusId="5";
            ctm_order.setStatusId(statusId);//欠款待提交
        }



        if (ctm_orderDao.insertOrderH(ctm_order)){//订单头录入

            int lineNo=1;
            for (Map<String,Object> m2:list){//订单详情录入
                for (Map.Entry<String, Object> entry : m2.entrySet()) {
                    if (entry.getValue() instanceof String) {
                        String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                        entry.setValue(origin);
                    }
                }
                Ctm_order_detail ctm_order_detail= MapUtils.mapToBean(m2,Ctm_order_detail.class);


                ctm_order_detail.setLineNo(lineNo++);
                ctm_order_detail.setStatusId(statusId);
                ctm_order_detail.setOrderNo(order);
                if (!ctm_orderDao.insertOrderB(ctm_order_detail)){


                    m.put("code",1);
                    m.put("msg","FLASE");
                    break;
                }
            }
            m.put("data",statusId);
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
    public Map getlinkpersonandTel(String cid) throws UnsupportedEncodingException {
        Map<String ,Object> map=ctm_orderDao.getlinkpersonandTel(cid);
        String CUSTOMER_AGENT=StringUtil.getUtf8(map.get("CUSTOMER_AGENT").toString());
        Object c=CUSTOMER_AGENT;
        map.put("CUSTOMER_AGENT",CUSTOMER_AGENT);

        return map;
    }

    /**
     * 获取客户优惠券
     * @param map
     * @return
     */
    @Override
    public Map getRebate(Map<String, Object> map) throws UnsupportedEncodingException {
        Map map1=new HashMap();
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());//当前时间
        String cid=map.get("cid").toString();
        String type=map.get("typeId").toString();

        List<Sal_rebate_certificate> list=ctm_orderDao.getRebate(cid,currentDate);
        List< Map<String, Object>> data=new ArrayList<>();
        if (!(type.equals("D")||type.equals("E")||type.equals("F"))){
            for (Sal_rebate_certificate sal_rebate_certificate:list){
                if (sal_rebate_certificate.getRebateType().equals("month")){
                    sal_rebate_certificate.setDateId("0");
                }
                Map<String, Object> map2=MapUtils.beanToMap(sal_rebate_certificate);
                for (Map.Entry<String, Object> entry : map2.entrySet()) {
                    if (entry.getValue() instanceof String) {
                        String origin = StringUtil.getUtf8(String.valueOf(entry.getValue()));
                        entry.setValue(origin);
                    }
                }
                data.add(map2);
            }
        }
        map1.put("data",data);
        map1.put("msg","SUCCESS");
        map1.put("code",0);

        return map1;
    }

    @Override
    public boolean updateOrderStatus(String orderNo, String customerCode,
                                     String statusId) {
        Timestamp dateUpdate= new Timestamp(System.currentTimeMillis());
        return ctm_orderDao.updateOrderStatus(orderNo,customerCode,statusId,dateUpdate);
    }

    /**
     * 生成订单号

     * @param product_group_tpye(A,B,C,D为墙纸,E为窗帘,F为软装)
     * @return
     */
    public String getOrderNumber(String product_group_tpye){
        String trueOrder="";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String s=df.format(new java.util.Date());
        s=s.substring(2);//获取时间后6位，如20190227得190227
        String orderN="";

        //确定订单号头字母
        if(product_group_tpye.equals("E")){//窗帘
            orderN="X";
        }else if(product_group_tpye.equals("F")){//软装
            orderN="Y";
        }else{//墙纸
            orderN="W";
        }
        String order=ctm_orderDao.getBigNum(orderN+s);
        if(order==null||order.equals("")){
            trueOrder=orderN+s+"0001b";
        }else{//截取自增
            order=order.substring(7,11);
            int o=10000;
            Integer i=Integer.parseInt(order);
            o=o+i+1;
            String p=o+"";
            p=p.substring(1);
            trueOrder=orderN+s+p+"b";//b为b2b订单号标志
        }
        return trueOrder;

    }

    /**
     * 计算返利点
     * @param promotion_cost 商品总价
     * @param allMoney 返利券总价
     * @param money 返利券
     * @param thisMoney 商品价格
     * @return 返利
     *
     */
    public BigDecimal getBackMoney(BigDecimal promotion_cost,BigDecimal allMoney,BigDecimal money,BigDecimal thisMoney){



        BigDecimal backMoney=BigDecimal.valueOf(0);
        if(money.compareTo(BigDecimal.valueOf(0))==0){
            return backMoney;
        }

        if (promotion_cost.compareTo(allMoney)==1){//价格大于优惠券

            backMoney=((thisMoney.divide(promotion_cost)).multiply(money.divide(allMoney))).multiply(money);//返利
        }else  if (promotion_cost.compareTo(allMoney)==-1){
            backMoney=((thisMoney.divide(promotion_cost)).multiply(money));
        }
        return backMoney;
    }
}
