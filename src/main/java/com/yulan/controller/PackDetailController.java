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

import java.math.BigDecimal;
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
		String lineNo = parameters.get("lineNo");
		String orderId = parameters.get("orderId");
		String itemNo = parameters.get("itemNo");
		List<PackDetail> packDetails = packDetailService.getPackDetailAppoint(lineNo,orderId);
		List<CtmOrderDetail> ctmOrderDetails = ctmOrderDetailService.getCtmOrderDetailAppoint(itemNo,orderId);
		BigDecimal number = new BigDecimal("0");
		if(ctmOrderDetails.size() != 0) {
			for (CtmOrderDetail ctmOrderDetail:ctmOrderDetails) {
				number = number.add(ctmOrderDetail.getQtyRequired());
			}
		}
		result.put("packDetails",packDetails);
		result.put("allCount",number);
		return result;
	}
}