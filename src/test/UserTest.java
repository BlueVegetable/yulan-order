import com.yulan.dao.Ctm_orderDao;
import com.yulan.pojo.Sal_rebate_certificate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserTest {
    @Autowired
    private Ctm_orderDao ctm_orderDao;


    @Test
    public void test1() throws UnsupportedEncodingException {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());//当前时间

        List<Sal_rebate_certificate> list=ctm_orderDao.getRebate("C01613",currentDate);
        for(Sal_rebate_certificate sal_rebate_certificate:list){
            System.out.println(sal_rebate_certificate.getDateEnd());
        }


    }

}

