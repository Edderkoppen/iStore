import components.fenetre.WindowScreen;
import connexion.DatabaseConnexion;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int width  = (int)dimension.getWidth();
        int height = (int)dimension.getHeight();

        DatabaseConnexion conn = new DatabaseConnexion();


        WindowScreen window = new WindowScreen(width, height);
        window.setVisible(true);

    }
}