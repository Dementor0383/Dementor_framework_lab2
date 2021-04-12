import com.github.Dementor0383.annotation.Before;
import com.github.Dementor0383.annotation.Test;


public class PrivateBefore {
    @Before
    private void throwsSomething() {
        throw new RuntimeException("throw!");
    }

    @Test
    public void testPrivateBefore() {
        // do nothing is ok
    }

    @Test
    public void testPrivateBeforeTwo() {
        // do nothing is ok
    }

}
