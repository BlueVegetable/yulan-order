import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Scanner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class test {
    @Test
   public  void main() {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int max_gcd1 = maxGcd4(a, b);
        System.out.println("最大公约数:"+max_gcd1);
    }

    public static int maxGcd4(int a, int b) {
        int i = a % b;
        int max_gcd = 1;
        if (i == 0) {
            max_gcd = b;
        } else {
            a = b;
            b = i;
            max_gcd = maxGcd4(a, b);
        }
        return max_gcd;

    }

    public static int maxGcd2(int a, int b, int c) {
        int d = maxGcd4(a, b);
        int i = c % d;
        int max_gcd = 1;
        if (i == 0) {
            max_gcd = d;
        } else {
            c = maxGcd4(a, b);
            d = i;
            max_gcd = maxGcd4(c, d);
        }
        return max_gcd;
    }

}