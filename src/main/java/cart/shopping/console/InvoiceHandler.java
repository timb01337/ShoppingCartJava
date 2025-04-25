package cart.shopping.console;

import cart.shopping.core.ShoppingCart;

public class InvoiceHandler {

    private final ShoppingCart shoppingCart;

    public InvoiceHandler(ShoppingCartHandler cartHandler) {
        shoppingCart = cartHandler.getCurrentUser().getShoppingCart();
    }

    public void displayInvoice() {
        String connectionWord = shoppingCart.getTotalArticleCount() == 1 ?  "ist" : "sind";
        System.out.printf("--------------------------------------------------------%n");
        System.out.printf("RECHNUNG:%n");
        System.out.printf("Es "+ connectionWord +" %d Artikel im Warenkorb.%n", shoppingCart.getTotalArticleCount());
        System.out.printf("Der Netto Preis beträgt %.2f EUR.%n", shoppingCart.calculateTotalNetPrice());
        System.out.printf("Der Brutto Preis beträgt %.2f EUR.%n", shoppingCart.calculateTotalGrossPrice());
        System.out.printf("--------------------------------------------------------%n");
    }
}