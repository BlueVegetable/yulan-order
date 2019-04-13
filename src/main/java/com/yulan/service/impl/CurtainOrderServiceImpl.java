package com.yulan.service.impl;

import com.yulan.dao.Ctm_orderDao;
import com.yulan.dao.CurtainOrderDao;
import com.yulan.pojo.Ctm_order;
import com.yulan.pojo.Ctm_order_detail;
import com.yulan.pojo.CurtainOrder;
import com.yulan.service.CurtainOrderService;
import com.yulan.utils.MapUtils;
import com.yulan.utils.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurtainOrderServiceImpl implements CurtainOrderService {
    @Autowired
    private  Ctm_orderDao ctm_orderDao;
    @Autowired
    private CurtainOrderDao curtainOrderDao;



    @Override
    public Map insertCurtain(Map map) throws UnsupportedEncodingException, InvocationTargetException, IllegalAccessException {
        Map m=new HashMap();
        Map dataMap=new HashMap();
        Ctm_order ctm_order=new Ctm_order();
        Map<String ,Object> ctm_ordermap=(Map<String, Object>) map.get("ctm_order");//订单头
        String cid=map.get("cid").toString();
        List<Map<String,Object>> list=(List) map.get("ctm_orders");
        String orderNo=this.getOrderNumber("E");//窗帘订单号
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

        ctm_order.setTelephone(linkpersonandTelmap.get("OFFICE_TEL").toString());//经办人电话

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
                for (Map<String, Object> m3 : curtainList){//窗帘详情录入
                    for (Map.Entry<String, Object> entry : m3.entrySet()) {//窗帘详情转码
                        if (entry.getValue() instanceof String) {
                            String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                            entry.setValue(origin);
                        }
                    }
                    CurtainOrder curtainOrder= MapUtils.mapToBean(m3, CurtainOrder.class);
                    curtainOrder.setOrderNo(orderNo);
                    curtainOrder.setLineNo(String.valueOf(lineNo));
                    curtainOrder.setDateUpdate(nowTime);
                    curtainOrderDao.insertCurtainOrder(curtainOrder);
                }
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

}
