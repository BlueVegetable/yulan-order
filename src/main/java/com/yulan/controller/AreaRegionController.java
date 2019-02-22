package com.yulan.controller;

import com.yulan.pojo.AreaRegion;
import com.yulan.service.AreaRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("areaRegion")
public class AreaRegionController {
    @Autowired
    private AreaRegionService areaRegionService;

    /**
     * 省区地址接口
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "getProvince")
    @ResponseBody
    public Map getProvince()throws IOException {
        Map<String,Object> map = new HashMap<>();
        List<AreaRegion> areaList =  areaRegionService.getProvince();
        map.put("province",areaList);
        return map;
    }

    /**
     * 市级地址
     * @param areaRegion
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "getCity")
    @ResponseBody
    public Map getCity(@RequestBody AreaRegion areaRegion)throws IOException{
        Map<String,Object> map = new HashMap<>();
        List<AreaRegion> areaList =  areaRegionService.getCity(areaRegion.getRegionId());
        map.put("city",areaList);
        return map;
    }

    /**
     * 县区地址
     * @param areaRegion
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "getCountry")
    @ResponseBody
    public Map getCountry(@RequestBody AreaRegion areaRegion)throws IOException{
        Map<String,Object> map = new HashMap<>();
        List<AreaRegion> areaList =  areaRegionService.getCountry(areaRegion.getRegionId());
        map.put("country",areaList);
        return map;
    }
}
