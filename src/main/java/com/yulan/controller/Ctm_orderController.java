package com.yulan.controller;

import com.yulan.service.Ctm_orderService;
import com.yulan.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("order")
public class Ctm_orderController {
    @Autowired
    private Ctm_orderService ctm_orderService;
    private Response response;

    @RequestMapping("getOrders")
    @ResponseBody
    public Map getOrders(@RequestBody Map<String,Object> m) throws UnsupportedEncodingException {
        Integer limit=Integer.parseInt(m.get("limit").toString());
        Integer page=Integer.parseInt(m.get("page").toString());
        String beginTime=m.get("beginTime").toString();
        String finishTime=m.get("finishTime").toString();
        if (beginTime.equals("") ||finishTime.equals("")){
            beginTime=null;
            finishTime=null;
        }
        String state_id=null;
        if (!m.get("state_id").toString().equals("")){
            state_id=m.get("state_id").toString();
        }


        String cid=m.get("cid").toString();
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
        Map map=ctm_orderService.getOrders(page,lastNum,cid,state_id,find,beginTime,finishTime);
        map.put("code",0);
        map.put("msg","");



        return map;
    }


    /**
     * 获取订单详情
     * @param m
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("getOrder_content")
    @ResponseBody
    public Map getOrder_content(@RequestBody Map<String,Object> m) throws UnsupportedEncodingException {
        String order_no = (String)m.get("order_no");
        String item_no = m.get("item_no").toString();
        return response.getResponseMap(0,"SUCCESS" ,ctm_orderService.getOrderB_content(order_no,item_no));
    }

    /**
     * 获取提货单详情
     */
    @RequestMapping("getPack")
    @ResponseBody
    public Map getPack(@RequestBody Map<String,Object> m) throws UnsupportedEncodingException {
        return  ctm_orderService.getPack(m);
    }

    /**
     * 获取活动价
     */
    @RequestMapping("getPromotion")
    @ResponseBody
    public Map getPromotion(@RequestBody List<Map<String,Object>> list){
        Map map=ctm_orderService.getPromotion(list);
        map.put("msg","SUCCESS");
        map.put("code",0);
        return  map;
    }

    /**
     * 获取用户余额
     * @param m
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("getResidemoney")
    @ResponseBody
    public Map getResidemoney(@RequestBody Map<String,Object> m) throws UnsupportedEncodingException {
        String cid = (String)m.get("cid");

        return response.getResponseMap(0,"SUCCESS" ,ctm_orderService.getResidemoney(cid));
    }

    /**
     * 修改订单状态接口(作废)
     * @param data
     * @return
     */
    @RequestMapping("updateOrderStatus")
    @ResponseBody
    public Map updateOrderStatus(@RequestBody Map<String,Object> data){
        String orderNo = (String)data.get("orderNo");
        String customerCode = (String)data.get("customerCode");
        String statusId = (String)data.get("statusId");
        if(ctm_orderService.updateOrderStatus(orderNo, customerCode, statusId)){
            return response.getResponseMap(0,"SUCCESS" ,ctm_orderService.updateOrderStatus(orderNo, customerCode, statusId));
        }else{
            return response.getResponseMap(1,"Failed" ,ctm_orderService.updateOrderStatus(orderNo, customerCode, statusId));
        }
    }

    /**
     * 订单结算
     */
    @RequestMapping("orderCount")
    @ResponseBody
    public Map orderCount(@RequestBody Map<String,Object> m) throws InvocationTargetException, IllegalAccessException, UnsupportedEncodingException {
        return  ctm_orderService.orderCount(m);
    }

    @RequestMapping("getlink")
    @ResponseBody
    public Map getlinkpersonandTel (@RequestBody Map<String,Object> m) throws UnsupportedEncodingException {
        String cid= m.get("cid").toString();
        return response.getResponseMap(0,"SUCCESS" ,ctm_orderService.getlinkpersonandTel(cid));
    }



}
