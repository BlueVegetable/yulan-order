package com.yulan.controller;

import com.yulan.dao.YLcontractentryDao;
import com.yulan.pojo.YLcontract_v2015;
import com.yulan.service.YLcontractentryService;
import com.yulan.utils.Response;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("YLcontractentry")
public class YLcontractentryController {
    @Autowired
    private YLcontractentryService yLcontractentryService;
    @Autowired
    private YLcontractentryDao yLcontractentryDao;

    private YLcontract_v2015 yLcontract_v2015;

    private Response response;

    /**
     * exchart显示资料卡执行汇总
     * @return
     */
    @RequestMapping("showEchart")
    @ResponseBody
    public Map<String, Object> showEchart(@RequestParam("year")String year){
        if (year.equals("")){
            year=null;
        }
        return yLcontractentryService.showStateEchartYCl(year);
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
        Map map = yLcontractentryService.getYClbyStateandCID(page,lastNum,year);
        map.put("code",0);
        map.put("msg","");
        return map;

    }

    @RequestMapping("getAllSates")
    @ResponseBody
    public List<Map<String,Object>> getAllStates(){
        return yLcontractentryService.getAllStates();
    }

    /**
     * 得到经销协议内容
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "getYLcontract")
    @ResponseBody
    public Map getYLcontract(@RequestBody Map<String,Object> data)throws IOException {
        String ccid = (String)data.get("ccid");
        yLcontract_v2015 = yLcontractentryService.getYLcontract_v2015(ccid);
        if(yLcontract_v2015 == null){
            return response.getResponseMap(1,"用户数据不存在" ,yLcontract_v2015);
        }else{
            return response.getResponseMap(0,"SUCCESS",yLcontract_v2015);
        }
    }

    /**
     * 创建协议书内容
     * @param yLcontract_v2015
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "createYLcontract")
    @ResponseBody
    public Map createYLcontract(@RequestBody YLcontract_v2015 yLcontract_v2015)throws IOException {
        if(yLcontractentryService.createYLcontract_v2015(yLcontract_v2015)){
            return response.getResponseMap(0,"SUCCESS",null);
        }else{
            return response.getResponseMap(1,"插入失败" ,null);
        }
    }

    /**
     * 修改协议书
     * @param yLcontract_v2015
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "updateYLcontract")
    @ResponseBody
    public Map updateYLcontract(@RequestBody YLcontract_v2015 yLcontract_v2015)throws IOException{
        if(yLcontractentryService.updateYLcontract_v2015(yLcontract_v2015)){
            return response.getResponseMap(0,"SUCCESS",null);
        }else{
            return response.getResponseMap(1,"更新失败" ,null);
        }
    }

    /**
     * 获得协议书的HTML(PC)
     * @param data
     * @return
     */
    @RequestMapping(value = "getYLcontractHTML")
    @ResponseBody
    public Map getYLcontractHTML(@RequestBody Map<String,Object> data)throws IOException{
        String cid = (String)data.get("cid");
        String yLcontractHTML = yLcontractentryService.getYLcontractHTML(cid);
        if(yLcontractHTML.equals("") || yLcontractHTML == null){
            return response.getResponseMap(1,"没有相关协议书" ,null);
        }else{
            return response.getResponseMap(0,"SUCCESS" ,yLcontractHTML);
        }

    }

