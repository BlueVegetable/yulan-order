package com.yulan.service.impl;

import com.yulan.dao.PostAddressDao;
import com.yulan.pojo.CustomerInfoCard;
import com.yulan.pojo.PostAddress;
import com.yulan.service.CustomerInfoService;
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
    @Autowired
    private CustomerInfoService customerInfoService;

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
        if(null != postAddress.getProvince()){
            postAddress.setProvince(stringUtil.setUtf8(postAddress.getProvince()));
        }
        if(null != postAddress.getCity()){
            postAddress.setCity(stringUtil.setUtf8(postAddress.getCity()));
        }
        if(null != postAddress.getCountry()){
            postAddress.setCountry(stringUtil.setUtf8(postAddress.getCountry()));
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
            if(null != postAddress.getProvince()){
                postAddress.setProvince(stringUtil.getUtf8(postAddress.getProvince()));
            }
            if(null != postAddress.getCity()){
                postAddress.setCity(stringUtil.getUtf8(postAddress.getCity()));
            }
            if(null != postAddress.getCountry()){
                postAddress.setCountry(stringUtil.getUtf8(postAddress.getCountry()));
            }
        }
        //添加默认地址
        CustomerInfoCard customerInfoCard = customerInfoService.getCustomerInfo(cid);
        PostAddress postAddress = new PostAddress();
        postAddress.setCid(customerInfoCard.getCid());
        postAddress.setProvince(customerInfoCard.getDistrictText());
        postAddress.setCity(customerInfoCard.getAreaDistrict2Text());
        postAddress.setCountry(customerInfoCard.getAreaDistrict3Text());
        postAddress.setAddressId(0);
        addressList.add(postAddress);

        map.put("data",addressList);
        return map;
    }
}
