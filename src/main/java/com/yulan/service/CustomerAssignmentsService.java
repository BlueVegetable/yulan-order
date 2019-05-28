package com.yulan.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface CustomerAssignmentsService {
    Map getAssignments(Map<String,Object>map) throws UnsupportedEncodingException;

}
