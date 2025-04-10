package cart.shopping.gui;

import cart.shopping.core.User;
import cart.shopping.data.MockArticleData;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MainWindow extends JFrame {

    private User user;
    private JSplitPane splitPane;

    public MainWindow(String applicationName) {
        setTitle(applicationName);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createInitialSplitPane();
    }

    private void createInitialSplitPane() {
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, GetUserInformationPanel(), GetEmptyPanel());
        splitPane.setDividerLocation(400);
        add(splitPane, BorderLayout.CENTER);
    }

    private JPanel GetUserInformationPanel() {
        JPanel userInformationPanel = new JPanel(new GridBagLayout());
        userInformationPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Vorname
        JLabel firstNameLabel = new JLabel("Vorname:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        userInformationPanel.add(firstNameLabel, gbc);

        JTextField firstNameField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        userInformationPanel.add(firstNameField, gbc);

        // Nachname
        JLabel lastNameLabel = new JLabel("Nachname:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        userInformationPanel.add(lastNameLabel, gbc);

        JTextField lastNameField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        userInformationPanel.add(lastNameField, gbc);

        // Artikel laden Button
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

            ReplaceLeftPanel(GetItemsPanel(), splitPane.getDividerLocation());
            ReplaceRightPanel(GetShoppingCartPanel(), splitPane.getDividerLocation());
        });

        return userInformationPanel;
    }

    private JPanel GetEmptyPanel() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.LIGHT_GRAY);

        return emptyPanel;
    }

    private JPanel GetItemsPanel() {
        JPanel itemsPanel = new JPanel();
        itemsPanel.setBackground(Color.LIGHT_GRAY);

        var shoppingItems = MockArticleData.getRandomArticles(5);

        //i need to hack some reusable card which displays a shopping item


        JLabel itemsLabel = new JLabel("Artikel werden hier angezeigt.");
        itemsPanel.add(itemsLabel);

        return itemsPanel;
    }

    private JPanel GetShoppingCartPanel() {
        JPanel shoppingCartPanel = new JPanel();

        if (user != null) {
            shoppingCartPanel.add(new JLabel("Willkommen, " + user.getFirstname() + " " + user.getLastname() + "!"));
        }

        return shoppingCartPanel;
    }

    private void ReplaceLeftPanel(JPanel newPanel, int dividerLocation) {
        splitPane.setLeftComponent(newPanel);
        splitPane.setDividerLocation(dividerLocation);

        RerenderPanels();
    }

    private void ReplaceRightPanel(JPanel newPanel, int dividerLocation) {
        splitPane.setRightComponent(newPanel);
        splitPane.setDividerLocation(dividerLocation);

        RerenderPanels();
    }

    private void RerenderPanels() {
        splitPane.revalidate();
        splitPane.repaint();
    }

}