    /**
     * 获得协议书APP端的数据
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "getYLcontractAPP")
    @ResponseBody
    public Map<String,Object> getYLcontractAPP(@RequestBody Map<String,Object> data)throws IOException{
        String cid = (String)data.get("cid");
        return yLcontractentryService.getYLcontractAPP(cid);
    }

    /**
     * 协议列表
     * @param m
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "getAllYlcs")
    @ResponseBody
    public Map getAllYlcs(@RequestBody Map<String,Object> m) throws UnsupportedEncodingException {
        Integer limit=Integer.parseInt(m.get("limit").toString());
        Integer page=Integer.parseInt(m.get("page").toString());
        String signed=m.get("signed").toString();//协议书通过标志
        Integer year=Integer.parseInt(m.get("year").toString());
        String cid=m.get("cid").toString();

        if(year.equals("")){
            year=null;
        }
        Integer lastNum=null;
        if(limit==null||page==null) {
            page=null;
            limit=null;
        } else {
            page=(page-1)*limit+1;
            lastNum=page+limit-1;
        }
        if(signed.equals("")){
            signed=null;
        }
        Map map=yLcontractentryService.getAllYlcs(page,lastNum,signed,year,cid);
        map.put("code",0);
        map.put("msg","");


        return map;
    }

    /**
     * 协议书审核列表获取
     * @param m
     * @return
     * @throws UnsupportedEncodingException
     */
        @RequestMapping(value = "getYlcsbysigned")
    @ResponseBody
    public Map getYlcsbysigned(@RequestBody Map<String,Object> m) throws UnsupportedEncodingException {
        Integer limit=Integer.parseInt(m.get("limit").toString());
        Integer page=Integer.parseInt(m.get("page").toString());
        Integer signed=null;
//        if(m.get("signed")!=null&&!m.get("signed").equals("")){
//           signed=Integer.parseInt(m.get("signed").toString());//协议书通过标志
//        }
        String need=m.get("need").toString();
        String position=m.get("position").toString();
            Integer legalchecked=null;
            if (m.get("legalchecked")!=null&&!m.get("legalchecked").equals("")){
                legalchecked=Integer.parseInt(m.get("legalchecked").toString());
            }
        Integer year=null;
        if (m.get("year")!=null&&!m.get("year").equals("")){
            year=Integer.parseInt(m.get("year").toString());
        }
        String cid=m.get("cid").toString();
        String area_1=m.get("area_1").toString();
        String area_2=m.get("area_2").toString();
        String find=StringUtil.setUtf8(m.get("find").toString());



        if(area_1.equals("")){
            area_1=null;
        }else{
            area_1= StringUtil.setUtf8(area_1);
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

        Map map=yLcontractentryService.getYlcsbySigned(page,lastNum,year,cid,area_1,area_2,find,need,position,legalchecked);
        map.put("code",0);
        map.put("msg","");

        return map;
    }

    /**
     * 协议网签执行汇总
     * @param data
     * @return
     */
    @RequestMapping(value = "getAllYLcontractentryState")
    @ResponseBody
    public List<Map<String,Object>> getAllYLcontractentryState(@RequestBody Map<String,Object> data){
        String year = (String)data.get("year");
        List<Map<String,Object>> list = yLcontractentryService.getAllYLcontractentryState(year);
        return list;
    }

    /**
     * 协议网签执行汇总（按大区）
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "getYLcontractentryStateByArea")
    @ResponseBody
    public List<Map<String,Object>> getYLcontractentryStateByArea(@RequestBody Map<String,Object> data)throws IOException{
        String year = (String)data.get("year");
        List<Map<String,Object>> list = yLcontractentryService.getYLcontractentryStateByArea(year);
        return list;
    }

    /**
     * APP版本号更新接口
     * @return
     */
    @RequestMapping(value = "updateAPPVersion")
    @ResponseBody
    public Map getAPPVersion(@RequestBody Map<String,Object> data){
        String version = (String)data.get("version");
        if(yLcontractentryDao.getAPPVersion(version)){
            return response.getResponseMap(0,"SUCCESS",null);
        }else{
            return response.getResponseMap(1,"更新失败" ,null);
        }
    }

    /**
     * 获取APP版本号
     * @return
     */
    @RequestMapping(value = "getAPPVersionPresent")
    @ResponseBody
    public Map getAPPVersionPresent(){
       Map map = new HashMap();
       map.put("data",yLcontractentryDao.getAPPVersionPresent());
       return map;
    }

}
