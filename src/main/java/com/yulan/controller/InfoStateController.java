package com.yulan.controller;

import com.yulan.pojo.CustomerInfoCard;
import com.yulan.pojo.YLcontractentry;
import com.yulan.service.CustomerInfoService;
import com.yulan.service.InfoStateService;
import com.yulan.service.YLcontractentryService;
import com.yulan.utils.Response;
import com.yulan.utils.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("infoState")
public class InfoStateController {

    private YLcontractentry yLcontractentry;
    @Autowired
    private InfoStateService infoStateService;
    @Autowired
    private YLcontractentryService yLcontractentryService;
    @Autowired
    private CustomerInfoService customerInfoService;

    private CustomerInfoCard customerInfoCard;

    private Response response;

    private StringUtil stringUtil;

    /**
     * 获取公告信息
     * 1.资料卡是否退回
     * 2.协议书是否发下
     *这个方法写的不大好，代码重复，但是因为赶进度就先写成这样了
     * @param data
     * @return
     */
    @RequestMapping(value = "getNoticeInfo")
    @ResponseBody
    public Map getNoticeInfo(@RequestBody Map<String, Object> data) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String cid = (String) data.get("cid");

        String customerInfo = null;
        customerInfoCard = customerInfoService.getCustomerInfo(cid);
        String customerInfoCardState = customerInfoCard.getState();
        String customerMemo = customerInfoCard.getMemo();
        List<String> memoReplaced = new ArrayList<>();
        //将原先数据库中的html解析
        Document doc = Jsoup.parse(customerMemo);
        for (String retval: doc.text().split(";")){
            memoReplaced.add(retval);
        }
        ;

        yLcontractentry = yLcontractentryService.getYLcontractentry(cid);
        String yLcontractentryState = yLcontractentry.getState();
        String yLcontractInfo = null;
        String yLcontractMemo = yLcontractentry.getWfmemo();
        List<String> wfmemoReplaced = new ArrayList<>();
        //将原先数据库中的html解析
        Document yldoc = Jsoup.parse(yLcontractMemo);
        for (String retval : yldoc.text().split(";")) {
            wfmemoReplaced.add(retval);
        }
        //转换原先审核记录中的特殊字段，可能还有其他的，以后再加
        Map<String, Object> state = new HashMap();
        state.put("销售副总", "#CSA_CHECK#");
        state.put("市场部", "#DEP_MARKET_CHECK#");
        state.put("销售中心经理", "#ASM_CHECKING#");
        for (Map.Entry<String, Object> entry : state.entrySet()) {
            wfmemoReplaced = stringUtil.replaceState(wfmemoReplaced,
                    (String) entry.getValue(), entry.getKey());
        }


        if (customerInfoCardState.equals("CUSTOMERPORCESSING2")) {
            customerInfo = "资料卡被退回请重新填写";

        } else {
            customerInfo = "暂无最新消息";
        }
        if (yLcontractentryState.equals("CUSTOMERAFFIRM")) {
            yLcontractInfo = "协议书已发下，请尽快确认";
        } else {
            yLcontractInfo = "暂无最新消息";
        }
        map.put("customerInfoCardState", customerInfo);
        map.put("customerMemo", memoReplaced);
        map.put("yLcontractState", yLcontractInfo);
        map.put("yLcontractMemo", wfmemoReplaced);

