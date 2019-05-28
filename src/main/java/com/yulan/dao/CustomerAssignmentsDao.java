package com.yulan.dao;

import com.yulan.pojo.CustomerAssignments;
import org.apache.ibatis.annotations.Param;

public interface CustomerAssignmentsDao {
    CustomerAssignments getCustomerAssignments(@Param("cid")String cid,@Param("year")String year,@Param("month")String month);

}
