package com.yulan.service;

import com.yulan.pojo.PostAddress;

import java.io.IOException;
import java.util.Map;

public interface PostAddressService {

    boolean  addPostAddress(PostAddress postAddress)throws IOException;

    boolean updatePostAddress(PostAddress postAddress)throws IOException;

    boolean deletePostAddress(PostAddress postAddress);

    Map getPostAddress(String cid)throws IOException;
}
