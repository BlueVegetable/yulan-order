import com.yulan.service.CommodityOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
