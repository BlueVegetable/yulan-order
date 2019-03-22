import com.yulan.pojo.AreaCode;
import com.yulan.utils.ObjectUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class test {
    @Test
   public  void main() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<AreaCode> areaCodes = new ArrayList<>();
        for (int i=0;i<20000000;i++) {
            AreaCode areaCode = new AreaCode();
            areaCode.setUseId("HelloWorld");
            areaCodes.add(areaCode);
        }
        long time = System.currentTimeMillis();
        for (AreaCode areaCode:areaCodes) {
            ObjectUtils.setNullToNone(areaCode);
        }
        System.out.println("time:"+(System.currentTimeMillis()-time));
//        System.out.println(areaCode);
    }
}