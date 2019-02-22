import com.yulan.dao.CustomerDao;
import com.yulan.dao.CustomerInfoCardDao;
import com.yulan.dao.YLcontractentryDao;
import com.yulan.pojo.Customer;
import com.yulan.pojo.CustomerInfoCard;
import com.yulan.utils.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CustomerinfocardgroupTest {
    @Autowired private CustomerDao customerDao;
    @Autowired private CustomerInfoCardDao customerInfoCardDao;
    @Autowired
    private YLcontractentryDao yLcontractentryDao;
    @Test
    public void test5(){
       String resource="<LI>2018-02-09 10:09 谢铁岩提交;</LI><LI>2018-02-09 14:14 被广元市城区康宝装饰材料经营部 通过协议文本;</LI>" +
               "<LI>2018-03-01 16:56 被刘伟 通过协议文本;</LI><LI>2018-03-27 15:44 " +
                        "被#DEP_MARKET_CHECK#黄诗adhdsa燕#DEP_MARKET_CHECK#打回[<SPAN CLASS=REJECTREASON>" +
               "兰居最低签约任务不得低于5万。</SPAN>];</LI><LI>2018-03-27 17:05 谢铁岩提交;" +
               "</LI><LI>2018-03-27 17:33 被广元市城区康宝装饰材料经营部否决[<SPAN CLASS=REJECTREASON>金额不对</SPAN>];" +
               "</LI><LI>2018-03-27 17:37 谢铁岩提交;</LI><LI>2018-03-27 17:38 被广元市城区康宝装饰材料经营部 通过协议文本;</LI><LI>2018-03-27 17:44 被刘伟 通过协议文本;" +
               "</LI><LI>2018-03-28 14:00 被#DEP_MARKET_CHECK#黄诗燕2#DEP_MARKET_CHECK# 审核通过协议文本;</LI><LI>2018-04-24 14:59 被#CSA_CHECK#周志强#CSA_CHECK# 审核批准协议文本;</LI>";
     System.out.println("hah"+ StringUtil.getName(resource,"#DEP_MARKET_CHECK#(.*?)#DEP_MARKET_CHECK#","#CSA_CHECK#"));
    }

    @Test
    public void test1() {
        List<CustomerInfoCard> customerInfoCards = new ArrayList<>();
        CustomerInfoCard customerInfoCard = new CustomerInfoCard();
        customerInfoCard.setGroupid("1");
        CustomerInfoCard customerInfoCard1 = new CustomerInfoCard();
        customerInfoCard1.setGroupid("2");
        customerInfoCards.add(customerInfoCard);
        customerInfoCards.add(customerInfoCard1);
        System.out.println(customerInfoCardDao.addCustomerInfoCard(customerInfoCard));
    }
    @Test
    public void test2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Customer customer = new Customer();
        Class cl = customer.getClass();
//        Method[] methods = cl.getMethods();
//        for (Method method:methods) {
//            System.out.println(method.getReturnType()+":"+method.getName());
//        }
        Field[] fields = cl.getDeclaredFields();
        for (Field field:fields) {
            if (field.getType().toString().equals("class java.lang.String")) {
                Method method = cl.getDeclaredMethod("set"+toUpper(field.getName()));
                System.out.println(cl.getMethod("get"+toUpper(field.getName())).invoke(null));
                method.invoke(cl.getMethod("get"+toUpper(field.getName())).invoke(null));
            }
        }
    }
    private String toUpper(String origin) {
        return Character.toUpperCase(origin.charAt(0)) + origin.substring(1);
    }
}
