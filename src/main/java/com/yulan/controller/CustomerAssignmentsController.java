package com.yulan.controller;

import com.yulan.service.CustomerAssignmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@RequestMapping("assignments")
public class CustomerAssignmentsController {
    @Autowired
    private CustomerAssignmentsService customerAssignmentsService;


    /**
     * 任务查询
     * @param m
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("getAssignments")
    @ResponseBody
    public Map getPack(@RequestBody Map<String,Object> m) throws UnsupportedEncodingException {
        return  customerAssignmentsService.getAssignments(m);
    }
}
