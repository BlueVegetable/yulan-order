package com.yulan.service.impl;

import com.yulan.dao.ProductGroupTypeDao;
import com.yulan.pojo.ProductGroupType;
import com.yulan.service.ProductGroupTypeService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class ProductGroupTypeServiceImpl implements ProductGroupTypeService {

	@Autowired
	private ProductGroupTypeDao productGroupTypeDao;

	@Override
	public boolean addProductGroupType(ProductGroupType productGroupType) {
		return productGroupTypeDao.addProductGroupType(productGroupType)>0;
	}

	@Override
	public boolean deleteProductGroupTypeByID(String productGroupTypeID) {
		return productGroupTypeDao.deleteProductGroupTypeByID(productGroupTypeID)>0;
	}

	@Override
	public ProductGroupType getProductGroupTypeByID(String productGroupTypeID) {
		return productGroupTypeDao.getProductGroupTypeByID(productGroupTypeID);
	}

	@Override
	public ProductGroupType getProductGroupTypeByName(String name) throws UnsupportedEncodingException {
		ProductGroupType productGroupType = productGroupTypeDao.getProductGroupTypeByName(name);
		productGroupType.setValue(StringUtil.getUtf8(productGroupType.getValue()));
		return productGroupType;
	}

	@Override
	public boolean updateProductGroupType(ProductGroupType productGroupType) {
		return productGroupTypeDao.updateProductGroupType(productGroupType)>0;
	}

}