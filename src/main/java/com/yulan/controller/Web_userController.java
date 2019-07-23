package com.yulan.controller;

import com.yulan.pojo.Web_user;
import com.yulan.service.CustomerTypeService;
import com.yulan.service.Web_userService;
import com.yulan.utils.Response;
import com.yulan.utils.StringUtil;
import com.yulan.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("web_user")
public class Web_userController {
    private Map<String, HttpSession> sessions = new HashMap<String,HttpSession>();
    @Autowired
    private Web_userService web_userService;
    @Autowired
    private CustomerTypeService customerTypeService;

    @RequestMapping("getWebUserName")
    @ResponseBody
    public Map getWebUserName(String CID) {
        Map result = Response.getResponseMap(0,"",null);
        Web_user webUser = web_userService.getWebUserByCID(CID);
        Map data = new HashMap();
        data.put("CID",CID);
        if(webUser!=null) {
            data.put("name", StringUtil.GBKToUTF8(webUser.getRealName()));
            result.put("data",data);
        }
        return result;
    }

    @RequestMapping("login")
    @ResponseBody
    public Map<String,Object> login(@RequestBody Map<String,Object> m)throws UnsupportedEncodingException {
        String loginName=m.get("loginName").toString();
        String password=m.get("password").toString();
        Integer year=Integer.parseInt(m.get("year").toString());
        Map map = web_userService.login(loginName,password,year);
        if(map==null) {
             map= Response.getResponseMap(1,"账号与密码不相符",null);
            return map;
        }
        else {
           /* int code=1;
            switch (web_user.getType()){
                case "ADMIN": code=1;break;
                case  "ECWEB":code=2;break;
                case  "SUPLY":code=3;break;
                case  "SALEMAN":code=4;break;
                case  "QUERY":code=5;break;
            }*/
//            Map map=Response.getResponseMap(0,"",web_user);
//            String token= Token.createToken(web_user);

            /*session.setAttribute("token",token);
            sessions.put(token,session);*/
//            map.put("token", token );
            Web_user webUser = web_userService.getWebUserByCID(loginName);
            String companyId = webUser.getCompanyId();
            if(companyId == null) {
                companyId = loginName;
            }
            if(customerTypeService.getCustomerTypeByCID(companyId)==null){
                map.put("customerType","");
            }else{
                map.put("customerType",customerTypeService.getCustomerTypeByCID(companyId).getCustomerTypeId());
            }

            map.put("logintime", TimeUtil.getTime());
            return map;
        }
    }
    @RequestMapping("exit")
    @ResponseBody
    public Map<String,Object> exit(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("token");
        if(sessions.containsKey(token)) {
            session.invalidate();
            sessions.remove(token);
            return Response.getResponseMap(0,"退出成功",null);
        } else {
            return Response.getResponseMap(1,"用户未登录",null);
        }
    }

    @RequestMapping("updateUserState")
    @ResponseBody
    public Map<String,Object> updateUserState(@RequestBody Map<String,Object> m) throws UnsupportedEncodingException {
        String cid=m.get("cid").toString();
        String userState=m.get("userState").toString();
        Integer year=Integer.parseInt(m.get("year").toString());
        return web_userService.updateuserState(userState,cid,year);

    }

}
