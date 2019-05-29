package com.yulan.controller;

import com.yulan.pojo.CustomerInfoCard;
import com.yulan.pojo.YLcontract_v2015_paa;
import com.yulan.service.CustomerInfoService;
import com.yulan.utils.FileUpload;
import com.yulan.utils.Response;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("customerInfo")
public class CustomerInfoController {
    @Autowired
    private CustomerInfoService customerInfoService;

    private Response response;

    private static final String CUSTOMER_DIRECTORY = "/customer-image";
    private static final String YLcontract_Directory = "/YLcontract-image";

    private CustomerInfoCard customerInfoCard;;

    /*
    *这里的CID应该是客户的loginName
     */
   /* @RequestMapping(value = "getCustomerInfo")
    @ResponseBody
    public CustomerInfoCard getCustomerInfo(@RequestParam("CID") String cID)throws Exception{
        System.out.println(customerInfoService.getCustomerInfo(cID));
        return customerInfoService.getCustomerInfo(cID);
    }*/

    /**
     * exchart显示资料卡执行汇总
     * @return
     */
    @RequestMapping("showStateEchart")
    @ResponseBody
    public Map<String, Object> showStateEchart(@RequestParam("year")String year){
        if (year.equals("")){
            year=null;
        }
        return customerInfoService.showStateEchart(year);
    }

    @RequestMapping("getAllSta")
    @ResponseBody
    public  Map<String,Object> getAllSta(@RequestParam(name = "limit", required = false) Integer limit,
                                         @RequestParam(name = "page", required = false) Integer page,
                                         @RequestParam("year")String year) throws UnsupportedEncodingException {
        if (year.equals("")){
            year=null;
        }
        if(limit==null||page==null) {
            page=null;
            limit=null;
        } else {
            page=(page-1)*limit+1;
        }
        int lastNum=page+limit-1;
        Map map = customerInfoService.getInfobyStateandmarketName(page,lastNum,year);
        map.put("code",0);
        map.put("msg","");
        return map;

    }

    @RequestMapping("getAllSates")
    @ResponseBody
    public List<Map<String,Object>> getAllStates(){
        return customerInfoService.getAllStates();
    }

    /**
     * 文件上传接口，有两个值一个是上传的文件，一个是文件类型
     * 文件类型有两种，一种是Customer , 一种是YLcontract
     * @param file
     * @param imgType
     * @return
     */
    @RequestMapping("upload")
    @ResponseBody
    public Map uploadImg(@RequestParam("file") MultipartFile file, @RequestParam("imgType") String imgType ,
                         @RequestParam("fileName")String fileName){
        int code = 0;
        String msg = null;
        Map<String,Object> data = new HashMap<>(2);

        if(imgType.equals("Customer")){
            Map<String,Object> value = FileUpload.copyCustomerImg(file,fileName);
            String name = (String) value.get("fileName");
            msg = value.get("code").equals("SUCCESS")?"":"上传失败";
            code = value.get("code").equals("SUCCESS")?0:1;
            data.put("path",CUSTOMER_DIRECTORY + "/" + name);
            data.put("type",value.get("fileType"));

        }else if(imgType.equals("YLcontract")){
            Map<String,Object> value = FileUpload.copyYLcontractImg(file, fileName);
            String name = (String) value.get("fileName");
            msg = value.get("code").equals("SUCCESS")?"":"上传失败";
            code = value.get("code").equals("SUCCESS")?0:1;
            data.put("path",YLcontract_Directory + "/" + name);
            data.put("type",value.get("fileType"));
        }

        Map<String,Object> result = new HashMap<>(3);
        result.put("code",code);
        result.put("msg",msg);
        result.put("data",data);
        return  result;
    }