        return map;
    }

    /**
     * 客户资料卡评审记录
     *
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "getCustomerInfoCardState")
    @ResponseBody
    public Map getCustomerInfoCardState(@RequestBody Map<String, Object> data) throws IOException {
        String cid = (String) data.get("cid");
        Integer year = (Integer)data.get("year");
        Map<String, Object> map = new HashMap<>();
        map = infoStateService.getCustomerInfoCardState(cid,year);
        return map;
    }

    /**
     * 协议书评审记录
     *
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "getYLcontractentryState")
    @ResponseBody
    public Map getYLcontractentryState(@RequestBody Map<String, Object> data) throws IOException {
        String cid = (String) data.get("cid");
        Integer cyear = (Integer) data.get("cyear");
        Map<String, Object> map = new HashMap<>();
        map = infoStateService.getYLcontractState(cid,cyear);
        return map;
    }

    /**
     * 业务员/订单部审核资料卡
     *
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "bussinessCheckCustomerInfoCard")
    @ResponseBody
    public Map businessCheckCustomerInfoCard(@RequestBody Map<String, Object> data) throws IOException {
        String cid = (String) data.get("cid");
        String state = (String) data.get("state");
        String memo = (String) data.get("memo");
        if (infoStateService.businessCheckCustomerInfoCard(cid, state, memo)) {
            return response.getResponseMap(0, "SUCCESS", null);
        } else {
            return response.getResponseMap(1, "更新失败", null);
        }
    }

    /**
     * 协议书审查接口
     *
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "checkYLcontractentryState")
    @ResponseBody
    public Map checkYLcontractentryState(@RequestBody Map<String, Object> data) throws IOException {
        String cid = (String) data.get("cid");
        String state = (String) data.get("state");
        String wfmemo = (String) data.get("wfmemo");
        Integer signed = (Integer)data.get("signed");
        String market = (String)data.get("market");
        String csa = (String)data.get("csa");
        if (infoStateService.checkYLcontractentryState(cid, state, wfmemo,signed,market,csa)) {
            return response.getResponseMap(0, "SUCCESS", null);
        } else {
            return response.getResponseMap(1, "更新失败", null);
        }
    }

    /**
     * 法务员抽查接口
     *
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "lawCheckState")
    @ResponseBody
    public Map lawCheckState(@RequestBody Map<String, Object> data) throws IOException {
        String cid = (String) data.get("cid");
        String yLcontractentryState = (String) data.get("yLcontractentryState");
        Integer legalcheckedYLcontractentry = (Integer) data.get("legalcheckedYLcontractentry");
        String wfmemo = (String) data.get("wfmemo");
        Integer signed = (Integer) data.get("signed");
        String customerState = (String) data.get("customerState");
        String customerMemo = (String) data.get("customerMemo");
        Integer legalcheckedCustomerInfoCard = (Integer) data.get("legalcheckedCustomerInfoCard");
        if (infoStateService.lawCheckCustomerInfoCardState(cid, customerState
                , customerMemo,legalcheckedCustomerInfoCard)
                && infoStateService.lawCheckYLcontractentryState(cid,
                yLcontractentryState, wfmemo,signed,legalcheckedYLcontractentry)) {
            return response.getResponseMap(0, "SUCCESS", null);
        } else {
            return response.getResponseMap(1, "更新失败", null);
        }

    }

    /**
     * 获取法务员抽查资料卡接口
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping("getCustomerInfoCardLeagalChecked")
    @ResponseBody
    public Map getCustomerInfoCardLeagalChecked(@RequestBody Map<String, Object> data)throws IOException{
        Integer legalchecked = (Integer) data.get("legalchecked");
        Integer limit = (Integer)data.get("limit");
        Integer page = (Integer)data.get("page");
        String market = (String) data.get("market");
        String submarket = (String)data.get("submarket");
        String state = (String)data.get("state");
        if(limit==null||page==null) {
            page=null;
            limit=null;
        } else {
            page=(page-1)*limit+1;
        }
        int lastNum=page+limit-1;
        Map<String, Object> map = new HashMap<>();
        map.put("data",infoStateService.getCustomerInfoCardLeagalChecked(page,lastNum,legalchecked));
        return map;
    }

    /**
     * 获取法务员抽查协议书接口
     * 可以按大区和片区查找
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping("getYLcontractentryLegalChecked")
    @ResponseBody
    public Map getYLcontractentryLegalChecked(@RequestBody Map<String, Object> data)throws IOException{
        Integer legalchecked = (Integer) data.get("legalchecked");
        Integer limit = (Integer)data.get("limit");
        Integer page = (Integer)data.get("page");
        String market = (String) data.get("market");
        String submarket = (String)data.get("submarket");
        String state = (String)data.get("state");
        Integer year = (Integer)data.get("year");
        if(limit==null||page==null) {
            page=null;
            limit=null;
        } else {
            page=(page-1)*limit+1;
        }
        int lastNum=page+limit-1;
        Map<String, Object> map = new HashMap<>();
        map.put("data",infoStateService.getYLcontractentryLeagalChecked(page,lastNum,legalchecked,market,submarket,state,year));
        return map;
    }
}
