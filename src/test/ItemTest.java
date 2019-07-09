import com.yulan.dao.SalPromotionDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ItemTest {
    @Autowired
    private SalPromotionDao salPromotionDao;
    @Test
    public void test1() {
        System.out.println(salPromotionDao.getSalPromotionByID("2019"));
    }
}
