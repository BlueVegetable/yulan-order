import com.yulan.dao.Ctm_orderDao;
import com.yulan.service.Ctm_orderService;
import com.yulan.utils.Arith;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserTest {
    @Autowired
    private Ctm_orderDao ctm_orderDao;
    @Autowired
    private Ctm_orderService ctm_orderService;

    private Arith arith;


    @Test
    public void test1() throws UnsupportedEncodingException {
        BigDecimal a = BigDecimal.valueOf(500.123);
        BigDecimal b = BigDecimal.valueOf(1000);
            System.out.println(arith.div(a,b));
        }


    }



