package cart.shopping.gui.components;

import cart.shopping.core.ShoppingCart;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CostSummary extends JPanel {
    public CostSummary(ShoppingCart shoppingCart) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        int totalItems = shoppingCart.getTotalArticleCount();
        JLabel itemCountLabel = new JLabel("Es befinden sich " + totalItems + " Artikel im Warenkorb");
        itemCountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(itemCountLabel);

        BigDecimal totalPriceWithoutTax = shoppingCart.calculateTotalNetPrice();
        JLabel priceWithoutTaxLabel = new JLabel("<html><i>Preis ohne Steuer: " + totalPriceWithoutTax.setScale(2, RoundingMode.HALF_UP) + "€</i></html>");
        priceWithoutTaxLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        add(priceWithoutTaxLabel);

        BigDecimal totalPrice = shoppingCart.calculateTotalGrossPrice();
        JLabel totalPriceLabel = new JLabel("Preis gesamt: " + totalPrice.setScale(2, RoundingMode.HALF_UP) + "€");
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(totalPriceLabel);
    }
}