package com.yulan.controller;

import com.yulan.service.CustomerBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("customerBalance")
public class CustomerBalanceController {
    @Autowired
    private CustomerBalanceService customerBalanceService;

    /**
     * 获取对账单信息接口
     * @param data
     * @return
     */
    @RequestMapping(value = "getCustomerBalanceInfo")
    @ResponseBody
    public Map getCustomerBalanceInfo(@RequestBody Map<String,Object> data){
        String cid = (String)data.get("cid");
        Integer limit = (Integer) data.get("limit");
        Integer page = (Integer)data.get("page");
        if(limit==null||page==null) {
            page=null;
            limit=null;
        } else {
            page=(page-1)*limit+1;
        }
        int lastNum=page+limit-1;
        return customerBalanceService.getCustomerBalanceInfo(cid,page,lastNum);
    }

    /**
     * 获取对账单的详细信息
     * @param data
     * @return
     */
    @RequestMapping(value = "getCustomerBalancePeriodDetailInfo")
    @ResponseBody
    public Map getCustomerBalancePeriodDetailInfo(@RequestBody Map<String,Object> data){
        String cid = (String)data.get("cid");
        String startDate = (String)data.get("startDate");
        String endDate = (String)data.get("endDate");
        Integer limit = (Integer) data.get("limit");
        Integer page = (Integer)data.get("page");
        if(limit==null||page==null) {
            page=null;
            limit=null;
        } else {
            page=(page-1)*limit+1;
        }
        int lastNum=page+limit-1;
        return customerBalanceService.getCustomerBalancePeriodDetailInfo(cid, startDate ,endDate, page,lastNum);
    }
}
