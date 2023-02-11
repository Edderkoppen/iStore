import components.screen.WindowScreen;
import controller.DatabaseConnexionController;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int width  = (int)dimension.getWidth();
        int height = (int)dimension.getHeight();

        DatabaseConnexionController conn = new DatabaseConnexionController();

        WindowScreen window = new WindowScreen(width, height);
        window.setVisible(true);
    }
}