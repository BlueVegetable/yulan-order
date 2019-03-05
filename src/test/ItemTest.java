import com.yulan.service.SalPromotionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ItemTest {
    @Autowired
    private SalPromotionService salPromotionService;
    @Test
    public void test()throws Exception{
        List<String> ids = new ArrayList<>();
//        ids.add("A04");
        System.out.println(salPromotionService.getSalPromotionNamesByIDs(ids));
    }
}
