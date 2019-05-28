package com.yulan.service;

import java.util.Date;
import java.util.Map;

public interface CustomerBalanceService {

    Map getCustomerBalanceInfo(String cid, Integer page, Integer lastNum);

    Map getCustomerBalancePeriodDetailInfo(String cid, Date startDate, Date endDate);

}
