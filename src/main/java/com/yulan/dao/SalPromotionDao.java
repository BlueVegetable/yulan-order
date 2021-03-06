package com.yulan.dao;

import com.yulan.pojo.SalPromotion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SalPromotionDao {

    SalPromotion getSalPromotionByID(String pId);

    List<String> getSalPromotionNamesByIDs(@Param("IDs") List<String> IDs);

    List<SalPromotion> selectSalPromotions(@Param("CID") String CID, @Param("itemNo")String itemNo,
                                           @Param("productType")String productType, @Param("productBrand")String productBrand);
}
