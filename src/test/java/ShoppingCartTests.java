import cart.shopping.core.Article;
import cart.shopping.core.ShoppingCart;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShoppingCartTests {

    @Test
    public void testAddArticle() {
        ShoppingCart cart = new ShoppingCart();
        Article article = new Article("A001", "Test Article", "Description", new BigDecimal("100.00"), new BigDecimal("0.20"));
        cart.addArticle(article);

        Assert.assertEquals(1, cart.getTotalArticleCount());
        Assert.assertTrue(cart.getArticles().contains(article));
    }

    @Test
    public void testRemoveArticle() {
        ShoppingCart cart = new ShoppingCart();
        Article article = new Article("A001", "Test Article", "Description", new BigDecimal("100.00"), new BigDecimal("0.20"));
        cart.addArticle(article);
        cart.removeArticle(article);

        Assert.assertEquals(0, cart.getTotalArticleCount());
        Assert.assertFalse(cart.getArticles().contains(article));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistentArticle() {
        ShoppingCart cart = new ShoppingCart();
        Article article = new Article("A001", "Test Article", "Description", new BigDecimal("100.00"), new BigDecimal("0.20"));
        cart.removeArticle(article);
    }

    @Test
    public void testCalculateTotalPrice() {
        ShoppingCart cart = new ShoppingCart();
        Article article1 = new Article("A001", "Article 1", "Description", new BigDecimal("100.00"), new BigDecimal("0.20"));
        Article article2 = new Article("A002", "Article 2", "Description", new BigDecimal("100.00"), new BigDecimal("0.20"));
        cart.addArticle(article1);
        cart.addArticle(article2);

        BigDecimal expectedTotalPrice = new BigDecimal("240.00");

        Assert.assertEquals(expectedTotalPrice, cart.calculateTotalPrice());
    }


    @Test
    public void testGetTotalArticleCount() {
        ShoppingCart cart = new ShoppingCart();
        Article article1 = new Article("A001", "Article 1", "Description", new BigDecimal("100.00"), new BigDecimal("0.20"));
        Article article2 = new Article("A002", "Article 2", "Description", new BigDecimal("50.00"), new BigDecimal("0.10"));
        cart.addArticle(article1);
        cart.addArticle(article2);

        Assert.assertEquals(2, cart.getTotalArticleCount());
    }

    @Test
    public void testEmptyCartTotalPrice() {
        ShoppingCart cart = new ShoppingCart();
        Assert.assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), cart.calculateTotalPrice());
    }
}
