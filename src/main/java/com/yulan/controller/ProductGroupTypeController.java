package com.yulan.controller;

import com.yulan.pojo.ProductGroupType;
import com.yulan.service.ProductGroupTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("productGroupType")
public class ProductGroupTypeController {

	@Autowired
	private ProductGroupTypeService productGroupTypeService;

	@ResponseBody@RequestMapping("getProductGroupTypeByName")
	public ProductGroupType getProductGroupTypeByName(String name) throws UnsupportedEncodingException {
		return productGroupTypeService.getProductGroupTypeByName(name);
	}

}