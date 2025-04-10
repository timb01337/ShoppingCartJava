package cart.shopping;

import cart.shopping.gui.MainWindow;

public class Main {

    private static final String APPLICATION_NAME = "Java Shopping Cart";
    private static final String CONSOLE_ONLY_ARGUMENT = "--console-only";

    public static void main(String[] args) {

        var consoleOnlyMode = false;

        for (String arg : args) {
            if (CONSOLE_ONLY_ARGUMENT.equals(arg)) {
                consoleOnlyMode = true;
                break;
            }
        }

        if (consoleOnlyMode) {

            System.out.println("Console application started.");


        } else {
            System.out.println("GUI application started.");

            var mainWindow = new MainWindow(APPLICATION_NAME);
            mainWindow.setVisible(true);

        }


    }
}