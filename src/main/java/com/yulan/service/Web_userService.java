package com.yulan.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface Web_userService {
    Map login(String loginName, String password, Integer year) throws UnsupportedEncodingException;

    Map updateuserState(String userState,String cid,Integer year) throws UnsupportedEncodingException;
}
