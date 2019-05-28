package com.yulan.dao;

import com.yulan.pojo.CustomerBalancePeriodDetail;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CustomerBalancePeriodDetailDao {

    List<CustomerBalancePeriodDetail> getCustomerBalancePeriodDetailInfo(@Param("CID") String cid, @Param("START_DATE") Date startDate, @Param("END_DATE") Date endDate);

}
