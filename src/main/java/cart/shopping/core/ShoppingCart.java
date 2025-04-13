package cart.shopping.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public final class ShoppingCart {

    private final List<Article> articles;

    public ShoppingCart() {
        this.articles = new ArrayList<>();
    }

    public void addArticle(Article article) {
        if (article == null) {
            throw new IllegalArgumentException("Article cannot be null");
        }
        articles.add(article);
    }

    public void removeArticle(Article article) {
        if (article == null || !articles.contains(article)) {
            throw new IllegalArgumentException("Article not found in the cart");
        }
        articles.remove(article);
    }

    public List<Article> getArticles() {
        return new ArrayList<>(articles);
    }

    public BigDecimal calculateTotalNetPrice() {
        return articles.stream()
                .map(Article::getNetPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                //especially when the cart is empty, ensure that the total price is 0.00 and not just 0
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateTotalGrossPrice() {
        return articles.stream()
                .map(Article::getGrossPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                //especially when the cart is empty, ensure that the total price is 0.00 and not just 0
                .setScale(2, RoundingMode.HALF_UP);
    }

    public int getTotalArticleCount() {
        return articles.size();
    }
}