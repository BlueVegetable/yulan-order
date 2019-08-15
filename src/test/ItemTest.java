import com.yulan.pojo.CommodityOrder;
import com.yulan.service.CommodityOrderService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ItemTest {
    @Autowired
    private CommodityOrderService commodityOrderService;
    @Test
    public void test1() {
        List<String> cartItemIDs = new ArrayList<>();
        cartItemIDs.add("15645798909038b5a8642ec83462093fc928e05982c1b");
        Map<String,Integer> lineNos = new HashMap<>();
        lineNos.put("15645798909038b5a8642ec83462093fc928e05982c1b",1);
        System.out.println(commodityOrderService.submitCommodityOrder(cartItemIDs,lineNos));
    }

    @Test
    public void test2() throws InvocationTargetException, IllegalAccessException, UnsupportedEncodingException {
        CommodityOrder commodityOrder = new CommodityOrder();
        BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
        Map<String,Object> map=new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {//转码
            if (entry.getValue() instanceof String) {
                String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                entry.setValue(origin);
            }
        }
        map.put("id","1");
        map.put("specification",2.0);
        BeanUtils.populate(commodityOrder,map);
        commodityOrder.setNote("hh");
        System.out.println(commodityOrder.getSpecification());

    }
}
