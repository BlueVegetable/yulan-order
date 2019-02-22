package com.yulan.controller;

import com.yulan.service.Ctm_orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@RequestMapping("order")
public class Ctm_orderController {
    @Autowired
    private Ctm_orderService ctm_orderService;

    @RequestMapping("getOrders")
    @ResponseBody
    public Map getOrders(@RequestBody Map<String,Object> m) throws UnsupportedEncodingException {
        Integer limit=Integer.parseInt(m.get("limit").toString());
        Integer page=Integer.parseInt(m.get("page").toString());
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
        Map map=ctm_orderService.getOrders(page,lastNum,cid,state_id,find);
        map.put("code",0);
        map.put("msg","");



        return map;
    }

}
