package cart.shopping.gui.components;

import cart.shopping.core.Article;
import cart.shopping.core.ShoppingCart;

import javax.swing.*;
import java.awt.*;

public class ArticleCard extends JPanel {

    public ArticleCard(Article article, ShoppingCart shoppingCart, Runnable refreshCallback) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(350, 50));

        JButton plusButton = new JButton("+");
        plusButton.setFont(new Font("Arial", Font.BOLD, 14));
        plusButton.setFocusPainted(false);
        plusButton.addActionListener(_ -> {
            shoppingCart.addArticle(article);
            if (refreshCallback != null) {
                refreshCallback.run(); //
            }
        });
        add(plusButton);

        JLabel nameLabel = new JLabel(article.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        add(nameLabel);

        JLabel descriptionLabel = new JLabel("<html><i>" + article.getDescription() + "</i></html>");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descriptionLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        add(descriptionLabel);

        JLabel priceLabel = new JLabel(article.getGrossPrice() + " €");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priceLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        add(priceLabel);
    }
}