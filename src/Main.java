import components.menus.PageConnexion;
import components.menus.PageInscription;
import components.fenetre.WindowScreen;

import java.awt.Dimension;

public class Main {
    public static void main(String[] args) {

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int width  = (int)dimension.getWidth();
        int height = (int)dimension.getHeight();

        WindowScreen window = new WindowScreen(width, height);
        window.setVisible(true);

    }
}