package com.yulan.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface Ctm_orderService {
   Map getOrders(Integer st,Integer number,String cid,String state_id,String find) throws UnsupportedEncodingException;
}
