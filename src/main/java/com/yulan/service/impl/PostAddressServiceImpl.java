package com.yulan.service.impl;

import com.yulan.dao.PostAddressDao;
import com.yulan.pojo.PostAddress;
import com.yulan.service.PostAddressService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
@Service
public class PostAddressServiceImpl implements PostAddressService {

    private StringUtil stringUtil;
    @Autowired
    private PostAddressDao postAddressDao;

    @Override
    public boolean addPostAddress(PostAddress postAddress) throws IOException {
        postAddress.setPostAddress(stringUtil.setUtf8(postAddress.getPostAddress()));
        postAddress.setWlContacts(stringUtil.setUtf8(postAddress.getWlContacts()));
        return postAddressDao.addPostAddress(postAddress);
    }

    @Override
    public boolean updatePostAddress(PostAddress postAddress) throws IOException {
        return false;
    }

    @Override
    public Map getPostAddress(String cid) throws IOException {
        return null;
    }
}
