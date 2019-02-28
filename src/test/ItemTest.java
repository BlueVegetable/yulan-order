import com.yulan.dao.CommodityDao;
import com.yulan.pojo.Commodity;
import com.yulan.pojo.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.BigInteger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ItemTest {
    @Autowired
    private CommodityDao commodityDao;
    @Test
    public void test()throws Exception{
        Commodity commodity = new Commodity();
        commodity.setId("155136680275847633ffe4d194cc581ea9e53697f0c1e");
        commodity.setCartItemId("1551366802683367063a45e9f4fa7b63b891c1fa10bd3");
        commodity.setQuantity(new BigInteger("40"));
        Item item = new Item();
        item.setItemNo("NPP006911");
        commodity.setItem(item);
        commodity.setActivityId("B33");
        commodity.setPrice(new BigDecimal("40.00"));
        commodityDao.updateCommodity(commodity);
    }
}
