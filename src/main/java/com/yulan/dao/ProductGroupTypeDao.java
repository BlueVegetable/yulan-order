package com.yulan.dao;

import com.yulan.pojo.ProductGroupType;

public interface ProductGroupTypeDao {

	int addProductGroupType(ProductGroupType productGroupType);

	int deleteProductGroupTypeByID(String productGroupTypeID);

	ProductGroupType getProductGroupTypeByID(String productGroupTypeID);

	ProductGroupType getProductGroupTypeByName(String name);

	int updateProductGroupType(ProductGroupType productGroupType);

}