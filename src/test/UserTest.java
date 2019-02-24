import com.yulan.dao.Ctm_orderDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserTest {
    @Autowired
    private Ctm_orderDao ctm_orderDao;


    @Test
    public void test1() throws UnsupportedEncodingException {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        List<Map<String,Object>> list=ctm_orderDao.getOrdersH(1,10,"C10110",null,null);
//        for (Map<String,Object> m:list){
//            System.out.println(formatter.format(m.get("WEB_TJ_TIME")));
//        }
////       System.out.println(ctm_orderDao.countOrders("C10110",null,null));
        java.util.Date utilDate=new Date();
        java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());

        java.sql.Time sTime=new java.sql.Time(utilDate.getTime());
        java.sql.Timestamp stp=new java.sql.Timestamp(utilDate.getTime());



    }

}

