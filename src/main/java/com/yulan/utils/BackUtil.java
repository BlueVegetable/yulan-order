package com.yulan.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BackUtil {
    /**
     * 计算返利点
     * @param promotion_cost 商品总价
     * @param allMoney 返利券总价
     * @param money 返利券
     * @param thisMoney 商品价格
     * @return 返利
     *
     */
    public static BigDecimal getBackMoney(BigDecimal promotion_cost, BigDecimal allMoney, BigDecimal money, BigDecimal thisMoney){



        BigDecimal backMoney=BigDecimal.valueOf(0);
        if(money.compareTo(BigDecimal.valueOf(0))==0){//优惠券为零（或不选）
            return backMoney;
        }
        backMoney=((thisMoney.multiply(money).divide(promotion_cost,2, RoundingMode.HALF_UP)));


        return backMoney;


    }

    /**
     * 计算最后一个商品分利
     * @param allMoney 返利券总额
     * @param lastAllMoney 除最后一个商品的总额
     * @param thisMoney 商品价格
     * @return
     */
    public static BigDecimal getLastBackMoney(BigDecimal allMoney,BigDecimal lastAllMoney,BigDecimal thisMoney){
        if(allMoney.compareTo(BigDecimal.valueOf(0))==0){
            return BigDecimal.valueOf(0);
        }
        if (allMoney.subtract(lastAllMoney).compareTo(thisMoney)==1){//大于商品价格
            return thisMoney;
        }else {
            return allMoney.subtract(lastAllMoney);
        }

    }
}
