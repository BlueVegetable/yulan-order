import com.yulan.dao.CommodityDao;
import com.yulan.dao.ItemDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ItemTest {
    @Autowired
    private CommodityDao commodityDao;
    @Autowired
    private ItemDao itemDao;
    @Test
    public void test()throws Exception{
        ;
    }
}
