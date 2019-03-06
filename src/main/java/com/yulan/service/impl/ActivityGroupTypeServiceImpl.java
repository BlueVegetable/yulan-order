package com.yulan.service.impl;

import com.yulan.dao.ActivityGroupTypeDao;
import com.yulan.pojo.ActivityGroupType;
import com.yulan.service.ActivityGroupTypeService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class ActivityGroupTypeServiceImpl implements ActivityGroupTypeService {

	@Autowired
	private ActivityGroupTypeDao activityGroupTypeDao;

	@Override
	public boolean addActivityGroupType(ActivityGroupType activityGroupType) {
		return activityGroupTypeDao.addActivityGroupType(activityGroupType)>0;
	}

	@Override
	public boolean deleteActivityGroupTypeByID(String activityGroupTypeID) {
		return activityGroupTypeDao.deleteActivityGroupTypeByID(activityGroupTypeID)>0;
	}

	@Override
	public ActivityGroupType getActivityGroupTypeByID(String activityGroupTypeID) {
		return activityGroupTypeDao.getActivityGroupTypeByID(activityGroupTypeID);
	}

	@Override
	public ActivityGroupType getActivityGroupTypeByName(String name) throws UnsupportedEncodingException {
		ActivityGroupType activityGroupType = activityGroupTypeDao.getActivityGroupTypeByName(name);
		if(activityGroupType!=null) {
			activityGroupType.setValue(StringUtil.getUtf8(activityGroupType.getValue()));
		}
		return activityGroupType;
	}

	@Override
	public boolean updateActivityGroupType(ActivityGroupType activityGroupType) {
		return activityGroupTypeDao.updateActivityGroupType(activityGroupType)>0;
	}

}