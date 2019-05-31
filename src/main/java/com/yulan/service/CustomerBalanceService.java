package com.yulan.service;

import java.util.Map;

public interface CustomerBalanceService {

    Map getCustomerBalanceInfo(String cid, Integer page, Integer lastNum);

    Map getCustomerBalancePeriodDetailInfo(String cid, String startDate, String endDate, Integer page, Integer lastNum);

    Map customerCheck(String cid, String startDate, String customerCheckState, String customerCheckComment);

}
