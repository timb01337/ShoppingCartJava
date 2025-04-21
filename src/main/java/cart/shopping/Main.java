package cart.shopping;

import cart.shopping.console.ConsoleMenu;
import cart.shopping.gui.MainWindow;

public class Main {

    private static final String APPLICATION_NAME = "Java Shopping Cart";
    private static final String LAUNCH_UI_ARGUMENT = "--launch-ui";

    public static void main(String[] args) {

        boolean launchUi = true;

        for (String arg : args) {
            if (LAUNCH_UI_ARGUMENT.equals(arg)) {
                launchUi = true;
                break;
            }
        }

        if (launchUi) {
            System.out.println("GUI application started.");

            MainWindow mainWindow = new MainWindow(APPLICATION_NAME);
            mainWindow.setVisible(true);

        } else {
            System.out.println("Console application started.");

            ConsoleMenu menu = new ConsoleMenu();
            menu.start(APPLICATION_NAME);
        }


    }
}