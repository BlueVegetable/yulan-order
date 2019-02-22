package com.yulan.service;

import com.yulan.pojo.Area_owner;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface Area_ownerService {
    List<Area_owner> getAreaOwnerByAreaCode(String areaCode);
    Map getebcontractwfmemberbyYear(Integer start, Integer number, String owner) throws UnsupportedEncodingException;
}
