import com.yulan.dao.CommodityOrderDao;
import com.yulan.pojo.CommodityOrder;
import com.yulan.pojo.Item;
import com.yulan.service.CommodityOrderService;
import com.yulan.utils.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ItemTest {
    @Autowired
    private CommodityOrderDao commodityOrderDao;
    @Autowired
    private CommodityOrderService commodityOrderService;
    @Test
    public void test() {
        CommodityOrder commodityOrder = new CommodityOrder();
        commodityOrder.setId(System.currentTimeMillis()+ StringUtil.createStringID());
        Item item = new Item();
        item.setItemNo("E350001");
        commodityOrder.setItem(item);
        commodityOrder.setOrderItemId("");
        commodityOrder.setWidth(new BigDecimal("1.0"));
        commodityOrder.setHeight(new BigDecimal("1.0"));
        commodityOrder.setOrderItemId(System.currentTimeMillis()+StringUtil.createStringID());
        commodityOrder.setUnit("jian");
        commodityOrder.setCurtainItemName("chuanglian");
        commodityOrder.setCurtainPartName("lt");
        commodityOrder.setDosage(new BigDecimal("3"));
        commodityOrder.setManufacturingInstructions("ManufacturingInstruction");
        commodityOrder.setCertainHeightWidth(0);
        commodityOrder.setIllustrate("illustrate");
        commodityOrder.setSaveTime(new Timestamp(System.currentTimeMillis()));
        commodityOrder.setOrderItemNumber(1);
        commodityOrder.setSuggestion("suggestion");
//        commodityOrderDao.addCommodityOrder(commodityOrder);
        List<CommodityOrder> commodityOrders = new ArrayList<>();
        commodityOrders.add(commodityOrder);
        commodityOrders.add(commodityOrder);
        commodityOrders.add(commodityOrder);
        System.out.println(commodityOrderDao.addCommodityOrders(commodityOrders));
    }
    @Test
    public void test1() {
        List<String> cartItemIDs = new ArrayList<>();
        cartItemIDs.add("1556249842365557d56147bbd4c6199f31ec839816172");
//        System.out.println(commodityOrderService.submitCommodityOrder(cartItemIDs));
    }
    @Test
    public void test2() {
        commodityOrderDao.addOrderNoByOrderItemIDs("1556249842365557d56147bbd4c6199f31ec839816172","WTX001");
    }
}
