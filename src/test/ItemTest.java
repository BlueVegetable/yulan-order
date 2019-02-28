import com.yulan.dao.ProductGroupTypeDao;
import com.yulan.pojo.ProductGroupType;
import com.yulan.utils.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ItemTest {
    @Autowired
    private ProductGroupTypeDao productGroupTypeDao;
    @Test
    public void test()throws Exception{
        ProductGroupType productGroupType = new ProductGroupType();
        productGroupType.setId(StringUtil.createStringID());
        productGroupType.setName("F");
        productGroupType.setValue(StringUtil.setUtf8("非窗帘类的软装"));
        productGroupTypeDao.addProductGroupType(productGroupType);
    }
}
