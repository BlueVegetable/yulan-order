import com.yulan.pojo.CurtainCommodity;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ItemTest {
    @Test
    public void test() {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("curtainItemName","Hello");
        JSONObject jsonObject = JSONObject.fromObject(parameters);
        CurtainCommodity curtainCommodity = (CurtainCommodity) JSONObject.toBean(jsonObject, CurtainCommodity.class);
        System.out.println(curtainCommodity);
    }
}
