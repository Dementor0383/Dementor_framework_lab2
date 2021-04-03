import com.github.Dementor0383.annotation.Before;
import com.github.Dementor0383.annotation.Test;


public class PrivateBefore {
    @Before
    private void throwsSomething() {
        throw new RuntimeException("throw!");
    }

    @Test
    public void test() {
        // do nothing is ok
    }

}
