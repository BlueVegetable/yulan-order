package com.yulan.service.impl;

import com.yulan.dao.Web_userDao;
import com.yulan.pojo.Web_user;
import com.yulan.service.Web_userService;
import com.yulan.utils.Response;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class Web_userServiceImpl implements Web_userService {
    @Autowired
    private Web_userDao web_userDao;

    @Override
    public Map login(String loginName, String password,Integer year) throws UnsupportedEncodingException {
        Map map=new HashMap();


            Web_user web_user=web_userDao.login(loginName,password);
            if(web_user!=null){
                if(web_user.getType().equals("SALEMAN")||web_user.getType().equals("USER")){//后做修改
                    String position=web_userDao.getPosition(web_user.getLoginName(),year);
                    if(position==null){
                        List<Map<String,Object>> list1=web_userDao.getArea_position(loginName);
                        List<Map<String,Object>> area_positions=new ArrayList<>();
                        for (Map<String,Object> m:list1){
                            Map<String,Object> map1=new HashMap<>();
                            String areap=StringUtil.getUtf8(m.get("POSITION").toString());
                            /**
                             * 销售中心经理>>中心总经理，大区经理>>办事处经理，片区经理>>业务经理
                             */
                            if(areap.equals("中心总经理")){
                                map1.put("position","MANAGER");
                            }else if(areap.equals("办事处经理")){
                                map1.put("position","SALEMAN_M");
                            }else if(areap.equals("业务经理")){
                                map1.put("position","SALEMAN_S");

                            }
                            area_positions.add(map1);
                        }
                        HashSet h = new HashSet(area_positions);
                        area_positions.clear();
                        area_positions.addAll(h);

                        map.put("pos",area_positions);

                    }else {
                        List<Map<String,Object>> inters=new ArrayList<>();
                        Map<String,Object> map2=new HashMap<>();
                        map2.put("position",position);
                        inters.add(map2);
                        HashSet h = new HashSet(inters);
                        inters.clear();
                        inters.addAll(h);

                        map.put("pos",inters);
                    }
                }

                web_user.setCompany(StringUtil.getUtf8(web_user.getCompany()));

                web_user.setRealName(StringUtil.getUtf8(web_user.getRealName()));


                map.put("data",web_user);
                map.put("code",0);
                return map;
            }

        return null;


    }

    @Override
    public Map updateuserState(String userState, String cid,Integer year) throws UnsupportedEncodingException {
        Map<String,Object> map=new HashMap<>();
        if (web_userDao.updateuserState(userState,cid)){
            String position=web_userDao.getPosition(cid,year);
            Web_user web_user=web_userDao.getUser(cid);
            map.put("position",StringUtil.getUtf8(position));
            map.put("data",web_user);
            map.put("code",0);
            map.put("msg","修改成功");
        }else {
            map= Response.getResponseMap(1,"修改失败",null);
        }
        return map;
    }
}
