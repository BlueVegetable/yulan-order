package com.yulan.controller;


import com.yulan.pojo.Webcontractwfmember;
import com.yulan.service.WebcontractwfmemberService;
import com.yulan.utils.Response;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="webcontractwfmember")
public class WebcontractwfmemberController {
    @Autowired
    private WebcontractwfmemberService webcontractwfmemberService;

    @RequestMapping("getWebcontractwfmembers")
    @ResponseBody
    public Map<String,Object> getwebcontractwfmember(@RequestParam(name = "limit", required = false) Integer limit,
                                        @RequestParam(name = "page", required = false) Integer page,
                                        @RequestParam(name="year",required = false) String year,
                                                     @RequestParam(name="wfUserId",required = false) String wfUserId) throws UnsupportedEncodingException {
        if(limit==null||page==null) {
            page=null;
            limit=null;
        } else {
            page=(page-1)*limit+1;
        }
        int lastNum=page+limit-1;
        Map map = webcontractwfmemberService.getebcontractwfmemberbyYear(year,page,lastNum,wfUserId);
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    @RequestMapping("getAllroles")
    @ResponseBody
    public List<Map> getAllroles() throws UnsupportedEncodingException {
        return webcontractwfmemberService.getAllroles();
    }

    @RequestMapping("checkUser")
    @ResponseBody
    public Map<String,Object> checkUser(@RequestParam("userId") String userId) throws UnsupportedEncodingException {

        if(webcontractwfmemberService.checkUser(userId)!=null) {
            return Response.getResponseMap(0,"成功",webcontractwfmemberService.checkUser(userId));
        } else {
            return Response.getResponseMap(1,"用户账号错误",null);
        }
    }

    @RequestMapping("update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody Webcontractwfmember webcontractwfmember) throws UnsupportedEncodingException {
        webcontractwfmember.setBindUserName(StringUtil.setUtf8(webcontractwfmember.getBindUserName()));
        if(webcontractwfmemberService.update(webcontractwfmember)!=0) {

            return Response.getResponseMap(0,"修改成功",null);
        } else {
            return Response.getResponseMap(1,"修改错误",null);
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestParam("wfUserId")String wfUserId,@RequestParam("cYear")String cYear) throws UnsupportedEncodingException {

        Integer year=Integer.parseInt(cYear);
        if(webcontractwfmemberService.delete(wfUserId,year)!=0) {

            return Response.getResponseMap(0,"删除成功",null);
        } else {
            return Response.getResponseMap(1,"删除失败",null);
        }
    }
    @RequestMapping("add")
    @ResponseBody
    public Map<String,Object> add(@RequestBody Webcontractwfmember webcontractwfmember)  {
        webcontractwfmember.setDisabled(1);

        if(webcontractwfmemberService.add(webcontractwfmember)!=0) {

            return Response.getResponseMap(0,"增加成功",null);
        } else {
            return Response.getResponseMap(1,"增加失败",null);
        }
    }
}
