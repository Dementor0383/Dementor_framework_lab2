import com.github.Dementor0383.annotation.Before;
import com.github.Dementor0383.annotation.Test;


public class PrivateTest {

    @Before
    public void printSomething() {
        System.out.println("Test private test start!");
    }

    @Test
    private void testPrivateTest() {
        // do nothing is ok
    }

}