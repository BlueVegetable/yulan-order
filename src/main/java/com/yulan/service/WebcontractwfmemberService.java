package com.yulan.service;

import com.yulan.pojo.Webcontractwfmember;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface WebcontractwfmemberService {
    //获取每年权限-审核人员
    Map getebcontractwfmemberbyYear(String year,Integer start,Integer number,String wfUserId) throws UnsupportedEncodingException;

    //获取所有年份
    List<Map> getAllroles() throws UnsupportedEncodingException;

    //获取用户名
    String checkUser(String userId) throws UnsupportedEncodingException;

    //更新
    int update( Webcontractwfmember webcontractwfmember);

    //新增
    int add(Webcontractwfmember webcontractwfmember);

    //删除
    int delete(String wfUserId,int cYear);


}
