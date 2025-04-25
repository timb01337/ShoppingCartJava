package cart.shopping.console;

import cart.shopping.core.Article;
import cart.shopping.core.User;

public class ShoppingCartHandler {

    private final User currentUser;

    public ShoppingCartHandler(User currentUser) {
        this.currentUser = currentUser;
        System.out.printf("Warenkorb für '%s %s' erstellt.%n", currentUser.getFirstname(), currentUser.getLastname());
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void addArticleToCart(Article article) {
        currentUser.getShoppingCart().addArticle(article);
        System.out.printf("Artikel '%s' wurde dem Warenkorb hinzugefügt.%n", article.getName());
    }

    public void showCart() {
        System.out.printf("--------------------------------------------------------%n");
        if (currentUser.getShoppingCart().getTotalArticleCount() == 0) {
            System.out.println("Der Warenkorb ist leer.");

        } else {
            System.out.println("Warenkorb-Inhalt:");
            currentUser.getShoppingCart().getArticles().forEach(article ->
                    System.out.printf("- %s: %.2f EUR%n", article.getName(), article.getNetPrice()));
        }
        System.out.printf("--------------------------------------------------------%n");
    }

    public void resetCart() {
        currentUser.getShoppingCart().getArticles().forEach(currentUser.getShoppingCart()::removeArticle);
        System.out.println("Der Warenkorb wurde zurückgesetzt.");
    }
}