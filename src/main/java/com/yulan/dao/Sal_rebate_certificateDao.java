package com.yulan.dao;

import com.yulan.pojo.Sal_rebate_certificate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Sal_rebate_certificateDao {
    /**
     * 获取客户优惠券
     */
    List<Sal_rebate_certificate> getRebate(@Param("cid")String cid);

}
