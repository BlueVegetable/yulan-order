package com.yulan.controller;

import com.yulan.pojo.PackDetail;
import com.yulan.service.PackDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller@RequestMapping("packDetail")
public class PackDetailController {
	@Autowired
	private PackDetailService packDetailService;

	@ResponseBody@RequestMapping("getPackDetailAppoint")
	public List<PackDetail> getPackDetailAppoint(@RequestBody Map<String,String> parameters) {
		String itemNo = parameters.get("itemNo");
		String orderId = parameters.get("orderId");
		return packDetailService.getPackDetailAppoint(itemNo,orderId);
	}
}