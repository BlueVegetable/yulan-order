import com.yulan.dao.Sal_rebate_certificate_recordDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class test {
    @Autowired
    private Sal_rebate_certificate_recordDao sal_rebate_certificate_recordDao;

    @Test
    public void main() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp date = Timestamp.valueOf("2019-10-20 00:00:00");
        String value = simpleDateFormat.format(date);
        System.out.println(value);
    }
}