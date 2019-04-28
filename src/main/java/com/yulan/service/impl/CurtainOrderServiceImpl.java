package com.yulan.service.impl;

import com.yulan.dao.Ctm_orderDao;
import com.yulan.dao.CurtainOrderDao;
import com.yulan.pojo.*;
import com.yulan.service.Ctm_orderService;
import com.yulan.service.CurtainOrderService;
import com.yulan.utils.BackUtil;
import com.yulan.utils.MapUtils;
import com.yulan.utils.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurtainOrderServiceImpl implements CurtainOrderService {

    @Autowired
    private  Ctm_orderDao ctm_orderDao;
    @Autowired
    private CurtainOrderDao curtainOrderDao;

    @Autowired
    private Ctm_orderService ctm_orderService;



    @Override
    public Map insertCurtain(Map map) throws UnsupportedEncodingException, InvocationTargetException, IllegalAccessException {
        Map m=new HashMap();
        Map dataMap=new HashMap();
        Ctm_order ctm_order=new Ctm_order();
        Map<String ,Object> ctm_ordermap=(Map<String, Object>) map.get("ctm_order");//订单头
        String cid=map.get("cid").toString();
        List<Map<String,Object>> list=(List) map.get("ctm_orders");
        String orderNo=ctm_orderService.getOrderNumber("E");//窗帘订单号
        Timestamp nowTime=new Timestamp(System.currentTimeMillis());//获取当前时间
        for (Map.Entry<String, Object> entry : ctm_ordermap.entrySet()) {//转码
            if (entry.getValue() instanceof String) {
                String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                entry.setValue(origin);
            }
        }
        BeanUtils.populate(ctm_order,ctm_ordermap);//转为Ctm_order类
        ctm_order.setOrderNo(orderNo);
        ctm_order.setWebTjTime(nowTime);//获取当前时间
        ctm_order.setDateCre(nowTime);//获取当前时间
        ctm_order.setDateUpdate(nowTime);//获取当前时间
        ctm_order.setCurtainStatusId("0");//待审核状态
        ctm_order.setCustomerCode(cid);
        Map<String,Object> linkpersonandTelmap=ctm_orderDao.getlinkpersonandTel(cid);

        ctm_order.setLinkperson(linkpersonandTelmap.get("CUSTOMER_AGENT").toString());//经办人

        ctm_order.setTelephone(linkpersonandTelmap.get("OFFICE_TEL").toString());//经办人电话q

        if (ctm_orderDao.insertOrderH(ctm_order)) {//订单头录入

            int lineNo = 1;
            for (Map<String, Object> m2 : list) {//订单详情录入


                for (Map.Entry<String, Object> entry : m2.entrySet()) {
                    if (entry.getValue() instanceof String) {
                        String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                        entry.setValue(origin);
                    }
                }
                List<Map<String,Object>> curtainList=(List) m2.get("curtains");//窗帘详情

//                for (Map<String, Object> m3 : curtainList){//窗帘详情录入
//                    for (Map.Entry<String, Object> entry : m3.entrySet()) {//窗帘详情转码
//                        if (entry.getValue() instanceof String) {
//                            String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
//                            entry.setValue(origin);
//                        }
//                    }
//                    CurtainOrder curtainOrder= MapUtils.mapToBean(m3, CurtainOrder.class);
//                    curtainOrder.setOrderNo(orderNo);
//                    curtainOrder.setLineNo(String.valueOf(lineNo));
//                    curtainOrder.setDateUpdate(nowTime);
////                    curtainOrder.setItemNo(m2.get("itemNo").toString());//由前端传，窗帘型号
//                    curtainOrderDao.insertCurtainOrder(curtainOrder);
//                }
                Ctm_order_detail ctm_order_detail = MapUtils.mapToBean(m2, Ctm_order_detail.class);
                ctm_order_detail.setOrderNo(orderNo);


                ctm_order_detail.setLineNo(lineNo);
                lineNo++;
                ctm_orderDao.insertOrderB(ctm_order_detail);
            }
        }
        dataMap.put("orderNo",orderNo);
  
        dataMap.put("curtainStatus",ctm_order.getCurtainStatusId());
        m.put("data",dataMap);
        m.put("code",0);
        m.put("msg","SUCCESS");
        return m;
    }

    /**
     * 窗帘审核状态更新 修改/退回
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public Map updateCurtainOrder(Map map) throws UnsupportedEncodingException, InvocationTargetException, IllegalAccessException {
        Map m=new HashMap();
        Map dataMap=new HashMap();
        Ctm_order ctm_order=new Ctm_order();
        Map<String ,Object> ctm_ordermap=(Map<String, Object>) map.get("ctm_order");//订单头

        List<Map<String,Object>> list=(List) map.get("ctm_orders");

        Timestamp nowTime=new Timestamp(System.currentTimeMillis());//获取当前时间
        for (Map.Entry<String, Object> entry : ctm_ordermap.entrySet()) {//转码
            if (entry.getValue() instanceof String) {
                String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                entry.setValue(origin);
            }
        }
        BeanUtils.populate(ctm_order,ctm_ordermap);//转为Ctm_order类



        ctm_order.setDateUpdate(nowTime);//获取当前时间



        if (ctm_orderDao.updateOrder(ctm_order)) {//订单头录入

            int lineNo = 1;
            for (Map<String, Object> m2 : list) {//订单详情录入


                for (Map.Entry<String, Object> entry : m2.entrySet()) {
                    if (entry.getValue() instanceof String) {
                        String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                        entry.setValue(origin);
                    }
                }
                List<Map<String,Object>> curtainList=(List) m2.get("curtains");//窗帘详情
                for (Map<String, Object> m3 : curtainList){//窗帘详情录入
                    for (Map.Entry<String, Object> entry : m3.entrySet()) {//窗帘详情转码
                        if (entry.getValue() instanceof String) {
                            String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                            entry.setValue(origin);
                        }
                    }
                    CurtainOrder curtainOrder= MapUtils.mapToBean(m3, CurtainOrder.class);

                    curtainOrder.setLineNo(String.valueOf(lineNo));
                    curtainOrder.setDateUpdate(nowTime);
//                    curtainOrder.setItemNo(m2.get("itemNo").toString());//由前端传，窗帘型号
                    curtainOrderDao.updateCurtainOrder(curtainOrder);
                }
                Ctm_order_detail ctm_order_detail = MapUtils.mapToBean(m2, Ctm_order_detail.class);



                ctm_order_detail.setLineNo(lineNo);
                lineNo++;
                ctm_orderDao.updateOrderB(ctm_order_detail);
            }
        }
        dataMap.put("orderNo",ctm_order.getOrderNo());
        dataMap.put("curtainStatusId",ctm_order.getCurtainStatusId());
        m.put("data",dataMap);
        m.put("code",0);
        m.put("msg","SUCCESS");
        return m;
    }

    @Override
    public Map updateCurOrderStatus(Map map) {
        Map m=new HashMap();
        Map dataMap=new HashMap();
        String orderNo=map.get("orderNo").toString();
        String curtainStatusId=map.get("curtainStatusId").toString();

        if (curtainOrderDao.updateCurOrderStatus(orderNo,curtainStatusId)){
            m.put("code",0);
            m.put("msg","SUCCESS");
            dataMap.put("orderNo",orderNo);
            dataMap.put("curtainStatusId",curtainStatusId);
            m.put("data",dataMap);
        }else{
            m.put("code",1);
            m.put("msg","FLAS");
        }
        return m;
    }

    @Override
    public Map gatAllCurOrders(Map m ) throws UnsupportedEncodingException {
        Integer limit=Integer.parseInt(m.get("limit").toString());
        Integer page=Integer.parseInt(m.get("page").toString());

        String beginTime=m.get("beginTime").toString();
        String finishTime=m.get("finishTime").toString();
        String curtainStatusId=m.get("curtainStatusId").toString();

        if (curtainStatusId.equals("") ||curtainStatusId.equals("")) {
            curtainStatusId = null;
        }
        if (beginTime.equals("") ||finishTime.equals("")){
            beginTime=null;
            finishTime=null;
        }

        String find=m.get("find").toString();

        if (find.equals("")){
            find=null;
        }
        Integer lastNum=null;
        if(limit==null||page==null) {
            page=null;
            limit=null;
        } else {
            page=(page-1)*limit+1;
            lastNum=page+limit-1;
        }

        Map<String,Object> map=new HashMap<>();
        List<Map<String,Object>> list=ctm_orderDao.getOrdersH(page,lastNum,null,"0",find,beginTime,finishTime,null,curtainStatusId);
        List<Map<String,Object>> data=new ArrayList<>();
        map.put("count",ctm_orderDao.countOrdersH(null,"0",find,beginTime,finishTime,null,curtainStatusId));
        for (Map<String,Object> m1:list) {

            for (Map.Entry<String, Object> entry : m1.entrySet()) {//将订单头内容转码
                if (entry.getValue() instanceof String) {
                    String origin = StringUtil.getUtf8(String.valueOf(entry.getValue()));
                    entry.setValue(origin);
                }
            }
            String order_no=m1.get("ORDER_NO").toString();
            List<Map<String,Object>> list2=ctm_orderDao.getOrdersB(order_no);
            int orderB_num=0;
            for (Map<String,Object> m2:list2) {//将订单具体内容转码

                m2.put("pack_id",0);

                for (Map.Entry<String, Object> entry : m2.entrySet()) {
                    if (entry.getValue() instanceof String) {
                        String origin = StringUtil.getUtf8(String.valueOf(entry.getValue()));
                        entry.setValue(origin);
                    }
                }
                List<PackDetail> packDetails=ctm_orderDao.findPackDetail(order_no,m2.get("ITEM_NO").toString());
                if (packDetails.size()!=0){
                    m2.put("packDetailId",1);
                }else{
                    m2.put("packDetailId",0);
                }
                BigDecimal unit_price=(BigDecimal)m2.get("UNIT_PRICE");
                BigDecimal num=(BigDecimal)m2.get("QTY_REQUIRED");
                m2.put("all_cost",unit_price.multiply(num));
                orderB_num++;
            }

            m1.put("OREDERB_NUM",orderB_num);//订单商品数量
            m1.put("ORDERBODY",list2);
            data.add(m1);
        }


        map.put("data",data);
        map.put("code",0);
        map.put("msg","SUCCESS");
        return map;

    }

    @Override
    public Map curOrderCount(Map map) throws UnsupportedEncodingException, InvocationTargetException, IllegalAccessException {
        Timestamp nowTime=new Timestamp(System.currentTimeMillis());//获取当前时间
        String product_group_tpye=map.get("product_group_tpye").toString();

        String rebateY=map.get("rebateY").toString();//年优惠券流水号
        String rebateM=map.get("rebateM").toString();//月优惠券流水号
        Map<String ,Object> dataMap=new HashMap();
        Map m=new HashMap();
        Ctm_order ctm_order=new Ctm_order();




        String cid= map.get("cid").toString();

        Map<String ,Object> ctm_ordermap=(Map<String, Object>) map.get("ctm_order");
        for (Map.Entry<String, Object> entry : ctm_ordermap.entrySet()) {//转码
            if (entry.getValue() instanceof String) {
                String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                entry.setValue(origin);
            }
        }


        BeanUtils.populate(ctm_order,ctm_ordermap);//转为Ctm_order类




        List<Map<String,Object>> list=(List) map.get("ctm_orders");

        ctm_order.setDateUpdate(nowTime);//获取当前时间
        ctm_order.setCurtainStatusId(" ");//置空窗帘状态


        String promotion_costString=map.get("promotion_cost").toString();//先变字符串
        BigDecimal promotion_cost=BigDecimal.valueOf(Double.valueOf(promotion_costString));//活动后总价


        /**
         * 优惠券
         */
        //初始为零
        BigDecimal money_m=BigDecimal.valueOf(0);
        BigDecimal money_y=BigDecimal.valueOf(0);
        BigDecimal money=BigDecimal.valueOf(0);
        if(!rebateY.equals("")&&!rebateM.equals("")){//同时选了年和月
            Sal_rebate_certificate rebate_y=ctm_orderDao.getRebateById(rebateY);//年优惠券
            Sal_rebate_certificate rebate_m=ctm_orderDao.getRebateById(rebateM);//月优惠券
            money_m=rebate_m.getRebateMoneyOver();
            money_y=rebate_y.getRebateMoneyOver();
        }else if (rebateY.equals("")&&!rebateM.equals("")){//月
            Sal_rebate_certificate rebate_m=ctm_orderDao.getRebateById(rebateM);//月优惠券
            money_m=rebate_m.getRebateMoneyOver();
        }else if(!rebateY.equals("")&&rebateM.equals("")){//年
            Sal_rebate_certificate rebate_y=ctm_orderDao.getRebateById(rebateY);//年优惠券
            money_y=rebate_y.getRebateMoneyOver();
        }
        money=money_y.add(money_m);//优惠券+总额





        //重新计算总花费-总花费
        BigDecimal allSpend=ctm_order.getAllSpend();
        if (money.compareTo(allSpend)!=-1){//优惠券大于等于总花费
            allSpend=BigDecimal.valueOf(0);
        }else {
            allSpend=allSpend.subtract(money);
        }
        ctm_order.setAllSpend(allSpend);

        BigDecimal resideMoney=ctm_orderDao.getResideMoney(cid).add(money);//加上优惠券
//        BigDecimal resideMoney=ctm_orderDao.getResideMoney(cid);

        String statusId=" ";
        if (resideMoney.compareTo(promotion_cost)!=-1){
            statusId="1";
            ctm_order.setStatusId(statusId);//已经提交
        }else{
            statusId="5";
            ctm_order.setStatusId(statusId);//欠款待提交
        }


        //统计所花券金额
        BigDecimal allRebateMonth=BigDecimal.valueOf(0);
        BigDecimal allRebateYear=BigDecimal.valueOf(0);

        if (ctm_orderDao.updateOrder(ctm_order)){//订单头录入

            String order=ctm_order.getOrderNo();

            int lineNo=1;
            for (Map<String,Object> m2:list){//订单详情录入

//                List<Map<String,Object>> curtainList=(List) m2.get("curtains");//窗帘详情
//                for (Map<String, Object> m3 : curtainList){//窗帘详情录入
//                    for (Map.Entry<String, Object> entry : m3.entrySet()) {//窗帘详情转码
//                        if (entry.getValue() instanceof String) {
//                            String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
//                            entry.setValue(origin);
//                        }
//                    }
//                    CurtainOrder curtainOrder= MapUtils.mapToBean(m3, CurtainOrder.class);
//
//
//                    curtainOrder.setDateUpdate(nowTime);
//
//                    curtainOrderDao.updateCurtainOrder(curtainOrder);
//                }//窗帘详情不用更新



                for (Map.Entry<String, Object> entry : m2.entrySet()) {
                    if (entry.getValue() instanceof String) {
                        String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                        entry.setValue(origin);
                    }
                }
                Ctm_order_detail ctm_order_detail= MapUtils.mapToBean(m2,Ctm_order_detail.class);


                ctm_order_detail.setLineNo(lineNo);
                ctm_order_detail.setStatusId(statusId);


                //优惠券
                BigDecimal rebateMonth =BigDecimal.valueOf(0);
                BigDecimal rebateYear=BigDecimal.valueOf(0);
                if(promotion_cost.compareTo(money)!=1){//订单价格小于优惠券
                    if (promotion_cost.compareTo(money_m)!=1){//订单小于月券
                        rebateMonth=BackUtil.getBackMoney(promotion_cost,money,promotion_cost,ctm_order_detail.getPromotionCost());//月返利

                    }else {
                        rebateMonth= BackUtil.getBackMoney(promotion_cost,money,money_m,ctm_order_detail.getPromotionCost());//月返利
                        rebateYear=BackUtil.getBackMoney(promotion_cost,money,promotion_cost.subtract(money_m),ctm_order_detail.getPromotionCost());
                    }
                }else{//订单价格大于优惠券
                    rebateMonth=BackUtil.getBackMoney(promotion_cost,money,money_m,ctm_order_detail.getPromotionCost());//月返利
                    rebateYear=BackUtil.getBackMoney(promotion_cost,money,money_y,ctm_order_detail.getPromotionCost());

                }

                /**
                 * 记录优惠券使用记录
                 */
                if(!rebateY.equals("")&&!rebateM.equals("")){//同时选了年和月
                    Sal_rebate_certificate_record sal_rebate_certificate_recordM=new Sal_rebate_certificate_record();//月优惠券使用记录
                    sal_rebate_certificate_recordM.setDateUse(nowTime);
                    sal_rebate_certificate_recordM.setLineNo(String.valueOf(lineNo));
                    sal_rebate_certificate_recordM.setOrderNo(order);
                    sal_rebate_certificate_recordM.setId(rebateM);
                    sal_rebate_certificate_recordM.setRebateMoney(rebateMonth);
                    if (rebateYear.compareTo(BigDecimal.valueOf(0))==1){//年也用了
                        Sal_rebate_certificate_record sal_rebate_certificate_recordY=new Sal_rebate_certificate_record();//月优惠券使用记录
                        sal_rebate_certificate_recordY.setDateUse(nowTime);
                        sal_rebate_certificate_recordY.setLineNo(String.valueOf(lineNo));
                        sal_rebate_certificate_recordY.setOrderNo(order);
                        sal_rebate_certificate_recordY.setId(rebateY);
                        sal_rebate_certificate_recordY.setRebateMoney(rebateYear);
                        ctm_orderDao.insertRebateRecord(sal_rebate_certificate_recordM);
                        ctm_orderDao.insertRebateRecord(sal_rebate_certificate_recordY);
                    }else {
                        ctm_orderDao.insertRebateRecord(sal_rebate_certificate_recordM);
                    }

                }else if (rebateY.equals("")&&!rebateM.equals("")){//月
                    Sal_rebate_certificate_record sal_rebate_certificate_recordM=new Sal_rebate_certificate_record();//月优惠券使用记录
                    sal_rebate_certificate_recordM.setDateUse(nowTime);
                    sal_rebate_certificate_recordM.setLineNo(String.valueOf(lineNo));
                    sal_rebate_certificate_recordM.setOrderNo(order);
                    sal_rebate_certificate_recordM.setId(rebateM);
                    sal_rebate_certificate_recordM.setRebateMoney(rebateMonth);
                    ctm_orderDao.insertRebateRecord(sal_rebate_certificate_recordM);

                }else if(!rebateY.equals("")&&rebateM.equals("")){//年

                    Sal_rebate_certificate_record sal_rebate_certificate_recordY=new Sal_rebate_certificate_record();//月优惠券使用记录
                    sal_rebate_certificate_recordY.setDateUse(nowTime);
                    sal_rebate_certificate_recordY.setLineNo(String.valueOf(lineNo));
                    sal_rebate_certificate_recordY.setOrderNo(order);
                    sal_rebate_certificate_recordY.setId(rebateY);
                    sal_rebate_certificate_recordY.setRebateMoney(rebateYear);
                    ctm_orderDao.insertRebateRecord(sal_rebate_certificate_recordY);
                }


                //统计
                allRebateMonth=allRebateMonth.add(rebateMonth);
                allRebateYear=allRebateYear.add(rebateYear);

                ctm_order_detail.setBackM(rebateMonth);
                ctm_order_detail.setBackY(rebateYear);

                ctm_order_detail.setFinalCost(ctm_order_detail.getPromotionCost().subtract(rebateMonth).subtract(rebateYear));//最终金额



                if (!ctm_orderDao.updateOrderB(ctm_order_detail)){


                    m.put("code",1);
                    m.put("msg","FLASE");
                    break;
                }

                lineNo++;




            }


            ctm_order.setAllBackM(allRebateMonth);//订单头存总返利
            ctm_order.setAllBackY(allRebateYear);
            ctm_orderDao.updateOrder(ctm_order);

            /**
             * 更新优惠券金额
             */
            if(!rebateY.equals("")&&!rebateM.equals("")){//同时选了年和月
                BigDecimal month=ctm_orderDao.getRebateById(rebateM).getRebateMoneyOver();
                BigDecimal year=ctm_orderDao.getRebateById(rebateY).getRebateMoneyOver();
                ctm_orderDao.updateRebatemoney(rebateM,month.subtract(allRebateMonth));
                ctm_orderDao.updateRebatemoney(rebateY,year.subtract(allRebateYear));

            }else if (rebateY.equals("")&&!rebateM.equals("")){//月
                BigDecimal month=ctm_orderDao.getRebateById(rebateM).getRebateMoneyOver();
                ctm_orderDao.updateRebatemoney(rebateM,month.subtract(allRebateMonth));
            }else if(!rebateY.equals("")&&rebateM.equals("")){//年
                BigDecimal year=ctm_orderDao.getRebateById(rebateY).getRebateMoneyOver();
                ctm_orderDao.updateRebatemoney(rebateY,year.subtract(allRebateYear));
            }



            dataMap.put("statusId",statusId);
            dataMap.put("order",order);
            m.put("data",dataMap);
            m.put("code",0);
            m.put("msg","SUCCESS");
        }




        return m;
    }

    @Override
    public Map getCurtainOrder(Map map) throws UnsupportedEncodingException {
        Map m=new HashMap();

        String orderNo=map.get("orderNo").toString();
        String lineNo=map.get("lineNo").toString();
        CurtainOrder curtainOrder=curtainOrderDao.getCurtainOrder(orderNo,lineNo);
        Map<String, Object> curtainOrderMap=MapUtils.beanToMaplin(curtainOrder);
        curtainOrderMap.put("type",curtainOrderDao.getTypeName(curtainOrder.getCurtainMenuGroupId()));
        for (Map.Entry<String, Object> entry : curtainOrderMap.entrySet()) {
            if (entry.getValue() instanceof String) {
                String origin = StringUtil.getUtf8(String.valueOf(entry.getValue()));
                entry.setValue(origin);
            }
        }
        m.put("data",curtainOrderMap);
        m.put("code",0);
        m.put("msg","SUCCESS");

        return m;
    }


}
