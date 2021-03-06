package com.yulan.controller;

import com.yulan.pojo.Commodity;
import com.yulan.service.CommodityService;
import com.yulan.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("commodity")
public class CommodityController{

	@Autowired@Qualifier("commodityService")
	private CommodityService commodityService;

	@ResponseBody
	@RequestMapping("addCommodity")
	public Map<String,Object> addCommodity(Commodity commodity) throws UnsupportedEncodingException {
		if(commodityService.addCommodity(commodity))
			return Response.getResponseMap(0,"添加成功",null);
		else
			return Response.getResponseMap(1,"添加失败",null);
	}

	@ResponseBody@RequestMapping("deletecommodity")
	public Map<String,Object> deleteCommodity(@RequestParam("commodityID")String commodityID) {
		if(commodityService.deleteCommodityByID(commodityID)) {
			return Response.getResponseMap(0,"修改成功",null);
		}
		else {
			return Response.getResponseMap(1,"修改失败",null);
		}
	}

	@ResponseBody@RequestMapping("getCommodityByID")
	public Commodity getCommodityByID(String commodityID) {
		return commodityService.getCommodityByID(commodityID);
	}

	@ResponseBody@RequestMapping("updateCommodity")
	public Map<String,Object> updateCommodity(Commodity commodity) throws UnsupportedEncodingException {
		if(commodityService.updateCommodity(commodity)) {
			return Response.getResponseMap(0,"",null);
		} else {
			return Response.getResponseMap(1,"",null);
		}
	}

	@ResponseBody@RequestMapping("alterCommodityStatus")
	public Map<String,Object> alterCommodityStatus(@RequestBody List<String> commodityIDs) {
		commodityService.alterCommoditiesStatus(commodityIDs,0);
		return Response.getResponseMap(0,"",null);
	}

	@ResponseBody@RequestMapping("alterCommoditiesStatusByCartItemID")
	public Map<String,Object> alterCommoditiesStatusByCartItemID(@RequestBody List<String> cartItemIDs) {
		commodityService.alterCommoditiesStatusByCartItemId(cartItemIDs,0);
		return Response.getResponseMap(0,"",null);
	}

}