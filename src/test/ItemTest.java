import com.yulan.dao.ItemDao;
import com.yulan.dao.PostAddressDao;
import com.yulan.pojo.PostAddress;
import com.yulan.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ItemTest {
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private ItemService itemService;
    @Autowired
    private PostAddressDao postAddressDao;

    @Test
    public void test()throws Exception{
     // System.out.println(itemService.getWallpaperInfo("C01613","111"));
        PostAddress postAddress = new PostAddress();
  //      postAddressDao.addAddress(postAddress);
    }
}
