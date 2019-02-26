package com.yulan.controller;

import com.yulan.pojo.ActivityGroupType;
import com.yulan.service.ActivityGroupTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("activityGroupType")
public class ActivityGroupTypeController{

	@Autowired
	private ActivityGroupTypeService activityGroupTypeService;

	@ResponseBody@RequestMapping("getActivityGroupTypeByName")
	public ActivityGroupType getActivityGroupTypeByName(String name) throws UnsupportedEncodingException {
		return activityGroupTypeService.getActivityGroupTypeByName(name);
	}

}