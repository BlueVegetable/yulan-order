package com.yulan.dao;

import com.yulan.pojo.Sal_rebate_certificate_record;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Sal_rebate_certificate_recordDao {
    /**
     * 获取优惠券使用记录
     * @param id
     * @return
     */
    List<Sal_rebate_certificate_record> findRecrods(@Param("id")String id);


    Map<String,Object> getReturn();

}
