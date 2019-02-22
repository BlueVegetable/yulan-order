package com.yulan.controller;

import com.yulan.pojo.*;
import com.yulan.service.*;
import com.yulan.utils.Response;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerinfocardgroupService customerInfoCardGroupService;
    @Autowired
    private CustomerInfoCardService customerInfoCardService;
    @Autowired
    private AreaDistrictService areaDistrictService;
    @Autowired
    private Area_ownerService area_ownerService;
    @Autowired
    private AreaCodeService areaCodeService;
    @Autowired
    private AreaRegionService areaRegionService;
    @Autowired
    private HesEmployeeService hesEmployeeService;

    /**
     * 批量创建资料卡
     * @param datas
     * @return
     * @throws UnsupportedEncodingException
     */
    @ResponseBody@RequestMapping("createDataCards")
    public Map createDataCard(@RequestBody Map<String,Object> datas) throws UnsupportedEncodingException {
        String customerCode = (String) datas.get("customerCode");
        String descp = (String) datas.get("descp");
        List<String> areaCodes = (List<String>) datas.get("areaCodes");
        List<String> areaDistricts = (List<String>) datas.get("areaDistricts");
        List<String> customerTypes = (List<String>) datas.get("customerTypes");
        if(areaCodes!=null) {
            areaCodes=areaCodes.size()==0?null:areaCodes;
        }
        if(areaDistricts!=null) {
            areaDistricts=areaDistricts.size()==0?null:areaDistricts;
        }
        if(customerTypes!=null) {
            customerTypes=customerTypes.size()==0?null:customerTypes;
        }
        List<Customer> customers;
        if(areaCodes==null&&areaDistricts==null&&customerTypes==null&&customerCode==null) {
            customers = new ArrayList<>();
        } else {
            customers = customerService.getCustomers(customerCode,areaCodes,areaDistricts,customerTypes);
        }
        descp = StringUtil.setUtf8(descp);
        Customerinfocardgroup customerinfocardgroup = customerInfoCardGroupService.getCustomerInfoCardGroupByName(descp);
        if(customerinfocardgroup==null) {
            customerinfocardgroup = new Customerinfocardgroup();
            customerinfocardgroup.setId(StringUtil.createStringID());
            customerinfocardgroup.setDescp(descp);
            customerInfoCardGroupService.addCustomerInfoCardGroup(customerinfocardgroup);
        } else {
            List<Customer> customersExist = customerService.getCustomersByGroupID(customerinfocardgroup.getId());
            customers.removeAll(customersExist);
        }
        int result = 0;
        customers=customers==null?new ArrayList<Customer>():customers;
        for (Customer customer:customers) {
            CustomerInfoCard customerInfoCard = integrate(customer,customerinfocardgroup);
            result += customerInfoCardService.addCustomerInfoCard(customerInfoCard)?1:0;
        }
        return Response.getResponseMap(0,"添加成功了"+result+"份资料卡",null);
    }

    /**
     * 将对象的数据整合完成后生成CustomerInfoCard对象
     * @param customer
     * @param customerInfoCardGroup
     * @return
     */
    private CustomerInfoCard integrate(Customer customer,Customerinfocardgroup customerInfoCardGroup) throws UnsupportedEncodingException {
        CustomerInfoCard customerInfoCard = new CustomerInfoCard();
        customerInfoCard.setGroupid(customerInfoCardGroup.getId());
        customerInfoCard.setDeleted((short)0);
        customerInfoCard.setCid(customer.getCustomerCode());
        customerInfoCard.setCname(customer.getCustomerName());
        customerInfoCard.setMarket(customer.getAreaCode());
        customerInfoCard.setSubmarket(customer.getAreaDistrict());
        customerInfoCard.setDistrictText(customer.getDistrict());
        customerInfoCard.setAreaDistrict2Text(customer.getAreaDistrict2());
        customerInfoCard.setAreaDistrict3Text(customer.getAreaDistrict3());
        customerInfoCard.setxJuridicPerson(customer.getJuridicPerson());
        customerInfoCard.setxOfficeTel(customer.getOfficeTel());
        customerInfoCard.setxHandset(customer.getHandset());
        customerInfoCard.setxPostAddress(customer.getPostAddress());
        customerInfoCard.setxOfficeTel1(customer.getOfficeTel1());
        customerInfoCard.setxHandset2(customer.getHandset2());
        customerInfoCard.setxDeliveryAdress(customer.getDeliveryAdress());
        customerInfoCard.setTxAgentName(customer.getCustomerAgent());
        customerInfoCard.setWlAgentName(customer.getCustomerAgent1());
        customerInfoCard.setQq(customer.getQqD());
        customerInfoCard.setxFax(customer.getFax());
        customerInfoCard.setxEmail(customer.getEmail());
        customerInfoCard.setxZipCode(customer.getZipCode());
        customerInfoCard.setFaxWl(customer.getFaxWlD());
        customerInfoCard.setZipCodeWl(customer.getZipCodeWlD());
        customerInfoCard.setAccount1Name(customer.getCustomerName());
        customerInfoCard.setAccount2Name(customer.getJuridicPerson());

        //设置资料卡的地区
        customerInfoCard.setxDistrict(customer.getAreaDistrict());
        customerInfoCard.setxAreaDistrict2(customer.getAreaDistrict2());
        customerInfoCard.setxAreaDistrict3(customer.getAreaDistrict3());
        if(customer.getAreaDistrict2()!=null) {
            String areaDistrict2 = customer.getAreaDistrict2();
            AreaRegion areaRegion = areaRegionService.getAreaRegionByID(areaDistrict2);
            if(areaRegion!=null) {
                customerInfoCard.setAreaDistrict2Text(areaRegion.getRegionName());
            }
        }
        if(customer.getAreaDistrict3()!=null) {
            String areaDistrict3 = customer.getAreaDistrict3();
            AreaRegion areaRegion = areaRegionService.getAreaRegionByID(areaDistrict3);
            if(areaRegion!=null) {
                customerInfoCard.setAreaDistrict3Text(areaRegion.getRegionName());
            }
        }

        //设置资料卡状态
        customerInfoCard.setState("CUSTOMERPORCESSING");
        //设置法务员审查状态
        customerInfoCard.setLegalchecked((short)0);

        //设置大区
        AreaCode areaCode = areaCodeService.getAreaCodeByAreaCode(customer.getAreaCode());
        if(areaCode!=null) {
            customerInfoCard.setMarketname(areaCode.getAreaName());
        }

        //设置大区，片区经理
        List<Area_owner> managers = area_ownerService.getAreaOwnerByAreaCode(customer.getAreaCode());
        String market = StringUtil.setUtf8("办事处经理");
        String subMarket = StringUtil.setUtf8("业务经理");
        managers = managers != null?managers:new ArrayList<Area_owner>();
        for (Area_owner manager:managers) {
            if(manager.getPosition().equals(market)) {
                customerInfoCard.setMarketmanager(manager.getOwner());
                customerInfoCard.setManagerposition(market);
                customerInfoCard.setMarketmanagername(hesEmployeeService.getHesEmployeeNameByNO(manager.getOwner()));
            }
            else if(manager.getPosition().equals(subMarket)) {
                customerInfoCard.setSubmarketmanager(manager.getOwner());
                customerInfoCard.setSubmarketmanagername(hesEmployeeService.getHesEmployeeNameByNO(manager.getOwner()));
            }
        }

        AreaDistrict areaDistrict = areaDistrictService.getAreaDistrictByDistrictID(customer.getAreaDistrict());
        if(areaDistrict!=null) {
            customerInfoCard.setSubmarketname(areaDistrict.getDistrictName());
        }

        customerInfoCard.setIsGeneraltaxpayer(customer.getGeneraltaxpayerStatus());
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String value = simpleDateFormat.format(date);
        Integer year = Integer.parseInt(value);
        customerInfoCard.setContractyear(year);
        return customerInfoCard;
    }
}
