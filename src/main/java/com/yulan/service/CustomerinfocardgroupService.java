package com.yulan.service;

import com.yulan.pojo.Customerinfocardgroup;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface CustomerinfocardgroupService {
    /**
     * 创建用户资料组
     * @param customerinfocardgroup
     * @return
     */
    boolean addCustomerInfoCardGroup(Customerinfocardgroup customerinfocardgroup);

    /**
     * 获取所有
     * @param start
     * @param number
     * @param descp
     * @param deleted
     * @return
     * @throws UnsupportedEncodingException
     */
    Map getCustomerinfocardgroups(Integer start, Integer number, String descp, int deleted) throws UnsupportedEncodingException;

    /**
     * 通过备注获取CustomerInfoCardGroup
     * @param descp 备注
     * @return
     */
    Customerinfocardgroup getCustomerInfoCardGroupByName(String descp);

    /**
     * 标记删除
     * @param Id
     * @return
     */
    int updateDelete(String Id);
}
