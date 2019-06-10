package com.yulan.dao;

import com.yulan.pojo.CustomerAssignments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerAssignmentsDao {
    CustomerAssignments getCustomerAssignments(@Param("users") List<String> users, @Param("year")String year, @Param("month")String month);

}
