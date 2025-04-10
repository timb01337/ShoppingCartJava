import cart.shopping.core.User;
import org.junit.Assert;
import org.junit.Test;

public class UserTests {

    @Test
    public void testCreateUserWithValidData() {
        User user = new User("John", "Doe");
        Assert.assertEquals("John", user.getFirstname());
        Assert.assertEquals("Doe", user.getLastname());
        Assert.assertNotNull("ShoppingCart should be initialized", user.getShoppingCart());
        Assert.assertTrue("ShoppingCart should be empty initially", user.getShoppingCart().getArticles().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserWithNullFirstname() {
        new User(null, "Doe");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserWithEmptyFirstname() {
        new User("   ", "Doe");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserWithNullLastname() {
        new User("John", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserWithEmptyLastname() {
        new User("John", "   ");
    }

}