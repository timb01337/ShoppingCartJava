package cart.shopping;

import cart.shopping.console.ConsoleMenu;
import cart.shopping.core.Article;
import cart.shopping.core.ShoppingCart;
import cart.shopping.core.User;
import cart.shopping.data.MockArticleData;
import cart.shopping.gui.MainWindow;

import java.util.List;

public class Main {

    private static final String APPLICATION_NAME = "Java Shopping Cart";
    private static final String LAUNCH_UI_ARGUMENT = "--launch-ui";
    private static final String LAUNCH_CONSOLE_ARGUMENT = "--launch-console";

    public static void main(String[] args) {

        boolean launchUi = false;
        boolean launchConsoleApp = false;

        for (String arg : args) {
            if (LAUNCH_UI_ARGUMENT.equals(arg)) {
                launchUi = true;
                break;
            }

            if (LAUNCH_CONSOLE_ARGUMENT.equals(arg)) {
                launchConsoleApp = true;
                break;
            }
        }

        if (launchUi) {
            System.out.println("GUI application started.");

            MainWindow mainWindow = new MainWindow(APPLICATION_NAME);
            mainWindow.setVisible(true);

        } else if (launchConsoleApp) {
            System.out.println("Console application started.");

            ConsoleMenu menu = new ConsoleMenu();
            menu.start(APPLICATION_NAME);
        } else {
            InitializeBasicSample();
        }


    }

    private static void InitializeBasicSample() {
        System.out.println("Basic sample:");

        User user1 = new User("Max", "Mustermann");
        PrintBasicSample(user1);

        User user2 = new User("Maria", "Musterfrau");
        PrintBasicSample(user2);
    }

    private static void PrintBasicSample(User user) {
        System.out.println("--------------------------------------------------------------");
        System.out.println("Benutzer: " + user.getFirstname() + " " + user.getLastname());

        List<Article> articles = MockArticleData.getRandomArticles(4);
        ShoppingCart shoppingCart = user.getShoppingCart();

        for (Article article : articles) {
            shoppingCart.addArticle(article);
        }

        System.out.println("Es befinden sich " + shoppingCart.getTotalArticleCount() + " Artikel im Warenkorb:");

        for (int i = 0; i < shoppingCart.getTotalArticleCount(); i++) {
            Article article = shoppingCart.getArticles().get(i);
            System.out.println((i + 1) + ". Artikel: " +
                    "Artikelnummer: " + article.getArticleNumber() + ", " +
                    "Artikel: " + article.getName() + ", " +
                    "Nettopreis: " + article.getNetPrice() + ", " +
                    "Steuersatz: " + article.getTaxRate() + ", " +
                    "Bruttopreis: " + article.getGrossPrice());
        }

        System.out.println("Der Nettopreis der im Warenkorb befindlichen Artikel beträgt: " + shoppingCart.calculateTotalNetPrice());
        System.out.println("Der Bruttopreis der im Warenkorb befindlichen Artikel beträgt: " + shoppingCart.calculateTotalGrossPrice());
        System.out.println("--------------------------------------------------------------");
    }

}