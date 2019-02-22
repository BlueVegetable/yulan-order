package com.yulan.dao;

import com.yulan.pojo.Webcontractwfmember;
import com.yulan.pojo.Webcontractwfrole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WebcontractwfmemberDao {
    //按年获取所有权限-人员
    List<Webcontractwfmember> getAllwebcontractwfmemberbyyear(@Param("year") String year, @Param("start")Integer start, @Param("number") Integer number,@Param("wfUserId") String wfUserId);
    //统计
    int count(@Param("year") String year,@Param("wfUserId") String wfUserId);

    //获取所有年份
    List<Map> getAllroles();

    //查找相应权限名称
    Webcontractwfrole gateRoleName(String roleId);

    //查找相应webuser用户名
    String checkUser(String userId);

    //更新
    int update(Webcontractwfmember webcontractwfmember);

    //新增
    int add(Webcontractwfmember webcontractwfmember);

    //删除
    int delete(@Param("wfUserId") String wfUserId,@Param("cYear") int cYear);
}
