package com.yulan.service;

import com.yulan.pojo.ProductGroupType;

import java.io.UnsupportedEncodingException;

public interface ProductGroupTypeService {

	boolean addProductGroupType(ProductGroupType productGroupType);

	boolean deleteProductGroupTypeByID(String productGroupTypeID);

	ProductGroupType getProductGroupTypeByID(String productGroupTypeID);

	ProductGroupType getProductGroupTypeByName(String name) throws UnsupportedEncodingException;

	boolean updateProductGroupType(ProductGroupType productGroupType);

}