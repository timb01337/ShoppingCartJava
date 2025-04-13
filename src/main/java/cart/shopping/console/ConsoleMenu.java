package cart.shopping.console;


import cart.shopping.core.User;

public class ConsoleMenu {
    private final UserInputHandler userInputHandler;

    public ConsoleMenu() {
        userInputHandler = new UserInputHandler();
    }

    public void start(String applicationName) {
        System.out.println("Willkommen im " + applicationName);

        String firstName = userInputHandler.getStringInput("Bitte geben Sie Ihren Vornamen ein");
        String lastName = userInputHandler.getStringInput("Bitte geben Sie Ihren Nachnamen ein");

        ShoppingCartHandler shoppingCartHandler = new ShoppingCartHandler(new User(firstName, lastName));
        ArticleHandler articleHandler = new ArticleHandler(userInputHandler, shoppingCartHandler);
        InvoiceHandler invoiceHandler = new InvoiceHandler(shoppingCartHandler);

        while (true) {
            System.out.println("\nHauptmenü:");
            System.out.println("1. Artikel anzeigen");
            System.out.println("2. Warenkorb anzeigen");
            System.out.println("3. Warenkorb zurücksetzen");
            System.out.println("4. aktuelle Rechnung anzeigen");
            System.out.println("5. Beenden");

            int choice = userInputHandler.getIntInput("Bitte wählen Sie eine Option");

            switch (choice) {
                case 1 -> articleHandler.showArticles();
                case 2 -> shoppingCartHandler.showCart();
                case 3 -> shoppingCartHandler.resetCart();
                case 4 -> invoiceHandler.displayInvoice();
                case 5 -> {
                    System.out.println("Auf Wiedersehen!");
                    return;
                }
                default -> System.out.println("Ungültige Auswahl. Bitte erneut versuchen.");
            }
        }
    }


}