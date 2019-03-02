import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class test {
    @Test
   public  void main(){
        int []A={1,5,9,10,36};
        int []B={0,23,35,48,57,86};
        int a=get(A,B,3);
        System.out .println(a);
    }

    static int  get(int[] A, int[] B, int k){
        if(k>A.length+B.length)return -1;
        int  i=0,j=0,count=0;
        for (int z=0;z<k-1;z++){
            if(i<A.length&&j<B.length) {
                if (A[i] > B[j]) {
                    j++;
                    count++;
                } else if (A[i] < B[j]) {
                    i++;
                    count++;
                } else {
                    i++;
                    count++;
                }
            }
            else if(i==A.length){
                j++;
                count++;
            }
            else {
                i++;
                count++;
            }
            System.out .println(A[i]+"---"+B[j]);
        }

        return A[i]<B[j]?A[i]:B[j];


    }
}