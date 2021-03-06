package com.yulan.service.impl;

import com.yulan.dao.*;
import com.yulan.pojo.*;
import com.yulan.service.Ctm_orderService;
import com.yulan.utils.MapUtils;
import com.yulan.utils.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Ctm_orderServiceImpl implements Ctm_orderService {
    @Autowired
    private Ctm_orderDao ctm_orderDao;

    @Autowired
    private CurtainOrderDao curtainOrderDao;

    @Autowired
    private CommodityOrderDao commodityOrderDao;

    @Autowired
    private Web_userDao web_userDao;

    @Autowired
    private ItemDao itemDao;


    @Override
    public Map getOrders(Integer start, Integer number, String cid, String state_id, String find,String beginTime,String finishTime,String orderType,String curtainStatusId,String companyId,String customerMainId) throws UnsupportedEncodingException {
        Map<String,Object> map=new HashMap<>();
        List<Map<String,Object>> list=new ArrayList<>();
        List<Map<String,Object>> data=new ArrayList<>();
        if (customerMainId!=null&&!customerMainId.equals("")){
            List<Map<String,Object>> userMaps=web_userDao.getAllUserByCustomerMainId(customerMainId);//查找属于同个customerMainId的用户
            List<String> users=new ArrayList<>();
            if (userMaps.size()!=0){
                for (Map<String,Object> map1:userMaps){
                    users.add(map1.get("LOGINNAME").toString());
                }
            }
            list=ctm_orderDao.getOrdersH(start,number,cid,state_id,find,beginTime,finishTime,orderType,curtainStatusId,users);
            map.put("count",ctm_orderDao.countOrdersH(cid,state_id,find,beginTime,finishTime,orderType,curtainStatusId,users));
        }else {
            list=ctm_orderDao.getOrdersManager(start,number,cid,state_id,find,beginTime,finishTime,orderType,curtainStatusId);
            map.put("count",ctm_orderDao.countOrdersManager(cid,state_id,find,beginTime,finishTime,orderType,curtainStatusId));
        }

        //一个公司的员工可以查看该公司所有订单




        for (Map<String,Object> m:list) {

//            String realName=web_userDao.getRealName(m.get("CUSTOMER_CODE").toString());//用户名
//            realName=StringUtil.getUtf8(realName);//转码


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
                String itemNo=m2.get("ITEM_NO").toString();//关联型号
                Item item=itemDao.getItemByItemNO(itemNo);
                String UNIT=itemDao.getUnit(item.getUnit());
                UNIT=StringUtil.getUtf8(UNIT);//单位转码
                m2.put("UNIT",UNIT);
                m2.put("item",item);

                List<Map<String,Object>> list3=ctm_orderDao.getPackDetail(order_no,m2.get("LINE_NO").toString());
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

            m.put("OREDERB_NUM",orderB_num);//订单商品数量
//            m.put("realName",realName);//客户吗，为了审核
            m.put("ORDERBODY",list2);
            data.add(m);
        }


        map.put("data",data);

        return map;
    }

    @Override
    public Map getOrderContent(String order_no,String cid) throws UnsupportedEncodingException {
        if (order_no.indexOf("X")==-1){//非窗帘
            return this.getOrders(1,1,cid,null,order_no,null,null,null,null,"","");//公司id"补空",customerMainId补空
        }else{
            Map<String,Object> map=new HashMap<>();
            List<Map<String,Object>> list=ctm_orderDao.getOrderContent(order_no);
            List<Map<String,Object>> data=new ArrayList<>();
            map.put("count",1);
            for (Map<String,Object> m:list) {

                for (Map.Entry<String, Object> entry : m.entrySet()) {//将订单头内容转码
                    if (entry.getValue() instanceof String) {
                        String origin = StringUtil.getUtf8(String.valueOf(entry.getValue()));
                        entry.setValue(origin);
                    }
                }

                List<Map<String,Object>> list2=ctm_orderDao.getOrdersB(order_no);
                int orderB_num=0;
                for (Map<String,Object> m2:list2) {//将订单具体内容转码
                    String itemNo=m2.get("ITEM_NO").toString();//关联型号
                    Item item=itemDao.getItemByItemNO(itemNo);
                    m2.put("item",item);

                    List<Map<String,Object>> list3=ctm_orderDao.getPackDetail(order_no,m2.get("LINE_NO").toString());
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
                    List<PackDetail> packDetails=ctm_orderDao.findPackDetail(order_no,m2.get("ITEM_NO").toString());//是否出货
                    if (packDetails.size()!=0){
                        m2.put("packDetailId",1);
                    }else{
                        m2.put("packDetailId",0);
                    }
                    BigDecimal unit_price=(BigDecimal)m2.get("UNIT_PRICE");
                    BigDecimal num=(BigDecimal)m2.get("QTY_REQUIRED");
                    m2.put("all_cost",unit_price.multiply(num));
                    List<CommodityOrder> list1=commodityOrderDao.getCommodityOrders(order_no,Integer.parseInt(m2.get("LINE_NO").toString()));
                    List<Map<String,Object>> list4=new ArrayList<>();
                    for (CommodityOrder commodityOrder:list1){
                        Map<String,Object> commodityOrderMap=MapUtils.beanToMaplin(commodityOrder);
                        for (Map.Entry<String, Object> entry : commodityOrderMap.entrySet()) {//将窗帘内容转码
                            if (entry.getValue() instanceof String) {
                                String origin = StringUtil.getUtf8(String.valueOf(entry.getValue()));
                                entry.setValue(origin);
                            }
                        }
                        list4.add(commodityOrderMap);

                    }
                    m2.put("curtains",list4);
                    orderB_num++;

                }

                m.put("OREDERB_NUM",orderB_num);//订单商品数量
                m.put("ORDERBODY",list2);
                data.add(m);
            }


            map.put("data",data);


            return map;
        }



    }

    @Override
    public Map getPromotion(List<Map<String, Object>> list) {
        Map<String,Object> map=new HashMap<>();
        List<Map<String,Object>> data=new ArrayList<>();
        for (Map<String, Object> m:list){
            BigDecimal promotion_cost=BigDecimal.valueOf(0.0);
            String numString=m.get("num").toString();
            BigDecimal num=BigDecimal.valueOf(Double.valueOf(numString));
            String pId=m.get("pId").toString();
            if (pId==null||pId.equals("")){
                m.put("promotion_cost",m.get("prime_cost"));
                data.add(m);
                continue;
            }
            String prime_costString=m.get("prime_cost").toString();
            BigDecimal prime_cost= BigDecimal.valueOf(Double.valueOf(prime_costString));
            Sal_promotion sal_promotion=ctm_orderDao.getPromotion(pId);
            if (sal_promotion==null){
                m.put("promotion_cost",m.get("prime_cost"));
                data.add(m);
                continue;
            }

            String type=sal_promotion.getType();

            BigDecimal discount=sal_promotion.getDiscount();
            BigDecimal price=sal_promotion.getPrice();
            if (type.equals("1")){//折扣
                promotion_cost= discount.multiply(prime_cost);//乘法保留两位小数
            }else {//定价
                promotion_cost= price.multiply(num);
            }
            m.put("promotion_cost",promotion_cost);
            data.add(m);

        }
        map.put("data",data);
        return map;
    }

    @Override
    public BigDecimal getResidemoney(String companyId) {
        return ctm_orderDao.getResideMoney(companyId);
    }

    @Override
    public Map orderCount(Map<String, Object> map) throws InvocationTargetException, IllegalAccessException, UnsupportedEncodingException {
        Timestamp nowTime=new Timestamp(System.currentTimeMillis());//获取当前时间
        String product_group_tpye=map.get("product_group_tpye").toString();

        String rebateY=map.get("rebateY").toString();//年优惠券流水号
        String rebateM=map.get("rebateM").toString();//月优惠券流水号
        String companyId=map.get("companyId").toString();//公司id
        String arrearsFlag="";
        if (map.get("arrearsFlag")!=null){
            arrearsFlag=map.get("arrearsFlag").toString();
        }


        List<Map<String,Object>> userMaps=web_userDao.getAllUserByComIdorFlink(companyId);//查找属于同个公司的用户
        List<String> users=new ArrayList<>();
        if (userMaps.size()!=0){
            for (Map<String,Object> map1:userMaps){
                users.add(map1.get("LOGINNAME").toString());
            }
        }


        Map<String ,Object> dataMap=new HashMap();
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



        Map<String,Object> linkpersonandTelmap=ctm_orderDao.getlinkpersonandTel(users);


        if (linkpersonandTelmap.get("CUSTOMER_AGENT")!=null){

            ctm_order.setLinkperson(linkpersonandTelmap.get("CUSTOMER_AGENT").toString());//经办人

        }
        if (linkpersonandTelmap.get("OFFICE_TEL")!=null){
            ctm_order.setTelephone(linkpersonandTelmap.get("OFFICE_TEL").toString());//经办人电话
        }

        List<Map<String,Object>> list=(List) map.get("ctm_orders");

        ctm_order.setDateCre(nowTime);//获取当前时间
        ctm_order.setDateUpdate(nowTime);//获取当前时间

        ctm_order.setCurrencyId("RMB");
        ctm_order.setCustomerCode(cid);
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

//        BigDecimal resideMoney=ctm_orderDao.getResideMoney(companyId).add(money);//加上优惠券
        BigDecimal resideMoney=ctm_orderDao.getResideMoney(cid);

        String statusId=" ";
        if(arrearsFlag.equals("N")){//不选活动，要检查欠帐，选活动了，就判断状态是否为y。当y时，要检查欠帐，为n时，不检查余额，直接提交成功变成已提交

            statusId="1";
            ctm_order.setStatusId(statusId);//已经提交
            ctm_order.setWebTjTime(nowTime);//获取当前时间（记录已经提交时间）

        }else {


            if ((promotion_cost.compareTo(BigDecimal.valueOf(0))==0)||(money.compareTo(promotion_cost)!=-1)||(resideMoney.compareTo(promotion_cost)!=-1)){//订单金额为0时可以直接提交,即优券金额大于
                statusId="1";
                ctm_order.setStatusId(statusId);//已经提交
                ctm_order.setWebTjTime(nowTime);//获取当前时间（记录已经提交时间）

            }else{
                statusId="5";
                ctm_order.setStatusId(statusId);//欠款待提交
            }
        }


        //统计所花券金额
        BigDecimal allRebateMonth=BigDecimal.valueOf(0);
        BigDecimal allRebateYear=BigDecimal.valueOf(0);

        BigDecimal allFinalCostB=BigDecimal.valueOf(0);//统计明显所有最终花费，更新头部总花费


        if (ctm_orderDao.insertOrderH(ctm_order)){//订单头录入

                    int lineNo=1;
                    int sizeNum=list.size();//统计所需
                    for (Map<String,Object> m2:list){//订单详情录入



                        for (Map.Entry<String, Object> entry : m2.entrySet()) {
                            if (entry.getValue() instanceof String) {
                                String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                                entry.setValue(origin);
                            }
                        }
                        Ctm_order_detail ctm_order_detail= MapUtils.mapToBean(m2,Ctm_order_detail.class);


                        ctm_order_detail.setLineNo(lineNo);
                        ctm_order_detail.setStatusId(statusId);
                        ctm_order_detail.setOrderNo(order);

                //优惠券
                BigDecimal rebateMonth =BigDecimal.valueOf(0);
                BigDecimal rebateYear=BigDecimal.valueOf(0);

                if (lineNo!=sizeNum){
                    if(promotion_cost.compareTo(money)!=1){//订单价格小于优惠券
                        if (promotion_cost.compareTo(money_m)!=1){//订单小于月券
                            rebateMonth=this.getBackMoney(promotion_cost,money,promotion_cost,ctm_order_detail.getPromotionCost());//月返利

                        }else {
                            rebateMonth=this.getBackMoney(promotion_cost,money,money_m,ctm_order_detail.getPromotionCost());//月返利
                            rebateYear=this.getBackMoney(promotion_cost,money,promotion_cost.subtract(money_m),ctm_order_detail.getPromotionCost());
                        }
                    }else{//订单价格大于优惠券
                        rebateMonth=this.getBackMoney(promotion_cost,money,money_m,ctm_order_detail.getPromotionCost());//月返利
                        rebateYear=this.getBackMoney(promotion_cost,money,money_y,ctm_order_detail.getPromotionCost());

                    }
                }else {//获取最后一个商品分利
                    rebateMonth=this.getLastBackMony(money_m,allRebateMonth,ctm_order_detail.getPromotionCost());
                    rebateYear=this.getLastBackMony(money_y,allRebateYear,ctm_order_detail.getPromotionCost());
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

                allFinalCostB=allFinalCostB.add(ctm_order_detail.getFinalCost());



                if (!ctm_orderDao.insertOrderB(ctm_order_detail)){


                    m.put("code",1);
                    m.put("msg","FLASE");
                    break;
                }

                lineNo++;



            }


            ctm_order.setAllBackM(allRebateMonth);//订单头存总返利
            ctm_order.setAllBackY(allRebateYear);
            ctm_order.setAllSpend(allFinalCostB);//更新总花费，避免一分之差

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



        m.put("code",0);
        m.put("msg","SUCCESS");
        return m;
    }

    @Override
    public Map getPack(Map<String, Object> m) throws UnsupportedEncodingException {

        String order_no=m.get("order_no").toString();

        String lineNo=Objects.toString(m.get("lineNo").toString());
        BigDecimal allNum=ctm_orderDao.getNum(order_no,lineNo);
        m.put("all_num",allNum);//总数
        List<Map<String,Object>> list=ctm_orderDao.getPackDetail(order_no,lineNo);
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
    public Map getlinkpersonandTel(String companyId) throws UnsupportedEncodingException {
        List<Map<String,Object>> userMaps=web_userDao.getAllUserByComIdorFlink(companyId);//查找属于同个公司的用户
        List<String> users=new ArrayList<>();
        if (userMaps.size()!=0){
            for (Map<String,Object> map1:userMaps){
                users.add(map1.get("LOGINNAME").toString());
            }
        }

        Map<String ,Object> map=ctm_orderDao.getlinkpersonandTel(users);
        String CUSTOMER_AGENT="";
        if (map!=null){
             CUSTOMER_AGENT=StringUtil.getUtf8(map.get("CUSTOMER_AGENT").toString());
            Object c=CUSTOMER_AGENT;
            map.put("CUSTOMER_AGENT",CUSTOMER_AGENT);
        }



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
//        java.sql.Date beforeDate=new java.sql.Date(currentDate.getTime()-(24*60*60*1000));//前一天
        java.sql.Date beforeDate=new java.sql.Date(currentDate.getTime());
        String cid=map.get("cid").toString();
        String companyId=map.get("companyId").toString();
        String type=map.get("typeId").toString();

        List<Sal_rebate_certificate> list=ctm_orderDao.getRebate(companyId,currentDate,beforeDate);
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
        }else {//软装
            for (Sal_rebate_certificate sal_rebate_certificate:list){

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

    /**
     * 显示优惠券摊分
     * @param map
     * @return
     */
    @Override
    public Map showRebate(Map<String, Object> map) throws InvocationTargetException, IllegalAccessException {


        List<Map<String,Object>> list2=new ArrayList<>();
        String rebateY=map.get("rebateY").toString();//年优惠券流水号
        String rebateM=map.get("rebateM").toString();//月优惠券流水号
        Map<String ,Object> dataMap=new HashMap();
        Map m=new HashMap();
        Ctm_order ctm_order=new Ctm_order();






        Map<String ,Object> ctm_ordermap=(Map<String, Object>) map.get("ctm_order");



        BeanUtils.populate(ctm_order,ctm_ordermap);//转为Ctm_order类







        List<Map<String,Object>> list=(List) map.get("ctm_orders");

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










        //统计所花券金额
        BigDecimal allRebateMonth=BigDecimal.valueOf(0);
        BigDecimal allRebateYear=BigDecimal.valueOf(0);

            int lineNo=1;
            int sizeNum=list.size();//统计所需
            for (Map<String,Object> m2:list){//订单详情遍历
                Map<String,Object> returnMap=new HashMap<>();



                Ctm_order_detail ctm_order_detail= MapUtils.mapToBean(m2,Ctm_order_detail.class);





                //优惠券
                BigDecimal rebateMonth =BigDecimal.valueOf(0);
                BigDecimal rebateYear=BigDecimal.valueOf(0);
                if (lineNo!=sizeNum){
                    if(promotion_cost.compareTo(money)!=1){//订单价格小于优惠券
                        if (promotion_cost.compareTo(money_m)!=1){//订单小于月券
                            rebateMonth=this.getBackMoney(promotion_cost,money,promotion_cost,ctm_order_detail.getPromotionCost());//月返利

                        }else {
                            rebateMonth=this.getBackMoney(promotion_cost,money,money_m,ctm_order_detail.getPromotionCost());//月返利
                            rebateYear=this.getBackMoney(promotion_cost,money,promotion_cost.subtract(money_m),ctm_order_detail.getPromotionCost());
                        }
                    }else{//订单价格大于优惠券
                        rebateMonth=this.getBackMoney(promotion_cost,money,money_m,ctm_order_detail.getPromotionCost());//月返利
                        rebateYear=this.getBackMoney(promotion_cost,money,money_y,ctm_order_detail.getPromotionCost());

                    }
                }else {//获取最后一个商品分利
                    rebateMonth=this.getLastBackMony(money_m,allRebateMonth,ctm_order_detail.getPromotionCost());
                    rebateYear=this.getLastBackMony(money_y,allRebateYear,ctm_order_detail.getPromotionCost());
                }
                returnMap.put("itemNo",ctm_order_detail.getItemNo());
                returnMap.put("rebateYear",rebateYear);
                returnMap.put("rebateMonth",rebateMonth);
                list2.add(returnMap);



                //统计
                allRebateMonth=allRebateMonth.add(rebateMonth);
                allRebateYear=allRebateYear.add(rebateYear);

                ctm_order_detail.setBackM(rebateMonth);
                ctm_order_detail.setBackY(rebateYear);



                lineNo++;

            }

            Map<String,Object> datamap=new HashMap<>();
            datamap.put("allRebateMonth",allRebateMonth);
            datamap.put("allRebateYear",allRebateYear);
            datamap.put("allspend",allSpend);
            datamap.put("rebate",list2);




            m.put("data",datamap);
            m.put("code",0);
            m.put("msg","SUCCESS");





        return m;
    }

    @Override
    public Map cancelOrder(Map<String, Object> map) {
        Map<String ,Object> m=new HashMap<>();
        String  orderNo=map.get("orderNo").toString();
        Timestamp dateUpdate= new Timestamp(System.currentTimeMillis());
        Ctm_order ctm_order=new Ctm_order();
        ctm_order.setOrderNo(orderNo);
        ctm_order.setStatusId("3");
        ctm_order.setDateUpdate(dateUpdate);
        ctm_order.setCurtainStatusId("");//滞空窗帘审核状态
        BigDecimal allmony_y=BigDecimal.valueOf(0);//年返利
        BigDecimal allmony_m=BigDecimal.valueOf(0);//月返利
        String yesrId="";//年返利券编号
        String monthId="";//月返利券编号
        if (ctm_orderDao.updateOrder(ctm_order)){
            ctm_orderDao.updateOrderBStatus(orderNo,"3",dateUpdate);//更新订单详情状态
            List<Sal_rebate_certificate_record> list=ctm_orderDao.findRecrod(orderNo);
            for (Sal_rebate_certificate_record s:list){
                String id=s.getId();
                if (id.indexOf("Y")!=-1){//年返利券
                    yesrId=id;
                    allmony_y=allmony_y.add(s.getRebateMoney());

                }else{//月
                    monthId=id;
                    allmony_m=allmony_m.add(s.getRebateMoney());
                }
            }
            if (!yesrId.equals("")){//更新年券
                Sal_rebate_certificate sal_rebate_certificateY=ctm_orderDao.findRebate(yesrId);
                BigDecimal rebateMoneyOver=sal_rebate_certificateY.getRebateMoneyOver().add(allmony_y);
                sal_rebate_certificateY.setRebateMoneyOver(rebateMoneyOver);
                ctm_orderDao.updateRebate(sal_rebate_certificateY);
            }
            if (!monthId.equals("")){//更新月券
                Sal_rebate_certificate sal_rebate_certificateM=ctm_orderDao.findRebate(monthId);
                BigDecimal rebateMoneyOver=sal_rebate_certificateM.getRebateMoneyOver().add(allmony_m);
                sal_rebate_certificateM.setRebateMoneyOver(rebateMoneyOver);
                ctm_orderDao.updateRebate(sal_rebate_certificateM);
            }
            ctm_orderDao.updateRecord("0",orderNo);
            m.put("code",0);
            m.put("data","3");//订单作废状态
            m.put("msg","SUCCESS");
        }




        return m;
    }

    @Override
    public Map putAgainOrder(Map<String, Object> map) {
        Timestamp nowTime=new Timestamp(System.currentTimeMillis());//获取当前时间
        Map<String ,Object> m=new HashMap<>();
        String orderNo=map.get("orderNo").toString();
        String cid=map.get("cid").toString();
        Ctm_order ctm_order=ctm_orderDao.getOrderH(orderNo);
        List<Ctm_order_detail> list=ctm_orderDao.getOrderB(orderNo);
        BigDecimal resideMoney=ctm_orderDao.getResideMoney(cid);//加上优惠券
        BigDecimal allSpend=ctm_order.getAllSpend();
//        BigDecimal resideMoney=ctm_orderDao.getResideMoney(cid);



        String statusId=ctm_order.getStatusId();
        if (statusId.equals("6")){
            statusId="1";
            ctm_order.setStatusId(statusId);
            ctm_order.setWebTjTime(nowTime);//获取当前时间（记录已经提交时间）
        }else{
            if (resideMoney.compareTo(allSpend)!=-1){
                statusId="1";
                ctm_order.setStatusId(statusId);//已经提交
                ctm_order.setWebTjTime(nowTime);//获取当前时间（记录已经提交时间）
            }else{
                statusId="5";
                ctm_order.setStatusId(statusId);//欠款待提交
            }
        }

        if (ctm_orderDao.updateOrder(ctm_order)){
            for (Ctm_order_detail ctm_order_detail:list){
                ctm_order_detail.setStatusId(statusId);
                ctm_orderDao.updateOrderB(ctm_order_detail);
            }
            m.put("code",0);
            m.put("data",statusId);
            m.put("msg","SUCCESS");

        }

        return m;
    }

    @Override
    public boolean updateOrderStatus(String orderNo,
                                     String statusId) {
        Timestamp dateUpdate= new Timestamp(System.currentTimeMillis());
        //更新订单头和详情状态
        if (ctm_orderDao.updateOrderStatus(orderNo,statusId,dateUpdate)&&ctm_orderDao.updateOrderBStatus(orderNo,statusId,dateUpdate)){

            return true;
        }else {
            return false;
        }

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
//        String order=ctm_orderDao.getBigNum(orderN+s);
        List<Map<String,Object>> ordersMap=ctm_orderDao.getTodayOders(orderN+s);


        if(ordersMap.size()==0){
            trueOrder=orderN+s+"0001b";
        }else{//截取自增
            String order="";
            List<Integer> nums=new ArrayList<>();
            for (Map<String,Object> map:ordersMap){
                String orderS=map.get("ORDER_NO").toString();
                orderS=orderS.substring(7,11);
                int orderI=Integer.parseInt(orderS);
                nums.add(orderI);

            }
            int orderIMax= Collections.max(nums);
//            order=order.substring(7,11);
            int o=10000;
//            Integer i=Integer.parseInt(order);
            o=o+orderIMax+1;
            String p=o+"";
            p=p.substring(1);
            trueOrder=orderN+s+p+"b";//b为b2b订单号标志
        }
        return trueOrder;

    }

    /**
     * 24小时自动取消订单
     * @return
     */
    @Override
    public Integer autoCancelOrder() {
        List<Ctm_order> list=ctm_orderDao.getAllCtms();
        java.sql.Timestamp currentDate = new java.sql.Timestamp(System.currentTimeMillis());//当前时间


        for (Ctm_order ctm_order:list){
            java.sql.Timestamp dateCre=ctm_order.getDateCre();//提交时间
            java.sql.Timestamp dateCre24=new java.sql.Timestamp(dateCre.getTime()+(24*60*60*1000));//加24小时
            Map<String,Object> map=new HashMap<>();
            if (currentDate.after(dateCre24)){
                map.put("orderNo",ctm_order.getOrderNo());
                this.cancelOrder(map);
                if (this.cancelOrder(map).get("code")!="0"){
                   return 0;
                }
            }else {
                continue;
            }
        }
        return 1;
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
        if(money.compareTo(BigDecimal.valueOf(0))==0){//优惠券为零（或不选）
            return backMoney;
        }
        backMoney=((thisMoney.multiply(money).divide(promotion_cost,2, RoundingMode.HALF_UP)));


        return backMoney;


    }


    /**
     * 计算最后一个商品分利
     * @param allMoney 返利券总额
     * @param lastAllMoney 除最后一个商品的总额
     *
     * @return
     */
    public BigDecimal getLastBackMony(BigDecimal allMoney,BigDecimal lastAllMoney,BigDecimal thisMoney){
        if(allMoney.compareTo(BigDecimal.valueOf(0))==0){
            return BigDecimal.valueOf(0);
        }
        if (allMoney.subtract(lastAllMoney).compareTo(thisMoney)==1){//大于商品价格
            return thisMoney;
        }else {
            return allMoney.subtract(lastAllMoney);
        }

    }
}
