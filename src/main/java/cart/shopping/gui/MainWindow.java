package cart.shopping.gui;

import cart.shopping.core.User;
import cart.shopping.data.MockArticleData;
import cart.shopping.gui.components.ArticleCard;
import cart.shopping.gui.components.CostSummary;
import cart.shopping.gui.components.ShoppingCartSummary;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MainWindow extends JFrame {

    private User user;
    private JSplitPane splitPane;

    public MainWindow(String applicationName) {
        setTitle(applicationName);
        setSize(1300, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getUserInformationPanel(), getEmptyPanel());
        splitPane.setDividerLocation(600);
        add(splitPane, BorderLayout.CENTER);
    }


    private JPanel getUserInformationPanel() {
        JPanel userInformationPanel = new JPanel(new GridBagLayout());
        userInformationPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel firstNameLabel = new JLabel("Vorname:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        userInformationPanel.add(firstNameLabel, gbc);

        JTextField firstNameField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        userInformationPanel.add(firstNameField, gbc);

        JLabel lastNameLabel = new JLabel("Nachname:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        userInformationPanel.add(lastNameLabel, gbc);

        JTextField lastNameField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        userInformationPanel.add(lastNameField, gbc);

        JButton loadArticlesButton = new JButton("Artikel laden");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        userInformationPanel.add(loadArticlesButton, gbc);
        loadArticlesButton.setEnabled(false);

        DocumentListener documentListener = new DocumentListener() {
            private void updateButtonState() {
                boolean isFirstNameFilled = !firstNameField.getText().trim().isEmpty();
                boolean isLastNameFilled = !lastNameField.getText().trim().isEmpty();
                loadArticlesButton.setEnabled(isFirstNameFilled && isLastNameFilled);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonState();
            }
        };

        firstNameField.getDocument().addDocumentListener(documentListener);
        lastNameField.getDocument().addDocumentListener(documentListener);

        loadArticlesButton.addActionListener(_ -> {
            user = new User(firstNameField.getText(), lastNameField.getText());

            replaceLeftPanel(getItemsPanel(), splitPane.getDividerLocation());
            replaceRightPanel(getShoppingCartPanel(), splitPane.getDividerLocation());
        });

        return userInformationPanel;
    }

    private JPanel getEmptyPanel() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.LIGHT_GRAY);

        return emptyPanel;
    }

    private JPanel getItemsPanel() {
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BorderLayout());
        itemsPanel.setBackground(Color.LIGHT_GRAY);

        var headerLabel = getHeaderLabel("Verfügbare Artikel:");
        itemsPanel.add(headerLabel, BorderLayout.NORTH);

        JPanel articlesPanel = new JPanel();
        articlesPanel.setLayout(new BoxLayout(articlesPanel, BoxLayout.Y_AXIS));
        articlesPanel.setBackground(Color.LIGHT_GRAY);
        articlesPanel.add(Box.createVerticalStrut(10));

        var shoppingItems = MockArticleData.getRandomArticles(15);

        for (var article : shoppingItems) {
            Runnable refreshCallback = () -> replaceRightPanel(getShoppingCartPanel(), splitPane.getDividerLocation());
            articlesPanel.add(new ArticleCard(article, user.getShoppingCart(), refreshCallback));
        }

        itemsPanel.add(articlesPanel, BorderLayout.CENTER);

        return itemsPanel;
    }

    private JPanel getShoppingCartPanel() {
        JPanel shoppingCartPanel = new JPanel();
        shoppingCartPanel.setLayout(new BorderLayout());
        shoppingCartPanel.setBackground(Color.LIGHT_GRAY);

        if (user != null) {

            var headerLabel = getHeaderLabel(user.getFirstname() + " " + user.getLastname() + "'s " + "Warenkorb:");
            shoppingCartPanel.add(headerLabel, BorderLayout.NORTH);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBackground(Color.LIGHT_GRAY);
            panel.add(Box.createVerticalStrut(20));

            var currentShoppingCart = user.getShoppingCart();
            Runnable refreshCallback = () -> replaceRightPanel(getShoppingCartPanel(), splitPane.getDividerLocation());

            if (currentShoppingCart.getTotalArticleCount() == 0) {
                JLabel emptyCartLabel = new JLabel("Der Warenkorb ist gerade leer.");
                emptyCartLabel.setFont(new Font("Arial", Font.ITALIC, 14));
                emptyCartLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(emptyCartLabel);
            }
            else {
                panel.add(new ShoppingCartSummary(currentShoppingCart, refreshCallback));
                panel.add(new CostSummary(currentShoppingCart));

                JButton resetButton = new JButton("Warenkorb zurücksetzen");
                resetButton.setFont(new Font("Arial", Font.PLAIN, 14));
                resetButton.setFocusPainted(false);
                resetButton.addActionListener(_ -> {
                    user = null;
                    replaceLeftPanel(getUserInformationPanel(), splitPane.getDividerLocation());
                    replaceRightPanel(getEmptyPanel(), splitPane.getDividerLocation());
                });
                panel.add(resetButton);
            }

            shoppingCartPanel.add(panel, BorderLayout.CENTER);
        }
        return shoppingCartPanel;
    }


    private JLabel getHeaderLabel(String text) {
        JLabel headerLabel = new JLabel(text);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return headerLabel;
    }

    private void replaceLeftPanel(JPanel newPanel, int dividerLocation) {
        splitPane.setLeftComponent(newPanel);
        splitPane.setDividerLocation(dividerLocation);

        rerenderPanels();
    }

    private void replaceRightPanel(JPanel newPanel, int dividerLocation) {
        splitPane.setRightComponent(newPanel);
        splitPane.setDividerLocation(dividerLocation);

        rerenderPanels();
    }

    private void rerenderPanels() {
        splitPane.revalidate();
        splitPane.repaint();
    }

}
