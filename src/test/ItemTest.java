import com.yulan.dao.CartDao;
import com.yulan.dao.CartItemDao;
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
    @Test
    public void test()throws Exception{
//        System.out.println(cartDao.getCartByCID("C01613"));
        System.out.println(cartItemDao.getCartItems("1550916381149d7b178658d1d47568fe42656598c2706","wallpaper"));
//        for(int i=0;i<10;i++)
//            System.out.println(System.currentTimeMillis()+ StringUtil.createStringID());
    }
}
