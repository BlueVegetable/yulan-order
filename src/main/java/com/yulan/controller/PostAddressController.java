package com.yulan.controller;

import com.yulan.pojo.PostAddress;
import com.yulan.service.PostAddressService;
import com.yulan.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("postAddress")
public class PostAddressController {
    @Autowired
    private PostAddressService postAddressService;
    /**
     * 添加发货地址接口
     * @param postAddress
     * @return
     */
    @RequestMapping(value = "addPostAddress")
    @ResponseBody
    public Map addPostAddress(@RequestBody PostAddress postAddress)throws IOException {
        if(postAddressService.addPostAddress(postAddress)){
            return Response.getResponseMap(0,"SUCCESS",null);
        }else{
            return Response.getResponseMap(1,"Failed",null);
        }
    }

    /**
     * 修改收货地址接口
     * @param postAddress
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "updatePostAddress")
    @ResponseBody
    public Map updatePostAddress(@RequestBody PostAddress postAddress)throws IOException {
        if(postAddressService.updatePostAddress(postAddress)){
            return Response.getResponseMap(0,"SUCCESS",null);
        }else{
            return Response.getResponseMap(1,"Failed",null);
        }
    }

    @RequestMapping(value = "deletePostAddress")
    @ResponseBody
    public Map deletePostAddress(@RequestBody PostAddress postAddress){
        if(postAddressService.deletePostAddress(postAddress)){
            return Response.getResponseMap(0,"SUCCESS",null);
        }else{
            return Response.getResponseMap(1,"Failed",null);
        }
    }

    /**
     * 得到收货地址接口
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "getPostAddress")
    @ResponseBody
    public Map getPostAddress(@RequestBody Map<String,Object> data)throws IOException {
        String cid = (String)data.get("cid");
        return postAddressService.getPostAddress(cid);
    }
}
