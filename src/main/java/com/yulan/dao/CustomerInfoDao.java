package com.yulan.dao;

import com.yulan.pojo.CustomerInfoCard;
import com.yulan.pojo.YLcontract_v2015_paa;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerInfoDao {

    CustomerInfoCard getCustomerInfo(@Param("CID") String cID);

    CustomerInfoCard getCustomerInfoByYear(@Param("CID") String cid,@Param("YEAR") Integer year);

    List<CustomerInfoCard> getCustomerInfoByMarketName(@Param("YEAR")Integer year,
                                                       @Param("marketname")String marketname,
                                                       @Param("submarketname")String submarketname);

    List<Map<String,Object>> getInfobyStateandmarketName(@Param("start")Integer start, @Param("number") Integer number,@Param("year")String year);

    List<Map> getInfoBySate(@Param("year")String year);

    int count(@Param("year")String year);

    List<CustomerInfoCard> getAllinfo(@Param("start")Integer start, @Param("number") Integer number,
                                      @Param("year")String year,@Param("state")String state,
                                      @Param("find")String find,@Param("area_1")String area_1,
                                      @Param("area_2")String area_2);

    int countInfo(@Param("year")String year,@Param("state")String state,
                  @Param("find")String find);

    List<Map<String,Object>> getAllStates();



    /**
     *
     * @param customerInfoCard
     * @return
     */
    boolean updateCustomerInfo(CustomerInfoCard customerInfoCard);

    /**
     *
     * @param cCID
     * @return
     */
    YLcontract_v2015_paa getYLcontract(@Param("CCID") String cCID);

    YLcontract_v2015_paa getYLcontractByYear(@Param("CCID") String cCID,@Param("CCYEAR") Integer ccyear);

    /**
     *
     * @param yLcontract_v2015_paa
     * @return
     */
    boolean insertYLcontract(YLcontract_v2015_paa yLcontract_v2015_paa);

    boolean updateYLcontract(YLcontract_v2015_paa yLcontract_v2015_paa);

    String getXAreaDistrictName(@Param("REGION_ID") String xAreaDistrict3);

    String getXDistrict(@Param("DISTRICT_ID") String xDistrict);

    //获取业务员所有区域
    List<Map<String,Object>> getAllArea(@Param("cid")String cid);

    List<Map<String,Object>> getAllStatisticsInfo(@Param("market") String market,@Param("userCID") String userCID,
                                                  @Param("userCName") String userCName);

    List<Map<String,Object>> getInfoandYlc(@Param("start")Integer start,@Param("number") Integer number,
                                           @Param("find")String find,@Param("year")String year,
                                           @Param("infoState")String infoStat,@Param("ylcState")String ylcState);

    int countInfoandYlc(@Param("find")String find,@Param("year")String year,
                        @Param("infoState")String infoStat,@Param("ylcState")String ylcState);
    //业务员审核资料卡
    boolean businessCheckCustomerInfoCard(@Param("CID") String cid ,@Param("STATE") String state,@Param("MEMO") String memo);

    boolean lawCheckCustomerInfoCardState(@Param("CID") String cid ,@Param("STATE") String state,@Param("MEMO") String memo,@Param("LEGALCHECKED")Integer legalchecked);

    //资料卡执行状态汇总
    List<Map<String,Object>> getAllCustomerInfoCardState(@Param("CONTRACTYEAR") String year);

    //获取所有资料卡所属的所有大区
    //暂时用不上这个方法
    List<Map<String,Object>> getAllCustomerInfoCardArea(@Param("CONTRACTYEAR") String year);

    //获取每个大区下的资料卡执行状态统计
    List<Map<String,Object>> getCustomerInfoCardStateByArea(@Param("CONTRACTYEAR") Integer year);

    List<CustomerInfoCard> getCustomerInfoCardLeagalChecked(@Param("start")Integer start,@Param("number") Integer number,@Param("LEGALCHECKED")Integer legalchecked );


    List<Map<String, Object>> getArea_Cmanager(@Param("cid")String cid);//销售中心经理获取管理地区

    List<Map<String, Object>> getArea_Mmanager(@Param("cid")String cid);//大区经理获取管理地区

    List<Map<String, Object>> getArea_Smanager(@Param("cid")String cid);//片区经理获取管理地区

    List<Map<String, Object>> gatArea_All() ;//所有区域（内部审核人员）


    String getYlcstate(@Param("cid")String cid,@Param("year")int year);//获取资料卡cid相应的协议书状态

    List<Map<String, Object>> getCustomerinfo_Cmanager(@Param("start")Integer start,@Param("number") Integer number,
                                                       @Param("cid")String cid,@Param("state")String state,
                                                       @Param("year")Integer year,@Param("area_1")String area_1,
                                                       @Param("area_2")String area_2,@Param("find")String find,
                                                       @Param("ylcstate")String ylcstate);//销售中心经理获取管理地区的资料卡

    List<Map<String, Object>> getCustomerinfo_Mmanager(@Param("start")Integer start,@Param("number") Integer number,
                                                       @Param("cid")String cid,@Param("state")String state,
                                                       @Param("year")Integer year,@Param("area_1")String area_1,
                                                       @Param("area_2")String area_2,@Param("find")String find,
                                                       @Param("ylcstate")String ylcstate);//大区经理获取管理地区的资料卡

    List<Map<String, Object>> getCustomerinfo_Smanager(@Param("start")Integer start,@Param("number") Integer number,
                                                       @Param("cid")String cid,@Param("state")String state,
                                                       @Param("year")Integer year,@Param("area_1")String area_1,
                                                       @Param("area_2")String area_2,@Param("find")String find,
                                                       @Param("ylcstate")String ylcstate);//片区经理获取管理地区的资料卡

    List<Map<String, Object>> getAllCustomerinfo(@Param("start")Integer start,@Param("number") Integer number,
                                                 @Param("cid")String cid,@Param("state")String state,
                                                 @Param("year")Integer year,@Param("area_1")String area_1,
                                                 @Param("area_2")String area_2,@Param("find")String find,
                                                 @Param("ylcstate")String ylcstate,@Param("legalchecked")Integer legalchecked);//内部审核获取的资料卡所有地区

    int count_Cmanager(@Param("start")Integer start,@Param("number") Integer number,
                       @Param("cid")String cid,@Param("state")String state,
                       @Param("year")Integer year,@Param("area_1")String area_1,
                       @Param("area_2")String area_2,@Param("find")String find,
                       @Param("ylcstate")String ylcstate);

    int count_Mmanager(@Param("start")Integer start,@Param("number") Integer number,
                      @Param("cid")String cid,@Param("state")String state,
                      @Param("year")Integer year,@Param("area_1")String area_1,
                      @Param("area_2")String area_2,@Param("find")String find,
                      @Param("ylcstate")String ylcstate);

    int count_Smanager(@Param("start")Integer start,@Param("number") Integer number,
                       @Param("cid")String cid,@Param("state")String state,
                       @Param("year")Integer year,@Param("area_1")String area_1,
                       @Param("area_2")String area_2,@Param("find")String find,
                       @Param("ylcstate")String ylcstate);

    int countAll(@Param("start")Integer start,@Param("number") Integer number,
                 @Param("cid")String cid,@Param("state")String state,
                 @Param("year")Integer year,@Param("area_1")String area_1,
                 @Param("area_2")String area_2,@Param("find")String find,
                 @Param("ylcstate")String ylcstate,@Param("legalchecked")Integer legalchecked);



}
