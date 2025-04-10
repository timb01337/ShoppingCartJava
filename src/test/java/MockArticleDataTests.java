import cart.shopping.core.Article;
import cart.shopping.data.MockArticleData;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MockArticleDataTests {
    @Test
    public void testGetRandomArticlesReturnsCorrectAmount() {
        int requestedCount = 5;
        List<Article> randomArticles = MockArticleData.getRandomArticles(requestedCount);
        Assert.assertEquals(requestedCount, randomArticles.size());
    }

}
