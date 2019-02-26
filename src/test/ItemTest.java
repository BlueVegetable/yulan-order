import com.yulan.dao.ActivityGroupTypeDao;
import com.yulan.pojo.ActivityGroupType;
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
    private ActivityGroupTypeDao activityGroupTypeDao;
    @Test
    public void test()throws Exception{
        ActivityGroupType activityGroupType = new ActivityGroupType();
        activityGroupType.setId(StringUtil.createStringID());
        activityGroupType.setName("D");
        activityGroupType.setValue(StringUtil.setUtf8("促销品计划"));
        activityGroupTypeDao.addActivityGroupType(activityGroupType);
    }
}
