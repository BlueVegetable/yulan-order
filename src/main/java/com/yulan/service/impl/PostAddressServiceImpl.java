package com.yulan.service.impl;

import com.yulan.dao.CustomerDao;
import com.yulan.dao.PostAddressDao;
import com.yulan.pojo.Customer;
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
    @Autowired
    private CustomerDao customerDao;


    @Override
    public boolean addPostAddress(PostAddress postAddress) throws IOException {
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
    public boolean deletePostAddress(PostAddress postAddress) {
        return postAddressDao.deletePostAddress(postAddress);
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
        Customer customer = customerDao.getCustomerByID(cid);
        PostAddress postAddress2 = new PostAddress();
        postAddress2.setCid(customer.getCustomerCode());
        postAddress2.setPostAddress(stringUtil.getUtf8(customer.getDeliveryAdress()));
        postAddress2.setAddressId(0);
        postAddress2.setWlContacts("TEST");
        postAddress2.setWlTel("test123");
        addressList.add(0,postAddress2);

        map.put("data",addressList);
        return map;
    }
}
