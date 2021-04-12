import com.github.Dementor0383.annotation.Before;
import com.github.Dementor0383.annotation.Test;
import com.github.Dementor0383.annotation.After;


public class RightTest {

    @Before
    public void printSomething() {
        System.out.println("Test Test With Arguments start!");
    }

    @After
    public void printAfterInformation(){
        System.out.println("Test finished!!!");
    }

    @Test
    static private void testArgumentsMethod() {
       int error = 0;
       error+=3;
    }

    @Test
    public void testArgumentsMethodTwo() {
        //do nothing
    }

}