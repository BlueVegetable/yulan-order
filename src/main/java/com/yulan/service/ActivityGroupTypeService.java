package com.yulan.service;

import com.yulan.pojo.ActivityGroupType;

import java.io.UnsupportedEncodingException;

public interface ActivityGroupTypeService {

	boolean addActivityGroupType(ActivityGroupType activityGroupType);

	boolean deleteActivityGroupTypeByID(String activityGroupTypeID);

	ActivityGroupType getActivityGroupTypeByID(String activityGroupTypeID);

	ActivityGroupType getActivityGroupTypeByName(String name) throws UnsupportedEncodingException;

	boolean updateActivityGroupType(ActivityGroupType activityGroupType);

}