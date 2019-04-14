package com.yulan.controller;

import com.yulan.service.Ctm_orderService;
import com.yulan.service.CurtainOrderService;
import com.yulan.service.Sal_rebate_certificateService;
import com.yulan.service.Sal_rebate_certificate_recordService;
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
    @Autowired
    private Sal_rebate_certificateService sal_rebate_certificateService;

    @Autowired
    private Sal_rebate_certificate_recordService sal_rebate_certificate_recordService;

    @Autowired
    private CurtainOrderService curtainOrderService;

    /**
     * 订单列表获取
     * @param m
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("getOrders")
    @ResponseBody
    public Map getOrders(@RequestBody Map<String,Object> m) throws UnsupportedEncodingException {
        Integer limit=Integer.parseInt(m.get("limit").toString());
        Integer page=Integer.parseInt(m.get("page").toString());
        String orderType=m.get("orderType").toString();
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
        String state_id=null;
        if (!m.get("state_id").toString().equals("")){
            state_id=m.get("state_id").toString();
        }
        if (orderType.equals("")){
            orderType=null;
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

        Map map=ctm_orderService.getOrders(page,lastNum,cid,state_id,find,beginTime,finishTime,orderType,curtainStatusId);
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
        String cid = (String)m.get("cid");
        return response.getResponseMap(0,"SUCCESS" ,ctm_orderService.getOrderContent(order_no,cid));
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


    /**
     * 获取用户优惠券
     * @param m
     * @return
     */
    @RequestMapping("getRebate")
    @ResponseBody
    public Map getRebate (@RequestBody Map<String,Object> m) throws UnsupportedEncodingException {
        return  ctm_orderService.getRebate(m);
    }

    /**
     * 显示优惠券摊分
     * @param m
     * @return
     */
    @RequestMapping("showRebate")
    @ResponseBody
    public Map showRebate (@RequestBody Map<String,Object> m) throws InvocationTargetException, IllegalAccessException {
        return  ctm_orderService.showRebate(m);
    }

    /**
     * 取消订单
     * @param m
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping("cancelOrder")
    @ResponseBody
    public Map cancelOrder (@RequestBody Map<String,Object> m) throws InvocationTargetException, IllegalAccessException {
        return  ctm_orderService.cancelOrder(m);
    }

    /**
     * 重新提交订单
     * @param m
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping("putAgainOrder")
    @ResponseBody
    public Map putAgainOrder (@RequestBody Map<String,Object> m) throws InvocationTargetException, IllegalAccessException {
        return  ctm_orderService.putAgainOrder(m);
    }

    /**
     * 个人中心优惠券管理
     * @param m
     * @return
     */
    @RequestMapping("findRebate")
    @ResponseBody
    public Map getRebates (@RequestBody Map<String,Object> m) throws InvocationTargetException, IllegalAccessException, UnsupportedEncodingException {
        return  sal_rebate_certificateService.getRebate(m);
    }


    /**
     * 优惠券使用记录
     * @param m
     * @return
     */
    @RequestMapping("findRecrods")
    @ResponseBody
    public Map findRecrods (@RequestBody Map<String,Object> m) throws InvocationTargetException, IllegalAccessException, UnsupportedEncodingException {
        return  sal_rebate_certificate_recordService.findRecrods(m);
    }

    /**
     * 获取优惠券返利集合
     * @param m
     * @return
     */
    @RequestMapping("getReturnRecord")
    @ResponseBody
    public Map getReturnRecord (@RequestBody Map<String,Object> m) throws InvocationTargetException, IllegalAccessException, UnsupportedEncodingException {
        return  sal_rebate_certificateService.getReturnRecord(m);
    }

    /**
     * 提交窗帘审核
     * @param m
     * @return
     */
    @RequestMapping("insertCurtain")
    @ResponseBody
    public Map insertCurtain (@RequestBody Map<String,Object> m) throws InvocationTargetException, IllegalAccessException, UnsupportedEncodingException {
        return  curtainOrderService.insertCurtain(m);
    }

    /**
     * 窗帘审核 退回/修改
     * @param m
     * @return
     */
    @RequestMapping("updateCurtainOrder")
    @ResponseBody
    public Map updateCurtainOrder (@RequestBody Map<String,Object> m) throws InvocationTargetException, IllegalAccessException, UnsupportedEncodingException {
        return  curtainOrderService.updateCurtainOrder(m);
    }

    /**
     * 窗帘审核 通过
     * @param m
     * @return
     */
    @RequestMapping("updateCurOrderStatus")
    @ResponseBody
    public Map updateCurOrderStatus (@RequestBody Map<String,Object> m) throws InvocationTargetException, IllegalAccessException, UnsupportedEncodingException {
        return  curtainOrderService.updateCurOrderStatus(m);
    }




}
