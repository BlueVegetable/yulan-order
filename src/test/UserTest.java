import com.yulan.dao.Ctm_orderDao;
import com.yulan.service.Ctm_orderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserTest {
    @Autowired
    private Ctm_orderDao ctm_orderDao;
    @Autowired
    private Ctm_orderService ctm_orderService;


    @Test
    public void test1() throws UnsupportedEncodingException {
            ctm_orderService.autoCancelOrder();
        }


    }



