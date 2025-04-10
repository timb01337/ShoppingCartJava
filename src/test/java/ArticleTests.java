import cart.shopping.core.Article;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ArticleTests {

    @Test
    public void testGrossPriceCalculation() {
        var article = new Article("A001", "Test Article", "Description", new BigDecimal("100.00"), new BigDecimal("0.20"));
        var expectedGrossPrice = new BigDecimal("120.00");
        Assert.assertEquals(expectedGrossPrice, article.getGrossPrice());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNetPriceBelowMinimum() {
        new Article("A002", "Invalid Article", "Description", new BigDecimal("0.00"), new BigDecimal("0.20"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTaxRateBelowMinimum() {
        new Article("A003", "Invalid Article", "Description", new BigDecimal("100.00"), new BigDecimal("0.00"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTaxRateAboveMaximum() {
        new Article("A004", "Invalid Article", "Description", new BigDecimal("100.00"), new BigDecimal("1.00"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNetPriceWithMoreThanTwoDecimalPlaces() {
        new Article("A005", "Invalid Article", "Description", new BigDecimal("100.123"), new BigDecimal("0.20"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTaxRateWithMoreThanTwoDecimalPlaces() {
        new Article("A006", "Invalid Article", "Description", new BigDecimal("100.00"), new BigDecimal("0.205"));
    }

    @Test
    public void testValidArticleCreation() {
        var article = new Article("A007", "Valid Article", "Description", new BigDecimal("50.00"), new BigDecimal("0.10"));
        Assert.assertEquals("A007", article.getArticleNumber());
        Assert.assertEquals("Valid Article", article.getName());
        Assert.assertEquals("Description", article.getDescription());
        Assert.assertEquals(new BigDecimal("50.00"), article.getNetPrice());
        Assert.assertEquals(new BigDecimal("0.10"), article.getTaxRate());
    }
}