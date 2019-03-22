package com.yulan.controller;

import com.yulan.pojo.CtmOrderDetail;
import com.yulan.pojo.PackDetail;
import com.yulan.service.CtmOrderDetailService;
import com.yulan.service.PackDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller@RequestMapping("packDetail")
public class PackDetailController {
	@Autowired
	private PackDetailService packDetailService;
	@Autowired
	private CtmOrderDetailService ctmOrderDetailService;

	@ResponseBody@RequestMapping("getPackDetailAppoint")
	public Map<String,Object> getPackDetailAppoint(@RequestBody Map<String,String> parameters) {
		Map<String,Object> result = new HashMap<>();
		String itemNo = parameters.get("itemNo");
		String orderId = parameters.get("orderId");
		List<PackDetail> packDetails = packDetailService.getPackDetailAppoint(itemNo,orderId);
		CtmOrderDetail ctmOrderDetail = ctmOrderDetailService.getCtmOrderDetailAppoint(itemNo,orderId);
		ctmOrderDetail = ctmOrderDetail==null?new CtmOrderDetail():ctmOrderDetail;
		result.put("packDetails",packDetails);
		result.put("allCount",ctmOrderDetail.getQtyRequired());
		return result;
	}
}