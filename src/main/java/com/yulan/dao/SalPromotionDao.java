package com.yulan.dao;

import com.yulan.pojo.SalPromotion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SalPromotionDao {

    SalPromotion getSalPromotionByID(String orderType);

    List<SalPromotion> selectSalPromotions(@Param("CID") String CID,@Param("itemNo")String itemNo);
}