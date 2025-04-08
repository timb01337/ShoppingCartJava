import cart.shopping.Main;
import org.junit.Assert;
import org.junit.Test;

public class BaseTest {

    @Test
    public void test1() {
        var lol = new Main();
        var ergebnis = lol.Calculate(5,3);

        Assert.assertEquals(8, ergebnis);
    }
}
