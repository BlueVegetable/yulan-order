package com.yulan.service;

import com.yulan.pojo.Web_user;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface Web_userService {
    Map login(String loginName, String password, Integer year) throws UnsupportedEncodingException;

    List<Web_user> getWebUsersByCompanyId(String companyId);

    Web_user getWebUserByCID(String CID);

    Map updateuserState(String userState,String cid,Integer year) throws UnsupportedEncodingException;
}
