package com.yulan.service.impl;

import com.yulan.dao.*;
import com.yulan.pojo.*;
import com.yulan.service.*;
import com.yulan.utils.BackUtil;
import com.yulan.utils.MapUtils;
import com.yulan.utils.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
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

    @Autowired
    private CommodityOrderService commodityOrderService;

    @Autowired
    private CommodityOrderDao commodityOrderDao;

    @Autowired
    private Web_userDao web_userDao;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CustomerTypeDao customerTypeDao;





    @Override
    public Map insertCurtain(Map map) throws UnsupportedEncodingException, InvocationTargetException, IllegalAccessException {
        Map m=new HashMap();
        Map dataMap=new HashMap();
        Ctm_order ctm_order=new Ctm_order();
        Map<String ,Object> ctm_ordermap=(Map<String, Object>) map.get("ctm_order");//订单头
        String cid=map.get("cid").toString();
        List<Map<String,Object>> list=(List) map.get("ctm_orders");

        List<String> cartItemIDs=(List) map.get("cartItemIDs");//new
        String companyId=map.get("companyId").toString();//公司id
        List<Map<String,Object>> userMaps=web_userDao.getAllUserByComId(companyId);//查找属于同个公司的用户
        List<String> users=new ArrayList<>();
        if (userMaps.size()!=0){
            for (Map<String,Object> map1:userMaps){
                users.add(map1.get("LOGINNAME").toString());
            }
        }

        Map<String,Integer> lineNos=new HashMap<>();


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

        ctm_order.setDateCre(nowTime);//获取当前时间
        ctm_order.setDateUpdate(nowTime);//获取当前时间

        ctm_order.setCurtainStatusId("0");//待审核状态
        ctm_order.setCustomerCode(cid);
        Map<String,Object> linkpersonandTelmap=ctm_orderDao.getlinkpersonandTel(users);

        if (linkpersonandTelmap.get("CUSTOMER_AGENT")!=null){

            ctm_order.setLinkperson(linkpersonandTelmap.get("CUSTOMER_AGENT").toString());//经办人

        }
        if (linkpersonandTelmap.get("OFFICE_TEL")!=null){
            ctm_order.setTelephone(linkpersonandTelmap.get("OFFICE_TEL").toString());//经办人电话
        }



        if (ctm_orderDao.insertOrderH(ctm_order)) {//订单头录入

            int lineNo = 1;
            for (Map<String, Object> m2 : list) {//订单详情录入


                for (Map.Entry<String, Object> entry : m2.entrySet()) {
                    if (entry.getValue() instanceof String) {
                        String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                        entry.setValue(origin);
                    }
                }



                Ctm_order_detail ctm_order_detail = MapUtils.mapToBean(m2, Ctm_order_detail.class);
                ctm_order_detail.setOrderNo(orderNo);


                ctm_order_detail.setLineNo(lineNo);


                /**
                 * 为了窗帘订单还未选择优惠券时总价也不为零
                 */
                BigDecimal unitPrice=ctm_order_detail.getUnitPrice();//单价
                BigDecimal qtyRequired=ctm_order_detail.getQtyRequired();//数量
                BigDecimal finalCost=unitPrice.multiply(qtyRequired);//原总价
                ctm_order_detail.setFinalCost(finalCost.setScale(2, BigDecimal.ROUND_HALF_UP));//四舍五入，保留两位小数

                lineNos.put(cartItemIDs.get(lineNo-1),lineNo);//new

                lineNo++;
                if (!ctm_orderDao.insertOrderB(ctm_order_detail)){

                    m.put("code",1);
                    m.put("msg","订单详情录入错误");
                    return m;
                }
            }
            List<String> orderItemIDs=commodityOrderService.submitCommodityOrder(cartItemIDs,lineNos);//窗帘详情录入
            for (String orderItemID:orderItemIDs){
                if (!commodityOrderService.addOrderNoByOrderItemIDs(orderItemID,orderNo)){

                    m.put("code",1);
                    m.put("msg","窗帘详情订单号录入错误");
                    return m;
                }
            }

            dataMap.put("orderNo",orderNo);

            dataMap.put("curtainStatus",ctm_order.getCurtainStatusId());
            m.put("data",dataMap);
            m.put("code",0);
            m.put("msg","SUCCESS");
        }else {
            m.put("code",1);
            m.put("msg","订单头部录入错误");
        }

        return m;
    }

    /**
     * 窗帘审核状态更新 修改/退回
     * 只修改窗帘详情
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public Map updateCurtainOrder(Map map) throws UnsupportedEncodingException, InvocationTargetException, IllegalAccessException {
        Map m=new HashMap();
        String orderNo=map.get("orderNo").toString();
        String curtainStatusId=map.get("curtainStatusId").toString();
        List<List<Map<String,Object>>> commodityOrderList=(List<List<Map<String,Object>>>) map.get("allCurtains");
        List<Map<String,Object>> ctmOrderDetails=(List<Map<String,Object>>) map.get("ctmOrderDetails");

        List<String> deleteIds=(List<String>)map.get("deleteIds");//需要删除的配件id



        /**
         * 通过订单号获取客户类型
         */
        String cid=ctm_orderDao.getCidByOrderNo(orderNo);
        String companyId=web_userDao.changeLoginNameToCompanyID(cid);
        CustomerType customerType=customerTypeDao.getCustomerTypeByCID(companyId);
        String customerTypeId="";
        if (customerType!=null){
            customerTypeId=customerType.getCustomerTypeId();
        }


        /**
         * 记得修改时间
         */
        if (curtainOrderDao.updateCurOrderStatus(orderNo,curtainStatusId)){

            /**
             * 订单详情审核意见
             */
            if (ctmOrderDetails!=null){
                for (Map<String,Object> ctmOrderDetailMap:ctmOrderDetails ){
                    for (Map.Entry<String, Object> entry : ctmOrderDetailMap.entrySet()) {//转码
                        if (entry.getValue() instanceof String) {
                            String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                            entry.setValue(origin);
                        }
                    }
                    Ctm_order_detail ctm_order_detail=new Ctm_order_detail();
                    BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
                    BeanUtils.populate(ctm_order_detail,ctmOrderDetailMap);
                    if (!ctm_orderDao.updateOrderB(ctm_order_detail)){
                        m.put("code",1);
                        m.put("msg","订单详情修改错误");
                        return  m;
                    }
                }
            }
//            if (map.get("ljSuggestion")!=null){
//                String ljSuggestion=map.get("ljSuggestion").toString();
//                Ctm_order_detail ctm_order_detail=new Ctm_order_detail(orderNo,Integer.parseInt(lineNo),ljSuggestion);
//                if (!ctm_orderDao.updateOrderB(ctm_order_detail)){
//                        m.put("code",1);
//                        m.put("msg","订单详情修改错误");
//                        return  m;
//                    }
//            }


            BigDecimal oneAllCost=BigDecimal.valueOf(0);//单个窗帘总花费
            /**
             * 窗帘审核意见
             */
            if (commodityOrderList!=null){
                for (List<Map<String,Object>> commodityOrderMaps:commodityOrderList){

                    oneAllCost=BigDecimal.valueOf(0);//窗帘价格重新置零

                    BigDecimal smallOne=BigDecimal.valueOf(0);//配件单价

                    String lineNo="";//商品行号
                    for (Map<String,Object> commodityOrderMap:commodityOrderMaps ){
                        lineNo=commodityOrderMap.get("lineNo").toString();
                        for (Map.Entry<String, Object> entry : commodityOrderMap.entrySet()) {//转码
                            if (entry.getValue() instanceof String) {
                                String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                                entry.setValue(origin);
                            }
                        }
//                     CommodityOrder commodityOrder=MapUtils.mapToBean(commodityOrderMap,CommodityOrder.class);
                        CommodityOrder commodityOrder = new CommodityOrder();
                        BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
                        /**
                         * 处理类中的类item
                         */
                        Item item=new Item();
                        Map itemMap=(Map) commodityOrderMap.get("item");
                        BeanUtils.populate(item,itemMap);
                        commodityOrderMap.remove("item");

                        BeanUtils.populate(commodityOrder,commodityOrderMap);
                        commodityOrder.setItem(item);

//                        String curtainItemName = commodityOrder.getCurtainItemName();
//                        curtainItemName = StringUtil.UTF8ToGBK(curtainItemName);
//                        commodityOrder.setCurtainItemName(curtainItemName);
                        if (!commodityOrderDao.updateCommodityOrder(commodityOrder)){
                            m.put("code",1);
                            m.put("msg","窗帘详情修改错误");
                            return  m;
                        }
                        if (commodityOrderMap.get("itemId")!=null){//当不传itemId时，为退回接口，不需要以下操作


                            /**
                             * 计算价格（new）
                             */


                            String itemId=commodityOrderMap.get("itemId").toString();//型号
                            BigDecimal dosage=new BigDecimal(commodityOrderMap.get("dosage").toString());//用量
                            BigDecimal onePrice=BigDecimal.valueOf(0);//单价
                            Item itemPrice = itemService.getItemByItemNO(itemId);//计算价格所需
                            switch (customerTypeId) {//通过客户类别判断销售单价
                                case "02":onePrice=itemPrice.getPriceSale();break;
                                case "06":onePrice=itemPrice.getPriceFx();break;
                                case "09":onePrice=itemPrice.getPriceHome();break;
                                case "05":onePrice=itemPrice.getSalePrice();break;
                                case "08":onePrice=itemPrice.getPriceSale();break;
                                case "10":onePrice=itemPrice.getPriceSale();break;
                                default: m.put("code",1);
                                    m.put("msg","窗帘计算错误");
                            }

                            if(onePrice==null){//判断价格是否为空
                                m.put("code",1);
                                m.put("data","编号itemId为"+itemId+"的配件销售价格为空");
                                m.put("msg","价格重新计算错误，数据库没有维护好！");
                                return  m;
                            }
                            smallOne=dosage.multiply(onePrice);

                            oneAllCost=oneAllCost.add(smallOne);
                        }


                        }

                        if (oneAllCost.compareTo(BigDecimal.valueOf(0))!=0){//价格不为零
                            Ctm_order_detail ctm_order_detail=ctm_orderDao.findCtmBbylineNo(orderNo,lineNo);
                            if(ctm_order_detail.getUnitPrice().compareTo(oneAllCost)!=0){//（型号）价格变动

                                BigDecimal qtyRequired=ctm_order_detail.getQtyRequired();//数量
                                BigDecimal finalCost=oneAllCost.multiply(qtyRequired);//总价等于变动后的单价oneAllCost乘于数量
                                BigDecimal finalCost2=finalCost.setScale(2, BigDecimal.ROUND_HALF_UP);//四舍五入，保留两位小数

                                if (!ctm_orderDao.updateCtmdeatailunitPrice(orderNo,lineNo,oneAllCost,finalCost2)){//更新价格
                                    m.put("code",1);
                                    m.put("msg","窗帘单价和总价更新错误");
                                }
                            }
                        }



                }

                /**
                 * 获取更新后的订单所有详情总价，更新订单头部总价
                 */
                List<Map<String,Object>> finalCostMaps=ctm_orderDao.getCtmBfinalCostsbyorderNo(orderNo);
                Ctm_order ctm_order=ctm_orderDao.getOrderH(orderNo);
                BigDecimal allBFinalCost=BigDecimal.valueOf(0);
                for (Map<String,Object> map1:finalCostMaps){
                    BigDecimal bFinalCost=(BigDecimal) map1.get("FINAL_COST");
                    allBFinalCost=allBFinalCost.add(bFinalCost);
                }
                if (ctm_order.getAllSpend().compareTo(allBFinalCost)!=1){//总价有变化，更新

                    if (!ctm_orderDao.updateOrderAllspend(orderNo,allBFinalCost)){
                        m.put("code",1);
                        m.put("msg","订单总价更新错误");
                    }
                }



            }




            m.put("code",0);
            m.put("data",oneAllCost);//单个窗帘花费
            m.put("msg","SUCCESS");
        }else {
            m.put("code",1);
            m.put("msg","订单头部修改错误");
        }

        if(deleteIds!=null){
            for (String id:deleteIds){
                if (!commodityOrderDao.deleteCommodityOrder(id)){
                    m.put("code",1);
                    m.put("msg","配件删除改错误");
                    break;
                }
            }
        }

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
    public Map getAllCurOrders(Map m ) throws UnsupportedEncodingException {
        Integer limit=Integer.parseInt(m.get("limit").toString());
        Integer page=Integer.parseInt(m.get("page").toString());

        String beginTime=m.get("beginTime").toString();
        String finishTime=m.get("finishTime").toString();
        String curtainStatusId=m.get("curtainStatusId").toString();
//        String companyId=m.get("companyId").toString();
//        List<String> users=new ArrayList<>();
//        if (companyId.equals("")){
//            users=null;
//        }else{
//            List<Map<String,Object>> userMaps=web_userDao.getAllUserByComId(companyId);//查找属于同个公司的用户
//
//
//            if (userMaps.size()!=0){
//                for (Map<String,Object> map1:userMaps){
//                    users.add(map1.get("LOGINNAME").toString());
//                }
//            }
//        }


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
        List<Map<String,Object>> list=ctm_orderDao.getOrdersManager(page,lastNum,null,"0",find,beginTime,finishTime,null,curtainStatusId);
        List<Map<String,Object>> data=new ArrayList<>();
        map.put("count",ctm_orderDao.countOrdersManager(null,"0",find,beginTime,finishTime,null,curtainStatusId));
        for (Map<String,Object> m1:list) {
            String realName=web_userDao.getRealName(m1.get("CUSTOMER_CODE").toString());//用户名
            realName=StringUtil.getUtf8(realName);//转码

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
            m1.put("realName",realName);//客户吗，为了审核
            m1.put("OREDERB_NUM",orderB_num);//订单商品数量
            m1.put("ORDERBODY",list2);
            data.add(m1);
        }


        map.put("data",data);
//        m.put("realName",realName);//客户吗，为了审核
        map.put("code",0);
        map.put("msg","SUCCESS");
        return map;

    }

    @Override
    public Map curOrderCount(Map map) throws UnsupportedEncodingException, InvocationTargetException, IllegalAccessException {
        Timestamp nowTime=new Timestamp(System.currentTimeMillis());//获取当前时间
        String product_group_tpye=map.get("product_group_tpye").toString();
        String companyId=map.get("companyId").toString();

        String arrearsFlag="";
        if (map.get("arrearsFlag")!=null){//判断是否为null
            arrearsFlag=map.get("arrearsFlag").toString();
        }//不选活动，要检查欠帐，选活动了，就判断状态是否为Y。当Y时，要检查欠帐，为N时，不检查余额，直接提交成功变成已提交

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

//        BigDecimal resideMoney=ctm_orderDao.getResideMoney(companyId).add(money);//加上优惠券
        BigDecimal resideMoney=ctm_orderDao.getResideMoney(cid);

        String statusId=" ";
        if(arrearsFlag.equals("N")){//不选活动，要检查欠帐，选活动了，就判断状态是否为y。当y时，要检查欠帐，为n时，不检查余额，直接提交成功变成已提交

            statusId="1";
            ctm_order.setStatusId(statusId);//已经提交
            ctm_order.setWebTjTime(nowTime);//获取当前时间（记录已经提交时间）

        }else {
            if ((promotion_cost.compareTo(BigDecimal.valueOf(0))==0)||(money.compareTo(promotion_cost)!=-1)){//订单金额为0时可以直接提交
                statusId="1";
                ctm_order.setStatusId(statusId);//已经提交
                ctm_order.setWebTjTime(nowTime);//获取当前时间（记录已经提交时间）

            }else{
                statusId="5";
                ctm_order.setStatusId(statusId);//欠款待提交
            }
        }

        /*if (resideMoney.compareTo(promotion_cost)!=-1){
            statusId="1";
            ctm_order.setStatusId(statusId);//已经提交
        }else{
            statusId="5";
            ctm_order.setStatusId(statusId);//欠款待提交
        }*/


        //统计所花券金额
        BigDecimal allRebateMonth=BigDecimal.valueOf(0);
        BigDecimal allRebateYear=BigDecimal.valueOf(0);

        BigDecimal allFinalCostB=BigDecimal.valueOf(0);//统计明显所有最终花费，更新头部总花费

        if (ctm_orderDao.updateOrder(ctm_order)){//订单头录入

            String order=ctm_order.getOrderNo();

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


                //优惠券
                BigDecimal rebateMonth =BigDecimal.valueOf(0);
                BigDecimal rebateYear=BigDecimal.valueOf(0);
                if (lineNo!=sizeNum){
                    if(promotion_cost.compareTo(money)!=1){//订单价格小于优惠券
                        if (promotion_cost.compareTo(money_m)!=1){//订单小于月券
                            rebateMonth=BackUtil.getBackMoney(promotion_cost,money,promotion_cost,ctm_order_detail.getPromotionCost());//月返利

                        }else {
                            rebateMonth=BackUtil.getBackMoney(promotion_cost,money,money_m,ctm_order_detail.getPromotionCost());//月返利
                            rebateYear=BackUtil.getBackMoney(promotion_cost,money,promotion_cost.subtract(money_m),ctm_order_detail.getPromotionCost());
                        }
                    }else{//订单价格大于优惠券
                        rebateMonth=BackUtil.getBackMoney(promotion_cost,money,money_m,ctm_order_detail.getPromotionCost());//月返利
                        rebateYear=BackUtil.getBackMoney(promotion_cost,money,money_y,ctm_order_detail.getPromotionCost());

                    }
                }else {//获取最后一个商品分利
                    rebateMonth=BackUtil.getLastBackMoney(money_m,allRebateMonth,ctm_order_detail.getPromotionCost());
                    rebateYear=BackUtil.getLastBackMoney(money_y,allRebateYear,ctm_order_detail.getPromotionCost());
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



                if (!ctm_orderDao.updateOrderB(ctm_order_detail)){


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
