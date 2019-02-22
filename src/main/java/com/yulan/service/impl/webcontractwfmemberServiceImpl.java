package com.yulan.service.impl;

import com.yulan.dao.WebcontractwfmemberDao;
import com.yulan.pojo.Webcontractwfmember;
import com.yulan.pojo.Webcontractwfrole;
import com.yulan.service.WebcontractwfmemberService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class webcontractwfmemberServiceImpl implements WebcontractwfmemberService {
    @Autowired
    private WebcontractwfmemberDao webcontractwfmemberDao;

    @Override
    public Map getebcontractwfmemberbyYear(String year,Integer start,Integer number,String wfUserId) throws UnsupportedEncodingException {
        Map map=new HashMap<String,Object>(2);
        List<Webcontractwfmember> list=webcontractwfmemberDao.getAllwebcontractwfmemberbyyear(year,start,number,wfUserId);
        List<Webcontractwfmember> list2=new ArrayList<>();
        for (Webcontractwfmember w:list){
            Webcontractwfrole webcontractwfrole=webcontractwfmemberDao.gateRoleName(w.getRoleId());
            if (webcontractwfrole.getDescp()!=null){
                webcontractwfrole.setDescp(StringUtil.getUtf8(webcontractwfrole.getDescp()));
            }
            w.setWebcontractwfrole(webcontractwfrole);
            if(w.getBindUserName()!=null){
                w.setBindUserName(StringUtil.getUtf8(w.getBindUserName()));
            }

            list2.add(w);
        }
        map.put("data",list2);
        map.put("count",webcontractwfmemberDao.count(year,wfUserId));
        return  map;

    }

    @Override
    public List<Map> getAllroles() throws UnsupportedEncodingException {
        List<Map> l=new ArrayList<>();
        for(Map m: webcontractwfmemberDao.getAllroles()){

            String id=StringUtil.getUtf8(m.get("id").toString());
            m.put("id",id);
            l.add(m);

        }
        return l;
    }

    @Override
    public String checkUser(String userId) throws UnsupportedEncodingException {
        if (webcontractwfmemberDao.checkUser(userId)!=null){
            return StringUtil.getUtf8(webcontractwfmemberDao.checkUser(userId));
        }
        return null;
    }

    @Override
    public int update(Webcontractwfmember webcontractwfmember) {
        return webcontractwfmemberDao.update(webcontractwfmember);
    }

    @Override
    public int add(Webcontractwfmember webcontractwfmember) {
        return webcontractwfmemberDao.add(webcontractwfmember);
    }

    @Override
    public int delete(String wfUserId,int cYear) {
        return webcontractwfmemberDao.delete(wfUserId,cYear);
    }
}
