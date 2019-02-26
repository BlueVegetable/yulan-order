package com.yulan.dao;

import com.yulan.pojo.ActivityGroupType;

public interface ActivityGroupTypeDao {

	int addActivityGroupType(ActivityGroupType activityGroupType);

	int deleteActivityGroupTypeByID(String activityGroupTypeID);

	ActivityGroupType getActivityGroupTypeByID(String activityGroupTypeID);

	ActivityGroupType getActivityGroupTypeByName(String name);

	int updateActivityGroupType(ActivityGroupType activityGroupType);

}