package com.yulan.service.impl;

import com.yulan.dao.CustomerInfoDao;
import com.yulan.dao.YLcontractentryDao;
import com.yulan.pojo.CustomerInfoCard;
import com.yulan.pojo.YLcontract_v2015;
import com.yulan.pojo.YLcontract_v2015_paa;
import com.yulan.service.CustomerInfoService;
import com.yulan.service.YLcontractentryService;
import com.yulan.utils.MapUtils;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {
    @Autowired
    private CustomerInfoDao customerInfoDao;

    private CustomerInfoCard customerInfoCard;

    private YLcontract_v2015_paa yLcontract_v2015_paa;
    @Autowired
    private YLcontractentryService yLcontractentryService;
    @Autowired
    private YLcontractentryDao yLcontractentryDao;

    private StringUtil stringUtil;

    private static MapUtils mapUtils;



    @Override
    public List<Map<String,Object>> getAllStates() {
        List<Map<String,Object>> list=customerInfoDao.getAllStates();
        List<Map<String,Object>> states=new ArrayList<>();
        for (Map m:list){
            if(m==null||m.get("STATE").equals("1234")){
                continue;
            }
            Map<String,Object> map=new HashMap<>();
            map.put("id",m.get("STATE"));

            switch (m.get("STATE").toString()){
                case "ONCREATE":
                    map.put("name","初始状态");
                    break;
                case "CUSTOMERPORCESSING":

                    map.put("name","客户填写中");
                    break;
                case "CUSTOMERPORCESSING2":

                    map.put("name","客户修改中");
                    break;
                case "BUSINESSCHECKING":

                    map.put("name","业务员审核中");
                    break;
                case "APPROVED":

                    map.put("name","已通过");
                    break;
                case "BIILDEPCHECKING":

                    map.put("name","订单部审核");
                    break;
                default:break;
            }
            states.add(map);
        }
        return states;
    }
    @Override
    public CustomerInfoCard getCustomerInfo(String cID) throws IOException {
        if(customerInfoDao.getCustomerInfo(cID) == null){
            return null;
        }else{
            customerInfoCard = customerInfoDao.getCustomerInfo(cID);
            //判断数据库中数据是否存在
            if(yLcontractentryDao.getYLcontract_v2015ByYear(customerInfoCard.getCid(),customerInfoCard.getContractyear()) != null) {
                customerInfoCard.setPrivateAccountAuthed(yLcontractentryDao.getYLcontract_v2015ByYear(customerInfoCard.getCid(),customerInfoCard.getContractyear()).getPrivateAccountAuthed());
            }
            Map<String, Object> map ;
            map = mapUtils.beanToMap(customerInfoCard);

            for (Map.Entry<String,Object> entry : map.entrySet()) {
                if(entry.getValue() instanceof String){
                    String origin = stringUtil.getUtf8(String.valueOf(entry.getValue()));
                    entry.setValue(origin);
                }
      //          System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }
            return mapUtils.mapToBean(map,CustomerInfoCard.class);
        }

    }

    public CustomerInfoCard getCustomerInfoByYear(String cID,Integer year) throws IOException {
        if(customerInfoDao.getCustomerInfoByYear(cID,year) == null){
            return null;
        }else{
            customerInfoCard = customerInfoDao.getCustomerInfoByYear(cID,year);
            if(yLcontractentryDao.getYLcontract_v2015ByYear(customerInfoCard.getCid(),customerInfoCard.getContractyear()) != null) {
                customerInfoCard.setPrivateAccountAuthed(yLcontractentryDao.getYLcontract_v2015ByYear(customerInfoCard.getCid(),customerInfoCard.getContractyear()).getPrivateAccountAuthed());
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map = mapUtils.beanToMap(customerInfoCard);

            for (Map.Entry<String,Object> entry : map.entrySet()) {
                if(entry.getValue() instanceof String){
                    String origin = stringUtil.getUtf8(String.valueOf(entry.getValue()));
                    entry.setValue(origin);
                }
                //          System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }
            return mapUtils.mapToBean(map,CustomerInfoCard.class);
        }

    }

    @Override
    public YLcontract_v2015_paa getYLcontract(String cCID,Integer ccyear) throws  IOException{
        if(customerInfoDao.getYLcontractByYear(cCID,ccyear)== null){
            return null;
        }else{
            yLcontract_v2015_paa = customerInfoDao.getYLcontractByYear(cCID,ccyear);
            Map<String, Object> map = new HashMap<String, Object>();
            map = mapUtils.beanToMap(yLcontract_v2015_paa);

            for (Map.Entry<String,Object> entry : map.entrySet()) {
                if(entry.getValue() instanceof String){
                    String origin = stringUtil.getUtf8(String.valueOf(entry.getValue()));
                    entry.setValue(origin);
                }
      //          System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }
            return mapUtils.mapToBean(map,YLcontract_v2015_paa.class);
        }
    }

    @Override
    public Map updateCustomerInfo(CustomerInfoCard customerInfoCard) throws IOException{
        Map mapdata = new HashMap();
        if(customerInfoCard.getPrivateAccountAuthed() != null ){
            if(yLcontractentryDao.getYLcontract_v2015ByYear(customerInfoCard.getCid(),customerInfoCard.getContractyear()) != null){
                YLcontract_v2015 yLcontract_v2015 = new YLcontract_v2015();
                yLcontract_v2015.setPrivateAccountAuthed( customerInfoCard.getPrivateAccountAuthed());
                yLcontract_v2015.setCcid(customerInfoCard.getCid());
                yLcontract_v2015.setCcyear(customerInfoCard.getContractyear());
                Map<String, Object> map = new HashMap<String, Object>();
                map = mapUtils.beanToMap(customerInfoCard);

                for (Map.Entry<String,Object> entry : map.entrySet()) {
                    if(entry.getValue() instanceof String){
                        String origin = stringUtil.setUtf8(String.valueOf(entry.getValue()));
                        entry.setValue(origin);
                        //         System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                    }
                }
                customerInfoCard = mapUtils.mapToBean(map,CustomerInfoCard.class);
                if(yLcontractentryDao.updateYLcontract_v2015(yLcontract_v2015) && customerInfoDao.updateCustomerInfo(customerInfoCard)){
                    mapdata.put("code",0);
                    mapdata.put("msg","SUCCESS");
                    mapdata.put("data","true");
                    return mapdata;
                }else{
                    mapdata.put("code",1);
                    mapdata.put("msg","Failed");
                    mapdata.put("data","False");
                    return mapdata;
                }
            }else{
                //协议书不存在
                Map<String, Object> map = new HashMap<String, Object>();
                map = mapUtils.beanToMap(customerInfoCard);

                for (Map.Entry<String,Object> entry : map.entrySet()) {
                    if(entry.getValue() instanceof String){
                        String origin = stringUtil.setUtf8(String.valueOf(entry.getValue()));
                        entry.setValue(origin);
                        //         System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                    }

                }
                customerInfoCard = mapUtils.mapToBean(map,CustomerInfoCard.class);
                if(customerInfoDao.updateCustomerInfo(customerInfoCard)){
                    mapdata.put("code",0);
                    mapdata.put("msg","SUCCESS");
                    mapdata.put("data","true 且 协议书不存在 无法更新privateAccountAuthed字段");
                    return mapdata;
                }else{
                    mapdata.put("code",1);
                    mapdata.put("msg","Failed");
                    mapdata.put("data","且 协议书不存在 无法更新privateAccountAuthed字段");
                    return mapdata;
                }

            }

        }else{
            Map<String, Object> map = new HashMap<String, Object>();
            map = mapUtils.beanToMap(customerInfoCard);

            for (Map.Entry<String,Object> entry : map.entrySet()) {
                if(entry.getValue() instanceof String){
                    String origin = stringUtil.setUtf8(String.valueOf(entry.getValue()));
                    entry.setValue(origin);
                    //         System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                }
            }
            customerInfoCard = mapUtils.mapToBean(map,CustomerInfoCard.class);
            if(customerInfoDao.updateCustomerInfo(customerInfoCard)){
                mapdata.put("code",0);
                mapdata.put("msg","SUCCESS");
                mapdata.put("data","true");
                return mapdata;
            }else{
                mapdata.put("code",1);
                mapdata.put("msg","Failed");
                mapdata.put("data","false");
                return mapdata;
            }
        }
    }

    @Override
    public boolean createYLcontract(YLcontract_v2015_paa yLcontract_v2015_paa) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map = mapUtils.beanToMap(yLcontract_v2015_paa);

        for (Map.Entry<String,Object> entry : map.entrySet()) {
            if(entry.getValue() instanceof String){
                String origin = stringUtil.setUtf8(String.valueOf(entry.getValue()));
                entry.setValue(origin);
     //           System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }
        }

        yLcontract_v2015_paa = mapUtils.mapToBean(map,YLcontract_v2015_paa.class);
        return customerInfoDao.insertYLcontract(yLcontract_v2015_paa);
    }

    @Override
    public boolean updateYLcontract(YLcontract_v2015_paa yLcontract_v2015_paa) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map = mapUtils.beanToMap(yLcontract_v2015_paa);

        for (Map.Entry<String,Object> entry : map.entrySet()) {
            if(entry.getValue() instanceof String){
                String origin = stringUtil.setUtf8(String.valueOf(entry.getValue()));
                entry.setValue(origin);
                //           System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }
        }

        yLcontract_v2015_paa = mapUtils.mapToBean(map,YLcontract_v2015_paa.class);
        return customerInfoDao.updateYLcontract(yLcontract_v2015_paa);
    }

    @Override
    public String getXDistrict(String xDistrict) throws IOException {
        if(xDistrict == null){
            return "--";
        }else {
            String a = customerInfoDao.getXDistrict(xDistrict);
            if ( a == null || a.equals("")) {
                return "";
            } else {
                xDistrict = stringUtil.getUtf8(a);
                return xDistrict;
            }
        }
    }

    @Override
    public String getXAreaDistrictName(String getXAreaDistrict3Name) throws IOException {
        if(getXAreaDistrict3Name == null){
            return "--";
        }else {
            if (customerInfoDao.getXAreaDistrictName(getXAreaDistrict3Name).equals("") || customerInfoDao.getXAreaDistrictName(getXAreaDistrict3Name) == null) {
                return null;
            } else {
                getXAreaDistrict3Name = stringUtil.getUtf8(customerInfoDao.getXAreaDistrictName(getXAreaDistrict3Name));
                return getXAreaDistrict3Name;
            }
        }
    }

    @Override
    public Map<String, Object> showStateEchart(String year) {
        String Y="全部年份";
        if (year!=null){
            Y=year;
        }
        List x=new ArrayList();
        List y=new ArrayList();
        Map<String,Object> map=new HashMap<>();
        List<Map> list=customerInfoDao.getInfoBySate(year);
        for(Map m:list){
            if(m==null||m.get("STATE")==null||m.get("STATE").equals("1234")){
                continue;
            }
            switch (m.get("STATE").toString()){
                case "ONCREATE":
                    y.add("初始状态");
                    x.add(m.get("NUMS"));
                    break;
                case "CUSTOMERPORCESSING":
                    y.add("客户填写中");
                    x.add(m.get("NUMS"));
                    break;
                case "CUSTOMERPORCESSING2":
                    y.add("客户修改中");
                    x.add(m.get("NUMS"));
                    break;
                case "BUSINESSCHECKING":
                    y.add("业务员审核中");
                    x.add(m.get("NUMS"));
                    break;
                case "APPROVED":
                    y.add("已通过");
                    x.add(m.get("NUMS"));
                    break;
                case "BIILDEPCHECKING":
                    y.add("订单部审核");
                    x.add(m.get("NUMS"));
                    break;
                default:break;
            }
        }
        map.put("textname",Y+"网签资料卡执行汇总");
        map.put("y",y);
        map.put("x",x);

        return map;
    }

    @Override
    public Map getInfobyStateandmarketName(Integer start, Integer number,String year) throws UnsupportedEncodingException {
        Map map=new HashMap<String,Object>(2);
        List<Map<String,Object>> list=customerInfoDao.getInfobyStateandmarketName(start,number,year);
        List<Map<String,Object>> list2=new ArrayList<>();
        for (Map<String,Object> m:list){
            if (m.get("MARKETNAME")!=null) {
                m.put("MARKETNAME", StringUtil.getUtf8(m.get("MARKETNAME").toString()));
            }
            if (m.get("SUBMARKETMANAGERNAME")!=null){
                m.put("SUBMARKETMANAGERNAME",StringUtil.getUtf8(m.get("SUBMARKETMANAGERNAME").toString()));
            }
            list2.add(m);
        }
        map.put("data",list2);
        map.put("count",customerInfoDao.count(year));
        return map;
    }

    @Override
    public List<Map> getAllStatisticsInfo(String userCID, String userCName, String managerCID) {
        List<Map> result = new ArrayList<>();
        List<Map<String,Object>> markets = customerInfoDao.getAllArea(managerCID);
        String[] statusNames = new String[]{"初始状态","客户填写中","客户修改中","业务员审核中","已通过","订单部审核"};
        long status[] = new long[6];
        for (int i=0;i<6;i++) {
            status[i] = 0;
        }
        for (Map<String,Object> market:markets) {
            List<Map<String,Object>> values = customerInfoDao.getAllStatisticsInfo((String) market.get("AREA_CODE"),userCID,userCName);
            for (Map<String,Object> value:values) {
                switch((String)value.get("STATE")) {
                    case "ONCREATE":status[0] += ((BigDecimal)value.get("NUMS")).longValue();break;
                    case "CUSTOMERPORCESSING": status[1] += ((BigDecimal)value.get("NUMS")).longValue();break;
                    case "CUSTOMERPORCESSING2":status[2] += ((BigDecimal)value.get("NUMS")).longValue();break;
                    case "BUSINESSCHECKING":status[3] += ((BigDecimal)value.get("NUMS")).longValue();break;
                    case "APPROVED":status[4] += ((BigDecimal)value.get("NUMS")).longValue();break;
                    case "BIILDEPCHECKING":status[5] += ((BigDecimal)value.get("NUMS")).longValue();break;
                    default:continue;
                }
            }
        }
        for (int i=0;i<6;i++) {
            Map<String,Object> value = new HashMap<>();
            value.put("state",statusNames[i]);
            value.put("number",status[i]);
            result.add(value);
        }
        return result;
    }

    @Override
    public List<Map<String,Object>> getAllCustomerInfoCardState(String year) {
        List<Map<String,Object>> list = customerInfoDao.getAllCustomerInfoCardState(year);
        return list;
    }

    @Override
    public List<Map<String, Object>> getCustomerInfoCardStateByArea(String year) throws IOException{
        List<Map<String, Object>> list = customerInfoDao.getCustomerInfoCardStateByArea(2018);
        for (Map<String, Object> map : list) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String origin = stringUtil.getUtf8(String.valueOf(entry.getValue()));
                entry.setValue(origin);
            }
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getUserArea(String cid,String position) throws UnsupportedEncodingException {
        List<Map<String,Object>> list=new ArrayList<>();
        List<Map<String,Object>> areas=new ArrayList<>();
        if(position.equals("MANAGER")){
            list=customerInfoDao.getArea_Cmanager(cid);
        }else if(position.equals("SALEMAN_M")){
            list=customerInfoDao.getArea_Mmanager(cid);
        }else if(position.equals("SALEMAN_S")){
            list=customerInfoDao.getArea_Smanager(cid);
        }else {
            list=customerInfoDao.gatArea_All();
        }
        if (!position.equals("SALEMAN_S")){

            for (Map<String, Object> map : list) {  //大区
                Map<String, Object> bigMap=new HashMap<>();

                String bid=map.get("AREA").toString();

                bigMap.put("bname",StringUtil.getUtf8(map.get("AREA_NAME").toString()));
                bigMap.put("bid",map.get("AREA"));
                List<Map> datas=new ArrayList<>();
                for (Map<String, Object> m : list){  //片区

                    if (m.get("AREA").toString().equals(bid)){
                        Map<String, Object> smallMap=new HashMap<>();
                        smallMap.put("sname",StringUtil.getUtf8(m.get("DISTRICT_NAME").toString()));
                        smallMap.put("sid",m.get("DISTRICT_ID"));
                        datas.add(smallMap);
                    }

                }
                bigMap.put("Sarea",datas);
                areas.add(bigMap);

            }
            HashSet h = new HashSet(areas);
            areas.clear();
            areas.addAll(h);//去重
        }else{//片区
            for (Map<String, Object> map : list) {  //大区


                Map<String, Object> smallMap=new HashMap<>();
                smallMap.put("sname",StringUtil.getUtf8(map.get("DISTRICT_NAME").toString()));
                smallMap.put("sid",map.get("DISTRICT_ID"));
                areas.add(smallMap);
            }

        }


        return areas;
    }

    @Override
    public Map getUserCustomerinfo(Integer start, Integer number, Integer year, String cid, String area_1, String area_2, String find,String state, String position,String ylcstate,Integer legalchecked) throws UnsupportedEncodingException {
        List<Map<String, Object>> list=new ArrayList<>();
        Map<String,Object> Map=new HashMap<>();
        String a="";
        int count=0;
        if(position.equals("MANAGER")){

            list=customerInfoDao.getCustomerinfo_Cmanager(start,number,cid,state,year,area_1,area_2,find,ylcstate);
            count=customerInfoDao.count_Cmanager(start,number,cid,state,year,area_1,area_2,find,ylcstate);

            Map.put("area",this.getUserArea(cid,position));
        }
        else if(position.equals("SALEMAN_M")){
            list=customerInfoDao.getCustomerinfo_Mmanager(start,number,cid,state,year,area_1,area_2,find,ylcstate);
            count=customerInfoDao.count_Mmanager(start,number,cid,state,year,area_1,area_2,find,ylcstate);
            Map.put("area",this.getUserArea(cid,position));
        }else if( position.equals("SALEMAN_S")){
            List<Map<String,Object>> area=customerInfoDao.getArea_Smanager(cid);

            for (Map<String,Object> m:area){
                for (Map.Entry<String, Object> entry : m.entrySet()) {
                    String origin = stringUtil.getUtf8(String.valueOf(entry.getValue()));
                    entry.setValue(origin);
                }
                a=m.get("DISTRICT_NAME").toString();
            }
            Map.put("area",this.getUserArea(cid,position));

            list=customerInfoDao.getCustomerinfo_Smanager(start,number,cid,state,year,area_1,area_2,find,ylcstate);
            count=customerInfoDao.count_Smanager(start,number,cid,state,year,area_1,area_2,find,ylcstate);

        }else{
            list=customerInfoDao.getAllCustomerinfo(start,number,cid,state,year,area_1,area_2,find,ylcstate,legalchecked);
            count=customerInfoDao.countAll(start,number,cid,state,year,area_1,area_2,find,ylcstate,legalchecked);
            Map.put("area",this.getUserArea(cid,position));

        }
        for (Map<String, Object> map : list) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String origin = stringUtil.getUtf8(String.valueOf(entry.getValue()));
                entry.setValue(origin);
            }
//            System.out.println(map.get("CID").toString());
//            String ylc=customerInfoDao.getYlcstate(map.get("CID").toString(),Integer.parseInt(map.get("CONTRACTYEAR").toString()));
//            if (ylc==null){
//                map.put("YLCSTATE","SALEMANFILLING");
//            }else{
//                map.put("YLCSTATE",ylc);
//            }
            if(map.get("FILE_1_IDCARD")==null){
                map.put("FILE_1_IDCARD",0);
            }else{
                map.put("FILE_1_IDCARD",1);
            }
            if(map.get("FILE_2_BUSINESSLICENSE")==null){
                map.put("FILE_2_BUSINESSLICENSE",0);
            }else{
                map.put("FILE_2_BUSINESSLICENSE",1);
            }
            if(map.get("FILE_3_ORGCODE")==null){
                map.put("FILE_3_ORGCODE",0);
            }else{
                map.put("FILE_3_ORGCODE",1);
            }
            if(map.get("FILE_4_GTQC")==null){
                map.put("FILE_4_GTQC",0);
            }else{
                map.put("FILE_4_GTQC",1);
            }
        }
//        if(ylcstate!=null){
//            Iterator<Map<String,Object>> it = list.iterator();
//            while(it.hasNext()){
//                Map<String,Object> x = it.next();
//                System.out.println(x.get("YLCSTATE"));
//                if(!x.get("YLCSTATE").equals(ylcstate)){
//                    it.remove();
//                }
//            }
//        }


        Map.put("data",list);
        Map.put("count",count);
        return Map;
    }


}
