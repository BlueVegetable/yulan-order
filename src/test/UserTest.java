import com.yulan.dao.Ctm_orderDao;
import com.yulan.dao.ItemDao;
import com.yulan.pojo.Item;
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
    @Autowired
    private ItemDao itemDao;


    @Test
    public void test1() throws UnsupportedEncodingException {

        Double width = 3.00;
        Double multiple = 2.0;
        Double height = 2.0;
        Item curtainItem = itemDao.getItemByItemNO("MLTB201742");

        if(curtainItem.getHighJia()== null){
            curtainItem.setHighJia(BigDecimal.valueOf(0));
        }

        BigDecimal usage =
                arith.mul(arith.round(arith.div(arith.dbToBD(width * multiple), arith.div(curtainItem.getFixGrade(), arith.dbToBD(1000.0))), 2), arith.sub(arith.dbToBD(height + 0.2), curtainItem.getHighJia()));

        System.out.println(usage);
        System.out.println("arith.dbToBD(width * multiple)" + arith.dbToBD(width * multiple));
        System.out.println("arith.div(arith.dbToBD(width * multiple), arith.div(curtainItem.getFixGrade(), arith.dbToBD(1000.0))" + arith.div(arith.dbToBD(width * multiple), arith.div(curtainItem.getFixGrade(), arith.dbToBD(1000.0))));
        System.out.println("arith.round(arith.div(arith.div(arith.dbToBD(width * multiple), curtainItem.getFixGrade()), arith.dbToBD(1000.0)), 2)"+arith.round(arith.div(arith.div(arith.dbToBD(width * multiple), curtainItem.getFixGrade()), arith.dbToBD(1000.0)), 2));
        System.out.println(" arith.sub(arith.dbToBD(height + 0.2), curtainItem.getHighJia())" +  arith.sub(arith.dbToBD(height + 0.2), curtainItem.getHighJia()));
    }
}



