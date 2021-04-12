import com.github.Dementor0383.annotation.Before;
import com.github.Dementor0383.annotation.Test;
import com.github.Dementor0383.annotation.After;


public class PrivateAfter {
    @Before
    public void printSomething() {
        System.out.println("Test private test start!");
    }

    @Test
    public void testPrivateAfter() {
        System.out.println("Loading test");
    }

    @Test
    public void testPrivateAfterTwo() {
        System.out.println("Loading test");
    }

    @After
    private void throwSomething() {
        throw new RuntimeException("throw!");
    }

}