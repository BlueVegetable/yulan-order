package com.yulan.service.impl;

import com.yulan.dao.CustomerInfoDao;
import com.yulan.dao.YLcontractentryDao;
import com.yulan.pojo.CustomerInfoCard;
import com.yulan.pojo.YLcontractentry;
import com.yulan.service.CustomerInfoService;
import com.yulan.service.InfoStateService;
import com.yulan.service.YLcontractentryService;
import com.yulan.utils.MapUtils;
import com.yulan.utils.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InfoStateServiceImpl implements InfoStateService {
    @Autowired
    private CustomerInfoService customerInfoService;
    @Autowired
    private YLcontractentryService yLcontractentryService;
    @Autowired
    private CustomerInfoDao customerInfoDao;
    @Autowired
    private YLcontractentryDao yLcontractentryDao;

    private YLcontractentry yLcontractentry;

    private CustomerInfoCard customerInfoCard;

    private StringUtil stringUtil;

    private MapUtils mapUtils;

    @Override
    public Map getCustomerInfoCardState(String cid,Integer year) throws IOException {
        Map<String,Object> map = new HashMap<>();
        String customerInfo = null;
        customerInfoCard = customerInfoService.getCustomerInfoByYear(cid,year);
        String customerInfoCardState = customerInfoCard.getState();
        String memo = customerInfoCard.getMemo();

        List<String> memoReplaced = new ArrayList<>();
        //将原先数据库中的html解析
        Document doc = Jsoup.parse(memo);
        for (String retval: doc.text().split(";")){
            memoReplaced.add(retval);
        }
        //转换原先审核记录中的特殊字段，可能还有其他的，以后再加
       /* Map<String,Object> state = new HashMap();
        state.put("销售副总","#CSA_CHECK#");
        state.put("市场部","#DEP_MARKET_CHECK#");
        state.put("销售中心经理","#ASM_CHECKING#");

        for (Map.Entry<String,Object> entry : state.entrySet()) {
            memoReplaced = stringUtil.replaceState(memoReplaced,(String)entry.getValue(),entry.getKey());
        }*/


        if(customerInfoCardState.equals("CUSTOMERPORCESSING2")){
            customerInfo = "资料卡被退回请重新填写";
        }else if(customerInfoCardState.equals("BUSINESSCHECKING")){
            customerInfo = "业务员审核中";
        }else if(customerInfoCardState.equals("APPROVED")){
            customerInfo = "资料卡通过";
        }else if(customerInfoCardState.equals("BIILDEPCHECKING")){
            customerInfo = "订单部审核中";
        }else{
            customerInfo = "暂无最新消息";
        }
        map.put("customerInfo",customerInfo);
        map.put("memo",memoReplaced);
        return map;
    }

    @Override
    public Map getYLcontractState(String cid,Integer cyear) throws IOException{
        Map<String,Object> map = new HashMap<>();
        String yLcontractInfo = null;


       yLcontractentry = yLcontractentryService.getYLcontractentryByYear(cid,cyear);
       String yLcontractentryState = yLcontractentry.getState();
       String yLcontractentryMemo = yLcontractentry.getWfmemo();

       if(yLcontractentryMemo !=null){
           List<String> memoReplaced = new ArrayList<>();
           //将原先数据库中的html解析
           Document doc = Jsoup.parse(yLcontractentryMemo);
           for (String retval: doc.text().split(";")){
               memoReplaced.add(retval);
           }
           //转换原先审核记录中的特殊字段，可能还有其他的，以后再加
           Map<String,Object> state = new HashMap();
           state.put("销售副总","#CSA_CHECK#");
           state.put("市场部","#DEP_MARKET_CHECK#");
           state.put("销售中心经理","#ASM_CHECKING#");

           for (Map.Entry<String,Object> entry : state.entrySet()) {
               memoReplaced = stringUtil.replaceState(memoReplaced,(String)entry.getValue(),entry.getKey());
           }

           if( yLcontractentryState.equals("CUSTOMERAFFIRM") ){
               yLcontractInfo ="客户查看确认协议数据中";
           }else if(yLcontractentryState.equals("SALEMANFILLING")){
               yLcontractInfo = "业务员填写中";
           }else if(yLcontractentryState.equals("SALEMANMODIFYING")){
               yLcontractInfo = "业务员修改中";
           }else if(yLcontractentryState.equals("DEP_MARKET_CHECK")){
               yLcontractInfo = "市场部审核中";
           }else if(yLcontractentryState.equals("CSA_CHECK")){
               yLcontractInfo = "销售总监审核中";
           }else if(yLcontractentryState.equals("APPROVED")){
               yLcontractInfo = "协议书通过";
           }else if(yLcontractentryState.equals("ASM_CHECKING")){
               yLcontractInfo = "销售中心经理审核中";
           }
           else{
               yLcontractInfo = "暂无最新消息";
           }
           map.put("yLcontractInfo",yLcontractInfo);
           map.put("yLcontractentryMemo",memoReplaced);
       }else{
           map.put("yLcontractInfo",yLcontractInfo);
           map.put("yLcontractentryMemo","data does not exist");
       }

        return map;
    }

    @Override
    public boolean businessCheckCustomerInfoCard(String cid, String state,
                                                  String memo) throws IOException {
         state = stringUtil.setUtf8(state);
         memo = stringUtil.setUtf8(memo);
        return customerInfoDao.businessCheckCustomerInfoCard(cid,state,memo);
    }

    @Override
    public boolean lawCheckCustomerInfoCardState(String cid, String state,
                                                 String memo,
                                                 Integer legalchecked) throws IOException {
        return customerInfoDao.lawCheckCustomerInfoCardState(cid,state,memo,legalchecked);
    }

    @Override
    public boolean lawCheckYLcontractentryState(String cid, String state, String wfmemo, Integer signed, Integer legalchecked) throws IOException {
        return yLcontractentryDao.lawCheckYLcontractentryState(cid,state,wfmemo,signed,legalchecked);
    }

    @Override
    public boolean checkYLcontractentryState(String cid, String state,
                                             String wfmemo,Integer signed,String market, String csa) throws IOException {
        state = stringUtil.setUtf8(state);
        wfmemo = stringUtil.setUtf8(wfmemo);
        market = stringUtil.setUtf8(market);
        csa = stringUtil.setUtf8(csa);
        return yLcontractentryDao.checkYLcontractentry(cid,state,wfmemo,signed,market,csa);
    }

    @Override
    public List<CustomerInfoCard> getCustomerInfoCardLeagalChecked(Integer start, Integer number, Integer legalChecked) throws IOException {
        List<CustomerInfoCard> list = customerInfoDao.getCustomerInfoCardLeagalChecked(start,number,legalChecked);
        for(int i=0; i<list.size(); i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map = mapUtils.beanToMap(list.get(i));
            for (Map.Entry<String,Object> entry : map.entrySet()) {
                if(entry.getValue() instanceof String){
                    String origin = stringUtil.getUtf8(String.valueOf(entry.getValue()));
                    entry.setValue(origin);
                }
            }
            list.set(i,mapUtils.mapToBean(map,CustomerInfoCard.class)) ;
       }
        return list;
    }

    @Override
    public List<YLcontractentry> getYLcontractentryLeagalChecked(Integer start, Integer number, Integer legalChecked,String market, String submarket, String state,Integer year) throws IOException {
        List<YLcontractentry> list = new ArrayList<YLcontractentry>();
        if(market == null || market.equals("")){
             list = yLcontractentryDao.getYLcontractentryLegalChecked(start,number,legalChecked,year,state);

        }else{
            List<CustomerInfoCard> customerInfoList = customerInfoDao.getCustomerInfoByMarketName(year,stringUtil.setUtf8(market),stringUtil.setUtf8(submarket));
            for(CustomerInfoCard customerInfoCard : customerInfoList){
                   YLcontractentry yLcontractentry = yLcontractentryDao.getYLcontractentryLegalCheckedSingle(start,number,legalChecked,year,customerInfoCard.getCid(),state);
                   if(yLcontractentryDao.getYLcontractentryLegalCheckedSingle(start,number,legalChecked,year,customerInfoCard.getCid(),state) == null){
                       continue;
                   }
                       yLcontractentry.setMarketname(stringUtil.getUtf8(customerInfoCard.getMarketname()));
                       yLcontractentry.setSubmarketname(stringUtil.getUtf8(customerInfoCard.getSubmarketname()));
                       list.add(yLcontractentry);

            }
        }
        for(int i=0; i<list.size(); i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map = mapUtils.beanToMap(list.get(i));
            for (Map.Entry<String,Object> entry : map.entrySet()) {
                if(entry.getValue() instanceof String){
                    String origin = stringUtil.getUtf8(String.valueOf(entry.getValue()));
                    entry.setValue(origin);
                }
            }
            list.set(i,mapUtils.mapToBean(map,YLcontractentry.class)) ;
        }
        return list;


    }
}
