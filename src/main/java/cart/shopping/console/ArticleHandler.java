package cart.shopping.console;

import cart.shopping.core.Article;
import cart.shopping.data.MockArticleData;

import java.util.List;

public class ArticleHandler {

    private final ShoppingCartHandler cartHandler;
    private final UserInputHandler inputHandler;
    private final List<Article> articles;

    public ArticleHandler(UserInputHandler inputHandler, ShoppingCartHandler cartHandler) {
        this.inputHandler = inputHandler;
        this.cartHandler = cartHandler;

        articles = MockArticleData.getRandomArticles(10);
    }

    public void showArticles() {
        System.out.printf("--------------------------------------------------------%n");
        System.out.println("Verfügbare Artikel:");
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            System.out.printf("%d. %s - %s (%.2f EUR)%n", i + 1, article.getName(), article.getDescription(), article.getNetPrice());
        }
        System.out.println("0. Zurück zum Hauptmenü");
        System.out.printf("--------------------------------------------------------%n");

        int choice = inputHandler.getIntInput("Bitte geben Sie die Artikelnummer ein");
        if (choice > 0 && choice <= articles.size()) {
            cartHandler.addArticleToCart(articles.get(choice - 1));
        } else if (choice != 0) {
            System.out.println("Ungültige Artikelnummer.");
        }
    }

}
