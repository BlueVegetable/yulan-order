package com.yulan.service.impl;

import com.yulan.dao.PostAddressDao;
import com.yulan.pojo.PostAddress;
import com.yulan.service.PostAddressService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PostAddressServiceImpl implements PostAddressService {

    private StringUtil stringUtil;
    @Autowired
    private PostAddressDao postAddressDao;

    @Override
    public boolean addPostAddress(PostAddress postAddress) throws IOException {
        if(null != postAddress.getPostAddress()){
            postAddress.setPostAddress(stringUtil.setUtf8(postAddress.getPostAddress()));
        }
        if(null != postAddress.getWlContacts()){
            postAddress.setWlContacts(stringUtil.setUtf8(postAddress.getWlContacts()));
        }
        return postAddressDao.addPostAddress(postAddress);
    }

    @Override
    public boolean updatePostAddress(PostAddress postAddress) throws IOException {
        if(null != postAddress.getPostAddress()){
            postAddress.setPostAddress(stringUtil.setUtf8(postAddress.getPostAddress()));
        }
        if(null != postAddress.getWlContacts()){
            postAddress.setWlContacts(stringUtil.setUtf8(postAddress.getWlContacts()));
        }
        return postAddressDao.updatePostAddress(postAddress);
    }

    @Override
    public Map getPostAddress(String cid) throws IOException {
        Map map = new HashMap();
        List<PostAddress> addressList = new ArrayList<>();
        addressList = postAddressDao.getPostAddress(cid);
        for(int i=0 ; i<addressList.size() ; i++){
            PostAddress postAddress = addressList.get(i);
            if(null != postAddress.getPostAddress()){
                postAddress.setPostAddress(stringUtil.getUtf8(postAddress.getPostAddress()));
            }
            if(null != postAddress.getWlContacts()){
                postAddress.setWlContacts(stringUtil.getUtf8(postAddress.getWlContacts()));
            }
        }
        map.put("data",addressList);
        return map;
    }
}
