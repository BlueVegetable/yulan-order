package com.yulan.dao;

import com.yulan.pojo.SalReturnRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SalReturnRecordDao {
    /**
     * 获取优惠券返利组成
     * @param id
     * @return
     */
    List<SalReturnRecord>  getReturnRecord(@Param("id")String id);
}