    /**
     * 里的CID应该是客户的loginName
     * 读取客户资料卡
     * @param data
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getCustomerInfo")
    @ResponseBody
    public Map getCustomerInfo(@RequestBody Map<String,Object> data)throws Exception{
        String cID = (String)data.get("CID");
        if(customerInfoService.getCustomerInfo(cID) == null){
            return response.getResponseMap(1,"用户数据不存在" ,customerInfoService.getCustomerInfo(cID));
        }else{
            return response.getResponseMap(0,"SUCCESS" ,customerInfoService.getCustomerInfo(cID));
        }
    }

    /**
     * 更新客户资料卡
     * @param customerInfoCard
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updateCustomerInfo")
    @ResponseBody
    public Map updateCustomerInfo(@RequestBody CustomerInfoCard customerInfoCard)throws Exception{
            return customerInfoService.updateCustomerInfo(customerInfoCard);

    }

    /**
     * 得到委托授权书
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "getYLcontract")
    @ResponseBody
    public Map getYLcontract(@RequestBody Map<String,Object> data)throws IOException {
        String ccID = (String)data.get("ccid");
        Integer ccyear = (Integer)data.get("ccyear");
        if(customerInfoService.getYLcontract(ccID,ccyear) == null){
            return response.getResponseMap(1,"用户数据不存在" ,customerInfoService.getYLcontract(ccID,ccyear));
        }else{
            return response.getResponseMap(0,"SUCCESS" ,customerInfoService.getYLcontract(ccID,ccyear));
        }
    }

    /**
     * 创建委托授权书
     * @param yLcontract_v2015_paa
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "createYLcontract")
    @ResponseBody
    public Map createYLcontract(@RequestBody YLcontract_v2015_paa yLcontract_v2015_paa)throws IOException{
        if(customerInfoService.createYLcontract(yLcontract_v2015_paa)){
            return Response.getResponseMap(0,"创建成功",null);
        }else{
            return Response.getResponseMap(1,"创建失败",null);
        }
    }

    /**
     * 修改委托协议书
     * @param yLcontract_v2015_paa
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "updateYLcontract")
    @ResponseBody
    public Map updateYLcontract(@RequestBody YLcontract_v2015_paa yLcontract_v2015_paa)throws IOException{
        if(customerInfoService.updateYLcontract(yLcontract_v2015_paa)){
            return Response.getResponseMap(0,"SUCCESS",null);
        }else{
            return Response.getResponseMap(1,"更新失败",null);
        }
    }

    /**
     * 经销授权书接口
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "getAuthorization")
    @ResponseBody
    public Map getAuthorization(@RequestBody Map<String,Object> data)throws IOException {
        String cid = (String)data.get("cid");
        Map<String,Object> map = new HashMap<>();
        customerInfoCard = customerInfoService.getCustomerInfo(cid);
        map.put("districtText",customerInfoCard.getDistrictText());
        map.put("areaDistrict2Text",customerInfoCard.getAreaDistrict2Text());
        map.put("areaDistrict3Text",customerInfoCard.getAreaDistrict3Text());
        return  map;
    }

    @ResponseBody@RequestMapping("getAllStatisticsInfo")
    public Map getAllStatisticsInfo(@RequestParam(value = "userCID",required = false)String userCID,@RequestParam(value = "userCName",required = false)String userCName,
                                    @RequestParam(value = "managerCID") String managerCID) {
        return Response.getResponseMap(0,"",customerInfoService.getAllStatisticsInfo(userCID,userCName,managerCID));
    }

    /**
     * 获取所有资料卡的状态统计
     * @param data
     * @return
     */
    @RequestMapping(value = "getAllCustomerInfoCardState")
    @ResponseBody
    public List<Map<String,Object>> getAllCustomerInfoCardState(@RequestBody Map<String,Object> data){
        String year = (String)data.get("year");
        List<Map<String,Object>> list = customerInfoService.getAllCustomerInfoCardState(year);
        return list;
    }

    /**
     * 获取每个大区下的资料卡执行状态统计
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "getCustomerInfoCardStateByArea")
    @ResponseBody
    public List<Map<String,Object>> getCustomerInfoCardStateByArea(@RequestBody Map<String,Object> data)throws IOException{
        String year = (String)data.get("year");
        List<Map<String,Object>> list = customerInfoService.getCustomerInfoCardStateByArea(year);
        return  list;
    }

    /**
     *获取登录用户所管地区
     */
    @RequestMapping("getAreas")
    @ResponseBody
    public List<Map<String,Object>> getAreas(@RequestBody Map<String,Object> data) throws UnsupportedEncodingException {
        String cid=data.get("cid").toString();
        String position=data.get("position").toString();
        return customerInfoService.getUserArea(cid,position);
    }

    /**
     * 增加了协议书修改筛选的资料卡列表
     * @param m
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("getNcustomerinfo")
    @ResponseBody
    public Map getUserCustomerinfo(@RequestBody Map<String,Object> m) throws UnsupportedEncodingException {
        Integer limit=Integer.parseInt(m.get("limit").toString());
        Integer page=Integer.parseInt(m.get("page").toString());
        String position=m.get("position").toString();
        String ylcstate=m.get("ylcstate").toString();
        String state=m.get("state").toString();
        Integer year=null;
        Integer legalchecked=null;
        if (m.get("legalchecked")!=null&&!m.get("legalchecked").equals("")){
            legalchecked=Integer.parseInt(m.get("legalchecked").toString());
        }
        if (m.get("year")!=null&&!m.get("year").equals("")){
            year=Integer.parseInt(m.get("year").toString());
        }
        String cid=m.get("cid").toString();
        String area_1=m.get("area_1").toString();
        String area_2=m.get("area_2").toString();
        String find= StringUtil.setUtf8(m.get("find").toString());
        if(state.equals("")){
            state=null;
        }
        if(ylcstate.equals("")){
            ylcstate=null;
        }
        if(area_1.equals("")){
            area_1=null;
        }else{
            area_1=StringUtil.setUtf8(area_1);
        }
        if(area_2.equals("")){
            area_2=null;
        }else{
            area_2=StringUtil.setUtf8(area_2);
        }
        Integer lastNum=null;
        if(limit==null||page==null) {
            page=null;
            limit=null;
        } else {
            page=(page-1)*limit+1;
            lastNum=page+limit-1;
        }
        Map<String,Object> map=customerInfoService.getUserCustomerinfo(page,lastNum,year,cid,area_1,area_2,find,state,position,ylcstate,legalchecked);
        map.put("msg","");
        map.put("code",0);

        return map;
    }


}
