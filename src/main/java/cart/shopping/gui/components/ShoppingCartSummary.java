package cart.shopping.gui.components;

import cart.shopping.core.Article;
import cart.shopping.core.ShoppingCart;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartSummary extends JPanel {
    public ShoppingCartSummary(ShoppingCart shoppingCart, Runnable refreshCallback) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);

        Map<Article, Integer> articleCounts = new HashMap<>();
        for (Article article : shoppingCart.getArticles()) {
            articleCounts.put(article, articleCounts.getOrDefault(article, 0) + 1);
        }

        List<Map.Entry<Article, Integer>> sortedEntries = new ArrayList<>(articleCounts.entrySet());
        sortedEntries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        for (Map.Entry<Article, Integer> entry : sortedEntries) {
            Article article = entry.getKey();
            int count = entry.getValue();
            BigDecimal totalPrice = article.getGrossPrice().multiply(BigDecimal.valueOf(count)).setScale(2, RoundingMode.HALF_UP);

            JPanel articlePanel = new JPanel();
            articlePanel.setLayout(new BoxLayout(articlePanel, BoxLayout.X_AXIS));
            articlePanel.setBackground(Color.WHITE);

            JLabel articleLabel = new JLabel(count + "x " + article.getName() + " - " + totalPrice +
                    " €" + "          " + "(MwSt. Satz " + article.getTaxRate().multiply(BigDecimal.valueOf(100)) + "%)");
            articleLabel.setMinimumSize(new Dimension(600, 30));
            articleLabel.setMaximumSize(new Dimension(600, 30));
            articleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            articleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            articlePanel.add(articleLabel);


            JButton minusButton = new JButton("-");
            minusButton.setFont(new Font("Arial", Font.BOLD, 14));
            minusButton.setFocusPainted(false);
            minusButton.addActionListener(_ -> {
                shoppingCart.removeArticle(article);
                if (refreshCallback != null) {
                    refreshCallback.run(); //
                }
            });

            articlePanel.add(minusButton);
            add(articlePanel);
        }
    }
}