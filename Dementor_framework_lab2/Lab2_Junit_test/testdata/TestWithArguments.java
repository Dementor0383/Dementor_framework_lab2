import com.github.Dementor0383.annotation.Before;
import com.github.Dementor0383.annotation.Test;


public class TestWithArguments {

    @Before
    public void printSomething() {
        System.out.println("Test Test With Arguments start!");
    }

    @Test
    public void testArgumentsMethod(int error, char test) {
        error += 3;
    }

}