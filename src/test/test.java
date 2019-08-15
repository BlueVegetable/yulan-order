import com.yulan.dao.Ctm_orderDao;
import com.yulan.pojo.CommodityOrder;
import com.yulan.pojo.Item;
import com.yulan.pojo.Sal_promotion;
import com.yulan.utils.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class test {
    @Autowired private Ctm_orderDao ctm_orderDao;

    @Test
    public void test2() throws InvocationTargetException, IllegalAccessException, UnsupportedEncodingException {
        Map<String,Object> map=new HashMap<>();
        Item i1=new Item();
        map.put("id","1");
        map.put("specification",2.0);
        map.put("item",i1);
        map.put("dosage",6.9);
        for (Map.Entry<String, Object> entry : map.entrySet()) {//转码
            if (entry.getValue() instanceof String) {
                String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                entry.setValue(origin);
            }
        }
        CommodityOrder commodityOrder = new CommodityOrder();
        BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
//        Item item=new Item();
//        Map itemMap=(Map) map.get("item");
//        BeanUtils.populate(item,itemMap);
//        map.remove("item");


        BeanUtils.populate(commodityOrder,map);
//        commodityOrder.setItem(item);
        commodityOrder.setNote("hh");
        System.out.println(commodityOrder.getSpecification()+": "+commodityOrder.getDosage());


    }

    @Test
    public void test1(){
        Sal_promotion sal_promotion=ctm_orderDao.getPromotion("20190608493");
    }


    @Test
   public  void main() {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int max_gcd1 = maxGcd4(a, b);
        System.out.println("最大公约数:"+max_gcd1);
    }

    public static int maxGcd4(int a, int b) {
        int i = a % b;
        int max_gcd = 1;
        if (i == 0) {
            max_gcd = b;
        } else {
            a = b;
            b = i;
            max_gcd = maxGcd4(a, b);
        }
        return max_gcd;

    }

    public static int maxGcd2(int a, int b, int c) {
        int d = maxGcd4(a, b);
        int i = c % d;
        int max_gcd = 1;
        if (i == 0) {
            max_gcd = d;
        } else {
            c = maxGcd4(a, b);
            d = i;
            max_gcd = maxGcd4(c, d);
        }
        return max_gcd;
    }

}