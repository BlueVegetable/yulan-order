import com.yulan.dao.CartDao;
import com.yulan.dao.CartItemDao;
import com.yulan.dao.CommodityDao;
import com.yulan.dao.ItemDao;
import com.yulan.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ItemTest {
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CartItemDao cartItemDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private CommodityDao commodityDao;
    @Test
    public void test()throws Exception{
//        System.out.println(cartDao.getCartByCID("C01613"));
//        System.out.println(cartItemDao.getCartItems("1550916381149d7b178658d1d47568fe42656598c2706","wallpaper"));
//        for(int i=0;i<10;i++)
//            System.out.println(System.currentTimeMillis()+ StringUtil.createStringID());
//        Commodity commodity = new Commodity();
//        commodity.setId("1550916381149d7b178658d1d47568fe42656598c2706");
//        commodity.setActivityId("A04");
//        commodity.setActivityPrice(null);
//        commodity.setPrice(new BigDecimal("100.00"));
//        commodity.setActivityPrice(new BigDecimal("100.00"));
//        commodity.setCartItemId("15509292472498797c6d3f5d34a1d91ff083e270825f0");
//        commodity.setQuantity(new BigInteger("100"));
//        Item item = new Item();
//        item.setItemNo("DNRF517501");
//        commodity.setItem(item);
//        commodityDao.addCommodity(commodity);
//        CartItem cartItem = new CartItem();
//        cartItem.setCartItemId("000");
//        cartItem.setCartId("1550916381149d7b178658d1d47568fe42656598c2706");
//        cartItem.setActivityGroupType("A");
//        cartItem.setCommodityType("curtain");
//        cartItem.setProductGroupType("B");
//        cartItemDao.addCartItem(cartItem);
        System.out.println("da");
    }
}
