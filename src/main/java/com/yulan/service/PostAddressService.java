package com.yulan.service;

import com.yulan.pojo.PostAddress;

import java.io.IOException;
import java.util.Map;

public interface PostAddressService {

    boolean  addPostAddress(PostAddress postAddress);

    boolean updatePostAddress(PostAddress postAddress);

    boolean deletePostAddress(PostAddress postAddress);

    Map getPostAddress(String cid)throws IOException;
}
