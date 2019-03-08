package com.yulan.dao;

import com.yulan.pojo.PostAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostAddressDao {

    boolean addPostAddress(PostAddress postAddress);

    boolean updatePostAddress(PostAddress postAddress);

    List<PostAddress> getPostAddress(@Param("CID") String cid);

    boolean deletePostAddress(PostAddress postAddress);


}
