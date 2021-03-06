package com.yulan.dao;

import com.yulan.pojo.CustomerBalancePeriod;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerBalancePeriodDao {

    List<CustomerBalancePeriod> getCustomerBalanceInfo(@Param("CID") String cid,@Param("start")Integer page, @Param("number")Integer lastNum);

    boolean customerCheck(@Param("CID") String cid,@Param("startDate") String startDate,@Param("customerCheckState") String customerCheckState,
                          @Param("customerCheckComment") String customerCheckComment);
}
