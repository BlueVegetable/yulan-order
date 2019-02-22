package com.yulan.controller;

import com.yulan.service.CustomerinfocardgroupService;
import com.yulan.utils.Response;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@RequestMapping("customerinfocardgroup")
public class CustomerinfocardgroupController {
    @Autowired
    private CustomerinfocardgroupService customerinfocardgroupService;

    @RequestMapping("getcustomerinfocardgroups")
    @ResponseBody
    public Map<String,Object> getwebcontractwfmember(@RequestParam(name = "limit", required = false) Integer limit,
                                                     @RequestParam(name = "page", required = false) Integer page,
                                                     @RequestParam(name="descp",required = false) String descp) throws UnsupportedEncodingException {
        int deleted=0;
        if(limit==null||page==null) {
            page=null;
            limit=null;
        } else {
            page=(page-1)*limit+1;
        }
        int lastNum=page+limit-1;
        descp= StringUtil.setUtf8(descp);
        Map map = customerinfocardgroupService.getCustomerinfocardgroups(page,lastNum,descp,deleted);
        map.put("code",0);
        map.put("msg","");
        return map;
    }


    @RequestMapping("delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestParam("Id") String Id) throws UnsupportedEncodingException {


        if(customerinfocardgroupService.updateDelete(Id)!=0) {

            return Response.getResponseMap(0,"删除成功",null);
        } else {
            return Response.getResponseMap(1,"删除失败",null);
        }
    }
}
