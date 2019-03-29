import com.yulan.dao.Sal_rebate_certificate_recordDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class test {
    @Autowired
    private Sal_rebate_certificate_recordDao sal_rebate_certificate_recordDao;

    @Test
   public  void main() {
        Map<String,Object> map=sal_rebate_certificate_recordDao.getReturn();
        System.out.println(map.get("ID"));
    }
}