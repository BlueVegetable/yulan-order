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
        Item curtainItem = itemDao.getItemByItemNO("GMTB163010");

        if(curtainItem.getHighJia()== null){
            curtainItem.setHighJia(BigDecimal.valueOf(0));
        }

        BigDecimal usage =
               usageCalculation(width,multiple,height,curtainItem);
        System.out.println(usage);
    }

    private BigDecimal usageCalculation(Double width, Double multiple,Double height, Item curtainItem){
        BigDecimal usage = BigDecimal.valueOf(0);
        //定高
        if ("02".equals(curtainItem.getFixType())) {
            usage =
                    arith.add(arith.dbToBD(width * multiple),
                            curtainItem.getDuihuaLoss());
        } else {
            //定宽
            if (curtainItem.getHighHh() == null || curtainItem.getHighHh().doubleValue() == 0) {
                usage =
                        arith.mul(arith.round(arith.div(arith.dbToBD(width * multiple), arith.div(curtainItem.getFixGrade(), arith.dbToBD(1000.0))), 0), arith.sub(arith.dbToBD(height + 0.2), curtainItem.getHighJia()));
            } else if (curtainItem.getHighHh().doubleValue() > 0) {
                //花回
                usage =
                        arith.mul(arith.mul(arith.round(arith.div(arith.dbToBD(width * multiple), arith.div(curtainItem.getFixGrade(), arith.dbToBD(1000.0))), 0),
                                arith.roundup(arith.div(arith.sub(arith.dbToBD(height + 0.2), curtainItem.getHighJia()), arith.div( curtainItem.getHighHh(),arith.dbToBD(1000.0))), 0)),
                                arith.div( curtainItem.getHighHh(),arith.dbToBD(1000.0)));
            }
        }

        return usage.setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}



