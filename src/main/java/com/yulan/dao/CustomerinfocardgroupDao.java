package com.yulan.dao;

import com.yulan.pojo.Customerinfocardgroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerinfocardgroupDao {

    /**
     * 创建用户资料组
     * @param customerinfocardgroup
     * @return
     */
    int addCustomerInfoCardGroup(Customerinfocardgroup customerinfocardgroup);

    /**
     * 获取所有
     * @param start
     * @param number
     * @param descp
     * @param deleted
     * @return
     */
    List<Customerinfocardgroup> getCustomerinfocardgroups(@Param("start")Integer start, @Param("number") Integer number
                                                            , @Param("descp") String descp,@Param("deleted")int deleted);

    /**
     * 统计
     * @param descp
     * @return
     */
    int count(@Param("descp") String descp);

    /**
     * 获取市场名
     * @param area_code
     * @return
     */
    String  getArea_codeName(String area_code);

    /**
     * 获取区域名
     * @param area_district
     * @return
     */
    String getArea_districtName(String area_district);

    /**
     * 获取客户类型名
     * @param customer_type
     * @return
     */
    String getCustomer_typeName(String customer_type);

    /**
     * 通过备注获取CustomerInfoCardGroup
     * @param descp 备注
     * @return
     */
    Customerinfocardgroup getCustomerInfoCardGroupByName(@Param("descp")String descp);

    /**
     * 标记删除
     * @param Id
     * @param deleted
     * @return
     */
    int updateDelete(@Param("Id") String Id,@Param("deleted")Integer deleted);
}