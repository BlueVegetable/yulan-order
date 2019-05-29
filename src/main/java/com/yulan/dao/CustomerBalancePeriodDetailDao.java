package com.yulan.dao;

import com.yulan.pojo.CustomerBalancePeriodDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerBalancePeriodDetailDao {

    List<CustomerBalancePeriodDetail> getCustomerBalancePeriodDetailInfo(@Param("CID") String cid, @Param("START_DATE")String startDate, @Param("END_DATE") String endDate,@Param("start")Integer page, @Param("number")Integer lastNum);

}
