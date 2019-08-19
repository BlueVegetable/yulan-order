package com.yulan.dao;

import org.apache.ibatis.annotations.Param;
import  com.yulan.pojo.Web_user;

import java.util.List;
import java.util.Map;

public interface Web_userDao {


    //登陆
    Web_user login(@Param("loginName")String loginName,@Param("password")String password);

    //检查账号密码
    String check(String loginName);

    //
    boolean updateuserState(@Param("userState")String userState,@Param("cid")String cid);

    Web_user getUser(@Param("cid")String cid);

    List<Web_user> getWebUsersByCompanyId(String companyId);

    String getPosition(@Param("cid")String cid,@Param("year")Integer year);//获取内部审核人员职位

    List<Map<String,Object>> getArea_position(@Param("cid") String cid);//获取业务员职位

    List<Map<String,Object>> getAllUserByCustomerMainId(@Param("customerMainId") String customerMainId);//获取同个customermainId所有用户
    //将操作员账号转换成公司ID
    String changeLoginNameToCompanyID(String cid);

    //将操作员账号转换成CurtainMainId
    String changeCompanyIdToCurtainMainId(@Param("companyId") String companyId);

    //获取用户名
    String getRealName(@Param("cid")String cid);

    //获取同个companyId所有用户
    List<Map<String,Object>> getAllUserByComIdorFlink(@Param("companyId") String companyId);
}
